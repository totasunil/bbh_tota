/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import static com.lightbend.lagom.javadsl.api.Service.topic;

import akka.Done;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;

public interface CompugainService extends Service {

  ServiceCall<BBH540Message, Done> useGreeting(String id);

  /**
   * This gets published to Kafka.
   */
  Topic<BBH540Event> helloEvents();

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("compugain").withCalls(
        pathCall("/api/hello/:id", this::useGreeting)
      ).publishing(
        topic("SwiftMessages", this::helloEvents)
          .withProperty(KafkaProperties.partitionKeyStrategy(), BBH540Event::getId)
      ).withAutoAcl(true);
    // @formatter:on
  }
}
