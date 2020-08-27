package com.qgraph;

import org.apache.tinkerpop.gremlin.structure.Element;

public abstract class AbstractElement implements Element {
  public static QGraph qGraph;
  protected Long id;
  protected String label;
  public static QVertexMapper qVertexMapper;
  public static QEdgeMapper qEdgeMapper;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
