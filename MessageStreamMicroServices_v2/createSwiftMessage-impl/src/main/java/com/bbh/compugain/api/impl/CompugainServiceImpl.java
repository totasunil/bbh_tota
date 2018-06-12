/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;

import akka.Done;
import akka.japi.Pair;
import com.bbh.compugain.api.CompugainService;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.bbh.compugain.api.BBH540Message;

import javax.inject.Inject;

import java.util.UUID;

/**
 * Implementation of the CompugainService.
 */
public class CompugainServiceImpl implements CompugainService {

  private final PersistentEntityRegistry persistentEntityRegistry;
  private final CompugainStreamRepository repository;

  @Inject
  public CompugainServiceImpl(PersistentEntityRegistry persistentEntityRegistry, CompugainStreamRepository repository) {
    this.persistentEntityRegistry = persistentEntityRegistry;
    this.repository = repository;
    persistentEntityRegistry.register(BBH540MessageEntity.class);
  }

  @Override
  public ServiceCall<BBH540Message, Done> useGreeting(String id) {
    return request -> persistentEntityRegistry.refFor(BBH540MessageEntity.class, UUID.randomUUID().toString())
              .ask(new BBH540Command.BBH540Message(request));
  }

  @Override
  public Topic<com.bbh.compugain.api.BBH540Event> helloEvents() {
    // We want to publish all the shards of the hello event
    return TopicProducer.taggedStreamWithOffset(BBH540Event.TAG.allTags(), (tag, offset) ->

            // Load the event stream for the passed in shard tag
            persistentEntityRegistry.eventStream(tag, offset).map(eventAndOffset -> {

              // Now we want to convert from the persisted event to the published event.
              // Although these two events are currently identical, in future they may
              // change and need to evolve separately, by separating them now we save
              // a lot of potential trouble in future.
              com.bbh.compugain.api.BBH540Event eventToPublish;

              if (eventAndOffset.first() instanceof BBH540Event.BBH540MessageChanged) {
                BBH540Event.BBH540MessageChanged messageChanged = (BBH540Event.BBH540MessageChanged) eventAndOffset.first();
                eventToPublish = new com.bbh.compugain.api.BBH540Event.BBH540MessageChanged(messageChanged.getId(),
                        messageChanged.getBbh540Message()
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
