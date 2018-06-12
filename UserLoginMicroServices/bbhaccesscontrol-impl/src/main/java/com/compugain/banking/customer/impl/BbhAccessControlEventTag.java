package com.compugain.banking.customer.impl;

import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;

public final class BbhAccessControlEventTag {
    public static final AggregateEventTag<BbhAccessControlEvent> INSTANCE =
            AggregateEventTag.of(BbhAccessControlEvent.class);
    public static final AggregateEventTag<BbhAccessControlRoleEvent> INSTANCE1 =
            AggregateEventTag.of(BbhAccessControlRoleEvent.class);
}
