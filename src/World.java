import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class should be used to contain all the different objects in your game world, and schedule their interactions.
 *
 * You are free to make ANY modifications you see fit.
 * These classes are provided simply as a starting point. You are not strictly required to use them.
 */
public class World {
    private static final String MAP_PATH = "assets/main.tmx";
    private static final String SOLID_PROPERTY = "solid";

    private Sprite sprite;
    private Sprite sprite2;
    //private Engineer engineer;

    //private CommandCentre ccentre;
    //private Engineer engineer;
    //private Pylon pylon;
    //private MetalMine metalMine;
    //private UnobtainiumMine unobtainiumMine;

    private TiledMap map;
    private Camera camera = new Camera();

    private Input lastInput;
    private int lastDelta;
    private double mouseX;
    private double mouseY;

    public Input getInput() {
        return lastInput;
    }

    public int getDelta() {
        return lastDelta;
    }

    List<UnobtainiumMine> unobtainiumMineList = new ArrayList<UnobtainiumMine>();
    List<CommandCentre> commandCentreList = new ArrayList<CommandCentre>();
    List<Engineer> engineerList = new ArrayList<Engineer>();
    List<Pylon> pylonList = new ArrayList<Pylon>();
    List<MetalMine> metalMineList = new ArrayList<MetalMine>();


    public void read_csv() {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("assets/objects.csv"))) {
            String text;
            while ((text = br.readLine()) != null) {
                String words[] = text.split(",");
                switch (words[0]) {
                    case "engineer":
                        engineerList.add(new Engineer(camera, Integer.parseInt(words[1]), Integer.parseInt(words[2]), false));
                        break;
                    case "pylon":
                        pylonList.add(new Pylon(camera, Integer.parseInt(words[1]), Integer.parseInt(words[2])));

                        break;
                    case "command_centre":
                        commandCentreList.add(new CommandCentre(camera, Integer.parseInt(words[1]), Integer.parseInt(words[2])));

                        break;
                    case "metal_mine":
                        metalMineList.add(new MetalMine(camera, Integer.parseInt(words[1]), Integer.parseInt(words[2])));

                        break;
                    case "unobtainium_mine":
                        unobtainiumMineList.add(new UnobtainiumMine(camera, Integer.parseInt(words[1]), Integer.parseInt(words[2])));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPositionFree(double x, double y) {
        int tileId = map.getTileId(worldXToTileX(x), worldYToTileY(y), 0);
        return !Boolean.parseBoolean(map.getTileProperty(tileId, SOLID_PROPERTY, "false"));
    }

    public double getMapWidth() {
        return map.getWidth() * map.getTileWidth();
    }

    public double getMapHeight() {
        return map.getHeight() * map.getTileHeight();
    }

    public World() throws SlickException {
        map = new TiledMap(MAP_PATH);
        sprite = new Sprite(camera, 684, 812, true);
        sprite2 = new Sprite(camera, 600,800, true);
        //engineer = new Engineer(camera, 700, 800, true);
        read_csv();

    }

    public void update(Input input, int delta) {
        lastInput = input;
        lastDelta = delta;
        getMouse();
        //camera.update(this);
        sprite.update(this);
        for (int i = 0; i < engineerList.size(); i++) {
            Engineer eng = engineerList.get(i);
            eng.update(this);
        }
        //engineer.update(this);
        sprite2.update(this);

    }

    public void render(Graphics g) {
        map.render((int) camera.globalXToScreenX(0),
                (int) camera.globalYToScreenY(0));

        sprite.render(g);
        sprite2.render(g);
        //engineer.render(g);
        for (int i = 0; i < commandCentreList.size(); i++) {
            CommandCentre cc = commandCentreList.get(i);
            cc.render();
        }
        for (int i = 0; i < pylonList.size(); i++) {
            Pylon py = pylonList.get(i);
            py.render();
        }
        for (int i = 0; i < metalMineList.size(); i++) {
            MetalMine mM = metalMineList.get(i);
            mM.render();
        }
        for (int i = 0; i < unobtainiumMineList.size(); i++) {
            UnobtainiumMine uM = unobtainiumMineList.get(i);
            uM.render();
        }
        for (int i = 0; i < engineerList.size(); i++) {
            Engineer eng = engineerList.get(i);
            eng.render(g);
        }

    }

    // This should probably be in a separate static utilities class, but it's a bit excessive for one method.
    public static double distance(double x1, double y1, double x2, double y2) {
        return (double) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    private int worldXToTileX(double x) {
        return (int) (x / map.getTileWidth());
    }

    private int worldYToTileY(double y) {
        return (int) (y / map.getTileHeight());
    }

    private void getMouse() {
        if (lastInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            mouseX = camera.screenXToGlobalX(lastInput.getMouseX());
            mouseY = camera.screenYToGlobalY(lastInput.getMouseY());
        }
    }

    public double mouseX(){
        return mouseX;
    }

    public double mouseY(){
        return mouseY;
    }
}


