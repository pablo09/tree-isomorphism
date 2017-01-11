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

    public Edge(Vertex from, Vertex to) {
      this.from = from;
      this.to = to;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public boolean containsVertex(Vertex vertex) {
        return from.equals(vertex) || to.equals(vertex);
    }

    public boolean containsOneOf(Vertex vertex1, Vertex vertex2) {
        return from.equals(vertex1) || to.equals(vertex1) || from.equals(vertex2) || to.equals(vertex2);
    }

    @Override
    public String toString() {
        return String.format("E%d%d ", from.getId(), to.getId());
    }

    /**
     * Gets one of the vertices which belongs to the edge
     * @param other The other vertex which belongs to edge which we DO NOT want to obtain
     * @return Vertex belonging to the edge
     */
    public Vertex getTheOtherVertex(Vertex other) {
        if(!containsVertex(other)) throw new IllegalArgumentException("Operation does not make sense: edge does not contain such vertex");

        if(from.equals(other)) return to;
        else return from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if(this.from.isOneOf(edge.getFrom(), edge.getTo()) && this.to.isOneOf(edge.getFrom(), edge.getTo())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result += to != null ? to.hashCode() : 0;
        return result;
    }
}
