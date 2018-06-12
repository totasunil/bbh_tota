/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.search.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Preconditions;
import lombok.NonNull;
import lombok.Value;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SearchEvent.SearchQueryEvent.class, name = "query")
})
public interface SearchEvent {

  String getId();

  @Value
  final class SearchQueryEvent implements SearchEvent {

    @NonNull
    String id;

    @NonNull
    SearchMessage searchMessage;

    public SearchQueryEvent(String id, SearchMessage searchMessage ){
      this.id = Preconditions.checkNotNull(id, "id");
      this.searchMessage = Preconditions.checkNotNull(searchMessage, "searchMessage");
    }
  }
}
