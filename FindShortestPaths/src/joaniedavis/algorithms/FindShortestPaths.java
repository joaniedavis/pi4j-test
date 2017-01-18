package joaniedavis.algorithms;

import cusack.hcg.model.PuzzleInstance;

import java.util.ArrayList;
import java.util.List;

import cusack.hcg.graph.GraphWithData;
import cusack.hcg.graph.Vertex;
import cusack.hcg.graph.algorithm.AbstractAlgorithm;
import cusack.hcg.model.PuzzleInstance;

/**
 * Insert comment here about what your algorithm does.
 * 
 * @author Insert your name here.
 */
public class FindShortestPaths extends AbstractAlgorithm<PuzzleInstance> {
	// You are encouraged to delete all of the non-Javadoc comments when
	// you have learned what you need from them.

	/*
	 * The superclass (AbstractAlgorithm) has several fields that you may use as
	 * needed. Each is listed below with a few comments. DO NOT UNCOMMENT THE
	 * CODE RELATED TO THESE FIELDS! The code is given so you know how they are
	 * declared in the superclass. If you uncomment the code you will most
	 * certainly break your algorithm.
	 */

	// protected PuzzleInstance puzzle;
	//
	// The field puzzle contains all of the data related to a graph problem that
	// you will need for your algorithm. When Algoraph runs algorithms, puzzle
	// is automatically set for you.
	//
	// The actual data for a puzzle is stored in a GraphWithData object in the
	// puzzle object. However, delegate methods are provided in puzzle for
	// many of the methods you may want to use. Alternatively you can get
	// direct access to the GraphWithData object using puzzle.getGraph().
	// You can then use the methods getData and setData to access/modify
	// data associated with vertices.
	//
	// The GraphWithData object stores the structure of the graph in a field
	// of type Graph and the data in maps--one that maps Vertex to VertexData
	// and one that maps Edge to EdgeData. You can get the Graph object by
	// calling getGraph on the GraphWithData object, or by calling
	// getGraph().getGraph() from the puzzle.
	//
	// It should be noted that your algorithm does not have to operate on the
	// puzzle class. You can take the data from that class and store it however
	// you wish. In particular, you may want to store the graph using
	// EfficientListGraph or EfficientMatrixGraph. You may also want to store
	// the data in arrays or ArrayLists instead of in Maps.
	//
	// See the Javadoc comments for PuzzleInstance for a complete list of
	// methods that you may want to use.

	// protected int numberOfOperations = -1;
	//
	// If you override countsOperations() to return true, you are expected to
	// keep track of the number of operations your algorithm uses by modifying
	// the value of numberOfOperations. Unless otherwise specified, you may
	// interpret "number of operations" as appropriate for your problem.

	public FindShortestPaths() {
		// You must have a default constructor in this class, even if it does
		// nothing. In fact, it probably SHOULD do nothing. The puzzle isn't
		// set yet, so you don't know what to do with any data.
	}

	/**
	 * This method is called first, and allows you to initialize any extra data
	 * that you may need in your algorithm.
	 */
	String result;
	GraphWithData g;
	@Override
	public void initializeData() {
		// Here is where you should initialize any data, including copying the
		// graph/data if you plan on using an alternative representation.
		g = puzzle.getGraph();
		result = "";
	}

	/**
	 * Runs the Algorithm. This is the most important method.
	 */
	@Override
	public void runAlgorithm() {
		//get all the vertices
		ArrayList<Vertex> vertices = g.getVertices();
		
		//new array, initialized to 0, this will store the accumulating 
		//path lengths as the algorithm executes.  All values except
		//the first vertex's corresponding index are initialized to -1
		int[] visited = new int[vertices.size()];
		for(int i = 1; i < visited.length; i++){
			visited[i] = -1;
		}
		
		for(int i = 0; i < vertices.size(); i++) {
			Vertex v = vertices.get(i);
			if(visited[vertices.indexOf(v)] != -1){
				List<Vertex> adjacency = v.getAdjacencyList();
				for(Vertex tempv: adjacency){
					if((visited[vertices.indexOf(tempv)] == -1) || (visited[vertices.indexOf(v)] + 1 < visited[vertices.indexOf(tempv)])){
						visited[vertices.indexOf(tempv)] = visited[vertices.indexOf(v)] + 1;		
					}
				}
			}
		}
		
		//format the result string
		result = "0 ";
		for(int i = 1; i < vertices.size(); i++) {
			String tempstring = visited[i] + " ";
			result = result.concat(tempstring);
		}
		
	}

	/**
	 * HTML text that will be displayed in the Algorithm Runner that explains
	 * what the algorithm is doing. Think of the status as a progress report.
	 * 
	 * @return
	 */
	@Override
	public String getProgressReport() {
		// Make sure you change this one so that it returns something
		// meaningful.
		return null;
	}

	/**
	 * Return the result of the algorithm in the context of the problem. i.e.
	 * Solvable, Unsolvable, Unknown. This tells more than "Done" or "Not_Done"
	 * that is given in the more general sense
	 * 
	 * @return the result
	 */
	@Override
	public String getResult() {
		return result;
	}

	/**
	 * @return A string representation of the current state of the problem data.
	 *         This should be in the same format as it was passed in to
	 *         setProblemData, only updated to give the current state based on
	 *         the algorithm. The idea is that this can be used to show the
	 *         progress of the algorithm by some GUI (or in text form, I
	 *         suppose).
	 */
	@Override
	public String getCurrentProblemData() {
		// You must return a string in the exact same format as the method
		// currentPuzzleDataToString in PuzzleInstance uses.
		// If you are using the puzzle field, you can probably just do:
		// return puzzle.currentPuzzleDataToString();
		return null;
	}

	/**
	 * @return True if and only if the algorithm intends to keep track of
	 *         operations.
	 */
	@Override
	public boolean countsOperations() {
		// Leave this alone if you don't intend to count operations.
		// Change it to "return true" if you do, and then make sure
		// you actually do count operations.
		// Recall that AbstractAlgorithm (the superclass of this class)
		// has field numberOfOperations that you can update as needed.
		return false;
	}

	/**
	 * Mostly for use in comparing results from previous versions of algorithms,
	 * and/or so we can know whether or not data in the database is based on an
	 * older version of the algorithm. Start with "return 1.0". Go up by
	 * whatever increments you like (e.g. 1.1, 1.2, etc. or 1, 2, 3, etc.)
	 * 
	 * @return
	 */
	@Override
	public double getVersion() {
		return 1;
	}

	/**
	 * This should return the class object representing the subclass of
	 * PuzzleInstance that this algorithm runs on. If you change what subclass
	 * of PuzzleInstance your algorithm is applicable for, those changes must be
	 * reflected in the return type of this method, the Class object that is
	 * returned, and the parameter in the class signature.
	 * 
	 * @return The class object representing the subclass of PuzzleInstance that
	 *         this algorithm runs on.
	 */
	@Override
	public Class<PuzzleInstance> getProblemType() {
		return PuzzleInstance.class;
	}
	
	/**
	 * (Hopefully) gracefully stop the computation.
	 */
	@Override
	public void quit() {
	}
}