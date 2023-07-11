package tibba.othmane.pathfinder.models;

public class Node {
    public int col;
    public int row;
    public int gCost;
    public int hCost;
    public int fCost;
    public boolean solid = false;
    public  boolean checked = false;
    public boolean open = false;

    public Node parent;

    public Node(int row , int col){
        this.row = row;
        this.col = col;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
}
