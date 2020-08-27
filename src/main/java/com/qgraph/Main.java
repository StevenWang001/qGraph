package com.qgraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Main {

  @Autowired
  private QVertexMapper qVertexMapper;

  @Autowired
  private QEdgeMapper qEdgeMapper;

  public static void main(String[] args) {
    System.out.println("000");
    SpringApplication.run(Main.class, args);
    System.out.println("111");
  }

  @PostConstruct
  public void init() {
    System.out.println("222");
//    Map<String, String> map = new HashMap<>();
//    map.put(Graph.GRAPH, "com.qgraph.QGraph");
//    QGraph graph = (QGraph) GraphFactory.open(map);
    QGraph.qVertexMapper = qVertexMapper;
    QGraph.qEdgeMapper = qEdgeMapper;
    QVertex.qVertexMapper = qVertexMapper;
    QVertex.qEdgeMapper = qEdgeMapper;
//    System.out.println(qVertexMapper);
//    Vertex v1 = graph.addVertex(T.id, 1L, T.label, "person");
//    Vertex v2 = graph.addVertex(T.id, 2L, T.label, "person");
//    Edge e1 = graph.addEdge(v2, v1, "link");

//    GraphTraversalSource g = graph.traversal();
//    System.out.println(g.V(1L).out().out().hasNext());
//    System.out.println(g.E());
    QGraphServer server = new QGraphServer("conf/gremlin-server.yaml");
    server.start();
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  System.out.println("QGraphServer stopping");
                  server.stop();
                  System.out.println("QGraphServer stopped");
                }));
  }
}
