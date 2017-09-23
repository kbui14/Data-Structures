package project6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.Consumer;

public class Bui {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random(20);


		//Read n_vert and n_edge from user
		System.out.print("Input number of vertices: ");
		int numVert = sc.nextInt();
		sc.nextLine();

		System.out.print("Input number of edges: ");
		int numEdge = sc.nextInt();
		sc.nextLine();

		sc.close();


		Graph<Double> G1 = new Graph<Double>(numVert);
		DiGraph<Double> G2 = new DiGraph<Double>(numVert);

		//add random vertices to G1
		for (int i = 0; i < numVert; i++) {
			Double d = new Double(i);
			G1.vertex.add(d);
			G2.vertex.add(d);
		}

		//add random edges to G1
		for (int i = 0; i < numEdge; i++) {
			int v1 = rd.nextInt(numVert);
			int v2 = rd.nextInt(numVert);
			G1.addEdge(v1, v2); // must call method
			G2.addEdge(v1, v2);
		}

		System.out.println("Graph:\n" + G1);
		System.out.println("DiGraph:\n" + G2);

		System.out.println("Graph BFS");
		G1.BFS(0, vert -> System.out.print(vert + ", "));
		System.out.println();
		sum = 0;
		G1.BFS(0, vert -> {accum(vert); System.out.print(sum + ", ");});
		System.out.println();

		System.out.println("DiGraph BFS");
		G2.BFS(0, vert -> System.out.print(vert + ", "));
		System.out.println();
		sum = 0;
		G2.BFS(0, vert -> {accum(vert); System.out.print(sum + ", ");});
		System.out.println();

		System.out.println("Graph DFS");
		G1.DFS(0, vert -> System.out.print(vert + ", "));
		System.out.println();
		sum = 0;
		G1.DFS(0, vert -> {accum(vert); System.out.print(sum + ", ");});
		System.out.println();

		System.out.println("DiGraph DFS");
		G2.DFS(0, vert -> System.out.print(vert + ", "));
		System.out.println();
		sum = 0;
		G2.DFS(0, vert -> {accum(vert); System.out.print(sum + ", ");});
		System.out.println();

		System.out.println("Graph RecursiveDFS");
		G1.RecursiveVisitedSet = new boolean[numVert];
		G1.RecursiveDFS(0, vert -> System.out.print(vert + ", "));
		System.out.println();
		sum = 0;
		G1.RecursiveVisitedSet = new boolean[numVert];
		G1.RecursiveDFS(0, vert -> {accum(vert); System.out.print(sum + ", ");});
		System.out.println();

		System.out.println("DiGraph RecursiveDFS");
		G2.RecursiveVisitedSet = new boolean[numVert];
		G2.RecursiveDFS(0, vert -> System.out.print(vert + ", "));
		System.out.println();
		sum = 0;
		G2.RecursiveVisitedSet = new boolean[numVert];
		G2.RecursiveDFS(0, vert -> {accum(vert); System.out.print(sum + ", ");});
		System.out.println();


		System.out.println("DiGraph BFS Path Find");
		int start = 0;
		int[] parents = G2.BFSFrom(start);
		for (int end = 0; end < numVert; end++) {			
			System.out.print(start + " -> " + end + ": ");
			int[] path = G2.BFSTo(parents, end);
			if (path != null) {
				for (int e : path) {
					System.out.print(e);
					if (e != end)
						System.out.print(" -> ");
					else
						System.out.println();
				}
			} else
				System.out.println("No Path");			
		}
		System.out.println();

	}

	static double sum;
	public static void accum(double i) {
		sum += i;
	}


	public static class Graph<T>{

		ArrayList<T> vertex;
		protected ArrayList<TreeSet<Integer>> adjList;

		Graph(int numVert){
			vertex = new ArrayList<T>(numVert);
			adjList = new ArrayList<TreeSet<Integer>>(numVert);
			for (int i = 0; i < numVert; i++) {
				adjList.add(new TreeSet<Integer>());
			}
		} // closing Graph constructor

		boolean addEdge(int v1, int v2){

			if(v1 < 0 || v1 >= adjList.size() ||
					v2 < 0 || v2 >= adjList.size() ||
					v1 == v2)
				return false;
			adjList.get(v1).add(v2);
			adjList.get(v2).add(v1);
			return true;
		} // closing addEdge method

		public String toString(){

			String temp = "";
			for(int i=0; i< vertex.size(); i++){
				temp += (i + ", " + vertex.get(i) + ": ");
				for(int adjV: adjList.get(i)){
					temp += adjV +", ";
				}
				//temp += ", " + adjList.get(i).last();
				temp += "\n";
			}
			return temp;
		} // closing toString method


		void BFS(int startV, Consumer<T> action) {  

			LinkedList<Integer> queue = new LinkedList<Integer>();
			//Stack<Integer> stack = new Stack<Integer>();
			boolean[] discoveredSet = new boolean[adjList.size()];
			//boolean[] visitedSet = new boolean[adjList.size()];


			queue.add(startV);
			discoveredSet[startV] = true;

			while ( !queue.isEmpty()){
				int currentV = queue.remove();
				action.accept(vertex.get(currentV));
				for (int adjV:adjList.get(currentV)){
					if( !discoveredSet[adjV]){
						queue.add(adjV);
						discoveredSet[adjV] = true;
					}
				}
			}
		} // closing BFS method

		void DFS(int startV, Consumer<T> action){

			//LinkedList<Integer> queue = new LinkedList<Integer>();
			Stack<Integer> stack = new Stack<Integer>();
			//boolean[] discoveredSet = new boolean[adjList.size()];
			boolean[] visitedSet = new boolean[adjList.size()];


			stack.push(startV);
			while (!stack.isEmpty()){
				int currentV = stack.pop();
				if(visitedSet[currentV] == false ){
					action.accept(vertex.get(currentV));
					visitedSet[currentV] = true;
					for(int adjV: adjList.get(currentV))
						stack.push(adjV);
				}
			}
		} // closing DFS method

		boolean[] RecursiveVisitedSet;

		void RecursiveDFS(int currentV, Consumer<T> action){

			//RecursiveVisitedSet = new boolean[adjList.size()];

			if (RecursiveVisitedSet[currentV] == false){
				RecursiveVisitedSet[currentV] = true;
				action.accept(vertex.get(currentV));
				for(int adjV: adjList.get(currentV))
					RecursiveDFS(adjV, action);
			}
		} // closing RecursiveVisitedSet method

		int[] BFSFrom (int startV){

			LinkedList<Integer> queue = new LinkedList<Integer>();
			//Stack<Integer> stack = new Stack<Integer>();
			boolean[] discoveredSet = new boolean[adjList.size()];
			//boolean[] visitedSet = new boolean[adjList.size()];
			int[] parents; 

			queue.add(startV);
			discoveredSet[startV] = true;
			parents = new int[vertex.size()];
			for(int i=0; i<parents.length;i++)
				parents[i] = -1;

			while(!queue.isEmpty()){
				int currentV = queue.remove();
				for (int adjV: adjList.get(currentV)){
					if(discoveredSet[adjV] == false){
						queue.push(adjV);
						discoveredSet[adjV] = true;
						parents[adjV] = currentV;
					}
				}
			}
			return parents;
		} // closing BFSFrom method

		int[] BFSTo (int[] parents, int endV){

			if (parents == null)
				return null;
			if (parents[endV] == -1)
				return null;

			int i = endV;
			int len = 0;
			while (i >= 0 && len < parents.length){
				i = parents[i];
				len++;
			}
			int[] path = new int[len];

			int j = len - 1;
			int k = endV;
			while ( j >= 0 ){
				path[j] = k;
				k = parents[k];
				j--;
			}
			return path;
		}

	} // closing Graph Class

	public static class DiGraph <T> extends Graph<T>{

		DiGraph(int numVert) {
			super(numVert);
		} // closing constructor

		boolean addEdge(int v1, int v2){
			
			if(v1 < 0 || v1 >= adjList.size() ||
					v2 < 0 || v2 >= adjList.size())
				return false;
			adjList.get(v1).add(v2);
			return true;
		} // closing addEdge override method
	} // closing DiGraph class



} // closing Bui Class
