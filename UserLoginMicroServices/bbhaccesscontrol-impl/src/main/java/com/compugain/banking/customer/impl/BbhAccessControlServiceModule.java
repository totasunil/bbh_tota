package com.compugain.banking.customer.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.compugain.banking.customer.api.BbhAccessControlService;

public class BbhAccessControlServiceModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(serviceBinding(BbhAccessControlService.class, BbhAccessControlServiceImpl.class));
        // Bind the subscriber eagerly to ensure it starts up


    }
}

