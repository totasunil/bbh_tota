package com.compugain.banking.customer.impl;

import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;

/**
 * Created by sunny.singh on 4/5/2017.
 */


public final class BbhAccessControlRoleEventTag {

    public static final AggregateEventTag<BbhAccessControlRoleEvent> INSTANCE1 =
            AggregateEventTag.of(BbhAccessControlRoleEvent.class);
}
