import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class InformedSearch extends ExpandNode {
    public static Node startSearch(Map map, Coord start, Coord goal, String searchType) {
        Node currentNode;
        ArrayList<Node> explored = new ArrayList<Node>();

        // Comparator used for ordering the PriorityQueue
        Comparator<Node> fCostComparator = new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return (int)(n1.getFCost() - n2.getFCost());
            }
        };
        // The comparison in PriorityQueue is based on fCost of each node and its compare method is only called while adding a new element
        PriorityQueue<Node> frontier = new PriorityQueue<>(fCostComparator);

        // Initialising first node (with start state)
        frontier.add(new Node(null, start, goal, searchType)); //adding initial node

        while(true) {

            // Unsuccesful search - when frontier is empty (there no nodes to explore)
            if (frontier.toString() == "[]") {
                System.out.println("fail");
                System.out.println(explored.size());
                return null;
            }

            // Printing and reordering the fronier 
            System.out.print("[");
            int count = 0;
            for (Node f : frontier) {
                count++;
                System.out.print(f.getState() + ":" + f.getFCost());
                if (count != frontier.size()) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            

            // Takes out the node with the lowest F_Coast function and assign the node to currentNode to be explored
            currentNode = frontier.remove();

            // Adds current node to explored nodes
            explored.add(currentNode);


            if (currentNode.getState().equals(goal)) {
                // Explored node is a goal node
                Node pathNode = currentNode;
                ArrayList<Coord> finalPath = new ArrayList<Coord>();
                finalPath.add(currentNode.getState());

                // Retrieving the path from a start to the goal using node's reference to its parent
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
                for (Node x : expand(currentNode, map, frontier, explored, goal, searchType)) {
                    frontier.add(x);
                }
            }
        }
    }
}
