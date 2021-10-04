package rm222jx;

import graphs.*;
import java.util.Iterator;

public class MyNode<E> extends Node<E> {

    protected MyNode(E item) {
        super(item);
    }

    public boolean hasSucc(Node<E> node) {

    }

    /**
     * Returns the number of successors (i.e. outgoing edges) of this node.
     *
     * @return node out-degree
     */
    public int outDegree() {

    }

    /**
     * Returns an iterator over all successor nodes.
     *
     * @return successor node iterator
     */
    public Iterator<Node<E>> succsOf() {

    }

    /**
     * Returns <tt>true</tt> if <tt>this</tt> node has <tt>node</tt> as predecessor,
     * otherwise <tt>false</tt>.
     *
     * @param a possible predecessor node
     * @return boolean
     */
    public boolean hasPred(Node<E> node) {

    }

    /**
     * Returns the number of predecessors (i.e. incoming edges) of this node.
     *
     * @return node out-degree
     */
    public int inDegree() {

    }

    /**
     * Returns an iterator over all predecessor nodes.
     *
     * @return predecessor node iterator
     */
    public Iterator<Node<E>> predsOf() {

    }

    /**
     * Adds node <tt>succ</tt> as a successor to <tt>this</tt> node.
     */
    protected void addSucc(Node<E> succ) {

    }

    /**
     * Removes node <tt>succ</tt> as a successor to <tt>this</tt> node.
     */
    protected void removeSucc(Node<E> succ) {

    }

    /**
     * Adds node <tt>pred</tt> as a predecessor to <tt>this</tt> node.
     */
    protected void addPred(Node<E> pred) {

    }

    /**
     * Removes node <tt>pred</tt> as a predecessor to <tt>this</tt> node.
     */
    protected void removePred(Node<E> pred) {

    }

    /**
     * Disconnects this node from all adjacent nodes. That is, removes all
     * successor, and predecessor, nodes to <tt>this</tt> node.
     */
    protected void disconnect() {

    }

}
