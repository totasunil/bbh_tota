/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.search.api;

import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import static com.lightbend.lagom.javadsl.api.Service.topic;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;

import static com.lightbend.lagom.javadsl.api.Service.named;

public interface SearchService extends Service {


  /**
   * This gets published to Kafka.
   */
  Topic<SearchEvent> searchEvent();

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("search").withCalls(
        //pathCall("/api/hello/:id", this::useGreeting)
      ).publishing(
        topic("SwiftQueries", this::searchEvent)
          .withProperty(KafkaProperties.partitionKeyStrategy(), SearchEvent::getId)
      ).withAutoAcl(true);
    // @formatter:on
  }
}
