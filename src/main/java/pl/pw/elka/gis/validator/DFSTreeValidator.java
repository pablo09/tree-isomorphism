package pl.pw.elka.gis.validator;

import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DFS based tree validator
 */
public class DFSTreeValidator implements TreeValidator {

    @Override
    public void validate(Set<Vertex> vertices, Set<Edge> edges) throws IllegalArgumentException{
        Map<Vertex, Set<Vertex>> neighbours = createNeighboursMap(vertices, edges);
        Set<Vertex> visited = new HashSet<>();

        performDFS(neighbours, vertices.stream().findAny().get(), null, visited);
        validateConnectivity(vertices, visited);
    }
    /**
     * Validates graph connectivity
     * @param vertices Set of graph vertices
     * @param visited  Set of visited vertices
     */
    private void validateConnectivity(Set<Vertex> vertices, Set<Vertex> visited) {
        if((visited.size()) != vertices.size()) {
            System.out.println("Graph is not connected");
            throw new IllegalArgumentException("Graph is not conntected ");
        }
    }

    /**
     * Performs DFS algorithm
     * @param neighbours Neighbours map
     * @param vertex Vertex that is being processed
     * @param parent Parent vertex of @vertex
     * @param visited Set of already visited vertices
     */
    private void performDFS(Map<Vertex, Set<Vertex>> neighbours, Vertex vertex, Vertex parent, Set<Vertex> visited) {
        if(vertex != null) {
            visited.add(vertex);
        }

        for(Vertex v: neighbours.get(vertex)) {
            if(!visited.contains(v)) {
                performDFS(neighbours, v, vertex, visited);
            } else if(!v.equals(parent)) {
                System.out.println("Cycle has been found");
                throw new IllegalArgumentException("Cycle has been found");
            }
        }
    }

    /**
     * Creates neighbours map
     * @param vertices Set of tree vertices
     * @param edges Set of tree edges
     * @return Map of vertices and coressponding neighbour vertices
     */
    private Map<Vertex, Set<Vertex>> createNeighboursMap(Set<Vertex> vertices, Set<Edge> edges) {
        Map<Vertex, Set<Vertex>> neighbours = new HashMap<>();

        vertices.stream().forEach(v -> {
            Set<Vertex> vertexNeighbours = new HashSet<>();
            edges.stream().forEach(e -> {
                     if(e.containsVertex(v)) {
                         vertexNeighbours.add(e.getTheOtherVertex(v));
                     }
            });
            neighbours.put(v, vertexNeighbours);
        });
        return neighbours;
    }
}
