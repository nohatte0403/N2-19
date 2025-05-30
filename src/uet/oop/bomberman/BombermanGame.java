package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.oop.bomberman.control.Menu;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animal.Animal;
import uet.oop.bomberman.entities.animal.Bomber;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.entities.block.Portal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.Level3;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.control.Menu.*;
import static uet.oop.bomberman.entities.block.Portal.*;
import static uet.oop.bomberman.levels.NextLevel.*;
import static uet.oop.bomberman.utility.SoundManager.updateSound;

public class BombermanGame extends Application {

    /**
     * The default size of the window
     * H: 480px W: 800px
     */
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;

    public static final List<Entity> block = new ArrayList<>(); //Contains fixed entities
    public static List<Animal> enemy = new ArrayList<>();       //Contains enemy entities
    public static int[][] idObjects;    //Two-dimensional array is used to test paths
    public static int[][] listKill;     //Array containing dead positions
    public static Animal player;
    public static boolean running;
    public static ImageView authorView; //hiển thị ảnh

    private GraphicsContext gc;
    private Canvas canvas;

    private int frame = 1;
    private long lastTime;

    public static Stage mainStage = null;

    /**
     * gọi Start() để bắt đầu JavaFX
     */
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT); //vẽ các đối tượng
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        Image author = new Image("images/author.png");
        authorView = new ImageView(author);
        authorView.setX(-400);
        authorView.setY(-208);
        authorView.setScaleX(0.5);
        authorView.setScaleY(0.5);
        Group root = new Group();
        Menu.createMenu(root); //hiện thanh menu
        root.getChildren().add(canvas);
        root.getChildren().add(authorView);

        Scene scene = new Scene(root);

        // phím mũi tên để di chuyển, SPACE để đặt bom
        scene.setOnKeyPressed(event -> {
            if (player.isLife())
                switch (event.getCode()) {
                    case UP:
                        Move.up(player);
                        break;
                    case DOWN:
                        Move.down(player);
                        break;
                    case LEFT:
                        Move.left(player);
                        break;
                    case RIGHT:
                        Move.right(player);
                        break;
                    case SPACE:
                        Bomb.putBomb();
                        break;
                }
        });

        stage.setScene(scene);
        stage.setTitle("Bomberman from N2-19");
        Image icon = new Image("images/iconGame.png");
        stage.getIcons().add(icon);
        mainStage = stage;
        mainStage.show();

        lastTime = System.currentTimeMillis();

        //khởi tạo vòng lặp game
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render(); //vẽ tất cả
                    update(); //cập nhật logic game
                    time(); //thời gian đếm ngược
                    updateMenu();
                }
            }
        };
        timer.start();

        player = new Bomber(1, 1, Sprite.player_right_2.getFxImage()); //tạo người chơi
        player.setLife(false);

    }

    /**
     * cập nhật logic game
     */
    public void update() {
        block.forEach(Entity::update);//cập nhật block
        enemy.forEach(Entity::update);//cập nhật enemy
        player.update();//cập nhật player

        player.setCountToRun(player.getCountToRun() + 1);
        if (player.getCountToRun() == 4) {
            Move.checkRun(player);
            player.setCountToRun(0);
        }

        for (Animal a : enemy) {
            a.setCountToRun(a.getCountToRun() + 1);
            if (a.getCountToRun() == 8) {
                Move.checkRun(a);
                a.setCountToRun(0);
            }
        }

        //nếu không còn quái và chưa có cổng ->tạo cổng
        if (enemy.size() == 0 && !isPortal && !wait) {
            Entity portal = new Portal(_width - 2, _height - 2, Sprite.portal.getFxImage());
            block.add(portal);
            //player đứng vào cổng -> chờ chuyển màn
            if (player.getX() / 32 == portal.getX() / 32 && player.getY() / 32 == portal.getY() / 32) {
                wait = true;
                waitingTime = System.currentTimeMillis();
            }
        }
        waitToLevelUp(); //đợi qua màn
        updateSound(); //âm thanh
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //xóa canvas
        block.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
        player.render(gc);
    }

    public void time() {
        frame++;

        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            mainStage.setTitle("Bomberman from N2-19 | " + frame + " frame");
            frame = 0;

            time.setText("Time: " + timeNumber);
            timeNumber--;
            // hết thời gian -> thua
            if (timeNumber < 0)
                player.setLife(false);
        }
    }
}