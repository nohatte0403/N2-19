package uet.oop.bomberman.entities.animal.intelligent;

import java.util.*;

//code tìm đường đi ngắn nhất đến bomber, né các vật cản
public class AStar {
    private Node[][] searchArea; //ma trận các node
    private PriorityQueue<Node> openList; //các node chờ xét
    private Set<Node> closedSet; //các node đã xét xong
    private Node initialNode; //node đầu
    private Node finalNode; //node cuối

    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        setNodes();
        this.closedSet = new HashSet<>();
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }

    public void setBlocks(int[][] blocksArray, int count) {
        for (int i = 0; i < count; i++) {
            int row = blocksArray[i][0];
            int col = blocksArray[i][1];
            setBlock(row, col);
        }
    }

    public List<Node> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll(); //lấy node có f nhỏ nhất
            closedSet.add(currentNode);
            assert currentNode != null;
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode); // thêm node hàng xóm vào openList
            }
        }
        return new ArrayList<Node>();
    }

    /**
     * truy ngược parent từ final về init
     * @param currentNode node đang xét
     * @return danh sách đường đi hợp lệ
     */
    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode); // hàng trên
        addAdjacentMiddleRow(currentNode); // hàng hiện tại
        addAdjacentLowerRow(currentNode); //hàng dưới
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, lowerRow);
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, lowerRow);
            }
            checkNode(currentNode, col, lowerRow);
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, row);
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, row);
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, upperRow);
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, upperRow);
            }
            checkNode(currentNode, col, upperRow);
        }
    }

    private void checkNode(Node currentNode, int col, int row) {
        Node adjacentNode = getSearchArea()[row][col];
        //nếu chưa gặp vật cản, chưa xét
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
            //nếu chưa trong openList -> thêm
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode);
                getOpenList().add(adjacentNode);
            } else {
                //nếu đã trong openList ->kiểm tra đường mới có tốt không
                boolean changed = adjacentNode.checkBetterPath(currentNode);
                if (changed) {
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public Set<Node> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<Node> closedSet) {
        this.closedSet = closedSet;
    }
}
