/*
 * Date: 2021-10-15
 * File Name: MyGML.java
 * Author: Rickard Marjanovic
 */

package rm222jx;

import graphs.DirectedGraph;
import graphs.GML;
import graphs.Node;
import java.util.Iterator;

/**
 * Class Description: My implementation of the GML class which can print a
 * string representation of a directed graph
 *
 * @param <E>
 * @version 1.0
 * @author Rickard Marjanovic
 */
public class MyGML<E> extends GML<E> {
    public MyGML(DirectedGraph<E> dg) {
        super(dg);
    }

    /**
     * The GML mark-up string constructor.
     */
    public String toGML() {

        // RMC: Adding front information.
        int id = 1;
        StringBuilder front = new StringBuilder("graph [" + "\n");
        front.append("\tcomment \"directed graph\"" + "\n");
        front.append("\tid ").append(id).append("\n");

        StringBuilder nodeString = new StringBuilder();
        StringBuilder edgeString = new StringBuilder();

        Iterator<Node<E>> i = graph.iterator();
        // RMC: Adding nodes to the end of the node string
        while (i.hasNext()) {
            MyNode<E> node = (MyNode<E>) i.next();
            nodeString.append("\tnode [\n");
            nodeString.append("\t\tid ").append(id).append("\n");
            nodeString.append("\t\tlabel \"node ").append(node).append("\"\n");
            nodeString.append("\t]\n");
            id++;

            // RMC: Adding edge parts to the a seperate string for each node
            Iterator<Node<E>> iS = node.succsOf();
            while (iS.hasNext()) {
                Node<E> s = iS.next();
                edgeString.append("\tedge [\n");
                edgeString.append("\t\tsource ").append(node).append("\n");
                edgeString.append("\t\ttarget ").append(s).append("\n");
                edgeString.append("\t\tlabel \"Edge from node ").append(node).append(" to node ").append(s)
                        .append("\"\n");
                edgeString.append("\t]\n");
            }
        }

        StringBuilder back = new StringBuilder("]");

        return front.toString() + nodeString.toString() + edgeString.toString() + back.toString();
    }
}
