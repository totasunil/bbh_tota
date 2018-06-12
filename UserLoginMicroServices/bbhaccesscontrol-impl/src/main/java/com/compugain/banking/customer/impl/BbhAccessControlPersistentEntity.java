package com.compugain.banking.customer.impl;

import akka.Done;
import com.compugain.banking.customer.api.User;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.util.Optional;

public class BbhAccessControlPersistentEntity extends PersistentEntity<BbhAccessControlCommand, BbhAccessControlEvent, BbhAccessControlState> {

    @Override
    public Behavior initialBehavior(Optional<BbhAccessControlState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(
                snapshotState.orElse(new BbhAccessControlState(Optional.empty())));

        // Creating a new User for bbhaccesscontrol.

        b.setCommandHandler(BbhAccessControlCommand.NewUserCommand.class, (cmd, ctx) -> {
            if (state().user.isPresent()) {

                // If User exits.
                ctx.invalidCommand("User " + cmd.user_name + " is already created");
                return ctx.done();
            } else {
                return ctx.thenPersist(new BbhAccessControlEvent.NewUserEvent(cmd.user_id, cmd.customer_Id, cmd.first_name, cmd.last_name, cmd.email, cmd.user_name, cmd.password, cmd.is_disabled, Optional.of(cmd.role)),
                        evt -> ctx.reply(Done.getInstance()));
            }
        });

        b.setCommandHandler(BbhAccessControlCommand.AssignRoleToUser.class, (cmd, ctx) -> {
            return ctx.thenPersist(new BbhAccessControlEvent.AssignRoleToUserEvent(cmd.user_id, cmd.customer_Id, cmd.first_name, cmd.last_name, cmd.email, cmd.user_name, cmd.password, cmd.is_disabled, Optional.of(cmd.role)),
                    evt -> ctx.reply(Done.getInstance()));
        });

        b.setEventHandler(BbhAccessControlEvent.NewUserEvent.class,
                evt -> new BbhAccessControlState(Optional.of(new User(evt.user_id, evt.customer_Id, evt.first_name, evt.last_name, evt.email, evt.user_name, evt.password, evt.is_disabled, Optional.of(evt.role)))));

        b.setEventHandler(BbhAccessControlEvent.AssignRoleToUserEvent.class,
                evt -> new BbhAccessControlState(Optional.of(new User(evt.user_id, evt.customer_Id, evt.first_name, evt.last_name, evt.email, evt.user_name, evt.password, evt.is_disabled, Optional.of(evt.role)))));

        return b.build();
    }

    ;

    public static enum TransactonType {DEPOSIT, WITHDRAWAL}
}
