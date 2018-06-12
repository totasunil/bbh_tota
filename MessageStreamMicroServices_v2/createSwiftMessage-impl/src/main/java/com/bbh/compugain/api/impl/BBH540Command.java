/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;

import akka.Done;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.NonNull;
import lombok.Value;


public interface BBH540Command extends Jsonable {


  @SuppressWarnings("serial")
  @Value
  @JsonDeserialize
  final class BBH540Message implements BBH540Command, CompressedJsonable, PersistentEntity.ReplyType<Done> {
    @NonNull
    public com.bbh.compugain.api.BBH540Message bbh540Message;

    @JsonCreator
    public BBH540Message(com.bbh.compugain.api.BBH540Message bbh540Message) {
      this.bbh540Message = bbh540Message;
    }
  }
}
