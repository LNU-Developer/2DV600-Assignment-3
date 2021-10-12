package rm222jx;

import graphs.Node;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class MyNode<E> extends Node<E> {
    // RMC: Hashsets is my way of solving the issue that of unique nodes, silently
    // not adding. Also reusing thought process from my solution from Assignment 2.
    private Set<Node<E>> _predecessors = new HashSet<>();
    private Set<Node<E>> _successors = new HashSet<>();

    protected MyNode(E item) {
        super(item);
    }

    public boolean hasSucc(Node<E> node) {
        return _successors.contains(node);
    }

    /**
     * Returns the number of successors (i.e. outgoing edges) of this node.
     *
     * @return node out-degree
     */
    public int outDegree() {
        return _successors.size();
    }

    /**
     * Returns an iterator over all successor nodes.
     *
     * @return successor node iterator
     */
    public Iterator<Node<E>> succsOf() {
        return _successors.iterator();
    }

    /**
     * Returns <tt>true</tt> if <tt>this</tt> node has <tt>node</tt> as predecessor,
     * otherwise <tt>false</tt>.
     *
     * @param a possible predecessor node
     * @return boolean
     */
    public boolean hasPred(Node<E> node) {
        return _predecessors.contains(node);
    }

    /**
     * Returns the number of predecessors (i.e. incoming edges) of this node.
     *
     * @return node out-degree
     */
    public int inDegree() {
        return _predecessors.size();
    }

    /**
     * Returns an iterator over all predecessor nodes.
     *
     * @return predecessor node iterator
     */
    public Iterator<Node<E>> predsOf() {
        return _predecessors.iterator();
    }

    /**
     * Adds node <tt>succ</tt> as a successor to <tt>this</tt> node.
     */
    protected void addSucc(Node<E> succ) {
        _successors.add(succ);
    }

    /**
     * Removes node <tt>succ</tt> as a successor to <tt>this</tt> node.
     */
    protected void removeSucc(Node<E> succ) {
        _successors.remove(succ);
    }

    /**
     * Adds node <tt>pred</tt> as a predecessor to <tt>this</tt> node.
     */
    protected void addPred(Node<E> pred) {
        _predecessors.add(pred);
    }

    /**
     * Removes node <tt>pred</tt> as a predecessor to <tt>this</tt> node.
     */
    protected void removePred(Node<E> pred) {
        _predecessors.remove(pred);
    }

    /**
     * Disconnects this node from all adjacent nodes. That is, removes all
     * successor, and predecessor, nodes to <tt>this</tt> node.
     */
    protected void disconnect() {
        _successors.clear();
        _predecessors.clear();
    }
}
