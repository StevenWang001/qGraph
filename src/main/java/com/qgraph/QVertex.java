package com.qgraph;

import org.apache.tinkerpop.gremlin.structure.*;
import org.apache.tinkerpop.gremlin.structure.util.ElementHelper;

import java.util.Iterator;
import java.util.List;

public class QVertex extends AbstractElement implements Vertex {

  public QVertex() {
  }

  public QVertex(Long id, String label) {
    this.id = id;
    this.label = label;
  }

  @Override
  public Edge addEdge(String label, Vertex inVertex, Object... keyValues) {
    if (null == inVertex) throw Graph.Exceptions.argumentCanNotBeNull("inVertex");
    ElementHelper.validateLabel(label);
    ElementHelper.legalPropertyKeyValueArray(keyValues);
    Object idValue = ElementHelper.getIdValue(keyValues).orElse(null);
    QEdge edge = new QEdge((Long)idValue, label, (Long)inVertex.id(), (Long)id());
    qEdgeMapper.insert(edge);
    return edge;
  }

  @Override
  public <V> VertexProperty<V> property(VertexProperty.Cardinality cardinality, String key, V value, Object... keyValues) {
    return null;
  }

  @Override
  public Iterator<Edge> edges(Direction direction, String... edgeLabels) {
    List<QEdge> qEdgeList;
    if (Direction.IN == direction) {
      qEdgeList = qEdgeMapper.getInEdgesByVertexId((Long)id());
    } else if (Direction.OUT == direction) {
      qEdgeList = qEdgeMapper.getOutEdgesByVertexId((Long)id());
    } else {
      qEdgeList = qEdgeMapper.getBothEdgesByVertexId((Long)id());
    }
    return qEdgeList.stream().map(qEdge -> (Edge)qEdge).iterator();
  }

  @Override
  public Iterator<Vertex> vertices(Direction direction, String... edgeLabels) {
    List<QVertex> qVertexList;
    if (Direction.IN == direction) {
      qVertexList = qVertexMapper.getInVertexIdsById((Long)id());
    } else if (Direction.OUT == direction) {
      qVertexList = qVertexMapper.getOutVertexIdsById((Long)id());
    } else {
      qVertexList = qVertexMapper.getBothVertexIdsById((Long)id());
    }
    return qVertexList.stream().map(qVertex -> (Vertex)qVertex).iterator();
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
  public void remove() {

  }

  @Override
  public <V> Iterator<VertexProperty<V>> properties(String... propertyKeys) {
    return null;
  }
}
