package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Animal extends Entity {
    protected int isMove;        // tốc độ di chuyển pixel/frame.
    protected int swap;          // swap image để tạo hiệu ứng di chuyển.
    protected String direction;  // hướng di chuyển.
    protected int count;         // đếm số frame trong 1 hđ để chuyển sang trạng thái khác.
    protected int countToRun;    // trì hoãn một hành động sau một số frame nhất định.
    protected boolean life;      // alive or dead

    public Animal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Animal(int isMove, int swap, String direction, int count, int countToRun) {
        this.isMove = isMove;
        this.swap = swap;
        this.direction = direction;
        this.count = count;
        this.countToRun = countToRun;
    }

    public Animal(boolean life) {
        this.life = life;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public int getIsMove() {
        return isMove;
    }

    public void setIsMove(int isMove) {
        this.isMove = isMove;
    }

    public int getSwap() {
        return swap;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountToRun() {
        return countToRun;
    }

    public void setCountToRun(int countToRun) {
        this.countToRun = countToRun;
    }

    public Animal() {

    }

    @Override
    public void update() {

    }
}
