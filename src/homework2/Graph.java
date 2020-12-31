package homework2;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A Graph<N> characterizes a graph of nodes of type N.
 * each node in the graph may have child nodes which are also contained in the graph.
 */
public class Graph<N> {
	
	// RepInvariant:
	//   nodeToChildMap(parent) contains child ==> nodeToChildMap keys contain child.
	//   nodeToChildMap does not contain any null keys or values.


  	// Abstraction Function:
  	//   The set of node which are contained in the graph is represented by the set of 
	//   keys in nodeToChildMap. The children of a node are represented by the Set that
	//   nodeToChildMap maps the node too. 


	// mapping a node to it's child nodes in the graph.
	private HashMap<N, Set<N>> nodeToChildMap = new HashMap<>();
	

	/**
     * Adds a node to the graph.
     * @requires n != null.
     * @effects adds node to the graph. If node is already in the graph, 
     *          this method has no effect.
     * @modifies this.
     */
	public void addNode(N node) {
		assert(checkRep());
		if (!nodeToChildMap.containsKey(node)) {
			nodeToChildMap.put(node, new HashSet<N>());
		}
		assert(checkRep());
	}
	

	/**
     * Adds an edge to the graph.
     * @requires parent != null && child != null && parent and child are in the graph.
     * @effects adds an edge from parent to child in the graph.
     * @modifies this.
     */
	public void addEdge(N parent, N child) {
		assert(checkRep());
		if(!nodeToChildMap.containsKey(parent) || !nodeToChildMap.containsKey(child)) {
			checkRep();
			throw new IllegalArgumentException();
		}
		nodeToChildMap.get(parent).add(child);
		assert(checkRep());
	}
	
	
	/**
     * Returns the nodes of the graph.
     * @effects returns a set that contains all the nodes of the graph.
     */
	public Set<N> getNodes() {
		// need to return a copy of the set. representation exposure.
		assert(checkRep());
		Set<N> nodes = Collections.unmodifiableSet(nodeToChildMap.keySet());
		assert(checkRep());
		return nodes;
	}
	
	
	/**
     * Returns the children of parent in the graph.
     * @effects returns a set of the children of parent in the graph.
     * @requires parent != null && parent is in the graph.
     */
	public Set<N> getChildren(N parent) {
		assert(checkRep());
		if (!nodeToChildMap.containsKey(parent)) {
			assert(checkRep());
			throw new IllegalArgumentException();
		}
		Set<N> children = Collections.unmodifiableSet(nodeToChildMap.get(parent));
		assert(checkRep());
		return children;
	}
	
	
  	/**
  	 * @effects checks the representation invariant.
  	 */
  	private boolean checkRep() {
  		if(nodeToChildMap.containsKey(null) || nodeToChildMap.containsValue(null)) {
  			return false;
  		}
  		Iterator<Set<N>> mapIterator = nodeToChildMap.values().iterator();
  		while(mapIterator.hasNext()) {
  			Iterator<N> childIterator = mapIterator.next().iterator();
  			while(childIterator.hasNext()) {
  				if (!nodeToChildMap.containsKey(childIterator.next())) {
  					return false;
  				}
  			}
  		}
  		return true;
  	}
		
}
