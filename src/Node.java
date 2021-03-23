// Class that reresents node in the frontier or explored list
public class Node {
    private Coord state;
    private Node parentNode;
    private int depth;
    private double pathCost;
    private double fCost;

    public Node(Node parentNode, Coord state) {
        this.parentNode = parentNode;
        this.state = state;

        if (parentNode != null) {
            depth = parentNode.getDepth() + 1;
            pathCost = parentNode.getPathCost() + 1;
        } else {
            depth = 0;
            pathCost = 0;
        }
    }

    public Node(Node parentNode, Coord state, Coord goal, String search) {
        this(parentNode, state);
        fCost(search, state, goal);
    }

    private void fCost(String search, Coord curState, Coord goal) {
        double manhattanDis;
        double euclidianDis;

        // Rows start and goal with reversed sign
        double rowS = -curState.getR();
        double rowG = -goal.getR();
        double colS = curState.getC();
        double colG = goal.getC();

        // To calculate heurictic distance - Manhattan Distance
        // Defference in rows and columns of the start and end point
        double difC = colS - colG;
        double difR = rowS - rowG;

        if ((difC > 0 && difR > 0) || (difC < 0 && difR < 0)) {
            manhattanDis = Math.abs(difC) + Math.abs(difR);
        } else {
            manhattanDis = Math.max(Math.abs(difC), Math.abs(difR));
        }

        // Determining the fCost according to the used alghorithm
        if (search == "BestF") {
            fCost = manhattanDis;
        }
        else if (search == "AStar") {
            fCost = pathCost + manhattanDis;
        }
        else if (search == "Euclid") {
            // Another heurictic - Euclidian distance
            // The euclidian distance is calculated according to parallelograms, that are created when we connect centers of hexagons. Then for the distance determination is used parallelogram's diagonal
            if (rowS == rowG || colS == colG) {
                euclidianDis = Math.abs(difC + difR);
            }
            else {
                if ((difC > 0 && difR > 0)||(difC < 0 && difR < 0)) {
                    euclidianDis = Math.sqrt(Math.pow(Math.abs(difC), 2)+Math.pow(Math.abs(difR), 2) - 2 * Math.abs(difR) * Math.abs(difC)*Math.cos(Math.toRadians(60)));
                }
                else {
                    euclidianDis = Math.sqrt(Math.pow(Math.abs(difC), 2)+Math.pow(Math.abs(difR), 2) - 2 * Math.abs(difR) * Math.abs(difC)*Math.cos(Math.toRadians(120)));
                }
            }
            fCost = euclidianDis;
        }
    }

    public double getFCost() {
        return fCost;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int getDepth() {
        return depth;
    }

    public double getPathCost() {
        return pathCost;
    }

    public Coord getState() {
        return state;
    }
}
