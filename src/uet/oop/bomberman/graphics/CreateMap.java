package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.block.Brick;
import uet.oop.bomberman.entities.block.Grass;
import uet.oop.bomberman.entities.block.Portal;
import uet.oop.bomberman.entities.block.Wall;
import uet.oop.bomberman.entities.item.FlameItem;
import uet.oop.bomberman.entities.item.SpeedItem;

import static uet.oop.bomberman.BombermanGame.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CreateMap {
    //load map
    public CreateMap(String level) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(level);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            StringTokenizer tokens = new StringTokenizer(line);
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                idObjects = new int[_width][_height];
                listKill = new int[_width][_height];
                for (int i = 0; i < _height; ++i) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);
                    // tạo các sprites
                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        Entity entity;
                        switch (s) {
                            case 1: //côổng
                                entity = new Portal(j, i, Sprite.grass.getFxImage());
                                s = 0;
                                break;
                            case 2: //tường
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3: //gạch
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case 6: //vp tăng tốc
                                entity = new SpeedItem(j, i, Sprite.brick.getFxImage());
                                break;
                            case 7: //vp lửa
                                entity = new FlameItem(j, i, Sprite.brick.getFxImage());
                                break;
                            default: //cỏ
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                        }
                        idObjects[j][i] = s;
                        block.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
