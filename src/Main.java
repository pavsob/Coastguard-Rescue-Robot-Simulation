/*
 * Main class for running search algorithms
 * 
 * Run with: java A1main <Algo> <ConfID>
 * Retrieves input and configuration and run search algorithm
 * Configuration represents particular map with obstacles (robot's environment)
 * Example: java A1main BFS TCONF00
 * 
 */


public class Main {

	public static void main(String[] args) {

		Conf conf = Conf.valueOf(args[1]);
		System.out.println("Configuration:"+args[1]);
		System.out.println("Map:");
		printMap(conf.getMap());
		System.out.println("Start - Person cell (r_p,c_p): "+conf.getP());
		System.out.println("Goal - Safety cell (r_s,c_s): "+conf.getS());
		System.out.println("Search algorithm:"+args[0]);

		//run your search algorithm 
		runSearch(args[0],conf.getMap(),conf.getP(),conf.getS());
	}

	private static void runSearch(String algo, Map map, Coord start, Coord goal) {
		switch(algo) {
		case "BFS": //run BFS
			GeneralSearch.startSearch(map, start, goal, "BFS");
			break;
		case "DFS": //run DFS
			GeneralSearch.startSearch(map, start, goal, "DFS");
			break;
		case "BestF": //run BestF
			InformedSearch.startSearch(map, start, goal, "BestF");
			break;
		case "AStar": //run AStar
			InformedSearch.startSearch(map, start, goal, "AStar");
			break;
		case "Euclid": //run Euclidian heuristic search
			InformedSearch.startSearch(map, start, goal, "Euclid");
			break;

		case "2Agent": //run Two Agent Search
			TwoAgentSearch.startSearch(map, start, goal, "2Agent");
			break;
		}

	}


	private static void printMap(Map m) {
		int[][] map=m.getMap();
		System.out.println();
		int rows=map.length;
		int columns=map[0].length;
		// first line
		for (int r = 0; r < rows + 5; r++) {
			System.out.print(" ");// shift to start 
		}
		for (int c = 0; c < columns; c++) {
			System.out.print(c); //index
			if (c < 10) {
				System.out.print(" ");
			}
		}
		System.out.println();
		// second line
		for (int r = 0; r < rows + 3; r++) {
			System.out.print(" ");
		}
		for (int c = 0; c < columns; c++) {
			System.out.print(" -");// separator
		}
		System.out.println();
		// the map
		for (int r = 0; r < rows; r++) {
			for (int d = r; d < rows - 1; d++) {
				System.out.print(" ");// shift to position
			}
			if (r < 10) {
				System.out.print(" ");
			}
			System.out.print(r + "/ ");// index+separator
			for (int c = 0; c < columns; c++) {
				System.out.print(map[r][c] + " ");// value in the map
			}
			System.out.println();
		}
		System.out.println();
	}
}
