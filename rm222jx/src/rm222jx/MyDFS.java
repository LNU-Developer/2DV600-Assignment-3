package rm222jx;

import graphs.DFS;
import graphs.DirectedGraph;
import graphs.Node;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Iterator;

public class MyDFS<E> implements DFS<E> {
    /**
     * Returns the nodes visited by a depth first search starting from the given
     * root node. Each visited node is also attached with a depth-first number.
     */
    public List<Node<E>> dfs(DirectedGraph<E> graph, Node<E> root) {
        nullChecker(graph);
        nullChecker(root);

        // RMC: Linked hashsset was picked due to the properties of the HashSet as well
        // as our need to preserve order. Our goal is to find all visited nodes.
        Set<Node<E>> visitedNodes = new LinkedHashSet<Node<E>>();
        performDFS(root, visitedNodes);
        return new ArrayList<Node<E>>(visitedNodes);
    }

    /**
     * Returns the nodes visited by a depth first search starting from an arbitrary
     * set of nodes. All nodes are visited. Each visited node is also attached with
     * a depth-first number.
     */
    public List<Node<E>> dfs(DirectedGraph<E> graph) {
        nullChecker(graph);
        // RMC: In this case there can be several heads that has no connection to
        // eachother, as such I need to check heads independantly

        Set<Node<E>> visited = new LinkedHashSet<>();

        Iterator<Node<E>> heads = graph.heads();
        heads.forEachRemaining(node -> performDFS(node, visited));

        return new ArrayList<Node<E>>(visited);
    }

    private void performDFS(Node<E> currentNode, Set<Node<E>> visitedNodes) {
        // RMC: If it already visited this node, it shoulnd't proceed
        if (visitedNodes.contains(currentNode))
            return;

        // RMC: Adding all numbering to the current node. Since this increases with the
        // visited set size I can remove the need for keeping a global property.
        currentNode.num = visitedNodes.size();
        // RMC: Adding current node to visited list reference (this will be saved for
        // the future due to it being a reference)
        visitedNodes.add(currentNode);

        // RMC: Finds all successors of a node
        Iterator<Node<E>> successors = currentNode.succsOf();

        // RMC: runs this operation as long as there are nodes
        successors.forEachRemaining(successor -> performDFS(successor, visitedNodes));
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
