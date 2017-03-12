package pl.pw.elka.gis.isomorphism;

import pl.pw.elka.gis.model.RootedTree;
import pl.pw.elka.gis.model.Vertex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Aho, Hopcroft, Ullman (AHU) Algorithm
 *
 * @see <a href="http://logic.pdmi.ras.ru/~smal/files/smal_jass08.pdf">http://logic.pdmi.ras.ru/~smal/files/smal_jass08.pdf</a>
 */
public class AHUAlgorithm implements IsomorphismAlgorithm {

    public boolean isIsomorphic(RootedTree t1, RootedTree t2) {
        String root1 = performAHU(t1);
        String root2 = performAHU(t2);

        return sortCanonicalName(root1).equals(sortCanonicalName(root2));
    }

    /**
     * Performs AHU algorithm
     * @param tree Rooted tree
     * @return Canonical name of tree root
     */
    private String performAHU(RootedTree tree) {
        Map<Vertex, String> tuples = new HashMap<>();

        assignCanonicalNames(tree, tree.getRoot(), tuples);
        return tuples.get(tree.getRoot());
    }

    /**
     * Sorts canonical names using binary comparator (leaving first '1' and last '0')
     * @param name Canonical name
     * @return Sorted canonical name
     */
    private String sortCanonicalName(String name) {
        List<String> names = Stream.of(name.substring(2, name.length() - 2).split(" ")).filter(n -> !n.isEmpty()).collect(Collectors.toList());
        String[] properNames = names.toArray(new String[names.size()]);
        Arrays.sort(properNames, new BinaryStringComparator());

        StringBuilder sb = new StringBuilder();
        for(String s: properNames) {
            sb.append(String.format(" %s ", s));
        }

        return String.format("1%s0", sb.toString());
    }

    /**
     * Assings canonical names for each vertex of tree
     * @param tree Rooted tree instance
     * @param v Vertex instance for which tuple name is being established
     * @param tuples Map of all tree vertices with their tuple names
     */
    private void assignCanonicalNames(RootedTree tree, Vertex v, Map<Vertex, String> tuples) {
        if (tree.getChildren(v).isEmpty()) {
            tuples.put(v, "10");
        } else {
           // tree.getChildren(v).stream().forEach(v1 -> assignCanonicalNames(tree, v1, tuples));
		   for(Vertex v1: tree.getChildren(v)) {
			   assignCanonicalNames(tree, v1, tuples);
		   }
        }

        String finalName = "1%s0";
        StringBuilder sb = new StringBuilder("");
		StringBuilder sb1 = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");

       // tree.getChildren(v).stream().forEach(v1 -> {
        //    sb.append(String.format(" %s ", tuples.get(v1).replaceAll("\\s+", "")));
        //});

		int i = 0;
		for(Vertex v1: tree.getChildren(v)) {
			i++;
			if(i < 4000) {
				sb.append(String.format(" %s ", tuples.get(v1).replaceAll("\\s+", "")));
			} else if(i >= 4000 && i < 8000){
				sb1.append(String.format(" %s ", tuples.get(v1).replaceAll("\\s+", "")));
			} else {
				sb2.append(String.format(" %s ", tuples.get(v1).replaceAll("\\s+", "")));
			}
		}
		
        tuples.put(v, String.format(finalName, sb.toString() + sb1.toString() + sb2.toString()));
    }

    /**
     * Comparator class for binary numbers represented as strigns
     */
    public static class BinaryStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            // check input
            if (!o1.matches("[01]*") || !o2.matches("[01]*")) {
                throw new IllegalArgumentException("Only strings representing binary values are allowed");
            }

            // compare lengths first
            if (o1.length() < o2.length()) {
                return -1;
            }

            if (o1.length() > o2.length()) {
                return 1;
            }

            // compare values if the length is equal
            final int i1 = Integer.parseInt(o1, 2);
            final int i2 = Integer.parseInt(o2, 2);
            return Integer.compare(i1, i2);
        }
    }
}
