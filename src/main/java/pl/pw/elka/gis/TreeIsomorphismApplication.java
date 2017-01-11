package pl.pw.elka.gis;

import pl.pw.elka.gis.isomorphism.AHUAlgorithm;
import pl.pw.elka.gis.isomorphism.IsomorphismAlgorithm;
import pl.pw.elka.gis.model.RootedTree;
import pl.pw.elka.gis.model.Tree;
import pl.pw.elka.gis.utils.TreeUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Pawel on 2017-01-11.
 */
public class TreeIsomorphismApplication {

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Error: Incorrect arguments number. \nYou have to give me two arguments which would be two URI of tree describing files");
            return;
        }

        Tree tree1 = TreeUtils.loadTreeFromFile(args[0]);
        Tree tree2 = TreeUtils.loadTreeFromFile(args[1]);

        if(!isTreeRooted(tree1) || !isTreeRooted(tree2)) {
            System.out.println("Error: Both trees must be rooted");
            return;
        }

        IsomorphismAlgorithm isomorphismAlgorithm = new AHUAlgorithm();

        Set<RootedTree> rooted1 = TreeUtils.convertTreeToRooted(tree1);
        Set<RootedTree> rooted2 = TreeUtils.convertTreeToRooted(tree2);

        if(rooted1.size() != rooted2.size()) {
            System.out.println("Tree are not isomoprhic.\nReason: different number of roots");
        } else if(rooted1.size() == 1 && rooted2.size() == 1) {
            getResult(isomorphismAlgorithm, rooted1.stream().findFirst(), rooted2.stream().findFirst());
        } else {
            List<RootedTree> rootedList1 = rooted1.stream().collect(Collectors.toList());
            List<RootedTree> rootedList2 = rooted2.stream().collect(Collectors.toList());

            if(isomorphismAlgorithm.isIsomorphic(rootedList1.get(0), rootedList2.get(0))) {
                System.out.println("Trees are isomorphic");
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
        if(isomorphismAlgorithm.isIsomorphic(t1, t2)) {
            System.out.println("Trees are isomorphic");
        } else {
            System.out.println("Tree are not isomorphic");
        }

        return;
    }

    private static boolean isTreeRooted(Tree tree) {
        return tree.centerTree().size() > 0;
    }
}
