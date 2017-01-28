package pl.pw.elka.gis;

import pl.pw.elka.gis.isomorphism.AHUAlgorithm;
import pl.pw.elka.gis.isomorphism.IsomorphismAlgorithm;
import pl.pw.elka.gis.model.RootedTree;
import pl.pw.elka.gis.model.Tree;
import pl.pw.elka.gis.utils.TreeUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Pawel on 2017-01-11.
 */
public class TreeIsomorphismApplication {

    public static void main(String[] args) {
        String files[] = new String[2];

        if(args.length == 2) {
            files[0] = args[0];
            files[1] = args[1];
        }
        else if(args.length == 0) {
            File f = new File("./");

            if(Arrays.stream(f.list()).filter(file -> file.endsWith("txt")).count() != 2) {
                System.out.println("Error: There should be only 2 txt files in JAR location when running application with no arguments");
                return;
            }

            int i = 0;
            for(String filename: f.list()) {
                if(filename.endsWith("txt")) {
                    files[i] = filename;
                    i++;
                }
            }
        } else  {
            System.out.println("Error: Incorrect arguments number.\nYou have to put two .txt files in the same location as JAR or you have to give their names as arguments");
            return;
        }

        Tree tree1 = null;
        Tree tree2 = null;

        try {
            tree1 = TreeUtils.loadTreeFromSameDirectory(files[0]);
            tree2 = TreeUtils.loadTreeFromSameDirectory(files[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Cannot create trees. Please verify your input files.");
            return;
        } catch(Exception ex) {
            System.out.println("Error occured while trying to load trees from files");
            return;
        }

        if(!isTreeRooted(tree1) || !isTreeRooted(tree2)) {
            System.out.println("Error: Both trees must be rooted");
            return;
        }

        IsomorphismAlgorithm isomorphismAlgorithm = new AHUAlgorithm();

        Set<RootedTree> rooted1 = TreeUtils.convertTreeToRooted(tree1);
        Set<RootedTree> rooted2 = TreeUtils.convertTreeToRooted(tree2);

        if(rooted1.size() != rooted2.size()) {
            System.out.println("Trees are not isomoprhic.\nReason: different number of roots");
        } else if(rooted1.size() == 1 && rooted2.size() == 1) {
            getResult(isomorphismAlgorithm, rooted1.stream().findFirst(), rooted2.stream().findFirst());
        } else {
            List<RootedTree> rootedList1 = rooted1.stream().collect(Collectors.toList());
            List<RootedTree> rootedList2 = rooted2.stream().collect(Collectors.toList());
			long start = System.currentTimeMillis();

            if(isomorphismAlgorithm.isIsomorphic(rootedList1.get(0), rootedList2.get(0))) {
				long end = System.currentTimeMillis() ;
                System.out.println("Trees are isomorphic");
				System.out.println("Total time: " + (end - start) + " ms");
            } else {
                getResult(isomorphismAlgorithm, rootedList1.get(1), rootedList2.get(0));
            }
        }

    }

    private static void getResult(IsomorphismAlgorithm isomorphismAlgorithm, Optional<RootedTree> tree1, Optional<RootedTree> tree2) {
        RootedTree t1 = tree1.get();
        RootedTree t2 = tree2.get();
        if(t1 == null || t2 == null) {
            throw new RuntimeException("Unknown exception");
        }

       getResult(isomorphismAlgorithm, t1, t2);
    }

    private static void getResult(IsomorphismAlgorithm isomorphismAlgorithm, RootedTree t1, RootedTree t2) {
        long start = System.currentTimeMillis();

		if(isomorphismAlgorithm.isIsomorphic(t1, t2)) {
            System.out.println("Trees are isomorphic");
        } else {
            System.out.println("Tree are not isomorphic");
        }
		
		long end = System.currentTimeMillis();
		System.out.println("Total time: " + (end - start) + " ms");

        return;
    }

    private static boolean isTreeRooted(Tree tree) {
        return tree.centerTree().size() > 0;
    }
}
