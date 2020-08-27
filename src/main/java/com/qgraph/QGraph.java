package com.qgraph;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.ElementHelper;

import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

public class QGraph implements Graph {

  protected Configuration configuration;

  public static QVertexMapper qVertexMapper;
  public static QEdgeMapper qEdgeMapper;

  private QGraph(Configuration configuration) {
    this.configuration = configuration;
  }

  private static final Configuration EMPTY_CONFIGURATION =
      new BaseConfiguration() {
        {
          this.setProperty(Graph.GRAPH, QGraph.class.getName());
        }
      };

  public static QGraph open() {
    return open(null);
  }

  public static QGraph open(Configuration configuration) {
    if (configuration == null) {
      return new QGraph(EMPTY_CONFIGURATION);
    }
    return new QGraph(configuration);
  }

  @Override
  public Vertex addVertex(Object... keyValues) {
    ElementHelper.legalPropertyKeyValueArray(keyValues);
    Object idValue = ElementHelper.getIdValue(keyValues).orElse(null);
    final String label = ElementHelper.getLabelValue(keyValues).orElse(Vertex.DEFAULT_LABEL);
    QVertex qVertex = new QVertex((Long)idValue, label);
    qVertexMapper.insert(qVertex);
    return qVertex;
  }

  @Override
  public <C extends GraphComputer> C compute(Class<C> graphComputerClass) throws IllegalArgumentException {
    throw Graph.Exceptions.graphComputerNotSupported();
  }

  @Override
  public GraphComputer compute() throws IllegalArgumentException {
    throw Graph.Exceptions.graphComputerNotSupported();
  }

  @Override
  public Iterator<Vertex> vertices(Object... vertexIds) {
    if (vertexIds.length == 0) {
      return Collections.EMPTY_LIST.iterator();
    } else {
      Stream<Object> stream = Stream.of(vertexIds);
      return stream
          .map(id -> qVertexMapper.getById((Long)id))
          .map(qVertex -> (Vertex)qVertex).iterator();
    }
  }

  @Override
  public Iterator<Edge> edges(Object... edgeIds) {
    if (edgeIds.length == 0) {
      return Collections.EMPTY_LIST.iterator();
    } else {
      Stream<Object> stream = Stream.of(edgeIds);
      return stream
          .map(id -> qEdgeMapper.getById((Long)id))
          .map(qEdge -> (Edge)qEdge).iterator();
    }
  }

  public Edge addEdge(Vertex outVertex, Vertex inVertex, String label, Object... keyValues) {
    return outVertex.addEdge(label, inVertex, keyValues);
  }

  @Override
  public Transaction tx() {
    throw Graph.Exceptions.transactionsNotSupported();
  }

  @Override
  public void close() throws Exception {

  }

  @Override
  public Variables variables() {
    throw Graph.Exceptions.variablesNotSupported();
  }

  @Override
  public Configuration configuration() {
    return configuration;
  }
}
