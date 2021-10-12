package rm222jx;

import graphs.DFS;
import graphs.DirectedGraph;
import graphs.Node;
import java.util.ArrayList;
import java.util.List;

public class MyDFS<E> implements DFS<E> {
    /**
     * Returns the nodes visited by a depth first search starting from the given
     * root node. Each visited node is also attached with a depth-first number.
     */
    public List<Node<E>> dfs(DirectedGraph<E> graph, Node<E> root) {
        nullChecker(graph);
        nullChecker(root);

        // RMC: Linked hashsset was picked due to the properties of the HashSet. Our
        // goal is to find all visited nodes.
        List<Node<E>> x = new ArrayList<Node<E>>();
        return x;
    }

    /**
     * Returns the nodes visited by a depth first search starting from an arbitrary
     * set of nodes. All nodes are visited. Each visited node is also attached with
     * a depth-first number.
     */
    public List<Node<E>> dfs(DirectedGraph<E> graph) {
        nullChecker(graph);
        List<Node<E>> x = new ArrayList<Node<E>>();
        return x;
    }

    /**
     * Returns a list of nodes ordered as post-order of the depth first tree
     * resulting from a depth first search starting at the given root node. Notice,
     * it only visits nodes reachable from given root node.
     * </p>
     * The algorithm also attaches a post-order number to each visited node.
     */
    public List<Node<E>> postOrder(DirectedGraph<E> g, Node<E> root) {
        nullChecker(g);
        nullChecker(root);
        List<Node<E>> x = new ArrayList<Node<E>>();
        return x;
    }

    /**
     * Returns a list of ALL nodes in the graph ordered as post-order of the depth
     * first forest resulting from depth first search starting at arbitrary start
     * nodes.
     * </p>
     * The algorithm also attaches a post-order number to each visited node.
     */
    public List<Node<E>> postOrder(DirectedGraph<E> g) {
        nullChecker(g);
        List<Node<E>> x = new ArrayList<Node<E>>();
        return x;
    }

    /**
     * Returns a list of ALL nodes in the graph ordered as post-order of the depth
     * first forest resulting from depth first search starting at arbitrary start
     * nodes.
     * </p>
     * The algorithm attaches a depth-first number if <tt>attach_dfs_number</tt> is
     * <tt>true</tt>, otherwise it attaches a post-order number.
     */
    public List<Node<E>> postOrder(DirectedGraph<E> g, boolean attach_dfs_number) {
        nullChecker(g);
        List<Node<E>> x = new ArrayList<Node<E>>();
        return x;
    }

    /**
     * Returns <tt>true</tt> if the graph contains one or more cycles, otherwise
     * <tt>false</tt>
     */
    public boolean isCyclic(DirectedGraph<E> graph) {
        return false;
    }

    /**
     * Returns a list of all nodes in the graph ordered topological. The algorithm
     * assumes that the graph is acyclic. The result for graphs with cycles are
     * undefined.
     */
    public List<Node<E>> topSort(DirectedGraph<E> graph) {
        nullChecker(graph);
        List<Node<E>> x = new ArrayList<Node<E>>();
        return x;
    }

    private void nullChecker(DirectedGraph<E> g) {
        if (g == null) {
            throw new NullPointerException();
        }
    }

    private void nullChecker(Node<E> n) {
        if (n == null) {
            throw new NullPointerException();
        }
    }

}
