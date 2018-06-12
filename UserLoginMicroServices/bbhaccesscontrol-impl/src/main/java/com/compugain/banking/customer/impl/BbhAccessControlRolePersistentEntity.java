package com.compugain.banking.customer.impl;

import akka.Done;
import com.compugain.banking.customer.api.Role;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.util.Optional;

public class BbhAccessControlRolePersistentEntity extends PersistentEntity<BbhAccessControlRoleCommand, BbhAccessControlRoleEvent, BbhAccessControlRoleState> {

    @Override
    public Behavior initialBehavior(Optional<BbhAccessControlRoleState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(new BbhAccessControlRoleState(Optional.empty())));

        // Creating a new Role.
        b.setCommandHandler(BbhAccessControlRoleCommand.NewRoleCommand.class, (cmd, ctx) -> {
            if (state().role.isPresent()) {
                // If Role is already exists bbhaccesscontrol.
                ctx.invalidCommand("Role " + cmd.role_id + " is already created");
                return ctx.done();
            } else {
                return ctx.thenPersist(new BbhAccessControlRoleEvent.NewRoleEvent(cmd.role_id, cmd.role_name, cmd.role_desc),
                        evt -> ctx.reply(Done.getInstance()));
            }
        });
        b.setEventHandler(BbhAccessControlRoleEvent.NewRoleEvent.class,
                evt -> new BbhAccessControlRoleState(Optional.of(new Role(evt.role_id, evt.role_name, evt.role_desc))));
        return b.build();
    }

    ;

    public static enum TransactonType {DEPOSIT, WITHDRAWAL}
}
