package uet.oop.bomberman.control;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.*;

public class Blocked {

    //kiểm tra va chạm dưới
    public static boolean block_down(Entity entity) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 + 1] == 0; //cho phép đi nếu là grass
    }

    public static boolean block_up(Entity entity) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 - 1] == 0;
    }

    public static boolean block_left(Entity entity) {
        return idObjects[entity.getX() / 32 - 1][entity.getY() / 32] == 0;
    }

    public static boolean block_right(Entity entity) {
        return idObjects[entity.getX() / 32 + 1][entity.getY() / 32] == 0;
    }

    public static boolean block_down_bomb(Entity entity, int power) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 0 //grass
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 3 //brick
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 6 //SpeedItem
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 7 //FlameItem
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 8; //khác
    }

    public static boolean block_up_bomb(Entity entity, int power) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 0
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 3
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 6
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 7
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 8;
    }

    public static boolean block_left_bomb(Entity entity, int power) {
        return idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 0
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 3
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 6
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 7
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 8;
    }

    public static boolean block_right_bomb(Entity entity, int power) {
        return idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 0
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 3
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 6
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 7
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 8;
    }
}