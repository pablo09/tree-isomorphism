package pl.pw.elka.gis.model;


import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Pawel on 2016-10-30.
 */
public class CenterTreeTest {

    /**
     * V1 - V2 - V3 - V4
     */
    @Test
    public void shouldFindTwoTreeCenters() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);

        tree.setVertices(v1, v2, v3, v4);
        tree.setEdges(e1, e2, e3);

        Set<Vertex> root = tree.centerTree();

        assertEquals(root.size(), 2);
        assertTrue(root.contains(v2));
        assertTrue(root.contains(v3));
    }
    /**
     * V1 - V2 - V3 - V4
     * |
     * V5
     */
    @Test
    public void shouldFindOneTreeCenter1() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);
        Edge e4 = new Edge(v1, v5);

        tree.setVertices(v1, v2, v3, v4, v5);
        tree.setEdges(e1, e2, e3, e4);

        Set<Vertex> root = tree.centerTree();

        assertEquals(root.size(), 1);
        assertTrue(root.contains(v2));
    }

    /**
     * V1 - V2 - V3 - V4 - V6
     *           |
     *           V5
     */
    @Test
    public void shouldFindOneTreeCenter2() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);
        Edge e4 = new Edge(v3, v5);
        Edge e5 = new Edge(v4, v6);

        tree.setVertices(v1, v2, v3, v4, v5, v6);
        tree.setEdges(e1, e2, e3, e4, e5);

        Set<Vertex> root = tree.centerTree();

        assertEquals(root.size(), 1);
        assertTrue(root.contains(v3));
    }

    /**
     * V1 - V2 - V3 - V4 - V6
     *  |         |           \
     * V10       V5           V9
     *         /   \
     *        V7    V8
     *
     */
    @Test
    public void shouldFindOneTreeCenter3() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);
        Vertex v9 = new Vertex(9);
        Vertex v10 = new Vertex(10);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);
        Edge e4 = new Edge(v3, v5);
        Edge e5 = new Edge(v4, v6);
        Edge e6 = new Edge(v5, v7);
        Edge e7 = new Edge(v5, v8);
        Edge e8 = new Edge(v6, v9);
        Edge e9 = new Edge(v1, v10);

        tree.setVertices(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
        tree.setEdges(e1, e2, e3, e4, e5, e6, e7, e8, e9);

        Set<Vertex> root = tree.centerTree();

        assertEquals(root.size(), 1);
        assertTrue(root.contains(v3));
    }
}
