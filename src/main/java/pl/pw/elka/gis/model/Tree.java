package pl.pw.elka.gis.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Tree object
 */
public class Tree {
    /** Set of tree's vertices */
    private Set<Vertex> vertices = new HashSet<>();

    /** Set of tre's edges */
    private Set<Edge> edges = new HashSet<>();

    public Tree() {}

    public Tree(Tree tree) {
        //TODO Add checking if tree having following vertices and edges can exist
        this.vertices = tree.getVertices().stream().map(v -> new Vertex(v.getId())).collect(Collectors.toSet());
        this.edges = tree.getEdges().stream().map(e -> new Edge(e.getFrom(), e.getTo())).collect(Collectors.toSet());
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    public void setEdges(Edge ... edges) { this.edges.addAll(Arrays.asList(edges));}

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void setVertices(Vertex ... vertices) {this.vertices.addAll(Arrays.asList(vertices));}

    /**
     * Finds tree centers
     * @return Set of tree centers
     */
    public Set<Vertex> centerTree() {
        Tree tree = new Tree(this);

        while(tree.getVertices().size() > 2 ) {
            tree = removeLeaves(tree);
        }

        return tree.getVertices();
    }

    /**
     * Removes tree leaves
     * @param tree Tree object
     * @return New tree instance with no leaves
     */
    public Tree removeLeaves(Tree tree) {
        Set<Vertex> leaves = findLeaves(tree);
        Set<Edge> edgesToRemove = new HashSet<>();

        leaves.stream().forEach(vertex -> {
            edgesToRemove.addAll(tree.edges.stream().filter(e -> e.containsVertex(vertex)).collect(Collectors.toSet()));
        });
        tree.edges.removeAll(edgesToRemove);
        tree.vertices.removeAll(leaves);

        return tree;
    }

    /**
     * Finds tree leaves
     * @param tree Tree object
     * @return Sets of tree leaves
     */
    public Set<Vertex> findLeaves(Tree tree) {
        return tree.getVertices().stream().filter(v -> isLeaf(tree, v)).collect(Collectors.toSet());
    }

    /**
     * Tests whether a vertex is a tree leaf
     * @param tree Tree object
     * @param vertex Vertex object
     * @return true if the vertex in the tree is a leaf, false otherwise
     */
    protected boolean isLeaf(Tree tree, Vertex vertex) {
        return tree.getEdges().stream().filter(e -> e.containsVertex(vertex)).count() == 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ");
        this.vertices.forEach(v -> sb.append(v));
        sb.append("Edges: ");
        this.edges.forEach(e -> sb.append(e));
        return sb.toString();
    }
}
