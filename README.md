# Search-Coastguard-Rescue-Simulations
Solving the search problem of coastguard rescue simulation with different search algorithms and approaches.
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
