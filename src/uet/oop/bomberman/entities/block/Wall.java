package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;

public class Wall extends Entity {
    private final boolean isSolid = true;

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        // Có thể thêm logic sau này nếu cần
    }

    public boolean isSolid() {
        return isSolid;
    }

    @Override
    public String toString() {
        return "Wall at (" + x + ", " + y + ")";
    }
}
