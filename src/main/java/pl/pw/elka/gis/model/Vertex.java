package pl.pw.elka.gis.model;

/**
 * Vertex object
 */
public class Vertex {
    /** Vertex identifier */
    private int id;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("V%d ", id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public boolean isOneOf(Vertex v1, Vertex v2) {
        return this.equals(v1) || this.equals(v2);
    }
}
