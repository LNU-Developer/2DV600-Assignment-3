package rm222jx;

import graphs.DirectedGraph;
import graphs.Node;
import graphs.TransitiveClosure;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collection;

public class MyTransitiveClosure<E> implements TransitiveClosure<E> {
    /**
     * Computes the transitive closure for the graph.
     *
     */
    public Map<Node<E>, Collection<Node<E>>> computeClosure(DirectedGraph<E> dg) {
        nullChecker(dg);
        Map<Node<E>, Collection<Node<E>>> closures = new LinkedHashMap<>();

        // RMC: By using DFS I can effectively find all possible reachable nodes.
        MyDFS<E> dfs = new MyDFS<E>();

        // RMC: Iterate through all nodes and see the reachable nodes for each node and
        // adding to map of nodes and collections.

        Iterator<Node<E>> iterator = dg.iterator();
        iterator.forEachRemaining(currentNode -> {
            // RMC: Find all reachable nodes from the graph and current node.
            List<Node<E>> reachableNodes = dfs.dfs(dg, currentNode);
            // RMC add current node as key and the collection as the possible routes for
            // that node.
            closures.put(currentNode, reachableNodes);
        });

        return closures;

    }

    private void nullChecker(DirectedGraph<E> dg) {
        if (dg == null) {
            throw new NullPointerException();
        }
    }
}
