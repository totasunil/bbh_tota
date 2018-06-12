/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.search.api.impl;

import com.bbh.compugain.search.api.SearchMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventShards;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.NonNull;
import lombok.Value;


public interface SearchEvent extends Jsonable, AggregateEvent<SearchEvent> {


  AggregateEventShards<SearchEvent> TAG = AggregateEventTag.sharded(SearchEvent.class, 4);

  /**
   * An event that represents a change in greeting message.
   */
  @SuppressWarnings("serial")
  @Value
  @JsonDeserialize
  final class SearchQueryEvent implements SearchEvent, CompressedJsonable {


    @NonNull
    String id;

    @NonNull
    SearchMessage searchMessage;

    @JsonCreator
    public SearchQueryEvent(String id, SearchMessage searchMessage ){
      this.id = Preconditions.checkNotNull(id, "id");
      this.searchMessage = Preconditions.checkNotNull(searchMessage, "searchMessage");
    }
  }
  @Override
  default AggregateEventTagger<SearchEvent> aggregateTag() {
    return TAG;
  }

}
