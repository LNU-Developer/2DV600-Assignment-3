# Time Complexity Estimates
## Depth-First Search
```java
    public List<Node<E>> dfs(DirectedGraph<E> graph) {
        nullChecker(graph); // O(1)

        Set<Node<E>> visitedNodes = new LinkedHashSet<>(); // O(1)
        Iterator<Node<E>> heads = graph.heads(); // O(1)
        heads.forEachRemaining(node -> {
            performDFS(node, visitedNodes) // O(C)
        }); // O(N)

        return new ArrayList<Node<E>>(visitedNodes); // O(1)
    }

    private void performDFS(Node<E> currentNode, Set<Node<E>> visitedNodes) {
        if (visitedNodes.contains(currentNode)) // O(1)
            return; // O(1)
        currentNode.num = visitedNodes.size(); // O(1)
        visitedNodes.add(currentNode); // O(1)

        Iterator<Node<E>> successors = currentNode.succsOf(); // O(1)

        successors.forEachRemaining(successor -> performDFS(successor, visitedNodes)); // O(C)
    }
```
- The constants in this algoritm can be removed, as such it is only the O(N)/O(C) to be considered. Total time complexity can be calculated as O(N+C) where the N are the amount of nodes and C are the children/successors of the heads. This is due to the max case of looping through all heads and all it's successors to find all nodes giving us O(N)+O(N+C) or O(2N+C) which is the same as **O(N+C)**.

## Breadth-First Search
```java
    public List<Node<E>> bfs(DirectedGraph<E> graph) {
        nullChecker(graph); // O(1)
        Set<Node<E>> visitedNodes = new LinkedHashSet<>(); // O(1)
        Iterator<Node<E>> heads = graph.heads(); // O(1)
        heads.forEachRemaining(head -> {
            performBFS(head, visitedNodes) // O(C)
        } );  // O(N)
        return new ArrayList<>(visitedNodes); // O(1)
    }

    private void performBFS(Node<E> currentNode, Set<Node<E>> visitedNodes) {
        Queue<Node<E>> queue = new LinkedList<>(); // O(1)
        queue.add(currentNode); // O(1)
        currentNode.num = visitedNodes.size(); // O(1)
        visitedNodes.add(currentNode); // O(1)

        while (!queue.isEmpty()) { // O(N)
            Node<E> processNode = queue.poll(); // O(1)
            Iterator<Node<E>> nodeIterator = processNode.succsOf(); // O(1)

            nodeIterator.forEachRemaining(successor -> { // O(C)
                if (visitedNodes.contains(successor)) // O(1)
                    return;
                successor.num = visitedNodes.size(); // O(1)
                visitedNodes.add(successor); // O(1)
                queue.add(successor); // O(1)
            });
        }
    }
```
- The constants in this algoritm can be removed, as such it is only the O(N)/O(C) to be considered. We can see two loops in these two methods. One loop over heads, and the other looping over the children/successors. Total time complexity can be calculated (in the same way and same motivation in Depth-First Search) as **O(N+C)** where the N are the amount of nodes and C are the children/successors of the heads. Logically this makes sense since BFS is very similar to DFS, differing on in which order nodes are visited.
## Transitive Closure
```java
    public Map<Node<E>, Collection<Node<E>>> computeClosure(DirectedGraph<E> dg) {
        nullChecker(dg); // O(1)
        Map<Node<E>, Collection<Node<E>>> closures = new LinkedHashMap<>(); // O(1)
        MyDFS<E> dfs = new MyDFS<E>(); // O(1)

        Iterator<Node<E>> iterator = dg.iterator(); // O(1)
        iterator.forEachRemaining(currentNode -> { // O(N)
            List<Node<E>> reachableNodes = dfs.dfs(dg, currentNode); // O(N + C) (as calculated above)
            closures.put(currentNode, reachableNodes); // O(1)
        });

        return closures; // O(1)
    }
```
- The constants in this algoritm can be removed, as such it is only the O(N)/O(C) to be considered. We can see two loops in these two methods, one loop over heads O(N) and the other looping over heads and children/successors O(N+C) as calculated above for BFS. As such the total time complexity can be calculated as **O(N(N+C)).**
## Connected Components
```java
    public Collection<Collection<Node<E>>> computeComponents(DirectedGraph<E> dg) {
        nullChecker(dg); // O(1)
        Set<Collection<Node<E>>> allComponents = new LinkedHashSet<>(); // O(1)
        Set<Node<E>> visitedNodes = new LinkedHashSet<>(); // O(1)
        MyDFS<E> dfs = new MyDFS<>(); // O(1)

        Iterator<Node<E>> iterator = dg.iterator(); // O(1)
        iterator.forEachRemaining(currentNode -> { // O(N)
            if (visitedNodes.contains(currentNode)) // O(1)
                return;

            List<Node<E>> reachableNodes = dfs.dfs(dg, currentNode); //O(N+S) (as calculated above, renaming to S since we use C below)
            visitedNodes.addAll(reachableNodes); //Worst-case scenario it might be O(N) since it iterates over componentUnderTest to add

            Set<Node<E>> componentUnderTest = new HashSet<>(reachableNodes); // O(1)

            for (Collection<Node<E>> component : allComponents) { // O(C)
                if (!Collections.disjoint(component, componentUnderTest)) { // O(C)
                    component.addAll(componentUnderTest); // O(C) as per above reasoning.
                    componentUnderTest.clear(); // O(C) since it iterates through the list and sets each value to null/garbage collects each.
                }
            }

            if (!componentUnderTest.isEmpty()) { // O(1)
                allComponents.add(componentUnderTest); // O(1)
            }
        });
        return allComponents;
    }
```
- The constants in this algoritm can be removed, as such it is only the O(N)/O(C) to be considered. My algoritm Loops through each head and performs DFS on them as the above case. Meaning we are in the same situation as the TC complexity, O(N(N+S)), furthermore we are add all nodes to another list (O(N((N+S)+N)), lastly, we iterate through all components C not once, but four times, giving us C(C(C(C))) or C^4. Total time complexity can be calculated as O(N((N+S)+N+C^4)) or O(N^2+NS+N^2+NC^4) or  O(2N^2+NS+NC^4) and in the end **O(N^2+NS+NC^4)**