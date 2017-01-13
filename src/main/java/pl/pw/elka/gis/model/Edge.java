package pl.pw.elka.gis.model;

/**
 * Edge object
 */
public class Edge {
    /** Starting vertex of the edge */
    private Vertex from;
    /** Ending vertex of the edge */
    private Vertex to;
    /** Tells if edge is directed */
    private boolean isDirected = false;
    /**
     * Creates Edge with two given Vertexes
     * @param from First Vertex
     * @param to Second Vertex
     */
    public Edge(Vertex from, Vertex to) {
      this.from = from;
      this.to = to;
    }
    /**
     * Getter for first Vertex
     * @return First Edge Vertex
     */
    public Vertex getFrom() {
        return from;
    }
    /**
     * Setter for first Vertex
     * @param from First Edge Vertex
     */
    public void setFrom(Vertex from) {
        this.from = from;
    }
    /**
     * Getter for second Vertex
     * @return Second Edge Vertex
     */
    public Vertex getTo() {
        return to;
    }
    /**
     * Setter for second Vertex
     * @param to Second Edge Vertex
     */
    public void setTo(Vertex to) {
        this.to = to;
    }
    /**
     * Checks if Edge contains given Vertex
     * @param vertex Vertex object
     * @return True if contain Vertex
     */
    public boolean containsVertex(Vertex vertex) {
        return from.equals(vertex) || to.equals(vertex);
    }
    /**
     * Checks if one of two given Vertexes is the same as one of this
     * @param vertex1 Vertex1
     * @param vertex2 Vertex2
     * @return True if Edge contains one of two given Vertexes
     */
    public boolean containsOneOf(Vertex vertex1, Vertex vertex2) {
        return from.equals(vertex1) || to.equals(vertex1) || from.equals(vertex2) || to.equals(vertex2);
    }
    /**
     * Returns string representation of the Edge
     * @return Two Vertexes identifiers
     */
    @Override
    public String toString() {
        return String.format("E%d%d ", from.getId(), to.getId());
    }

    /**
     * Gets one of the vertices which belongs to the edge
     * @param other The other vertex which belongs to edge which we DO NOT want to obtain
     * @return Vertex belonging to the edge
     * @throws IllegalArgumentException
     */
    public Vertex getTheOtherVertex(Vertex other) {
        /** Exception is thrown if Edge do not contains given Vertex*/
        if(!containsVertex(other)) throw new IllegalArgumentException("Operation does not make sense: edge does not contain such vertex");
        /** Compares given Vertex with one of the Vertex belonging to the Edge*/
        if(from.equals(other)) return to;
        else return from;
    }
    /**
     * Compares two Edge objects based on Vertexes
     * @param o any object
     * @return true if same object or objects with same Vertexes else return false
     */
    @Override
    public boolean equals(Object o) {
        /** Same object*/
        if (this == o) return true;
        /** Not Edge class object*/
        if (o == null || getClass() != o.getClass()) return false;
        /** Creates new Edge object based on given object*/
        Edge edge = (Edge) o;
        /** Returns true if same Vertexes in comparing Edges*/
        if(this.from.isOneOf(edge.getFrom(), edge.getTo()) && this.to.isOneOf(edge.getFrom(), edge.getTo())) {
            return true;
        }

        return false;
    }
    /**
     * Vertex hashCode
     * @return Edge hashCode
     */
    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result += to != null ? to.hashCode() : 0;
        return result;
    }
}
