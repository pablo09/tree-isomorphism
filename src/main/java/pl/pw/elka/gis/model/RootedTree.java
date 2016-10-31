package pl.pw.elka.gis.model;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Rooted tree object
 */
public class RootedTree extends Tree {

    /** Tree root */
    private Vertex root;

    public RootedTree(Tree tree, Vertex root) {
        super(tree);
        this.root = new Vertex(root.getId());
        setParentVertices(this.root);
    }

    /**
     * Sets children-parent relationship inside a tree to make it act as a rooted tree
     * @param localRoot
     */
    private void setParentVertices(Vertex localRoot) {
        Set<Vertex> children = getEdges().stream().filter(e -> e.containsVertex(localRoot)).map(e -> e.map(localRoot)).collect(Collectors.toSet());
        children = rewriteChildren(children);
        if(children.stream().filter(Vertex::hasNoParent).count() == 0) return;

        Set<Vertex> childrenToAdd = children.stream().filter(v -> !v.equals(root)).filter(Vertex::hasNoParent).collect(Collectors.toSet());
        getVertices().stream().filter(v -> childrenToAdd.contains(v)).forEach(v -> v.setParent(localRoot));

        childrenToAdd.stream().forEach(v -> setParentVertices(v));
    }

    /**
     * Finds a vertex (direct) children
     * @param root Vertex object
     * @return Set of root chlidren vertices
     */
    public Set<Vertex> getChildren(Vertex root) {
        return getVertices().stream().filter(Vertex::hasParent).filter(v -> v.getParent().equals(root)).collect(Collectors.toSet());
    }

    /**
     * Replaces set of vertices with tree vertices to keep track of objects reference
     * @param children Set of vertices
     * @return
     */
    private Set<Vertex> rewriteChildren(Set<Vertex> children) {
        return getVertices().stream().filter(v -> children.contains(v)).collect(Collectors.toSet());
    }

    public Vertex getRoot() {
        return root;
    }
}
