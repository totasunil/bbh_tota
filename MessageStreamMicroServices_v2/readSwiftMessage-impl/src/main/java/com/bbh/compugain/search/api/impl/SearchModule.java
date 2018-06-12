/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.search.api.impl;

import com.bbh.compugain.search.api.SearchService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * The module that binds the SearchService so that it can be served.
 */
public class SearchModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {

    bindService(SearchService.class, SearchServiceImpl.class);
    // Bind the subscriber eagerly to ensure it starts up
    bind(SearchStreamSubscriber.class).asEagerSingleton();
  }
}
