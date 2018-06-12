/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;

import com.bbh.compugain.api.BBH540Message;
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


public interface BBH540Event extends Jsonable, AggregateEvent<BBH540Event> {


  AggregateEventShards<BBH540Event> TAG = AggregateEventTag.sharded(BBH540Event.class, 4);

  /**
   * An event that represents a change in greeting message.
   */
  @SuppressWarnings("serial")
  @Value
  @JsonDeserialize
  final class BBH540MessageChanged implements BBH540Event, CompressedJsonable {
    @NonNull
    public String id;

    @NonNull
    public BBH540Message bbh540Message;

    @JsonCreator
    public BBH540MessageChanged(String id,BBH540Message bbh540Message ){
      this.id = Preconditions.checkNotNull(id, "id");
      this.bbh540Message = Preconditions.checkNotNull(bbh540Message, "bbh540Message");
    }
  }
  @Override
  default AggregateEventTagger<BBH540Event> aggregateTag() {
    return TAG;
  }

}
