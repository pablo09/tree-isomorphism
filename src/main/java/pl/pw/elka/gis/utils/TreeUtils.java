package pl.pw.elka.gis.utils;

import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.Tree;
import pl.pw.elka.gis.model.Vertex;
import pl.pw.elka.gis.validator.DFSTreeValidator;
import pl.pw.elka.gis.validator.TreeValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pawel on 2016-11-11.
 */
public class TreeUtils {

    private static final TreeValidator treeValidator = new DFSTreeValidator();

    public static Tree loadTreeFromFile(String path) {
        Map<Integer, Set<Integer>> neighbours = createNeighbours(path);

        Set<Vertex> vertices = neighbours.keySet().stream().map(v -> new Vertex(v)).collect(Collectors.toSet());
        Set<Edge> edges = new HashSet<Edge>();

        neighbours.keySet().stream().forEach(v1 -> {
            Set<Integer> vertexNeighbours = neighbours.get(v1);
            vertexNeighbours.forEach(v2 -> {
                edges.add(new Edge(new Vertex(v1), new Vertex(v2)));
            });
        });

        return new Tree(vertices, edges);
    }

    private static Map<Integer, Set<Integer>> createNeighbours(String path)  {
        List<String> lines = fileToListLines("trees/" + path);
        Map<Integer, Set<Integer>> neighbours = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            List<Integer> matrixNeighbours = Arrays.stream(lines.get(i).split(" ")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());
            Set<Integer> vertexNeighbours = new HashSet<>();

            for (int j = 0; j < matrixNeighbours.size(); j++) {
                if (matrixNeighbours.get(j) != 0) {
                    vertexNeighbours.add(j + 1);
                }
            }

            neighbours.put(i + 1, vertexNeighbours);
        }

        return neighbours;
    }

    private static List<String> fileToListLines(String path) {
        List<String> lines = new ArrayList<>();

        try (InputStream is = TreeUtils.class.getClassLoader().getResourceAsStream(path)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error occured while opening file " + path);
            throw new RuntimeException(e);
        }

        return lines;
    }
}
