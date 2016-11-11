package pl.pw.elka.gis.validator;

import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.Vertex;

import java.util.Set;

/**
 * Created by Pawel on 2016-11-11.
 */
public interface TreeValidator {

     void validate(Set<Vertex> vertices, Set<Edge> edges) throws IllegalArgumentException;
}
