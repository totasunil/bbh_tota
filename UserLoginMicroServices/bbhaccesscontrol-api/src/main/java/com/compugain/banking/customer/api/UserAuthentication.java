package com.compugain.banking.customer.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Created by sunny.singh on 4/4/2017.
 */
@Immutable
@JsonDeserialize
public final class UserAuthentication {
    public final String user_name;
    public final String password;

    @JsonCreator
    public UserAuthentication(String user_name,String password ) {
        this.user_name =  Preconditions.checkNotNull(user_name, "user_name");
        this.password =  Preconditions.checkNotNull(password, "password");
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof User && equalTo((UserAuthentication) another);
    }

    private boolean equalTo(UserAuthentication another) {
        return user_name.equals(another.user_name);
    }

    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + user_name.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("UserAuthentication").add("user_id", user_name)
                .add("user_name", user_name).add("password", password).toString();
    }
}