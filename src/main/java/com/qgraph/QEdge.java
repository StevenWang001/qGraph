package com.qgraph;

import org.apache.tinkerpop.gremlin.structure.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QEdge extends AbstractElement implements Edge {
  private Long srcId;
  private Long destId;

  public QEdge() {

  }

  public QEdge(Long id, String label, Long srcId, Long destId) {
    this.id = id;
    this.label = label;
    this.srcId = srcId;
    this.destId = destId;
  }

  @Override
  public Iterator<Vertex> vertices(Direction direction) {
    List<Long> ids = new ArrayList<>();
    if (Direction.IN == direction) {
      ids.add(srcId);
    } else if (Direction.OUT == direction) {
      ids.add(destId);
    } else {
      ids.add(srcId);
      ids.add(destId);
    }
    return qVertexMapper.findByIds(ids).stream().map(qVertex -> (Vertex)qVertex).iterator();
  }

  @Override
  public Object id() {
    return id;
  }

  @Override
  public String label() {
    return label;
  }

  @Override
  public Graph graph() {
    return qGraph;
  }

  @Override
  public <V> Property<V> property(String key, V value) {
    return null;
  }

  @Override
  public void remove() {

  }

  @Override
  public <V> Iterator<Property<V>> properties(String... propertyKeys) {
    return null;
  }

  public Long getSrcId() {
    return srcId;
  }

  public void setSrcId(Long srcId) {
    this.srcId = srcId;
  }

  public Long getDestId() {
    return destId;
  }

  public void setDestId(Long destId) {
    this.destId = destId;
  }

  @Override
  public String toString() {
    return "Edge(src_id=" + srcId + ", dest_id=" + destId + ", label=" + label + ")";
  }
}
