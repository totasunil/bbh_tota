package com.compugain.banking.customer.impl;

import com.compugain.banking.customer.api.Role;
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
public class BbhAccessControlRoleState implements CompressedJsonable {

    public final Optional<Role> role;

    @JsonCreator
    public BbhAccessControlRoleState(Optional<Role> role) {
        this.role = Preconditions.checkNotNull(role, "role");
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof BbhAccessControlRoleState && equalTo((BbhAccessControlRoleState) another);
    }

    private boolean equalTo(BbhAccessControlRoleState another) {
        return role.equals(another.role);
    }

    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + role.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("FriendState").add("role", role).toString();
    }
}