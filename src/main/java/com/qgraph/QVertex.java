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
    QEdge edge = new QEdge(QGraphUtil.getId(idValue), label, QGraphUtil.getId(inVertex.id()), QGraphUtil.getId(id()));
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
    if (null == edgeLabels || edgeLabels.length == 0) {
      if (Direction.IN == direction) {
        qEdgeList = qEdgeMapper.getInEdgesByVertexId(QGraphUtil.getId(id()));
      } else if (Direction.OUT == direction) {
        qEdgeList = qEdgeMapper.getOutEdgesByVertexId(QGraphUtil.getId(id()));
      } else {
        qEdgeList = qEdgeMapper.getBothEdgesByVertexId(QGraphUtil.getId(id()));
      }
    } else {
      if (Direction.IN == direction) {
        qEdgeList = qEdgeMapper.getInEdgesByVertexIdLabels(QGraphUtil.getId(id()), edgeLabels);
      } else if (Direction.OUT == direction) {
        qEdgeList = qEdgeMapper.getOutEdgesByVertexIdLabels(QGraphUtil.getId(id()), edgeLabels);
      } else {
        qEdgeList = qEdgeMapper.getBothEdgesByVertexIdLabels(QGraphUtil.getId(id()), edgeLabels);
      }
    }
    return qEdgeList.stream().map(qEdge -> (Edge)qEdge).iterator();
  }

  @Override
  public Iterator<Vertex> vertices(Direction direction, String... edgeLabels) {
    List<QVertex> qVertexList;
    if (null == edgeLabels || edgeLabels.length == 0) {
      if (Direction.IN == direction) {
        qVertexList = qVertexMapper.getInVertexIdsById(QGraphUtil.getId(id()));
      } else if (Direction.OUT == direction) {
        qVertexList = qVertexMapper.getOutVertexIdsById(QGraphUtil.getId(id()));
      } else {
        qVertexList = qVertexMapper.getBothVertexIdsById(QGraphUtil.getId(id()));
      }
    } else {
      if (Direction.IN == direction) {
        qVertexList = qVertexMapper.getInVertexIdsByIdEdgeLabels(QGraphUtil.getId(id()), edgeLabels);
      } else if (Direction.OUT == direction) {
        qVertexList = qVertexMapper.getOutVertexIdsByIdEdgeLabels(QGraphUtil.getId(id()), edgeLabels);
      } else {
        qVertexList = qVertexMapper.getBothVertexIdsByIdEdgeLabels(QGraphUtil.getId(id()), edgeLabels);
      }
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

  @Override
  public String toString() {
    return "Vertex(id=" + id + ")";
  }
}
