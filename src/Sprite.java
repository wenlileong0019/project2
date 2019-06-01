import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Sprite extends Units{
    private Image image;

    // Initially, we don't need to move at all
    public Sprite(Camera camera, double x, double y, boolean isSelect) throws SlickException {
        super(camera, x, y, "assets/units/scout.png", 0.3, x, y,isSelect);
//        image = new Image(super.getPATH());
//        super.setImage(image);
    }

}
