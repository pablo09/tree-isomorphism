package pl.pw.elka.gis.validator;

import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.Vertex;

import java.util.Set;

/**
 * Tree validator interface
 */
public interface TreeValidator {

     /**
      * Checks if tree might be created out of given vertices and edges
      * @param vertices Set of tree vertices
      * @param edges Set of tree edges
      * @throws IllegalArgumentException Exception is thrown if cycle has been found which means tree cannot be created
      */
     void validate(Set<Vertex> vertices, Set<Edge> edges) throws IllegalArgumentException;
}
