package com.qgraph;

import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Property;

import java.util.NoSuchElementException;

public class QProperty<V> implements Property<V> {
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
  public Element element() {
    return null;
  }

  @Override
  public void remove() {

  }
}
