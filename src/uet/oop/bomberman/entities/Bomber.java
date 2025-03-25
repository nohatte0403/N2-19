package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int direction = 0; // 0: đứng yên, 1: trái, 2: phải, 3: lên, 4: xuống

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void move(int dx, int dy) {
        this.x += dx * Sprite.SCALED_SIZE;
        this.y += dy * Sprite.SCALED_SIZE;

        if (dx == -1) direction = 1;
        if (dx == 1) direction = 2;
        if (dy == -1) direction = 3;
        if (dy == 1) direction = 4;
    }

    @Override
    public void update() {
        // Cập nhật trạng thái, kiểm tra va chạm hoặc phím bấm
    }

    @Override
    public void render(GraphicsContext gc) {
        ImageView iv = new ImageView(img);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        // Xoay ảnh theo hướng di chuyển
        switch (direction) {
            case 1 -> iv.setRotate(180); // Trái
            case 2 -> iv.setRotate(0);   // Phải
            case 3 -> iv.setRotate(-90); // Lên
            case 4 -> iv.setRotate(90);  // Xuống
        }

        gc.drawImage(iv.snapshot(params, null), x, y);
    }

    public Bomb placeBomb() {
        return new Bomb(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.bomb);
    }
}
