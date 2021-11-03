# Search-Coastguard-Rescue-Simulations

Coastguard rescue simulation simulates the robot which has to navigate through the Giant's Causeway and find the shortest path to take people in danger to the safe position. The map consists of hexagonal elements (figure 1) and contains two types of obstacles. The robot can not move through the obstacle type one in any direction and can move only horizontally through the obstacle type two (figure 2).

![image](https://user-images.githubusercontent.com/81230042/140093550-287dd26c-08af-4ad2-8913-d76c5493168f.png)
Figure 1: 	Example map

![image](https://user-images.githubusercontent.com/81230042/140093608-07235aef-a78d-49a7-9462-b1946bc17042.png)
Figure 2: 	Obstacles of type 2

# Running the code instructions
To compile attached files run javac *.java in the command window.
To run particular algorithm navigate to the src folder in the command window and write java Main <search type> <configuration> where search type and configuration are arguments. There are six types of search arguments to run six different algorithms:
1.	BFS – Breadth-first search
2.	DFS – Depth-first search
3.	BestF – Best first search
4.	AStar – A* search
5.	Euclid – Informed search that uses Euclidian distance as a heuristic
6.	2Agent – Bidirectional search
There is 24 different map configurations CONF0 till CONF24 and also two additional TCONF00 and TCONF01, these are used as argument <configuration>.

Example of a command for calling breadth-first search: java Main BFS CONF7
