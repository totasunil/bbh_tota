/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.search.api.impl;

import akka.japi.Pair;

import com.bbh.compugain.search.api.SearchService;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;

import javax.inject.Inject;

/**
 * Implementation of the SearchService.
 */
public class SearchServiceImpl implements SearchService {

    private final PersistentEntityRegistry persistentEntityRegistry;
    private final CassandraSession db;

    @Inject
    public SearchServiceImpl(PersistentEntityRegistry persistentEntityRegistry, CassandraSession db) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        this.db = db;
        //persistentEntityRegistry.register(BBH540MessageEntity.class);
    }

    @Override
    public Topic<com.bbh.compugain.search.api.SearchEvent> searchEvent() {

        return TopicProducer.taggedStreamWithOffset(SearchEvent.TAG.allTags(), (tag, offset) ->

                // Load the event stream for the passed in shard tag
                persistentEntityRegistry.eventStream(tag, offset).map(eventAndOffset -> {

                    // Now we want to convert from the persisted event to the published event.
                    // Although these two events are currently identical, in future they may
                    // change and need to evolve separately, by separating them now we save
                    // a lot of potential trouble in future.
                    com.bbh.compugain.search.api.SearchEvent eventToPublish;

                    if (eventAndOffset.first() instanceof SearchEvent.SearchQueryEvent) {
                        SearchEvent.SearchQueryEvent queryMessage = (SearchEvent.SearchQueryEvent) eventAndOffset.first();
                        eventToPublish = new com.bbh.compugain.search.api.SearchEvent.SearchQueryEvent(queryMessage.getId(),
                                queryMessage.getSearchMessage()
                        );
                    } else {
                        throw new IllegalArgumentException("Unknown event: " + eventAndOffset.first());
                    }

                    // We return a pair of the translated event, and its offset, so that
                    // Lagom can track which offsets have been published.
                    return Pair.create(eventToPublish, eventAndOffset.second());
                })
        );
    }
}
