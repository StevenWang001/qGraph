package com.qgraph.jsr223;

import com.qgraph.*;
import org.apache.tinkerpop.gremlin.jsr223.AbstractGremlinPlugin;
import org.apache.tinkerpop.gremlin.jsr223.DefaultImportCustomizer;
import org.apache.tinkerpop.gremlin.jsr223.ImportCustomizer;

public final class QGraphGremlinPlugin extends AbstractGremlinPlugin {
    private static final String NAME = "com.qgraph";

    private static final ImportCustomizer imports = DefaultImportCustomizer.build()
            .addClassImports(
                QEdge.class,
                AbstractElement.class,
                QGraph.class,
                QProperty.class,
                QVertex.class,
                QVertexProperty.class).create();

    private static final QGraphGremlinPlugin instance = new QGraphGremlinPlugin();

    public QGraphGremlinPlugin() {
        super(NAME, imports);
    }

    public static QGraphGremlinPlugin instance() {
        return instance;
    }
}