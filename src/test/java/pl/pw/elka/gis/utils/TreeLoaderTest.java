package pl.pw.elka.gis.utils;

import org.junit.Test;

import java.util.UnknownFormatConversionException;

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
    public void shouldThrowExceptionDueToGraphNotBeingTree() {
        TreeUtils.loadTreeFromFile("cyclic1.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToGraphNotBeingTree2() {
        TreeUtils.loadTreeFromFile("cyclic2.txt");
    }

    @Test(expected = UnknownFormatConversionException.class)
    public void shouldhrowExceptionDueToBrokenFile() {
        TreeUtils.loadTreeFromFile("broken1.txt");
    }

    @Test(expected = UnknownFormatConversionException.class)
    public void shouldhrowExceptionDueToBrokenFile2() {
        TreeUtils.loadTreeFromFile("broken2.txt");
    }

    @Test(expected = UnknownFormatConversionException.class)
    public void shouldhrowExceptionDueToBrokenFile3() {
        TreeUtils.loadTreeFromFile("broken3.txt");
    }
}
