package pl.pw.elka.gis.isomorphism;

import org.junit.Test;
import pl.pw.elka.gis.model.Edge;
import pl.pw.elka.gis.model.RootedTree;
import pl.pw.elka.gis.model.Tree;
import pl.pw.elka.gis.model.Vertex;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Pawel on 2016-10-31.
 */
public class AHUAlgorithmTest {

    

    @Test
    public void treesShouldBeIsomorphic() {
        RootedTree rootedTree1 = createTree1();
        RootedTree rootedTree2 = createTree2();

        IsomorphismAlgorithm ahu = new AHUAlgorithm();
				 long start = System.currentTimeMillis();
		boolean isIsomorphic = ahu.isIsomorphic(rootedTree1, rootedTree2);
				 long end = System.currentTimeMillis();
		 System.out.println("Time2: " + (end - start));

        assertTrue(isIsomorphic);
    }
	
	@Test
    public void sameTreeShouldBeIsomorphicToItself() {
        RootedTree rootedTree = createTree1();
        AHUAlgorithm ahu = new AHUAlgorithm();
		 long start = System.currentTimeMillis();
		boolean isIsomorphic = ahu.isIsomorphic(rootedTree, rootedTree);
		 long end = System.currentTimeMillis();
		 System.out.println("Time1: " + (end - start));
        assertTrue(isIsomorphic);
    }

    @Test
    public void treesShouldNotBeIsomorphic1() {
        RootedTree rootedTree1 = createTree1();
        RootedTree rootedTree2 = createTree3();

        IsomorphismAlgorithm ahu = new AHUAlgorithm();
						 long start = System.currentTimeMillis();

				boolean isIsomorphic = ahu.isIsomorphic(rootedTree1, rootedTree2);
								 long end = System.currentTimeMillis();

		 System.out.println("Time3: " + (end - start));

        assertFalse(isIsomorphic);
    }

    @Test
    public void treesShouldNotBeIsomorphic2() {
        RootedTree rootedTree1 = createTree1();
        RootedTree rootedTree2 = createTree5();

         IsomorphismAlgorithm ahu = new AHUAlgorithm();
						 long start = System.currentTimeMillis();

				boolean isIsomorphic = ahu.isIsomorphic(rootedTree1, rootedTree2);
								 long end = System.currentTimeMillis();

		 System.out.println("Time4: " + (end - start));

        assertFalse(isIsomorphic);
    
    }
    /**
     * We comparing V1 - V2 - V3 - V4 (*) vs V1 - V2 - V3 - V4 (**)
     * Such tree has 2 centers (V2 and V3)
     * We have to create two rooted trees for both (*) and (**) and then compare one to another
     */
    @Test
    public void bicenteredTreesShouldBeIsomorphic() {
        RootedTree rootedTree1 = createTree4();
        RootedTree rootedTree2 = createTree5();

        RootedTree rootedTree3 = createTree5();
        RootedTree rootedTree4 = createTree4();

        IsomorphismAlgorithm ahu = new AHUAlgorithm();
								 long start = System.currentTimeMillis();

		boolean isIsomorphic1 = ahu.isIsomorphic(rootedTree1, rootedTree3) ;
		boolean isIsomorphic2 = ahu.isIsomorphic(rootedTree2, rootedTree3);
										 long end = System.currentTimeMillis();
		 System.out.println("Time5: " + (end - start));

        assertTrue(isIsomorphic1 || isIsomorphic2);
    }


    /**
     * V1 - V2 - V3 - V4 - V6
     *           |
     *           V5
     */
    private RootedTree createTree1() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);
        Edge e4 = new Edge(v3, v5);
        Edge e5 = new Edge(v4, v6);

        tree.setVertices(v1, v2, v3, v4, v5, v6);
        tree.setEdges(e1, e2, e3, e4, e5);

        Set<Vertex> root = tree.centerTree();

        return new RootedTree(tree, root.stream().findFirst().orElseThrow(RuntimeException::new));
    }

    /**
     * V2 - V3 - V4 - V6
     *       |
     *      V5
     *       |
     *       V1
     */
    private RootedTree createTree2() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);

        Edge e1 = new Edge(v2, v3);
        Edge e2 = new Edge(v3, v4);
        Edge e3 = new Edge(v4, v6);
        Edge e4 = new Edge(v3, v5);
        Edge e5 = new Edge(v5, v1);

        tree.setVertices(v1, v2, v3, v4, v5, v6);
        tree.setEdges(e1, e2, e3, e4, e5);

        Set<Vertex> root = tree.centerTree();

        return new RootedTree(tree, root.stream().findFirst().orElseThrow(RuntimeException::new));
    }

    /**
     * V2 - V3 - V4                                V5
     *       |                                   /   \
     *      V5         after centering         V3     V1
     *       |                               /   \      \
     *       V1                             V2    V4     V6
     *       |
     *       V6
     */
    private RootedTree createTree3() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);

        Edge e1 = new Edge(v2, v3);
        Edge e2 = new Edge(v3, v4);
        Edge e3 = new Edge(v3, v5);
        Edge e4 = new Edge(v5, v1);
        Edge e5 = new Edge(v1, v6);

        tree.setVertices(v1, v2, v3, v4, v5, v6);
        tree.setEdges(e1, e2, e3, e4, e5);

        Set<Vertex> root = tree.centerTree();
        return new RootedTree(tree, root.stream().findFirst().orElseThrow(RuntimeException::new));
    }

    /**
     * V1 - V2 - V3 - V4        V2
     *                         /  \
     *                        V1   V3
     *                               \
     *                               V4
     */
    private RootedTree createTree4() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);

        tree.setVertices(v1, v2, v3, v4);
        tree.setEdges(e1, e2, e3);

        Set<Vertex> root = tree.centerTree();

        return new RootedTree(tree, root.stream().filter(v -> v.equals(v2)).findFirst().orElseThrow(RuntimeException::new));
    }

    /**
     * V1 - V2 - V3 - V4        V3
     *                         /  \
     *                        V2   V4
     *                       /
     *                      V1
     */
    private RootedTree createTree5() {
        Tree tree = new Tree();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);

        tree.setVertices(v1, v2, v3, v4);
        tree.setEdges(e1, e2, e3);

        Set<Vertex> root = tree.centerTree();

        return new RootedTree(tree, root.stream().filter(v -> v.equals(v3)).findFirst().orElseThrow(RuntimeException::new));
    }
}
