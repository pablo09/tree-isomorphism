package pl.pw.elka.gis.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Rooted tree object
 */
public class RootedTree extends Tree {

    /** Map contains pair: (Vertex identifier, Vertex parent) */
    private final Map<Integer, Vertex> parentMap = new HashMap<>();
    /** Tree root */
    private Vertex root;
    /**
     * Creates Rooted Tree
     * @param tree Tree
     * @param root Vertex representing root of the Tree
     * */
    public RootedTree(Tree tree, Vertex root) {
        super(tree);
        this.root = new Vertex(root.getId());
        /** Creates relationship between Vertices*/
        setParentVertices(this.root);
    }

    /**
     * Sets children-parent relationship inside a tree to make it act as a rooted tree
     * We start from the root and then go recursively deeper and deeper
     * @param localRoot Current root vertex
     */
    private void setParentVertices(Vertex localRoot) {
        /** Set of neighbours based on local root*/
        Set<Vertex> neighbours = findNeighbours(localRoot);
        /** Checks neighbours id*/
        if(neighbours.stream().filter(v -> !parentMap.containsKey(v.getId())).count() == 0) return;
        /** Set of localRoot children Vertexes*/
        Set<Vertex> childrenToAdd = findChildren(localRoot, neighbours);
        /** Sets parent relations with children Vertexes*/
        childrenToAdd.stream().forEach(v -> setParentVertices(v));
    }

    /**
     * Finds local root's children
     * This method MUST be called recursively starting from tree root and then going deeper by recursion.
     * @param localRoot Local root
     * @param neighbours Set of local root neighbours
     * @return Set of local root's children
     */
    private Set<Vertex> findChildren(Vertex localRoot, Set<Vertex> neighbours) {
        Set<Vertex> childrenToAdd = neighbours.stream().filter(v -> !v.equals(root)).filter(v -> !parentMap.containsKey(v.getId())).collect(Collectors.toSet());
        getVertices().stream().filter(v -> childrenToAdd.contains(v)).forEach(v -> parentMap.put(v.getId(), localRoot));
        return childrenToAdd;
    }

    /**
     * Finds vertex's neighbours
     * @param vertex Vertex for which we look for neighbours
     * @return
     */
    private Set<Vertex> findNeighbours(Vertex vertex) {
        return getEdges().stream().filter(e -> e.containsVertex(vertex)).map(e -> e.getTheOtherVertex(vertex)).collect(Collectors.toSet());
    }

    /**
     * Finds a vertex's (direct) children
     * @param root Vertex object
     * @return Set of vertex's children
     */
    public Set<Vertex> getChildren(Vertex root) {
        return getVertices().stream().filter(v -> parentMap.containsKey(v.getId())).filter(v -> parentMap.get(v.getId()).equals(root)).collect(Collectors.toSet());
    }

    public Vertex getRoot() {
        return root;
    }
}
