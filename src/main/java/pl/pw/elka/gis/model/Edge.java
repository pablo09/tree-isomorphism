package pl.pw.elka.gis.model;

/**
 * Created by Pawel on 2016-10-30.
 */
public class Edge {
    private Vertex from;
    private Vertex to;
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

    @Override
    public String toString() {
        return String.format("E%d%d ", from.getId(), to.getId());
    }

    /**
     * Gets one of the vertices which belongs to the edge
     * @param theOther The other vertex which belongs to edge which we DO NOT want to obtain
     * @return Vertex belonging to the edge
     */
    public Vertex map(Vertex theOther) {
        if(!containsVertex(theOther)) throw new IllegalArgumentException("Operation does not make sense: edge does not contain such vertex");

        if(from.equals(theOther)) return to;
        else return from;
    }
}
