package uet.oop.bomberman.levels;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.animal.Animal;
import uet.oop.bomberman.entities.animal.Balloon;
import uet.oop.bomberman.entities.animal.Doll;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.enemy;
import static uet.oop.bomberman.control.Menu.bombNumber;
import static uet.oop.bomberman.control.Menu.timeNumber;
import static uet.oop.bomberman.entities.animal.Bomber.swapKill;
import static uet.oop.bomberman.entities.block.Bomb.isBomb;
import static uet.oop.bomberman.entities.item.SpeedItem.speed;
import static uet.oop.bomberman.utility.SoundManager.isSoundDied;
import static uet.oop.bomberman.utility.SoundManager.isSoundTitle;

public class Level3 {
    /**
     * 2 Balloon, 1 Doll	40 bomb
     */
    public Level3() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        new CreateMap("res/levels/Level3.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        speed = 1;
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        bombNumber = 40;
        isBomb = 0;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        Animal enemy1 = new Balloon(5, 5, Sprite.balloon_left1.getFxImage());
        Animal enemy2 = new Balloon(11, 9, Sprite.balloon_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);

        Animal enemy3 = new Doll(7, 5, Sprite.doll_left1.getFxImage());
        enemy.add(enemy3);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}