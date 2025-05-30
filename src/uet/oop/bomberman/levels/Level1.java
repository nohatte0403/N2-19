package uet.oop.bomberman.levels;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.animal.Animal;
import uet.oop.bomberman.entities.animal.Balloon;
import uet.oop.bomberman.entities.animal.Oneal;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.control.Menu.bombNumber;
import static uet.oop.bomberman.control.Menu.timeNumber;
import static uet.oop.bomberman.entities.animal.Bomber.swapKill;
import static uet.oop.bomberman.entities.block.Bomb.isBomb;
import static uet.oop.bomberman.entities.block.Bomb.powerBomb;
import static uet.oop.bomberman.entities.item.SpeedItem.speed;
import static uet.oop.bomberman.utility.SoundManager.isSoundDied;
import static uet.oop.bomberman.utility.SoundManager.isSoundTitle;

public class Level1 {
    /**
     * khởi tạo lại toàn bộ
     * lvl 1: 3 Balloon, 2 Oneal, 20 bomb
     */
    public Level1() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        powerBomb = 0;
        new CreateMap("res/levels/Level1.txt"); //tạo map theo ảnh
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        bombNumber = 20;
        isBomb = 0;
        speed = 1;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        Animal enemy1 = new Balloon(4, 4, Sprite.balloon_left1.getFxImage());
        Animal enemy2 = new Balloon(9, 9, Sprite.balloon_left1.getFxImage());
        Animal enemy3 = new Balloon(22, 6, Sprite.balloon_left1.getFxImage());
        enemy.add(enemy1);
        enemy.add(enemy2);
        enemy.add(enemy3);

        Animal enemy4 = new Oneal(7, 6, Sprite.oneal_right1.getFxImage());
        Animal enemy5 = new Oneal(13, 8, Sprite.oneal_right1.getFxImage());
        enemy.add(enemy4);
        enemy.add(enemy5);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}