/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;


import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import akka.Done;
import java.util.Optional;


public class BBH540MessageEntity extends PersistentEntity<BBH540Command, BBH540Event, BBH540State> {

  /**
   * An entity can define different behaviours for different states, but it will
   * always start with an initial behaviour. This entity only has one behaviour.
   */
  @Override
  public Behavior initialBehavior(Optional<BBH540State> snapshotState) {

    /*
     * Behaviour is defined using a behaviour builder. The behaviour builder
     * starts with a state, if this entity supports snapshotting (an
     * optimisation that allows the state itself to be persisted to combine many
     * events into one), then the passed in snapshotState may have a value that
     * can be used.
     *
     * Otherwise, the default state is to use the Hello greeting.
     */
    BehaviorBuilder b = newBehaviorBuilder(
        snapshotState.orElse(BBH540State.EMPTY));

    /*
     * Command handler for the UseGreetingMessage command.
     */
    b.setCommandHandler(BBH540Command.BBH540Message.class, (cmd, ctx) ->
    // In response to this command, we want to first persist it as a
    // GreetingMessageChanged event
    ctx.thenPersist(new BBH540Event.BBH540MessageChanged(entityId(), cmd.bbh540Message),
        // Then once the event is successfully persisted, we respond with done.
        evt -> ctx.reply(Done.getInstance())));

    /*
     * Event handler for the GreetingMessageChanged event.
     */
    b.setEventHandler(BBH540Event.BBH540MessageChanged.class,
        // We simply update the current state to use the greeting message from
        // the event.
        evt -> new BBH540State(evt.bbh540Message)
    );
    /*
     * We've defined all our behaviour, so build and return it.
     */
    return b.build();
  }

}
