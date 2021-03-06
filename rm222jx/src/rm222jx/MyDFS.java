/*
 * Date: 2021-10-15
 * File Name: MyDFS.java
 * Author: Rickard Marjanovic
 */
package rm222jx;

import graphs.DFS;
import graphs.DirectedGraph;
import graphs.Node;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

/**
 * Class Description: My implementation of the Depth first class which performs
 * said algoritm on graphs
 *
 * @param <E>
 * @version 1.0
 * @author Rickard Marjanovic
 */
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

        Set<Node<E>> visitedNodes = new LinkedHashSet<>();
        Iterator<Node<E>> heads = graph.heads();
        heads.forEachRemaining(node -> performDFS(node, visitedNodes));

        return new ArrayList<Node<E>>(visitedNodes);
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

        // RMC: Linked hashsset was picked due to the properties of the HashSet as well
        // as our need to preserve order. Our goal is to find all visited nodes.
        Set<Node<E>> visitedNodes = new LinkedHashSet<>();
        List<Node<E>> postOrderNodes = new LinkedList<>();
        performPostOrder(root, visitedNodes, postOrderNodes);
        return postOrderNodes;
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
        Set<Node<E>> visitedNodes = new LinkedHashSet<>();
        List<Node<E>> postOrderNodes = new LinkedList<>();
        // RMC: In this case I perform iteration on the whole graph
        Iterator<Node<E>> nodes = g.iterator();
        nodes.forEachRemaining(node -> performPostOrder(node, visitedNodes, postOrderNodes));
        return postOrderNodes;
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
        Set<Node<E>> visitedNodes = new LinkedHashSet<>();
        List<Node<E>> postOrderNodes = new LinkedList<>();

        // RMC: In this case I perform iteration on all heads, attaching a DFS number to
        // them.
        Iterator<Node<E>> heads = g.heads();
        heads.forEachRemaining(node -> {
            if (attach_dfs_number) {
                // RMC: Adding all numbering to the current node. Since this increases with the
                // visited set size I can remove the need for keeping a global property.
                node.num = visitedNodes.size();
            }

            performPostOrder(node, visitedNodes, postOrderNodes);
        });
        return postOrderNodes;
    }

    /**
     * Returns <tt>true</tt> if the graph contains one or more cycles, otherwise
     * <tt>false</tt>
     */
    public boolean isCyclic(DirectedGraph<E> graph) {
        for (Node<E> node : postOrder(graph)) { // RMC: Nice way of doing post order ordering in connection to the for
                                                // loop.
            // RMC: getting all successors of a node
            Iterator<Node<E>> successors = node.succsOf();

            // RMC: Iteration through nodes.
            while (successors.hasNext()) {
                // RMC: Checking if any backward edges
                if (successors.next().num >= node.num) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns a list of all nodes in the graph ordered topological. The algorithm
     * assumes that the graph is acyclic. The result for graphs with cycles are
     * undefined.
     */
    public List<Node<E>> topSort(DirectedGraph<E> graph) {
        nullChecker(graph);
        // RMC: Topological sort is in theory reverse post-order meaning we can call the
        // post order method and reverse the result.
        List<Node<E>> postOrderList = postOrder(graph);
        Collections.reverse(postOrderList);
        return postOrderList;
    }

    /**
     * Method to perform depth first search .
     *
     * @param currentNode  current node to begin post sorting
     * @param visitedNodes all already visited nodes
     */
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
     * Method to perform post order sorting.
     *
     * @param currentNode      current node to begin post sorting
     * @param visitedNodes     all already visited nodes
     * @param postOrderedNodes all nodes ordered according to post order
     */
    private void performPostOrder(Node<E> currentNode, Set<Node<E>> visitedNodes, List<Node<E>> postOrderedNodes) {
        // RMC: If it already visited this node, it shoulnd't proceed
        if (visitedNodes.contains(currentNode))
            return;

        // RMC: Adding current node to visited list reference (this will be saved for
        // the future due to it being a reference)
        visitedNodes.add(currentNode);

        // RMC: Finds all successors of a node
        Iterator<Node<E>> successors = currentNode.succsOf();

        // RMC: runs this operation as long as there are nodes
        successors.forEachRemaining(successor -> performPostOrder(successor, visitedNodes, postOrderedNodes));

        // RMC: Adding all numbering to the current node. Since this increases with the
        // visited set size I can remove the need for keeping a global property.
        currentNode.num = postOrderedNodes.size();

        // RMC: Finally adding current node to the postOrderedNodes
        postOrderedNodes.add(currentNode);
    }

    /**
     * Helper method to check for null and throw an exception.
     *
     * @param g a directed graph object of type E
     */
    private void nullChecker(DirectedGraph<E> g) {
        if (g == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Helper method to check for null and throw an exception.
     *
     * @param n node object of type E
     */
    private void nullChecker(Node<E> n) {
        if (n == null) {
            throw new NullPointerException();
        }
    }

}
