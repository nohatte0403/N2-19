package uet.oop.bomberman.entities.animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.animal.intelligent.AStar;
import uet.oop.bomberman.entities.animal.intelligent.Node;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Doll extends Animal {
    private static int swapKill = 1;
    private static int countKill = 0;

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

    private void killDoll(Animal animal) {
        if (countKill % 16 == 0) {
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

    private void moveDoll() {
       //dựa theo Intelligent
    }

    @Override
    public void update() {
        countKill++;
        for (Animal animal : enemy) {
            if (animal instanceof Doll && !animal.life)
                killDoll(animal);
        }
        moveDoll();
    }
}
