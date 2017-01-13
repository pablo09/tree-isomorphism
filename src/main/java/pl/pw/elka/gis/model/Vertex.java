package pl.pw.elka.gis.model;

/**
 * Vertex object
 */
public class Vertex {
    /** Vertex identifier */
    private int id;
    /**
     * Creates Vertex with id argument
     * @param id Vertex identifier
     */
    public Vertex(int id) {
        this.id = id;
    }
    /**
     * Id getter
     * @return Vertex identifier
     */
    public int getId() {
        return id;
    }
    /**
     * Id setter
     * @param id Vertex identifier
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns string representation of the Vertex
     * @return Vertex identifier
     */
    @Override
    public String toString() {
        return String.format("V%d ", id);
    }
    /**
     * Compares two Vertex objects based on id
     * @param o any object
     * @return true if same object or objects with same id else return false
     */
    @Override
    public boolean equals(Object o) {
        /** Same object*/
        if (this == o) return true;
        /** Not Vertex class object*/
        if (o == null || getClass() != o.getClass()) return false;
        /** Creates new Vertex object based on given object*/
        Vertex vertex = (Vertex) o;
        /** Returns true if same id in comparing Vertexes*/
        return id == vertex.id;
    }
    /**
     * Vertex hashCode
     * @return Vertex identifier
     */
    @Override
    public int hashCode() {
        return id;
    }
    /**
     * Checks if one of two given Vertexes is the same as this
     * @param v1 Vertex object1
     * @param v2 Vertex object2
     * @return
     */
    public boolean isOneOf(Vertex v1, Vertex v2) {
        return this.equals(v1) || this.equals(v2);
    }
}
