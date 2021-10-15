package rm222jx;

import graphs.BFS;
import graphs.DirectedGraph;
import graphs.Node;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Queue;

public class MyBFS<E> implements BFS<E> {
    /**
     * Returns the nodes visited by a breadth-first search starting from the given
     * root node. Each visited node is also attached with a breadth-first number.
     */
    public List<Node<E>> bfs(DirectedGraph<E> graph, Node<E> root) {
        nullChecker(graph);
        nullChecker(root);
        // RMC: Just exploring one specific path by providing a root node.
        Set<Node<E>> visitedNodes = new LinkedHashSet<>();
        performBFS(root, visitedNodes);
        return new ArrayList<>(visitedNodes);
    }

    /**
     * Returns the nodes visited by a breadth first search starting from an
     * arbitrary set of nodes. All nodes are visited. Each visited node is also
     * attached with a breadth-first number.
     */
    public List<Node<E>> bfs(DirectedGraph<E> graph) {
        nullChecker(graph);
        // RMC: All nodes are visitable by transversing all heads.
        Set<Node<E>> visitedNodes = new LinkedHashSet<>();
        Iterator<Node<E>> heads = graph.heads();
        heads.forEachRemaining(head -> performBFS(head, visitedNodes));
        return new ArrayList<>(visitedNodes);
    }

    private void performBFS(Node<E> node, Set<Node<E>> visitedNodes) {
        // RMC: I will need a queue to process all nodes that are direct successors
        // first and then move on the the next ones.
        Queue<Node<E>> queue = new LinkedList<>();
        // RMC Adding new node to queue to process its successors, as well as adding it
        // to the visited node
        queue.add(node);
        node.num = visitedNodes.size(); // RMC: Adding all numbering to the current node. Since this increases with the
        // visited set size I can remove the need for keeping a global property.
        visitedNodes.add(node);

        // RMC while i.e node doesn't have any successors left.
        while (!queue.isEmpty()) {

            // RMC: Return and remove the first node in the queue and creating an iteration
            // of all it's successors.
            Node<E> processNode = queue.poll();
            Iterator<Node<E>> nodeIterator = processNode.succsOf();

            nodeIterator.forEachRemaining(successor -> {
                // RMC: visited nodes are ignored
                if (visitedNodes.contains(successor))
                    return;

                // RMC: When more successors are found add them to the visited list and queue in
                // the same fashion as above.
                successor.num = visitedNodes.size();
                visitedNodes.add(successor);

                // RMC: Due to the the queue system, the successors are added before it's
                // children which is what the breadth first search is all about.
                queue.add(successor);
            });
        }
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
