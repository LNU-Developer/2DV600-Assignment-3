package rm222jx;

import graphs.DirectedGraph;
import graphs.Node;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class MyGraph<E> implements DirectedGraph<E> {
    // RMC: Hashsets/Map is my way of solving the issue that of unique nodes,
    // silently not adding.
    private Map<E, MyNode<E>> _graph = new HashMap<E, MyNode<E>>();
    private Set<Node<E>> _heads = new HashSet<Node<E>>();
    private Set<Node<E>> _tails = new HashSet<Node<E>>();

    // RMC: Requirment of a default constructor MyGraph() generating an empty graph.
    public MyGraph() {

    }

    /**
     * Adds a node representing <tt>item</tt> if not added before. Exception is
     * thrown if <tt>item</tt> is null. It returns the node representing
     * <tt>item</tt> (new or previously constructed).
     *
     * @param item,
     * @return Node representing <tt>item</tt>
     */
    public Node<E> addNodeFor(E item) {
        if (item == null)
            throw new IllegalArgumentException("Test");

        MyNode<E> node = new MyNode<E>(item);
        _heads.add(node);
        _tails.add(node);
        _graph.put(item, node);
        return _graph.get(item);

    }

    /**
     * Returns the node representing <tt>item</tt>. Exception is thrown if
     * <tt>item</tt> is null or if no node representing <tt>item</tt> is found.
     *
     * @param item
     * @return Node representing <tt>item</tt>
     */
    public Node<E> getNodeFor(E item) {
        if (item == null || !_graph.containsKey(item))
            throw new IllegalArgumentException("Test");

        return _graph.get(item);
    }

    /**
     * Adds an edge between the nodes represented by <tt>from</tt> and <tt>to</tt>
     * if not added before. The nodes representing <tt>from</tt> and <tt>to</tt> are
     * added if not added before. Exception is thrown if <tt>from</tt> or
     * <tt>to</tt> is null. It returns <tt>true</tt> if edge not added before,
     * otherwise <tt>false</tt>.
     *
     * @param from, source node
     * @param to,   target node
     * @return <tt>true</tt> if edge not added before, otherwise <tt>false</tt>.
     */
    public boolean addEdgeFor(E from, E to) {
        return false;
    }

    /**
     * Returns <tt>true</tt> if the node representing <tt>item</tt> is contained in
     * the graph, otherwise <tt>false</tt>. Exception is thrown if <tt>item</tt> is
     * null.
     *
     * @param item, node to be checked.
     */
    public boolean containsNodeFor(E item) {
        return false;
    }

    /**
     * Returns the number of nodes in the graph.
     *
     * @return number of nodes
     */
    public int nodeCount() {
        return _graph.size();
    }

    /**
     * Returns an iterator over all nodes in the graph.
     *
     * @return graph nodes iterator
     */
    public Iterator<Node<E>> iterator() {
        return null;
    }

    /**
     * Returns an iterator over all nodes with no in-edges.
     *
     * @return heads iterator
     */
    public Iterator<Node<E>> heads() {
        return _heads.iterator();
    }

    /**
     * The number of nodes with no in-edges.
     *
     * @return number of head nodes.
     */
    public int headCount() {
        return 0;
    }

    /**
     * Returns an iterator over all nodes with no out-edges.
     *
     * @return tails iterator
     */
    public Iterator<Node<E>> tails() {
        return _tails.iterator();
    }

    /**
     * The number of nodes with no out-edges.
     *
     * @return number of head nodes.
     */
    public int tailCount() {
        return 0;
    }

    /**
     * Returns a list over all node items currently used in the graph.
     *
     * @return list of items
     */
    public List<E> allItems() {
        return null;
    }

    /**
     * Returns the number of graph edges.
     *
     * @return edge count
     */
    public int edgeCount() {
        return 0;
    }

    /**
     * Removes the node represented by <tt>item</item> and
     * all its connecting edges. Exception is thrown if <tt>item</tt> is null or if
     * no node representing <tt>item</tt> is found.
     *
     * @param item, node to be removed.
     */
    public void removeNodeFor(E item) {
    }

    /**
     * Returns <tt>true</tt> if an edge between the nodes represented by
     * <tt>from</tt> and <tt>to</tt> is added to the graph. Exception is thrown if
     * <tt>from</tt> or <tt>to</tt> is null.
     *
     * @param from, source node item
     * @param to,   target node item
     * @return <tt>true</tt> if edge in graph, otherwise <tt>false</tt>.
     */
    public boolean containsEdgeFor(E from, E to) {
        return false;
    }

    /**
     * Removes the edge between the nodes represented by <tt>from</tt> and
     * <tt>to</tt> if it exist. Returns <tt>true</tt> if an edge between the nodes
     * represented by <tt>from</tt> and <tt>to</tt> is found and successfully
     * removed. Exception is thrown if <tt>from</tt> or <tt>to</tt> is null.
     *
     * @param from, source node item
     * @param to,   target node item
     * @return <tt>true</tt> if edge in graph and successfully removed, otherwise
     *         <tt>false</tt>.
     */
    public boolean removeEdgeFor(E from, E to) {
        return false;
    }

    /**
     * A textual representation of the graph content (nodes and edges) constructed
     * by applying <tt>toString()</tt> on the nodes.
     *
     */
    public String toString() {
        return null;
    }

}
