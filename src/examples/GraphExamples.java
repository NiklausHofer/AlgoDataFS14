package examples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class GraphExamples<V,E> {

	final private Object NUMBER = new Object();
	final private Object VISITED = new Object();
	
	private Graph<V,E> g;
	public GraphExamples(Graph<V,E> g){
		this.g=g;
	}

	public void setNumberAttribute(){
		Iterator<Vertex<V>> it = g.vertices();
		int i=0;
		while(it.hasNext()){
			Vertex v = it.next();
			v.set(NUMBER, i++);
		}
	}
	
	public boolean isConnected(){
		// recursively visit all vertices which
		// we can reach from a arbitrary vertex
		depthFirstSearch(g.aVertex());
		int n=0;
		Iterator<Vertex<V>> it = g.vertices();
		while (it.hasNext()){
			Vertex<V>  u = it.next();
			if (u.has(VISITED)) {
				u.destroy(VISITED);
				n++;
			}
		}
		return n==g.numberOfVertices();
	}

	void depthFirstSearch(Vertex<V> v){
		// recursive depth first search  
		v.set(VISITED,true);
		Iterator<Edge<E>> eIt = g.incidentEdges(v);
		while (eIt.hasNext()){
			Edge<E> e = eIt.next();
			Vertex<V> w = g.opposite(e, v);
			if ( ! w.has(VISITED)) depthFirstSearch(w);
		}
	}
	
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// make an undirected graph
		IncidenceListGraph<String,String> g = 
			new IncidenceListGraph<>(false);
		GraphExamples<String,String> ge = new GraphExamples<>(g);
		// add an example graph:
		//
		// A-->B-->C--->D
		// |\  |   |  /|
		// | \ |   | / |
		// v  vv   vv  v
		// E-->F   G-->H
		// |  /   /|\  |
		// | /   / | \ |
		// vv   v  v  vv
		// I-->J-->K   L
        // |\     /|   |
		// | \   / |   |
		// v  v v  v   V
		// M-->N-->O-->P
		// vertices
		Vertex<String> vA = g.insertVertex("A");
		Vertex<String> vB = g.insertVertex("B");
		Vertex<String> vC = g.insertVertex("C");
		Vertex<String> vD = g.insertVertex("D");
		Vertex<String> vE = g.insertVertex("E");
		Vertex<String> vF = g.insertVertex("F");
		Vertex<String> vG = g.insertVertex("G");
		Vertex<String> vH = g.insertVertex("H");
		Vertex<String> vI = g.insertVertex("I");
		Vertex<String> vJ = g.insertVertex("J");
		Vertex<String> vK = g.insertVertex("K");
		Vertex<String> vL = g.insertVertex("L");
		Vertex<String> vM = g.insertVertex("M");
		Vertex<String> vN = g.insertVertex("N");
		Vertex<String> vO = g.insertVertex("O");
		Vertex<String> vP = g.insertVertex("P");
		// edges:
		Edge<String> eAB = g.insertEdge(vA, vB, "AB"); 
		Edge<String> eBC = g.insertEdge(vB, vC, "BC"); 
		Edge<String> eCD = g.insertEdge(vC, vD, "CD"); 
		Edge<String> eAE = g.insertEdge(vA, vE, "AE"); 
		Edge<String> eAF = g.insertEdge(vA, vF, "AF"); 
		Edge<String> eBF = g.insertEdge(vB, vF, "BF"); 
		Edge<String> eCG = g.insertEdge(vC, vG, "CG"); 
		Edge<String> eDG = g.insertEdge(vD, vG, "DG"); 
		Edge<String> eDH = g.insertEdge(vD, vH, "DH"); 
		Edge<String> eEF = g.insertEdge(vE, vF, "EF"); 
		Edge<String> eGH = g.insertEdge(vG, vH, "GH"); 
		Edge<String> eEI = g.insertEdge(vE, vI, "EI"); 
		Edge<String> eFI = g.insertEdge(vF, vI, "FI"); 
		Edge<String> eGJ = g.insertEdge(vG, vJ, "GJ");
		Edge<String> eGK = g.insertEdge(vG, vK, "GK"); 
		Edge<String> eGL = g.insertEdge(vG, vL, "GL"); 
		Edge<String> eHL = g.insertEdge(vH, vL, "HL"); 
		Edge<String> eIJ = g.insertEdge(vI, vJ, "IJ"); 
		Edge<String> eJK = g.insertEdge(vJ, vK, "JK"); 
		Edge<String> eIM = g.insertEdge(vI, vM, "IM"); 
		Edge<String> eIN = g.insertEdge(vI, vN, "IN"); 
		Edge<String> eKN = g.insertEdge(vK, vN, "KN"); 
		Edge<String> eKO = g.insertEdge(vK, vO, "KO"); 
		Edge<String> eLP = g.insertEdge(vL, vP, "LP"); 
		Edge<String> eMN = g.insertEdge(vM, vN, "MN"); 
		Edge<String> eNO = g.insertEdge(vN, vO, "NO"); 
		Edge<String> eOP = g.insertEdge(vO, vP, "OP"); 	
//		System.out.println(g);
//		ge.setNumberAttribute();
		System.out.println(ge.isConnected());
	}
}

