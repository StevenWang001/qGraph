package com.qgraph;

import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QVertexProperty<V> implements VertexProperty<V> {
  @Override
  public String key() {
    return null;
  }

  @Override
  public V value() throws NoSuchElementException {
    return null;
  }

  @Override
  public boolean isPresent() {
    return false;
  }

  @Override
  public Vertex element() {
    return null;
  }

  @Override
  public void remove() {

  }

  @Override
  public Object id() {
    return null;
  }

  @Override
  public <V> Property<V> property(String key, V value) {
    return null;
  }

  @Override
  public <U> Iterator<Property<U>> properties(String... propertyKeys) {
    return null;
  }
}
