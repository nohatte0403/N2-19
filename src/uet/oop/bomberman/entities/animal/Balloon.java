package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.enemy;
import static uet.oop.bomberman.BombermanGame.listKill;

public class Balloon extends Animal {
    private static int swapKill = 1;   // đổi hiệu ứng chết theo từng giai đoạn.
    private static int countKill = 0;  // xác định thời điểm thực hiện hiệu ứng chết (sau 16 frame).

    public Balloon(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Balloon() {
    }

    // phương thức thực hiện hiệu ứng chết của Balloon.
    private void killBalloon(Animal animal) {
        if (countKill % 16 == 0) { // sau 16 frame thì thực hiện hiệu ứng chết.

            // Hiển thị tuần tự 3 hình ảnh chết (mob_dead1, mob_dead2, mob_dead3) cho kẻ địch chết:
            if (swapKill == 1) {
                animal.setImg(Sprite.mob_dead1.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.mob_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                animal.setImg(Sprite.mob_dead3.getFxImage());
                swapKill = 4;
            } else {
                animal.setLife(false); // cập nhật Ballloon đã chết.
                enemy.remove(animal); // xóa Balloon khỏi danh sách enermy.
                swapKill = 1;
            }
        }
    }

    // Phương thức kiểm tra Balloon có nằm trong phạm vi bom nổ không.
    private void kill() {
        for (Animal animal : enemy) {
            if (listKill[animal.getX() / 32][animal.getY() / 32] == 4) { // đổi từ tọa độ pixel sang tọa độ ô.
                animal.setLife(false);
            }
        }
    }

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        kill();       // check xem Balloon có chết không.
        countKill++; // đếm frame.
        for (Animal animal : enemy) {

            // duyệt danh sách enemy xem có Balloon nào chết không:
            if (animal instanceof Balloon && !animal.life)
                killBalloon(animal); // thực hiện hiệu ứng chết của Balloon.
        }

        if (this.y % 16 == 0 && this.x % 16 == 0) { // Balloon thực hiện di chuyển khi tọa độ pixel chia hết cho 16.
            Random random = new Random();
            int dir = random.nextInt(4);    // sinh ngẫu nhiên số từ 0 đến 3.
            switch (dir) {
                case 0:
                    Move.down(this);
                    break;
                case 1:
                    Move.up(this);
                    break;
                case 2:
                    Move.left(this);
                    break;
                case 3:
                    Move.right(this);
                    break;
            }
        }
    }
}
