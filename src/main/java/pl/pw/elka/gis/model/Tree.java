package pl.pw.elka.gis.model;

import pl.pw.elka.gis.validator.DFSTreeValidator;
import pl.pw.elka.gis.validator.TreeValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Tree object
 */
public class Tree {
    /** Tree validator */
    private final static TreeValidator treeValidator = new DFSTreeValidator();
    /** Set of Tree Vertices */
    private Set<Vertex> vertices = new HashSet<>();
    /** Set of Tree Edges */
    private Set<Edge> edges = new HashSet<>();
    /**
     * Creates empty Tree
     * @deprecated
     * @see Tree#Tree(Tree)
     */
    @Deprecated
    public Tree() {}
    /**
     * Creates new Tree
     * @param tree
     *
     */
    public Tree(Tree tree) {
        /** Maps new Tree based on given Tree*/
        this.vertices = tree.getVertices().stream().map(v -> new Vertex(v.getId())).collect(Collectors.toSet());
        this.edges = tree.getEdges().stream().map(e -> new Edge(e.getFrom(), e.getTo())).collect(Collectors.toSet());
        /** Validates the Tree*/
        treeValidator.validate(vertices, edges);
    }
    /**
     * Creates new Tree
     * @param edges Set of Edges
     * @param vertices Set of Vertices
     * */
    public Tree(Set<Vertex> vertices, Set<Edge> edges) {
        /** Validates the Tree*/
        treeValidator.validate(vertices, edges);

        this.vertices = vertices;
        this.edges = edges;
    }
    /**
     * Getter on set of Tree Edges
     * @return set of Edges
     * */
    public Set<Edge> getEdges() {
        return edges;
    }
    /**
     * Setter on set of Tree Edges
     * @param edges set of Edges
     * */
    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }
    /**
     * Setter on set of Tree Edges
     * @param edges set of Edges
     * */
    public void setEdges(Edge ... edges) { this.edges.addAll(Arrays.asList(edges));}
    /**
     * Getter on set of Tree Vertices
     * @return set of Vertices
     * */
    public Set<Vertex> getVertices() {
        return vertices;
    }
    /**
     * Setter on set of Tree Vertices
     * @param vertices set of Vertices
     * */
    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }
    /**
     * Setter on set of Tree Vertices
     * @param vertices set of Vertices
     * */
    public void setVertices(Vertex ... vertices) {this.vertices.addAll(Arrays.asList(vertices));}

    /**
     * Finds Tree centers
     * @return Set of Tree centers
     */
    public Set<Vertex> centerTree() {
        /** Creates new Tree instance*/
        Tree tree = new Tree(this);
        /** Removes Tree Leaves*/
        while(tree.getVertices().size() > 2 ) {
            tree = removeLeaves(tree);
        }

        return tree.getVertices();
    }

    /**
     * Removes Tree leaves
     * @param tree Tree object
     * @return New Tree instance with no leaves
     */
    public Tree removeLeaves(Tree tree) {
        /** Set of Tree leaves*/
        Set<Vertex> leaves = findLeaves(tree);
        /** Set of Edges to remove*/
        Set<Edge> edgesToRemove = new HashSet<>();
        /** Adds Edges to remove based of leaves connected to them*/
        leaves.stream().forEach(vertex -> {
            edgesToRemove.addAll(tree.edges.stream().filter(e -> e.containsVertex(vertex)).collect(Collectors.toSet()));
        });
        /** Removes selected Tree Edges*/
        tree.edges.removeAll(edgesToRemove);
        /** Removes Tree leaves*/
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
    /**
     * Returns string representation of the Tree
     * @return Set of Tree Edges and Vertices
     */
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
