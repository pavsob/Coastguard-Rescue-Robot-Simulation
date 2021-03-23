import java.util.ArrayList;
import java.util.Collections;

public class GeneralSearch extends ExpandNode {

    public static Node startSearch(Map map, Coord start, Coord goal, String searchType) {
        Node currentNode;
        ArrayList<Node> frontier = new ArrayList<Node>();
        ArrayList<Node> explored = new ArrayList<Node>();

        // frontierStates is used for displaying frontier's states
        ArrayList<Coord> frontierStates = new ArrayList<Coord>();


        // Initialising first node (with start state)
        frontier.add(new Node(null, start)); //adding initial node

        while(true) {

            // Unsuccesful search - when frontier is empty (there no nodes to explore)
            if (frontier.toString() == "[]") {
                System.out.println("fail");
                System.out.println(explored.size());
                return null;
            }

            // Printing the fronier
            for (Node f : frontier) {
                frontierStates.add(f.getState());
            }
            System.out.println(frontierStates.toString());
            frontierStates.clear();

            // Decides which kind of search to use
            // BFS exploring nodes from the beginning of the frontier and DFS from the end of the frontier
            if (searchType == "BFS") {
                currentNode = frontier.get(0);
                frontier.remove(0);
            }
            else if (searchType == "DFS") {
                currentNode = frontier.get(frontier.size()-1);
                frontier.remove(frontier.size()-1);
            }
            else {
                System.out.println("Invalid search alghorithm");
                return null;
            }

            // Adds current node to explored nodes
            explored.add(currentNode);

            if (currentNode.getState().equals(goal)) {
                // Explored node is a goal node
                Node pathNode = currentNode;
                ArrayList<Coord> finalPath = new ArrayList<Coord>();
                finalPath.add(currentNode.getState());

                // Retrieving the path from a start to the goal using node's reference to its
                // parent
                while (pathNode != null) {
                    if (pathNode.getParentNode() != null) {
                        finalPath.add(pathNode.getParentNode().getState());
                    }
                    pathNode = pathNode.getParentNode();
                }
                Collections.reverse(finalPath);

                // Printing the path from a start to the goal
                for (Coord c : finalPath) {
                    System.out.print(c);
                }
                System.out.println();

                // Cost to the goal
                System.out.println(currentNode.getPathCost());

                // Number of nodes visited
                System.out.println(explored.size());

                return currentNode;
            } else {
                // Explored node is not a goal; therefore, it expands available nodes around the current node
                if (searchType == "BFS") {
                    for (Node x : expand(currentNode, map, frontier, explored)) {
                        frontier.add(x);
                    }
                }
                else {
                    // DFS takes nodes from the end of the frontier; thus for correct nodes exploring order of new added nodes must be reversed 
                    ArrayList<Node> reorderNodes = new ArrayList<Node>();
                    reorderNodes = expand(currentNode, map, frontier, explored);
                    Collections.reverse(reorderNodes);
                    for (Node x : reorderNodes) {
                        frontier.add(x);
                    }
                }

            }
        }
    }
}
