package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.block;
import static uet.oop.bomberman.BombermanGame.listKill;

public class Brick extends Entity {

    private boolean destroyed = false;
    private Entity hiddenItem;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        this.hiddenItem = null;
    }

    // Constructor để Brick có thể giấu Item
    public Brick(int x, int y, Image img, Entity hiddenItem) {
        super(x, y, img);
        this.hiddenItem = hiddenItem;
    }

    @Override
    public void update() {
        int tileX = getX() / 32;
        int tileY = getY() / 32;

        // Kiểm tra xem Brick đã bị phá (giá trị 4 trong listKill)
        if (!destroyed && listKill[tileX][tileY] == 4) {
            destroyed = true;
            setImg(Sprite.grass.getFxImage());

            // Nếu có Item giấu dưới Brick, thì thêm vào danh sách block để hiện ra
            if (hiddenItem != null) {
                block.add(hiddenItem);
                hiddenItem = null;
            }
        }
    }
}
