package pl.pw.elka.gis.isomorphism;

import pl.pw.elka.gis.model.RootedTree;

/**
 * Tests rooted tree isomorphism
 * Created by Pawel on 2016-10-30.
 */
public interface IsomorphismAlgorithm {

    /**
     * Tests whether two rooted tress are isomorphic
     * @param t1 Rooted tree entity
     * @param t2 Rooted tree entity
     * @return True/False: Rooted tress are isomorphic/Rooted trees are not isomorphic
     */
    boolean isIsomorphic(RootedTree t1, RootedTree t2);
}
