package com.qgraph;

import org.apache.tinkerpop.gremlin.server.Settings;

public class QGraphServer {
  protected final String configPath;
  protected ContextGremlinServer gremlinServer;

  public QGraphServer(String gremlinConfPath) {
    configPath = gremlinConfPath;
  }

  public void start() {
    Settings settings = null;
    try {
      settings = Settings.read(configPath);
    } catch (Exception e) {
      e.printStackTrace();
    }
    gremlinServer = new ContextGremlinServer(settings);
    try {
      gremlinServer
          .start()
          .exceptionally(
              t -> {
                gremlinServer.stop().join();
                return null;
              })
          .join();
      ;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    gremlinServer.stop();
  }
}
