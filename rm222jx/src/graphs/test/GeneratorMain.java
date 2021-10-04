/**
 *
 */
package graphs.test;

import graphs.DirectedGraph;
import graphs.GML;

import java.io.File;

import rm222jx.*; // Replace with a package (named after your LNU user name, e.g. na222fx) that contains your implementation of the DirectedGraph and GML interface/abstract class*;

/**
 * A simple program to test and print graphs generated by GraphGenerator.
 *
 * @author jonasl
 *
 */
public class GeneratorMain {
	private static File home;

	public static void main(String[] args) throws Exception {
		File f = new File(System.getProperty("user.dir"));
		f = new File(f, "src");
		f = new File(f, "graphs");
		home = new File(f, "test");
		System.out.println("Home: " + home);

		GraphGenerator gen = new GraphGenerator(new MyGraph());
		Integer[] data = gen.getUsedItems();

		DirectedGraph<Integer> g;

		DirectedGraph<Integer> dg = gen.getSmallAcyclic();
		dump(dg, "small_acyclic.gml");

		dg = gen.getSmallCyclic();
		dump(dg, "small_cyclic.gml");

		dg = gen.getTwoParts();
		dump(dg, "two_parts.gml");

		dg = gen.getCyclic();
		dump(dg, "cyclic.gml");

		dg = gen.getDisconnected();
		dump(dg, "disconnected.gml");

		dg = gen.getComplete(4);
		dump(dg, "complete.gml");

		dg = gen.getBinaryTree(4);
		dump(dg, "binary_tree.gml");

		for (int i = 1; i < 10; i++) {
			dg = gen.getRandom(100, 0.25);
			System.out.println("\nNodes: " + dg.nodeCount());
			System.out.println("Edges: " + dg.edgeCount());
		}

		dg = gen.getRandom(15, 0.15);
		dump(dg, "random.gml");

		dg = gen.getRandomReachable(3, 1);
		dump(dg, "random_reachable.gml");
	}

	private static void dump(DirectedGraph<Integer> dg, String file_name) throws Exception {
		File f = new File(home, file_name);

		GML<Integer> gml = new MyGML<Integer>(dg);
		gml.dumpGML(f);
	}
}
