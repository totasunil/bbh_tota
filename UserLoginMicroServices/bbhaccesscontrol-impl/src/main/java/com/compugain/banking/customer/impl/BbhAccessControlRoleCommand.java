package com.compugain.banking.customer.impl;

import akka.Done;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

public interface BbhAccessControlRoleCommand extends Jsonable {

    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    class NewRoleCommand implements BbhAccessControlRoleCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
        public final String role_id;
        public final String role_name;
        public final String role_desc;

        @JsonCreator
        public NewRoleCommand(String role_id, String role_name, String role_desc) {
            this.role_id = Preconditions.checkNotNull(role_id, "role_id");
            this.role_name = Preconditions.checkNotNull(role_name, "role_name");
            this.role_desc = Preconditions.checkNotNull(role_desc, "role_desc");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof NewRoleCommand && equalTo((NewRoleCommand) another);
        }

        private boolean equalTo(NewRoleCommand another) {
            return role_id.equals(another.role_id);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + role_id.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("NewRoleCommand").add("role_id", role_id).toString();
        }
    }
}