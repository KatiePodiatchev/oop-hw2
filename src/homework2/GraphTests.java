package homework2;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	// TODO: add additional white box tests
	@Test
	public void testPathFinder() {
		Graph<WeightedNode> graph = new Graph<>();
		WeightedNode node1 = new WeightedNode("node1", 0);

		WeightedNodePath node1Path = new WeightedNodePath(node1);
		List<WeightedNodePath>startNodes = new ArrayList<>();
		List<WeightedNode>endNodes = new ArrayList<>();
		startNodes.add(new WeightedNodePath(node1));
		endNodes.add(node1);
		graph.addNode(node1);
		assertEquals("path from node1 to node1", 
					 node1Path,
			         PathFinder.nodeWeightedShortestPath(graph, startNodes, endNodes));
		
		
		WeightedNode node2 = new WeightedNode("node2", 0);
		WeightedNode node3 = new WeightedNode("node3", 0);
		graph.addNode(node2);
		graph.addNode(node3);
		
		graph.addEdge(node1, node2);
		graph.addEdge(node2, node3);
		graph.addEdge(node3, node2);
		startNodes = new ArrayList<>();
		endNodes = new ArrayList<>();
		startNodes.add(new WeightedNodePath(node1));
		endNodes.add(node3);
		
		WeightedNodePath expectedShortestPath = new WeightedNodePath(node1);
		expectedShortestPath = expectedShortestPath.extend(node2);
		expectedShortestPath = expectedShortestPath.extend(node3);
		
		assertEquals("path from node1 to node3", 
				     expectedShortestPath,
				     PathFinder.nodeWeightedShortestPath(graph, startNodes, endNodes));
		
		startNodes = new ArrayList<>();
		endNodes = new ArrayList<>();
		startNodes.add(new WeightedNodePath(node3));
		endNodes.add(node1);
		assertEquals("no shortest paths", 
					 null,
			     	 PathFinder.nodeWeightedShortestPath(graph, startNodes, endNodes));
	}

}
