package homework2;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

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


	@Test
	public void Graph(){
			System.out.print("Testing graph creation: ");
		   try{new Graph<>();
			   System.out.print("Succeeded\n");}
		   catch (Throwable T){
			   System.out.print("Failed\n");
			   System.out.print("fail to create graph:\n");
		   }
		   
		}

		@Test
		public void addNode(){
			System.out.print("Testing addNode:\n");
			Graph<WeightedNode> graph = new Graph<>(); 
			WeightedNode node1 = new WeightedNode("n1",1);
			WeightedNode node2 = new WeightedNode("n1",0);
			WeightedNode node3 = new WeightedNode("n2",2);
			WeightedNode node4 = new WeightedNode("",1);
			
			//Success expected
			try{graph.addNode(node1);
				System.out.print("Succeeded ");}
			catch (Throwable T){
				System.out.print("Failed");
			}
			try{graph.addNode(node2);
				System.out.print("Failed");}
			catch (Throwable T){
				System.out.print("Succeeded ");
			}
			try{graph.addNode(node3);
				System.out.print("Succeeded ");}
			catch (Throwable T){
				System.out.print("Failed");
			}
			try{graph.addNode(node4);
				System.out.print("Succeeded ");}
			catch (Throwable T){
				System.out.print("Failed");
			}
			
			//Failure expected
			try{graph.addNode(node1);
				System.out.print("Failed");}
			catch (Throwable T){
				System.out.print("Succeeded ");
			}
			try	{graph.addNode(null);
				System.out.print("Failed");}
			catch (Throwable T){
				System.out.print("Succeeded ");
			}

			System.out.print("\n");

		}



	@Test
	public void addEdge() {

			System.out.print("Testing addEdge:\n");

		Graph<WeightedNode> graph = new Graph<>();
		WeightedNode node1 = new WeightedNode("n1",1);
		WeightedNode node2 = new WeightedNode("n2",0);
		WeightedNode node3 = new WeightedNode("n3",2);
		WeightedNode node4 = new WeightedNode("",1);
		graph.addNode(node1);
		graph.addNode(node2);
		graph.addNode(node3);

		//Success expected
		try	{graph.addEdge(node1,node2);
			System.out.print("Succeeded ");}
		catch (Throwable T){
			System.out.print("Failed");
		}
		try	{graph.addEdge(node1,node1);
			System.out.print("Succeeded ");}
		catch (Throwable T){
			System.out.print("Failed");
		}

		try	{graph.addEdge(node2,node1);
			System.out.print("Succeeded ");}
		catch (Throwable T){
			System.out.print("Failed");
		}
		try	{graph.addEdge(node3,node1);
			System.out.print("Succeeded ");}
		catch (Throwable T){
			System.out.print("Failed");
		}

		//Failure expected
		try	{graph.addEdge(null,node1);
			System.out.print("Failed");}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}
		try	{graph.addEdge(node4,node1);
			System.out.print("Failed");}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}
		try	{graph.addEdge(node1,node4);
			System.out.print("Failed");}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}

		System.out.print("\n");

	}

	@Test
	public void getNodes() {
		System.out.print("Testing getNodes:\n");
		Graph<WeightedNode> graph1 = new Graph<>(); 
		Graph<WeightedNode> graph2 = new Graph<>();
		WeightedNode node1 = new WeightedNode("n1",1);
		WeightedNode node2 = new WeightedNode("n1",0);
		WeightedNode node3 = new WeightedNode("n2",2);
		graph1.addNode(node1);
		graph1.addNode(node2);
		graph1.addNode(node3);
		
		//Failure expected - adding "null" node
		try	{graph1.addNode(null);
			System.out.print("Failed");}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}
		try
		{
			LinkedList<WeightedNode> nodeList = new LinkedList<WeightedNode>();
			nodeList.addAll(graph1.getNodes());
			LinkedList <WeightedNode> tmpList = new LinkedList<>();
			tmpList.add(node1);
			tmpList.add(node2);
			tmpList.add(node3);
			
			assertEquals("Expected Nodes: Succeeded ", 
					 nodeList,
			     	 tmpList);
		}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}
		try
		{
			LinkedList<WeightedNode> nodeList = new LinkedList<WeightedNode>();
			nodeList.addAll(graph2.getNodes());
			LinkedList <WeightedNode> L = new LinkedList<>();

			if(nodeList.equals(L))System.out.print("Succeeded ");
			else System.out.print("Failed");
		}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}
		graph2.addNode(node2);
		try
		{
			LinkedList<WeightedNode> nodeList = new LinkedList<WeightedNode>();
			nodeList.addAll(graph2.getNodes());
			LinkedList <WeightedNode> L = new LinkedList<>();
			L.add(node2);
			if(nodeList.equals(L))System.out.print("Succeeded ");
			else System.out.print("Failed");
		}
		catch (Throwable T){
			System.out.print("Failed");
		}
		System.out.print("\n");
	}


	@Test
	public void getChildren() {
		System.out.print("Testing getChildren:\n");
		Graph<WeightedNode> gr = new Graph<>();
		WeightedNode node1 = new WeightedNode("n1",1);
		WeightedNode node2 = new WeightedNode("n1",0);
		WeightedNode node3 = new WeightedNode("n2",2);
		WeightedNode node4 = new WeightedNode("",1);
		gr.addNode(node1);
		gr.addNode(node2);
		gr.addNode(node3);


		gr.addEdge(node1,node2);
		gr.addEdge(node1,node1);
		gr.addEdge(node2,node1);

		try
		{
			LinkedList<WeightedNode> nodeList = new LinkedList<WeightedNode>();
			nodeList.addAll(gr.getChildren(node1));
			LinkedList <WeightedNode> tmpList = new LinkedList<>();
			tmpList.add(node2);
			tmpList.add(node1);
			
			assertEquals("Expected children: Succeeded ", 
					 nodeList,
			     	 tmpList);
		}
		catch (Throwable T){
			System.out.print("Failed");
		}

		try
		{
			LinkedList<WeightedNode> nodeList = new LinkedList<WeightedNode>();
			nodeList.addAll(gr.getChildren(node2));
			LinkedList <WeightedNode> tmpList = new LinkedList<>();
			tmpList.add(node1);
			
			assertEquals("Expected children: Succeeded ", 
					 nodeList,
			     	 tmpList);
		}
		catch (Throwable T){
			System.out.print("Failed");
		}
		
		try
		{
			LinkedList<WeightedNode> nodeList = new LinkedList<WeightedNode>();
			nodeList.addAll(gr.getChildren(node3));
			LinkedList <WeightedNode> tmpList = new LinkedList<>();
			
			assertEquals("Expected children: Succeeded ", 
					 nodeList,
			     	 tmpList);
		}
		catch (Throwable T){
			System.out.print("Failed");
		}

		//fail
		try{
			gr.getChildren(node4);
			System.out.print("Failed");
		}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}
		try{gr.getChildren(node1).add(node3);
			System.out.print("Failed");
		}
		catch (Throwable T){
			System.out.print("Succeeded ");
		}
		System.out.print("\n");

	}

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
