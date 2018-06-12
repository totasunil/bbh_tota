package com.compugain.banking.customer.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Map;
import java.util.Optional;

public interface BbhAccessControlEvent extends Jsonable, AggregateEvent<BbhAccessControlEvent> {

    @Override
    default AggregateEventTag<BbhAccessControlEvent> aggregateTag() {
        return BbhAccessControlEventTag.INSTANCE;
    }

    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class NewUserEvent implements BbhAccessControlEvent {
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
        public NewUserEvent(String user_id, String customer_Id, String first_name, String last_name, String email, String user_name, String password, String is_disabled, Optional<Map<Integer, String>> role) {
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
            return another instanceof NewUserEvent && equalTo((NewUserEvent) another);
        }

        private boolean equalTo(NewUserEvent another) {
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
            return MoreObjects.toStringHelper("NewUserEvent").add("user_id", user_id).toString();
        }
    }

    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class AssignRoleToUserEvent implements BbhAccessControlEvent {
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
        public AssignRoleToUserEvent(String user_id, String customer_Id, String first_name, String last_name, String email, String user_name, String password, String is_disabled, Optional<Map<Integer, String>> role) {

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
            return another instanceof NewUserEvent && equalTo((NewUserEvent) another);
        }

        private boolean equalTo(NewUserEvent another) {
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
            return MoreObjects.toStringHelper("NewUserEvent").add("user_id", user_id).toString();
        }
    }

}
