import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

public class CommandCentre {
    private static final String CCentre_PATH = "assets/buildings/command_centre.png";

    private int p_x;
    private int p_y;

    private Image image;
    private Camera camera;

    public int getP_x() {
        return p_x;
    }

    public int getP_y() {
        return p_y;
    }

    public void update(World world){
        Input input = world.getInput();
    }

    public CommandCentre(Camera camera, int p_x, int p_y) throws SlickException {
        image = new Image(CCentre_PATH);
        this.p_x = p_x;
        this.p_y = p_y;
        this.camera = camera;
    }

    public void render() {
        image.drawCentered((int)camera.globalXToScreenX(p_x),
                (int)camera.globalYToScreenY(p_y));
    }

}
