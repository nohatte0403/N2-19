package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    // Tọa độ pixel của thực thể:
    protected int x;
    protected int y;

    // Ảnh đại diện(sprite) để vẽ thực thể đó lên màn hình:
    // Sử dụng javafx.scene.image.Image để load ảnh.
    protected Image img;

    // Khởi tạo thực thể tại vị trí xUnit, yUnit theo lưới bản đồ (unit).
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE; // Nhân để đổi từ tọa độ ô sang tọa độ pixel thực tế.
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    // Vẽ thực thể lên màn hình tại tọa độ (x, y):
    // Dùng javafx.scene.canvas.GraphicsContext để vẽ trên canvas.
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    // Hành vi thay đổi mỗi frame (di chuyển, kiểm tra va chạm, thay đổi sprite,...)
    public abstract void update();
}