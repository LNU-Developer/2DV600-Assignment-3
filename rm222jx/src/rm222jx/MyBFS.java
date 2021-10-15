package rm222jx;

import graphs.BFS;
import graphs.DirectedGraph;
import graphs.Node;
import java.util.List;
import java.util.ArrayList;

public class MyBFS<E> implements BFS<E> {
    /**
     * Returns the nodes visited by a breadth-first search starting from the given
     * root node. Each visited node is also attached with a breadth-first number.
     */
    public List<Node<E>> bfs(DirectedGraph<E> graph, Node<E> root) {

        return new ArrayList<>();
    }

    /**
     * Returns the nodes visited by a breadth first search starting from an
     * arbitrary set of nodes. All nodes are visited. Each visited node is also
     * attached with a breadth-first number.
     */
    public List<Node<E>> bfs(DirectedGraph<E> graph) {
        return new ArrayList<>();
    }
}
