package homework2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A class which provides a method for calculating the shortest path in a node
 * weighted graph. 
 */
public class PathFinder {
    
    /**
     * Returns a shortest path from any element of starts to any
	 * element of goals in a node-weighted graph.
     * @return the shortest path from an element in the input start nodes to the
     * 		   input end nodes. null in case there is no path from any child node
     * 		   to any end nodes.
     * @requires each path in startPaths contains exactly one node which represents
     *           a node in starts. All the nodes in startPaths and endNodes are in
     *           the graph. 
     */
	public static <N, P extends Path<N,P>> P nodeWeightedShortestPath(
			Graph<N> graph,
			List<P> startPaths,
			List<N> endNodes) {
		// maps nodes -> paths
		Map<N,P> paths = new HashMap<N, P>();
		for (P startPath : startPaths) {
			N node = startPath.getEnd();
			paths.put(node, startPath);
		}
		// The priority queue contains nodes with priority equal to the cost
		// of the shortest path to reach that node. Initially it contains the
		// start nodes
		PriorityQueue<P> active = new PriorityQueue<P>();
		for (P startPath : startPaths) {
			active.add(startPath);
		}
		
		// The set of finished nodes are those for which we know the shortest
		// paths from starts and whose children we have already examined
		Set<N> finished = new HashSet<N>();
		
		while(active.size() > 0) {
			// queueMin is the element of active with shortest path
			N queueMin = active.poll().getEnd();
			P queueMinPath = paths.get(queueMin);
			
			if (endNodes.contains(queueMin)) {
				return queueMinPath;
			}
			// iterate over edges (queueMin, c) in queueMin.edges
			for (N child : graph.getChildren(queueMin)) {
			    P shortestKnownPathToCild = paths.get(child);
				if (!finished.contains(child) && !active.contains(shortestKnownPathToCild)) {
					P shortestPathToChild = queueMinPath.extend(child);
					paths.put(child, shortestPathToChild);
					active.add(shortestPathToChild);
				}
			}

			finished.add(queueMin);
		}

		// execution reaches this point only if active becomes empty
		return null;
	}
	
}
