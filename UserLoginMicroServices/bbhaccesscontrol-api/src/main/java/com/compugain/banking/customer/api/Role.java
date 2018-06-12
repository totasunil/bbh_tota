package com.compugain.banking.customer.api;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
@JsonDeserialize
public final class Role {
    public final String role_id;
    public final String role_name;
    public final String role_desc;

    @JsonCreator
    public Role(String  role_id, String  role_name, String role_desc) {
        this.role_id = Preconditions.checkNotNull(role_id, "role_id");
        this.role_name = Preconditions.checkNotNull(role_name, "role_name");
        this.role_desc = Preconditions.checkNotNull(role_desc, "role_desc");
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof Role && equalTo((Role) another);
    }

    private boolean equalTo(Role another) {
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
        return MoreObjects.toStringHelper("Role").add("role_id", role_id)
                .add("role_name", role_name).add("role_desc", role_desc)
                .toString();
    }
}