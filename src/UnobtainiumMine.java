import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

public class UnobtainiumMine {
    private static final String CCentre_PATH = "assets/resources/unobtainium_mine.png";

    private int p_x;
    private int p_y;
    private int n;

    private Image image;
    private Camera camera;

    public void update(World world){
        Input input = world.getInput();
    }

    public UnobtainiumMine(Camera camera, int p_x, int p_y) throws SlickException {
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
