package com.compugain.banking.customer.impl;

import com.compugain.banking.customer.api.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public class BbhAccessControlState implements CompressedJsonable {

    public final Optional<User> user;
    public static final User EMPTY = null;

    @JsonCreator
    public BbhAccessControlState(Optional<User> user) {
        this.user = Preconditions.checkNotNull(user, "user");
    }


    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof BbhAccessControlState && equalTo((BbhAccessControlState) another);
    }

    private boolean equalTo(BbhAccessControlState another) {
        return user.equals(another.user);
    }

    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + user.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("FriendState").add("user", user).toString();
    }
}
