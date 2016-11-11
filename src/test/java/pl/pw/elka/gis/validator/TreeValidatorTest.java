package pl.pw.elka.gis.validator;

import org.junit.Test;
import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.Tree;
import pl.pw.elka.gis.model.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pawel on 2016-11-11.
 */
public class TreeValidatorTest {


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToGraphNotBeingTree1() {
        createTree1();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToGraphNotBeingTree2() {
        createTree2();
    }

    @Test
    public void shouldNotThrowExceptionDueToGraphNotBeingTree1() {
        createTree3();
    }

    @Test
    public void shouldNotThrowExceptionDueToGraphNotBeingTree2() {
        createTree4();
    }

    private Tree createTree1() {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);

        Edge e12 = new Edge(v1, v2);
        Edge e23 = new Edge(v2, v3);
        Edge e13 = new Edge(v1, v3);

        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> edges = new HashSet<>();

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        edges.add(e12);
        edges.add(e23);
        edges.add(e13);

        return new Tree(vertices, edges);
    }

    private Tree createTree2() {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        Edge e12 = new Edge(v1, v2);
        Edge e23 = new Edge(v2, v3);
        Edge e34 = new Edge(v3, v4);
        Edge e41 = new Edge(v4, v1);

        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> edges = new HashSet<>();

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);

        edges.add(e12);
        edges.add(e23);
        edges.add(e34);
        edges.add(e41);

        return new Tree(vertices, edges);
    }

    private Tree createTree3() {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);

        Edge e12 = new Edge(v1, v2);
        Edge e23 = new Edge(v2, v3);
        Edge e13 = new Edge(v1, v3);

        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> edges = new HashSet<>();

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        edges.add(e12);
        edges.add(e23);
       // edges.add(e13);

        return new Tree(vertices, edges);
    }

    private Tree createTree4() {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        Edge e12 = new Edge(v1, v2);
        Edge e23 = new Edge(v2, v3);
        Edge e34 = new Edge(v3, v4);
        Edge e41 = new Edge(v4, v1);

        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> edges = new HashSet<>();

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);

        edges.add(e12);
        edges.add(e23);
        edges.add(e34);
        // edges.add(e41);

        return new Tree(vertices, edges);
    }
}
