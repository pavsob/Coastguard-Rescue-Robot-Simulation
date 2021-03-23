import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class ExpandNode {

    // This method takes explored states from the expandedStates() method, check if those states are not already in frontier or in 
    // explored nodes, then creates arraylist of new nodes (expanded nodes)
    protected static ArrayList<Node> expand(Node currentNode,Map map, ArrayList<Node> frontier, ArrayList<Node> explored) {
        ArrayList<Node> expanded = new ArrayList<Node>();
        ArrayList<Coord> stateList = expandedStates(currentNode.getState(), map);

        ArrayList<Coord> newstateList = new ArrayList<>(stateList);

        for (Coord state : stateList) {

            for (Node fNode : frontier) {
                if (fNode.getState().equals(state)) {
                    newstateList.remove(state);
                }
            }
    
            for (Node eNode : explored) {
                if (eNode.getState().equals(state)) {
                    newstateList.remove(state);
                }
            }
        }
    
        for (Coord state : newstateList) {
            expanded.add(new Node(currentNode, state));
        }
        return expanded;
        
    }
    
    // The same as above except frontier as PriorityQueue, it also checks whether the state in frontier or in explored has higher f cost. If the new node
    // has the lower f cost, it is added to the frontier
    protected static ArrayList<Node> expand(Node currentNode,Map map, PriorityQueue<Node> frontier, ArrayList<Node> explored, Coord goal, String searchType) {
        ArrayList<Node> expanded = new ArrayList<Node>();
        ArrayList<Coord> stateList = expandedStates(currentNode.getState(), map);

        ArrayList<Coord> newstateList = new ArrayList<>(stateList);

        for (Coord state : stateList) {

            for (Node fNode : frontier) {
                if (fNode.getState().equals(state) && fNode.getFCost() <= new Node(currentNode, state, goal, searchType).getFCost()) {
                    newstateList.remove(state);
                }
            }

            for (Node eNode : explored) {
                if (eNode.getState().equals(state) && eNode.getFCost() <= new Node(currentNode, state, goal, searchType).getFCost()) {
                    newstateList.remove(state);
                }
            }
        }

        for (Coord state : newstateList) {
            expanded.add(new Node(currentNode, state, goal, searchType));
        }
        return expanded;
    }

    

    // This method looks around the current node and discover the states around it according to tie-breaking strategy ( from SE direction anticlockwise to SW)
    // This is done by direction steps
    private static ArrayList<Coord> expandedStates(Coord currentState, Map map) {
        ArrayList<Coord> stateList = new ArrayList<Coord>();
        int obstacle;
        Coord newState;
        int stepR;
        int stepC;
        int curObstacle;
        int directionSteps[][] = {{1,0,-1,-1,0,1},{1,1,0,-1,-1,0}};
        curObstacle = map.getMap()[currentState.getR()][currentState.getC()];

        for(int i = 0; i < 6; i++) {
            stepR = directionSteps[0][i];
            stepC = directionSteps[1][i];
            try { 
                newState = new Coord(currentState.getR()+stepR, currentState.getC()+stepC);
                obstacle = map.getMap()[newState.getR()][newState.getC()];

                if (curObstacle == 2) {
                    if(currentState.getR() == newState.getR()) {
                        if (obstacle == 0) {
                            stateList.add(newState);
                        }
                        else if (obstacle == 2) {
                            if (currentState.getR() == newState.getR()) {
                                stateList.add(newState);
                            }
                        }
                    }
                }
                else {
                    if (obstacle == 0) {
                        stateList.add(newState);
                    }
                    else if (obstacle == 2) {
                        if (currentState.getR() == newState.getR()) {
                            stateList.add(newState);
                        }
                    }
                }
            } catch(IndexOutOfBoundsException ioobe) {}
        }
        return stateList;
    }
    
}
