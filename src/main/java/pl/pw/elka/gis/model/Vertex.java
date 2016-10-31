package pl.pw.elka.gis.model;

/**
 * Vertex object
 */
public class Vertex {
    /** Vertex identifier */
    private int id;
    /** Vertex parent */
    private Vertex parent;

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasParent() {return parent != null;}

    public boolean hasNoParent() {return parent == null;}

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
}
