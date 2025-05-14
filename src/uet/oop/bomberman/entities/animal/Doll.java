package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.animal.intelligent.AStar;
import uet.oop.bomberman.entities.animal.intelligent.Node;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Doll extends Animal {
    private static int swapKill = 1;  // đổi hiệu ứng chết theo từng giai đoạn.
    private static int countKill = 0; // xác định thời điểm thực hiện hiệu ứng chết (sau 16 frame).

    public Doll(int x, int y, Image img) {
        super(x, y, img);
    }

    public Doll(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Doll(boolean life) {
        super(life);
    }

    public Doll() {
    }

    // phương thức thực hiện hiệu ứng chết của Doll.
    private void killDoll(Animal animal) {
        if (countKill % 16 == 0) {  // sau 16 frame thì thực hiện hiệu ứng chết.

            // Hiển thị tuần tự 3 hình ảnh chết (do_dead1, mob_dead2, mob_dead3) cho kẻ địch chết:
            if (swapKill == 1) {
                animal.setImg(Sprite.doll_dead.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 3;
            } else {
                animal.setLife(false);
                enemy.remove(animal);
                swapKill = 1;
            }
        }
    }

    // Di chuyển Doll theo Intelligent.
    private void moveDoll() {
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            Node initialNode = new Node(this.y / 32, this.x / 32);              // xác định node bắt đầu.
            Node finalNode = new Node(player.getY() / 32, player.getX() / 32);  // xác định node đích là node của Bomber.

            // Kích thước bản đồ:
            int rows = _height;
            int cols = _width;

            // thuật toán A* tìm đường đi ngắn nhất từ initialNode đến finalNode.
            AStar aStar = new AStar(rows, cols, initialNode, finalNode);

            int[][] blocksArray = new int[_width * _height][2]; // Mảng 2D lưu vị trí các ô bị chặn dưới dạng (row, col).
            int countBlock = 0;

            for (int i = 0; i < _height; i++) {
                for (int j = 0; j < _width; j++) {
                    if (idObjects[j][i] != 0) {
                        blocksArray[countBlock][0] = i; // Lưu tọa độ hàng (row) của vật cản.
                        blocksArray[countBlock][1] = j; // Lưu tọa độ cột (col) của vật cản.
                        countBlock++;
                    }
                }
            }

            // Truyền danh sách vật cản vào A-Star để tránh đi vào ô bị chặn.
            aStar.setBlocks(blocksArray, countBlock);

            //  Tìm đường đi bằng A-Star và di chuyển:
            List<Node> path = aStar.findPath();   // Danh sách các Node từ Doll đến Bomber (được sắp xếp từ vị trí hiện tại đến đích).
            if (path.size() != 0) {
                int nextY = path.get(1).getRow(); // Lấy hàng của ô tiếp theo.
                int nextX = path.get(1).getCol(); // Lấy cột của ô tiếp theo.

                // Thực hiện di chuyển dến nextX hoặc nextY:
                if (this.y / 32 > nextY) {
                    Move.up(this);
                }
                if (this.y / 32 < nextY) {
                    Move.down(this);
                }
                if (this.x / 32 > nextX) {
                    Move.left(this);
                }
                if (this.x / 32 < nextX) {
                    Move.right(this);
                }
            }
        }
    }

    @Override
    public void update() {
        countKill++;
        for (Animal animal : enemy) {
            if (animal instanceof Doll && !animal.life)
                killDoll(animal); // thực hiện hiệu ứng chết của Doll
        }
        moveDoll();
    }
}
