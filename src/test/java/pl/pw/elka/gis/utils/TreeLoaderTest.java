package pl.pw.elka.gis.utils;

import org.junit.Test;

/**
 * Created by Pawel on 2016-11-11.
 */
public class TreeLoaderTest {

    @Test
    public void shoudlLoadTree() {
        TreeUtils.loadTreeFromFile("tree1.txt");
    }

    @Test
    public void shoudlLoadTree2() {
        TreeUtils.loadTreeFromFile("tree2.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shoudlThrowExceptionDueToGraphNotBeingTree() {
        TreeUtils.loadTreeFromFile("cyclic1.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shoudlThrowExceptionDueToGraphNotBeingTree2() {
        TreeUtils.loadTreeFromFile("cyclic2.txt");
    }
}
