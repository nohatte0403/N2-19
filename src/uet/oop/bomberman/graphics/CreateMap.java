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

            idObjects = new int[_width][_height];
            listKill = new int[_width][_height];

            for (int i = 0; i < _height; ++i) {
                String lineTile = sc.nextLine();
                StringTokenizer tokenTile = new StringTokenizer(lineTile);

                for (int j = 0; j < _width; j++) {
                    int s = Integer.parseInt(tokenTile.nextToken());
                    idObjects[j][i] = s;

                    switch (s) {
                        case 1: { // Portal dưới brick
                            Entity portal = new Portal(j, i, Sprite.portal.getFxImage());
                            block.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            block.add(portal);
                            block.add(new Brick(j, i, Sprite.brick.getFxImage(), portal));
                            break;
                        }
                        case 2: { // Tường cố định
                            block.add(new Wall(j, i, Sprite.wall.getFxImage()));
                            break;
                        }
                        case 3: { // Brick thường
                            block.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        }
                        case 6: { // SpeedItem dưới Brick
                            Entity item = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            block.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            block.add(item);
                            block.add(new Brick(j, i, Sprite.brick.getFxImage(), item));
                            break;
                        }
                        case 7: { // FlameItem dưới Brick
                            Entity item = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            block.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            block.add(item);
                            block.add(new Brick(j, i, Sprite.brick.getFxImage(), item));
                            break;
                        }
                        default: {
                            block.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
