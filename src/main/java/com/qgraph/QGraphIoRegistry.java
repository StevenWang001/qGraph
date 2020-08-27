package com.qgraph;

import org.apache.tinkerpop.gremlin.structure.io.AbstractIoRegistry;
import org.apache.tinkerpop.gremlin.structure.io.Io;

public class QGraphIoRegistry extends AbstractIoRegistry {
  private static final QGraphIoRegistry instance = new QGraphIoRegistry();

  public static QGraphIoRegistry instance() {
    return instance;
  }

  private QGraphIoRegistry() {

  }

  @SuppressWarnings("rawtypes")
  @Override
  public void register(Class<? extends Io> ioClass, Class clazz, Object ser) {
    super.register(ioClass, clazz, ser);
  }
}
