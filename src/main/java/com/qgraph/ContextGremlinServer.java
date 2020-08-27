package com.qgraph;

import org.apache.tinkerpop.gremlin.server.GremlinServer;
import org.apache.tinkerpop.gremlin.server.Settings;

public class ContextGremlinServer extends GremlinServer {
  public ContextGremlinServer(Settings settings) {
    super(settings);
  }
}
