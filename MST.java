package apps;

import structures.*;
import java.util.ArrayList;

public class MST {

	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph
	 *            Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		/* COMPLETE THIS METHOD */
		PartialTreeList List = new PartialTreeList();
		for (int i = 0; i < graph.vertices.length; i++) {
			Vertex liluzi = graph.vertices[i];
			PartialTree tree = new PartialTree(liluzi);
			Vertex.Neighbor nb = graph.vertices[i].neighbors;
			MinHeap<PartialTree.Arc> Ptree = tree.getArcs();
			for (nb = nb; nb != null; nb = nb.next) {
				PartialTree.Arc temp = new PartialTree.Arc(graph.vertices[i], nb.vertex, nb.weight);
				Ptree.insert(temp);
				if (nb.vertex == graph.vertices[i]) {
					nb = nb.next;
				}
			}
			List.append(tree);
		}
		return List;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree
	 * list
	 * 
	 * @param ptlist
	 *            Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is
	 *         irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		/* COMPLETE THIS METHOD */
		ArrayList<PartialTree.Arc> result = new ArrayList<PartialTree.Arc>();
		while (ptlist.size() >= 2) {
			PartialTree x = ptlist.remove();
			PartialTree.Arc arc = x.getArcs().deleteMin();
			Vertex uzivert2 = arc.v2;
			while (!x.getArcs().isEmpty()) {
				if (x.getRoot().equals(uzivert2.getRoot())) {
					arc = x.getArcs().deleteMin();
					uzivert2 = arc.v2;
				} else {
					break;
				}
			}
			PartialTree y = ptlist.removeTreeContaining(uzivert2);
			x.merge(y);
			ptlist.append(x);
			result.add(arc);
		}
		return result;
	}
}
