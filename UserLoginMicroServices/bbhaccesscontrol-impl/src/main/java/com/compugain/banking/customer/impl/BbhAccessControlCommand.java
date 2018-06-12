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
import java.util.Map;
import java.util.Optional;

public interface BbhAccessControlCommand extends Jsonable {

    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize

    public final class NewUserCommand implements BbhAccessControlCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {

        public final String user_id;
        public final String customer_Id;
        public final String first_name;
        public final String last_name;
        public final String email;
        public final String user_name;
        public final String password;
        public final String is_disabled;
        public final Map<Integer, String> role;

        @JsonCreator
        public NewUserCommand(String user_id, String customer_Id, String first_name, String last_name, String email, String user_name, String password, String is_disabled, Optional<Map<Integer, String>> role) {
            this.user_id = Preconditions.checkNotNull(user_id, "user_id");
            this.customer_Id = Preconditions.checkNotNull(customer_Id, "customer_Id");
            this.first_name = Preconditions.checkNotNull(first_name, "first_name");
            this.last_name = Preconditions.checkNotNull(last_name, "last_name");
            this.email = Preconditions.checkNotNull(email, "email");
            this.user_name = Preconditions.checkNotNull(user_name, "user_name");
            this.password = Preconditions.checkNotNull(password, "password");
            this.is_disabled = Preconditions.checkNotNull(is_disabled, "is_disabled");
            this.role = role.orElse(null);
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof NewUserCommand && equalTo((NewUserCommand) another);
        }

        private boolean equalTo(NewUserCommand another) {
            return user_id.equals(another.user_id);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + user_id.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("NewUserCommand").add("user_id", user_id).toString();
        }
    }

    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class AssignRoleToUser implements BbhAccessControlCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {

        public final String user_id;
        public final String customer_Id;
        public final String first_name;
        public final String last_name;
        public final String email;
        public final String user_name;
        public final String password;
        public final String is_disabled;
        public final Map<Integer, String> role;

        @JsonCreator
        public AssignRoleToUser(String user_id, String customer_Id, String first_name, String last_name, String email, String user_name, String password, String is_disabled, Optional<Map<Integer, String>> role) {
            this.user_id = Preconditions.checkNotNull(user_id, "user_id");
            this.customer_Id = Preconditions.checkNotNull(customer_Id, "customer_Id");
            this.first_name = Preconditions.checkNotNull(first_name, "first_name");
            this.last_name = Preconditions.checkNotNull(last_name, "last_name");
            this.email = Preconditions.checkNotNull(email, "email");
            this.user_name = Preconditions.checkNotNull(user_name, "user_name");
            this.password = Preconditions.checkNotNull(password, "password");
            this.is_disabled = Preconditions.checkNotNull(is_disabled, "is_disabled");
            this.role = role.orElse(null);
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof NewUserCommand && equalTo((NewUserCommand) another);
        }

        private boolean equalTo(NewUserCommand another) {
            return user_id.equals(another.user_id);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + user_id.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("NewUserCommand").add("user_id", user_id).toString();
        }
    }
}
