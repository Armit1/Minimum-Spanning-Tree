package apps;

import structures.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	/*
	 * public static void main(String[] args) throws IOException { Scanner sc =
	 * new Scanner(System.in); System.out.print("Enter file name:"); String file
	 * = sc.nextLine(); Graph graph = new Graph(file); PartialTreeList ptl =
	 * MST.initialize(graph); ArrayList<PartialTree.Arc> a = MST.execute(ptl);
	 * graph.print(); //ptl.remove();
	 * 
	 * }
	 */
	public static void main(String[] args) throws IOException {
		// Graph test1 = new Graph("graph2.txt");
		// PartialTreeList list1 = MST.initialize(test1);
		Graph graph = new Graph("graph1.txt");
		PartialTreeList list = MST.initialize(graph);
		ArrayList<PartialTree.Arc> test = MST.execute(list);
		System.out.print(test);
	}
}