package pl.pw.elka.gis.utils;

import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.RootedTree;
import pl.pw.elka.gis.model.Tree;
import pl.pw.elka.gis.model.Vertex;
import pl.pw.elka.gis.validator.DFSTreeValidator;
import pl.pw.elka.gis.validator.TreeValidator;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility methods to help working with trees
 */
public class TreeUtils {

    private static final TreeValidator treeValidator = new DFSTreeValidator();

    private TreeUtils() {}

    /**
     * Builds tree object based on specfic file
     * @param path Path to file describing tree
     * @return Tree instance
     */
    public static Tree loadTreeFromFile(String path) {
        Map<Integer, Set<Integer>> neighbours = createNeighbours(path, true);

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

    /**
     * Builds tree object based on a file from the same directory as JAR
     * @param path Path to file being relative to JAR location
     * @return Tree instance
     */
    public static Tree loadTreeFromSameDirectory(String path) {
        Map<Integer, Set<Integer>> neighbours = createNeighbours(path, false);

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

    public static Set<RootedTree> convertTreeToRooted(Tree tree) {
        Set<Vertex> roots = tree.centerTree();
        Set<RootedTree> trees = new HashSet<>();
        roots.stream().forEach(root -> {
            RootedTree rootedTree = new RootedTree(tree, root);
            trees.add(rootedTree);
        });

        return trees;
    }

    /**
     * Creates neighbours map
     * @param isForTests True/False: It is used for tests purposes and files will be read from resources/It is for real use and files will be read from the same directory as jar
     * @param path Path to file describing tree
     * @return Map of vertices and their neighbours
     */
    private static Map<Integer, Set<Integer>> createNeighbours(String path, boolean isForTests)  {
        List<String> lines = getLines(path, isForTests);
        Map<Integer, Set<Integer>> neighbours = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            List<Integer> matrixNeighbours = Arrays.stream(lines.get(i).split(" ")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());
            if(!hasEveryLineCorrectNumbersOfCharacters(lines, matrixNeighbours)) {
                 throw new UnknownFormatConversionException("Every line of line has to contain equal number of elements and equal to number of rows in file");
            }
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

    /**
     * Converts file describing tree to list of strings
     * @param path Path to file describing tree
     * @return List of string containing information about tree
     */
    private static List<String> getLines(String path, boolean isForTests) {
        String finalPath = isForTests ? ("trees/" + path) : ("./" + path);
        List<String> lines = new ArrayList<>();

        try (InputStream is = (isForTests ? getTestFilesInputStream(finalPath) : getRealFilesInputStream(finalPath))) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null) {
                if(!line.matches("[01\\s]+")) {
                    System.out.println("Error: There are some forbidden characters in tree file.\nOnly 0 and 1 are allowed.");
                    throw new UnknownFormatConversionException("Tree file contains unknwon characters");
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error occured while opening file " + path);
            throw new RuntimeException(e);
        }

        return lines;
    }

    /**
     * Checks if line of tree file has correct number of elements
     * @param lines Lines of tree file
     * @param matrixNeighbours List of 0 and 1 elements which describes if given ndoe is a neibgour of n-th vertex
     * @return True/False: Correct number of elements/Incorrect number of elements
     */
    private static boolean hasEveryLineCorrectNumbersOfCharacters(List<String> lines, List<Integer> matrixNeighbours) {
       if(lines.size() != matrixNeighbours.size()) {
           return false;
       }

       return true;
    }


    /**
     * Returns input stream of resources
     * @param path Path to resources
     * @return Input stream of resource
     */
    private static InputStream getTestFilesInputStream(String path) {
        return TreeUtils.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * Returns file input stream
     * @param path Path to file
     * @return File input stream
     * @throws FileNotFoundException Thrown when cannot find file at given path
     */
    private static InputStream getRealFilesInputStream(String path) throws FileNotFoundException{
        return new FileInputStream(path);
    }

}
