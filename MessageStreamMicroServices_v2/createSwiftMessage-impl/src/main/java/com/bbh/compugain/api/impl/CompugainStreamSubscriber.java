/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;

import akka.Done;
import akka.stream.javadsl.Flow;
import com.bbh.compugain.api.BBH540Event;
import com.bbh.compugain.api.CompugainService;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This subscribes to the CompugainService event stream.
 */
public class CompugainStreamSubscriber {

  @Inject
  public CompugainStreamSubscriber(CompugainService compugainService, CompugainStreamRepository repository) {
    // Create a subscriber
    compugainService.helloEvents().subscribe()
      // And subscribe to it with at least once processing semantics.
      .atLeastOnce(
        // Create a flow that emits a Done for each message it processes
        Flow.<BBH540Event>create().mapAsync(1, event -> {

          if (event instanceof BBH540Event.BBH540MessageChanged) {
              BBH540Event.BBH540MessageChanged messageChanged = (BBH540Event.BBH540MessageChanged) event;
            // Update the message
              CompletionStage<Done> results = repository.updateMessage(messageChanged.getBbh540Message());
              return results;
          } else {
            // Ignore all other events
            return CompletableFuture.completedFuture(Done.getInstance());
          }
        })
      );

  }
}
