import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Engineer extends Units{
    private Image image;

    // Initially, we don't need to move at all
    public Engineer(Camera camera, double x, double y, boolean isSelect) throws SlickException {
        super(camera, x, y, "assets/units/engineer.png", 0.1, x, y, isSelect);
        //image = new Image(super.getPATH());
//        super.setImage(image);
//        camera.followSprite(this);

    }



}
