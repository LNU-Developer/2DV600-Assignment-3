package rm222jx;

import graphs.DirectedGraph;
import graphs.Node;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
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
        if (item != null) {
            // RMC: This check needed otherwise the value gets replaced, while it instead
            // silent no change was needed.
            if (!_graph.containsKey(item)) {
                MyNode<E> node = new MyNode<E>(item);
                _heads.add(node);
                _tails.add(node);
                _graph.put(item, node);
            }
            return _graph.get(item);
        } else {
            throw new IllegalArgumentException("Test");
        }
    }

    /**
     * Returns the node representing <tt>item</tt>. Exception is thrown if
     * <tt>item</tt> is null or if no node representing <tt>item</tt> is found.
     *
     * @param item
     * @return Node representing <tt>item</tt>
     */
    public Node<E> getNodeFor(E item) {
        // RMC: Exception to be thrown if the key doesn't exist or if the item is null.
        // Perhaps it should be better to just return null, which would enable me to
        // reuse this method in e.g. containsNodeFor.
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
        if (from == null || to == null)
            throw new IllegalArgumentException("Test");

        MyNode<E> source = (MyNode<E>) addNodeFor(from);
        MyNode<E> target = (MyNode<E>) addNodeFor(to);

        // RMC: According to method docs the method should return false if the edge was
        // added before i.e has a successor.
        if (source.hasSucc(target))
            return false;

        source.addSucc(target);
        target.addPred(source);

        _tails.remove(source);
        _heads.remove(target);
        return true;

    }

    /**
     * Returns <tt>true</tt> if the node representing <tt>item</tt> is contained in
     * the graph, otherwise <tt>false</tt>. Exception is thrown if <tt>item</tt> is
     * null.
     *
     * @param item, node to be checked.
     */
    public boolean containsNodeFor(E item) {
        if (item == null)
            throw new IllegalArgumentException("Test");
        // RMC: Get the item and check that it exists
        return _graph.get(item) != null;
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
        return ConvertToNodeHashMap().values().iterator();
    }

    // RMC: Method to convert to a hashmap of Nodes
    private HashMap<E, Node<E>> ConvertToNodeHashMap() {
        HashMap<E, Node<E>> hashMap = new HashMap<E, Node<E>>();

        // RMC: Foreach node get the add each key and convert to node as value
        for (MyNode<E> node : _graph.values()) {
            hashMap.put(node.item(), node);
        }
        return hashMap;
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
        return _heads.size();
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
        return _tails.size();
    }

    /**
     * Returns a list over all node items currently used in the graph.
     *
     * @return list of items
     */
    public List<E> allItems() {
        List<E> allItems = new ArrayList<>();
        // RMC: For each node get the values and att them to a list
        for (MyNode<E> node : _graph.values()) {
            allItems.add(node.item());
        }
        return allItems;
    }

    /**
     * Returns the number of graph edges.
     *
     * @return edge count
     */
    public int edgeCount() {
        int edges = 0;
        // RMC: Foreach node get the value from the map and count the edge values of
        // each node
        for (MyNode<E> node : _graph.values()) {
            edges += node.outDegree();
        }
        return edges;
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
        // RMC: Exception to be thrown if to or from is null.
        if (from == null || to == null)
            throw new IllegalArgumentException("Test");

        // RMC: If there exists nodes from both from and to check if from has a
        // successor in to
        if (containsNodeFor(from) && containsNodeFor(to))
            return _graph.get(from).hasSucc(_graph.get(to));

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
