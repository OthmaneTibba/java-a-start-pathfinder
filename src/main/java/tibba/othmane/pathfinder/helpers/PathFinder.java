package tibba.othmane.pathfinder.helpers;

import tibba.othmane.pathfinder.models.Node;
import tibba.othmane.pathfinder.models.NodeDto;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    Node[][] nodes;
    ArrayList<Node> openList;
    ArrayList<NodeDto> pathList;
    Node startNode,currentNode,goalNode;
    boolean goalReached;

    int step;

    List<NodeDto> nodeDtos;

    public PathFinder(){
        openList = new ArrayList<>();
        pathList = new ArrayList<>();
        goalReached = false;
        step = 500;
        initiateNodes();
    }

    public void setNodeDtos(List<NodeDto> nodeDtos) {
        this.nodeDtos = nodeDtos;
         startNode = nodes[getStartNode().row][getStartNode().col];
         goalNode = nodes[getGoalNode().row][getGoalNode().col];
        currentNode = startNode;
        currentNode.open = true;
        openList.add(currentNode);
    }

    private void initiateNodes() {
        int rows = 20;
        int  columns = 45;
        nodes = new Node[rows][columns];
        for(int row = 0 ; row < rows ; row++){
            for(int column = 0 ; column < columns ; column ++){
                nodes[row][column] = new Node(row,column);
            }
        }
    }

    private void reset(){
        int rows = 20;
        int  columns = 45;
        for(int row = 0 ; row < rows ; row++){
            for(int column = 0 ; column < columns ; column ++){
                nodes[row][column].solid = false;
                nodes[row][column].open = false;
                nodes[row][column].checked = false;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }


    public void setNodes(){
        reset();

        for(NodeDto nodeDto : nodeDtos){
            if(nodeDto.isObstacle()){
                nodes[nodeDto.getRow()][nodeDto.getCol()].solid = true;
            }
        }

        for(int row = 0 ; row < 20 ; row++){
            for(int column = 0 ; column < 45 ; column++){
                getCost(nodes[row][column]);
            }
        }


    }

    private void getCost(Node node) {
        //gCost
        int xDistance = Math.abs(node.row - startNode.row);
        int yDistance = Math.abs(node.col - startNode.col);
        node.gCost = xDistance + yDistance;
        //hCost
        xDistance = Math.abs(node.row - goalNode.row);
        yDistance = Math.abs(node.col - goalNode.col);
        node.hCost = xDistance + yDistance;
        //fCost
        node.fCost = node.gCost + node.hCost;
    }


    public Node getStartNode(){
        for(NodeDto nodeDto : nodeDtos){
            if(nodeDto.isStart()){
                return new Node(nodeDto.getRow(), nodeDto.getCol());
            }
        }
        return  null;
    }

    public Node getGoalNode(){
        for(NodeDto nodeDto : nodeDtos){
            if(nodeDto.isTarget()){
                return new Node(nodeDto.getRow(), nodeDto.getCol());
            }
        }
        return  null;
    }
    public boolean search(){

        while (!goalReached && step < 500){
            int row = currentNode.row;
            int col = currentNode.col;
            currentNode.checked = true;
            openList.remove(currentNode);

            if(row - 1  >= 0){
                openNode(nodes[row - 1][col]);
            }
            if(row + 1 < 20){
                openNode(nodes[row+1][col]);
            }
            if(col - 1 >= 0 ){
                openNode(nodes[row][col-1]);
            }
            if(col + 1 < 45){
                openNode(nodes[row][col+1]);
            }
            int bestNodeIndex = 0;
            int bestNodeFcost = 999;
            for(int i = 0 ; i< openList.size() ; i++){
                if(openList.get(i).fCost < bestNodeFcost){
                    bestNodeFcost = openList.get(i).fCost;
                    bestNodeIndex = i;
                }else if(openList.get(i).fCost == bestNodeFcost){
                    if(openList.get(i).gCost <  openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            if(openList.isEmpty()){
                break;
            }
            currentNode = openList.get(bestNodeIndex);
            if(currentNode.col == goalNode.col && currentNode.row == goalNode.row){
                goalReached = true;
                trackPath();
            }
            step++;
        }
        return goalReached;
    }

    public ArrayList<NodeDto> trackPath() {
        Node current = goalNode.parent;

        while (current != startNode){
            NodeDto nodeDto = new NodeDto(current.row , current.col, false, false, false,false, true);
            pathList.add(0,nodeDto);
            current = current.parent;
        }
        return pathList;
    }

    private void openNode(Node n) {
        if(!n.checked && !n.solid  && !n.open){
            n.open = true;
            n.parent = currentNode;
            openList.add(n);
        }
    }
}
