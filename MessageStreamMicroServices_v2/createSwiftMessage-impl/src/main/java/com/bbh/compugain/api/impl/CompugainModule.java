/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.bbh.compugain.api.CompugainService;

/**
 * The module that binds the CompugainService so that it can be served.
 */
public class CompugainModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {

    bindService(CompugainService.class, CompugainServiceImpl.class);
    // Bind the subscriber eagerly to ensure it starts up
    bind(CompugainStreamSubscriber.class).asEagerSingleton();
  }
}
