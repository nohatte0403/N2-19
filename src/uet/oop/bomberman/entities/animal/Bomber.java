package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends Animal {
    public static int swapKill = 1;   // đổi hiệu ứng chết theo từng giai đoạn.
    private static int countKill = 0; // xác định thời điểm thực hiện hiệu ứng chết (sau 16 frame).

    public Bomber(int isMove, int swap, String direction, int count, int countToRun) {
        super(8, 1, "down", 0, 0);
    }

    public Bomber() {
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    // phương thức thực hiện hiệu ứng chết của Bomber.
    private void killBomber(Animal animal) {
        if (countKill % 16 == 0) { // sau 16 frame thì thực hiện hiệu ứng chết.

            // Hiển thị tuần tự 3 hình ảnh chết (player_dead1, player_dead2, player_dead3) cho Bomber chết:
            if (swapKill == 1) {
                animal.setImg(Sprite.player_dead1.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.player_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 4;
            } else {
                animal.setImg(Sprite.transparent.getFxImage());        // thiết lập hình ảnh Bomber thành trong suốt (transparent).
                running = false;                                      // dừng trò chơi.
                Image gameOver = new Image("images/gameOver.png"); // load ảnh gameOver.
                authorView.setImage(gameOver);                       // hiển thị ảnh gameOver.
            }
        }
    }

    // Check va chạm với lửa của bom.
    private void checkBombs() {
        if (listKill[player.getX() / 32][player.getY() / 32] == 4) { // đổi từ tọa độ pixel sang tọa độ ô.
            player.setLife(false);
        }
    }

    // Check va chạm với enemy
    private void checkEnemy() {
        // Lấy tọa độ của Bomber:
        int ax = player.getX();
        int ay = player.getY();

        // Lấy tọa độ của enemy rồi so sánh với tọa độ Bomber:
        for (Animal animal : enemy) {
            int bx = animal.getX();
            int by = animal.getY();
            if (ax == bx && by - 32 <= ay && by + 32 >= ay            // Cùng cột, cách nhau ≤32px theo chiều dọc.
                    || ay == by && bx - 32 <= ax && bx + 32 >= ax) { // Cùng hàng, cách nhau ≤32px theo chiều ngang.
                player.life = false;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkBombs();     // Nếu dẫm lên lửa → chết
        checkEnemy();    // Nếu chạm enemy → chết
        countKill++;     // Đếm frame
        if (!player.life)
            killBomber(player);  // Thực hiện hiệu ứng chết
    }
}