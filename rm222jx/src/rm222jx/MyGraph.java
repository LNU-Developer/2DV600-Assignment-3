/*
 * Date: 2021-10-15
 * File Name: MyGraph.java
 * Author: Rickard Marjanovic
 */

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
import java.util.NoSuchElementException;

/**
 * Class Description: My implementation of the graph class that Represents a
 * directed graph, which can contain nodes and edges connecting nodes to each
 * other.
 *
 * @param <E>
 * @version 1.0
 * @author Rickard Marjanovic
 */
public class MyGraph<E> implements DirectedGraph<E> {
    // RMC: Hashsets/Map is my way of solving the issue that of unique nodes,
    // silently not adding.
    private Map<E, MyNode<E>> _graph = new HashMap<E, MyNode<E>>();
    private Set<Node<E>> _heads = new HashSet<Node<E>>(); // RMC: To make it easier to iterate through the heads,
                                                          // however tradeoff in that I need to keep track of and remove
                                                          // these in other methods.
    private Set<Node<E>> _tails = new HashSet<Node<E>>(); // RMC: To make it easier to iterate through the heads,
                                                          // however tradeoff in that I need to keep track of and handle
                                                          // these in other methods.

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
        nullChecker(item);
        // RMC: This check needed otherwise the value gets replaced, while it instead
        // silent no change was needed.
        if (!_graph.containsKey(item)) {
            MyNode<E> node = new MyNode<E>(item);
            _heads.add(node);
            _tails.add(node);
            _graph.put(item, node);
        }
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
        // RMC: Exception to be thrown if the key doesn't exist or if the item is null.
        // Perhaps it should be better to just return null, which would enable me to
        // reuse this method in e.g. containsNodeFor.
        nullChecker(item);
        existChecker(item);
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
        nullChecker(from);
        nullChecker(to);

        MyNode<E> source = (MyNode<E>) addNodeFor(from);
        MyNode<E> target = (MyNode<E>) addNodeFor(to);

        // RMC: According to method docs the method should return false if the edge was
        // added before i.e has a successor or predecessor.
        if (source.hasSucc(target) && target.hasPred(source))
            return false;

        // RMC: Add the target as successor to source and add source as predecessor
        // to target. This is where linking happens.
        source.addSucc(target);
        target.addPred(source);

        // RMC: Due to handling of heads/tails as properties.
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
        nullChecker(item);
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

    /**
     * Returns an iterator over all nodes with no in-edges.
     *
     * @return heads iterator
     */
    public Iterator<Node<E>> heads() {
        // RMC: Due to my creating a specific property for heads/tails, I can can call
        // the iterator method directly on the property instead of looping through and
        // checking each item if they are a head/tail.
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
        // RMC: Due to my creating a specific property for heads/tails, I can can call
        // the iterator method directly on the property instead of looping through and
        // checking each item if they are a head/tail.
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
        nullChecker(item);
        existChecker(item);

        MyNode<E> nodeToDelete = (MyNode<E>) getNodeFor(item);
        // RMC remove the linkage on all nodes connected to the node being removed
        for (MyNode<E> node : _graph.values()) {
            if (node.hasPred(nodeToDelete)) {
                node.removePred(nodeToDelete);
            }

            if (node.hasSucc(nodeToDelete)) {
                node.removeSucc(nodeToDelete);
            }
        }
        // RMC: Finally able to disconnect/remove the node from the list. Also if node
        // is removed all the head/tails are also removed. As such we don't readd these
        // to head/tail
        nodeToDelete.disconnect();
        _graph.remove(item);
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
        nullChecker(from);
        nullChecker(to);

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
        // RMC: Exception to be thrown if to or from is null.
        nullChecker(from);
        nullChecker(to);

        // RMC: if the node d
        if (!containsEdgeFor(from, to))
            return false;

        MyNode<E> source = (MyNode<E>) getNodeFor(from);
        MyNode<E> target = (MyNode<E>) getNodeFor(to);

        // RMC: Reversing what is done in the addEdge for. Removing
        // successor/predecessor link.
        source.removeSucc(target);
        target.removePred(source);

        // RMC: Also reversing what is done in the addEgde method. If they are
        // tails/heads they are added to these lists for further handling.
        if (source.isTail())
            _tails.add(source);

        if (target.isHead())
            _heads.add(target);

        return true;
    }

    /**
     * A textual representation of the graph content (nodes and edges) constructed
     * by applying <tt>toString()</tt> on the nodes.
     *
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (MyNode<E> node : _graph.values()) {
            sb.append("Node: " + node.item());

            sb.append("\t Successors:");
            sb.append(stringPoints(node.succsOf()));

            sb.append("\t Predecessors:");
            sb.append(stringPoints(node.predsOf()));
        }

        return sb.toString();
    }

    /**
     * Method that iterates through a provided iteration of nodes and creates a
     * representation string for it *
     *
     * @param tmp, Iterator object of nodes
     * @return a string representation of the nodes
     */
    private String stringPoints(Iterator<Node<E>> tmp) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t[ ");

        while (tmp.hasNext()) {
            sb.append(tmp.next());

            if (tmp.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append(" ] \n");

        return sb.toString();
    }

    /**
     * Method to convert to a hashmap of Nodes
     *
     * @return a hashmap of all nodes
     */
    private HashMap<E, Node<E>> ConvertToNodeHashMap() {
        HashMap<E, Node<E>> hashMap = new HashMap<E, Node<E>>();

        // RMC: Foreach node get the add each key and convert to node as value
        for (MyNode<E> node : _graph.values()) {
            hashMap.put(node.item(), node);
        }
        return hashMap;
    }

    /**
     * Helper method to check for null and throw an exception.
     *
     * @param E object
     */
    private void nullChecker(E item) {
        if (item == null)
            throw new NullPointerException();
    }

    /**
     * Helper method to check for element and throw an exception if it doesn't exist
     *
     * @param E object
     */
    private void existChecker(E item) {
        if (!_graph.containsKey(item))
            throw new NoSuchElementException();
    }

}
