/*
 * Date: 2021-10-15
 * File Name: MyConnectedComponents.java
 * Author: Rickard Marjanovic
 */

package rm222jx;

import graphs.ConnectedComponents;

import graphs.DirectedGraph;
import graphs.Node;

import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;

/**
 * Class Description: My implementation of the connected components class which
 * can compute if there are any connected componenets within a directed graph
 *
 * @param <E>
 * @version 1.0
 * @author Rickard Marjanovic
 */
public class MyConnectedComponents<E> implements ConnectedComponents<E> {
    /**
     * Method to find all connected components.
     *
     * @param dg a directed graph object of type E
     */
    public Collection<Collection<Node<E>>> computeComponents(DirectedGraph<E> dg) {
        nullChecker(dg);

        // RMC: Keeping each node of the grah in it's own connected component.
        Set<Collection<Node<E>>> allComponents = new LinkedHashSet<>();
        Set<Node<E>> visitedNodes = new LinkedHashSet<>();

        // RMC: By using DFS I can effectively find all possible reachable nodes.
        MyDFS<E> dfs = new MyDFS<>();

        Iterator<Node<E>> iterator = dg.iterator();
        // Iterate all nodes
        iterator.forEachRemaining(currentNode -> {
            // RMC: If node has been visited do nothing
            if (visitedNodes.contains(currentNode))
                return;

            // RMC: Find all possible paths
            List<Node<E>> reachableNodes = dfs.dfs(dg, currentNode);
            // RMC: Add traversed nodes to visited list.
            visitedNodes.addAll(reachableNodes);

            // RMC: Creating a hashsset to add all unique nodes as a component.
            Set<Node<E>> componentUnderTest = new HashSet<>(reachableNodes);

            // RMC: Iterating all components to check if any are within the same collection.
            // In that case they are merged, and one or more components are merged as one
            // connected component.
            // If statement check I.e returns false if they have one or more elements in
            // common
            for (Collection<Node<E>> component : allComponents) {
                if (!Collections.disjoint(component, componentUnderTest)) {
                    component.addAll(componentUnderTest);
                    componentUnderTest.clear();
                }
            }

            // RMC: Should there be a case where it's a totally new component with no
            // connection to another component we would just add this to the list
            if (!componentUnderTest.isEmpty()) {
                allComponents.add(componentUnderTest);
            }
        });
        return allComponents;
    }

    /**
     * Helper method to check for null and throw an exception.
     *
     * @param dg a directed graph object of type E
     */
    private void nullChecker(DirectedGraph<E> dg) {
        if (dg == null) {
            throw new NullPointerException();
        }
    }
}
