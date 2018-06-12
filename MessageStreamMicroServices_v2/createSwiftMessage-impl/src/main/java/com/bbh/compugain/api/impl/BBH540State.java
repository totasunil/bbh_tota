/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;

import com.bbh.compugain.api.BBH540Message;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.lightbend.lagom.serialization.CompressedJsonable;
import lombok.Value;



@SuppressWarnings("serial")
@Value
@JsonDeserialize
public final class BBH540State implements CompressedJsonable {
  public static final BBH540State EMPTY = new BBH540State(null);
  public BBH540Message bbh540Message;

  @JsonCreator
  public BBH540State(BBH540Message bbh540Message) {
    this.bbh540Message = bbh540Message;
  }

}
