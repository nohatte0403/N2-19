package uet.oop.bomberman.entities.animal.intelligent;

public class Node {
    // chi phí di chuyển
    private int g; // từ đầu đến node này
    private int f; // dự đoán ừ node đến đích
    private int h; //=f+g

    private int row;
    private int col;
    private boolean isBlock;
    private Node parent; //truy ngược đường đi đã tìm

    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    /**
     * Dự doán chi phí từ node hiện tại đến đích
     * @param finalNode đích
     */
    public void calculateHeuristic(Node finalNode) {
        this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
    }

    /**
     * Cập nhật g,f
     * @param currentNode node đang xét
     */
    public void setNodeData(Node currentNode) {
        int gCost = currentNode.getG();
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    /**
     * kiểm tra nếu có đường tốt hơn
     * @param currentNode node đã ở trong openList
     * @return có/không, cập nhật g,f,parent
     */
    public boolean checkBetterPath(Node currentNode) {
        int gCost = currentNode.getG();
        if (gCost < getG()) {
            setNodeData(currentNode);
            return true;
        }
        return false;
    }

    private void calculateFinalCost() {
        int finalCost = getG() + getH();
        setF(finalCost);
    }

    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();
    }

    @Override
    public String toString() {
        return "Node [row=" + row + ", col=" + col + "]";
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}