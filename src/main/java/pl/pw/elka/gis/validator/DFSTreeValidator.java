package pl.pw.elka.gis.validator;

import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Pawel on 2016-11-11.
 */
public class DFSTreeValidator implements TreeValidator {

    @Override
    public void validate(Set<Vertex> vertices, Set<Edge> edges) throws IllegalArgumentException{
        Map<Vertex, Set<Vertex>> neighbours = createNeighboursMap(vertices, edges);
        Set<Vertex> visited = new HashSet<>();

        performDFS(neighbours, vertices.stream().findAny().get(), null, visited);
    }

    private void performDFS(Map<Vertex, Set<Vertex>> neighbours, Vertex vertex, Vertex parent, Set<Vertex> visited) {
        visited.add(parent);
        for(Vertex v: neighbours.get(vertex)) {
            if(!visited.contains(v)) {
                performDFS(neighbours, v, vertex, visited);
            } else if(!v.equals(parent)) {
                throw new IllegalArgumentException("Cycle has been found");
            }
        }
    }

    private Map<Vertex, Set<Vertex>> createNeighboursMap(Set<Vertex> vertices, Set<Edge> edges) {
        Map<Vertex, Set<Vertex>> neighbours = new HashMap<>();

        vertices.stream().forEach(v -> {
            Set<Vertex> vertexNeighbours = new HashSet<>();
            edges.stream().forEach(e -> {
                     if(e.containsVertex(v)) {
                         vertexNeighbours.add(e.map(v));
                     }
            });
            neighbours.put(v, vertexNeighbours);
        });
        return neighbours;
    }
}
