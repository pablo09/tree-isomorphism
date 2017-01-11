package pl.pw.elka.gis.model;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Pawel on 2016-10-30.
 */
public class RootedTreeTest {

    /**
     * V1 - V2 - V3 - V4 - V6
     *           |
     *           V5
     */
    @Test
    public void shouldFindVertexChildren() {
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

        RootedTree rootedTree = new RootedTree(tree, root.stream().findFirst().orElseThrow(RuntimeException::new));
        Set<Vertex> directChildren = rootedTree.getChildren(v3);
        assertEquals(directChildren.size(), 3);
        assertTrue(directChildren.contains(v2));
        assertTrue(directChildren.contains(v5));
        assertTrue(directChildren.contains(v4));

        directChildren = rootedTree.getChildren(v2);
        assertEquals(directChildren.size(), 1);
        assertTrue(directChildren.contains(v1));
    }
}
