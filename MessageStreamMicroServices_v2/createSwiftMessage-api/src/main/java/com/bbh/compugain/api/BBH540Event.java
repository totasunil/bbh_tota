/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Preconditions;
import lombok.NonNull;
import lombok.Value;



@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BBH540Event.BBH540MessageChanged.class, name = "message-changed")
})
public interface BBH540Event  {

  String getId();

  @Value
  final class BBH540MessageChanged implements BBH540Event {
    @NonNull
    String id;

    @NonNull
    BBH540Message bbh540Message;

    public BBH540MessageChanged(String id,BBH540Message bbh540Message ){
      this.id = Preconditions.checkNotNull(id, "id");
      this.bbh540Message = Preconditions.checkNotNull(bbh540Message, "bbh540Message");
    }
  }

}
