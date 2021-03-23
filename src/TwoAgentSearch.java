import java.util.ArrayList;
import java.util.Collections;

public class TwoAgentSearch extends ExpandNode {
    // This search uses two agents using BFS (Breadth first search) to find the way to the goal. One agent starts search in Start state and the other in the Goal state.
    // When they explore or add to the frontier the node with the same state, their paths can be connected, which leads to obtaining the path from start to the goal.
    
    public static Node startSearch(Map map, Coord start, Coord goal, String searchType) {
        Node currentNodeA1;
        Node currentNodeA2;

        // Agent1
        ArrayList<Node> frontierA1 = new ArrayList<Node>();
        ArrayList<Node> exploredA1 = new ArrayList<Node>();

        // Agent2
        ArrayList<Node> frontierA2 = new ArrayList<Node>();
        ArrayList<Node> exploredA2 = new ArrayList<Node>();

        // frontierStates is used for displaying frontier's states
        ArrayList<Coord> frontierStatesA1 = new ArrayList<Coord>();
        ArrayList<Coord> frontierStatesA2 = new ArrayList<Coord>();



        // Initialising first node (with start state)
        frontierA1.add(new Node(null, start)); //adding initial node
        frontierA2.add(new Node(null, goal)); //adding initial node


        while(true) {

            // Unsuccesful search - when frontier is empty (there no nodes to explore)
            if (frontierA1.toString() == "[]" || frontierA2.toString() == "[]") {
                System.out.println("fail");
                System.out.println(exploredA1.size() + exploredA2.size());
                return null;
            }

            // Printing the fronier of agent 1
            System.out.println("Agent 1: ");
            for (Node f : frontierA1) {
                frontierStatesA1.add(f.getState());
            }
            System.out.println(frontierStatesA1.toString());
            frontierStatesA1.clear();

            // Printing the fronier of agent 2
            System.out.println("Agent 2: ");
            for (Node f : frontierA2) {
                frontierStatesA2.add(f.getState());
            }
            System.out.println(frontierStatesA2.toString());
            frontierStatesA2.clear();

            // Both agents are using Breadth First Search
            // BFS exploring nodes from the beginning of the frontier
            currentNodeA1 = frontierA1.get(0);
            frontierA1.remove(0);
            currentNodeA2 = frontierA2.get(0);
            frontierA2.remove(0);

            // Adds current node to explored nodes
            exploredA1.add(currentNodeA1);
            exploredA2.add(currentNodeA2);

            ArrayList<Node> intersectNodes = nodeIntersect(frontierA1, frontierA2, exploredA1, exploredA2);

            // If there is intersection find it means, it can construct the path
            if ( intersectNodes != null){
                // Nodes used for path cost
                Node curNodeA1 = intersectNodes.get(0);
                Node curNodeA2 = intersectNodes.get(1);

                // Nodes used for eploring the path
                Node pathNodeA1 = intersectNodes.get(0);
                Node pathNodeA2 = intersectNodes.get(1);

                ArrayList<Coord> finalPathA1 = new ArrayList<Coord>();
                finalPathA1.add(pathNodeA1.getState());

                // Retrieving the path from a start to the intersection using node's reference to its
                // parent
                while (pathNodeA1 != null) {
                    if (pathNodeA1.getParentNode() != null) {
                        finalPathA1.add(pathNodeA1.getParentNode().getState());
                    }
                    pathNodeA1 = pathNodeA1.getParentNode();
                }
                Collections.reverse(finalPathA1);

                ArrayList<Coord> finalPathA2 = new ArrayList<Coord>();
                finalPathA2.add(pathNodeA2.getState());
                // Retrieving the path from a start to the intersection using node's reference to its
                // parent
                while (pathNodeA2 != null) {
                    if (pathNodeA2.getParentNode() != null) {
                        finalPathA2.add(pathNodeA2.getParentNode().getState());
                    }
                    pathNodeA2 = pathNodeA2.getParentNode();
                }

                // Final path array merges finalPathA1 and finalPathA2
                ArrayList<Coord> finalPath = new ArrayList<Coord>(finalPathA1);
                // Here it removed the first state from the finalPathA2 to avoid repeating the same node while printing the final path
                finalPathA2.remove(0);
                finalPath.addAll(finalPathA2);

                // Printing the path from a start to the goal
                for (Coord c : finalPath) {
                    System.out.print(c);
                }
                System.out.println();

                // Cost to the goal
                System.out.println(curNodeA1.getPathCost() + curNodeA2.getPathCost());

                // Number of nodes visited
                System.out.println(exploredA1.size() + exploredA2.size());

                return null;
            } else {
                // Explored node is not a goal; therefore, it expands available nodes around the current node
                for (Node x : expand(currentNodeA1, map, frontierA1, exploredA1)) {
                    frontierA1.add(x);
                }
                for (Node x : expand(currentNodeA2, map, frontierA2, exploredA2)) {
                    frontierA2.add(x);
                }
            }
        }
    }

    // This method finds the intersection between agents' explored list and frontier
    private static ArrayList<Node> nodeIntersect(ArrayList<Node> frontierA1, ArrayList<Node> frontierA2, ArrayList<Node> exploredA1, ArrayList<Node> exploredA2) {
        ArrayList<Node> mergedA1 = new ArrayList<>(frontierA1);
        mergedA1.addAll(exploredA1);

        ArrayList<Node> mergedA2 = new ArrayList<>(frontierA2);
        mergedA2.addAll(exploredA2);

        for (Node node1 : mergedA1) {
            for (Node node2 : mergedA2) {
                if (node1.getState().equals(node2.getState())) {
                    ArrayList<Node> finalNodes = new ArrayList<>();
                    finalNodes.add(node1);
                    finalNodes.add(node2);
                    return finalNodes;
                }
            }
        }
        return null;
    }
}
