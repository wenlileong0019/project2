import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Factory extends Select {

    private boolean isSelect;
    private Image highlight = new Image("assets/highlight_large.png");
    private Image image = new Image("assets/buildings/factory.png");
    private Camera camera;

    private double mouseX;
    private double mouseY;


    public Factory(Camera camera, double x, double y, boolean isSelect) throws SlickException {
        super(x, y);
        this.camera = camera;
        this.isSelect = isSelect;

    }


    public void update(World world) {
        Input input = world.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            mouseX = camera.screenXToGlobalX(input.getMouseX());
            mouseY = camera.screenYToGlobalY(input.getMouseY());
            if (World.distance(mouseX, mouseY, super.getX(), super.getY()) <= 32) {
                isSelect = selecting(isSelect);
            }
        }
    }

    public boolean selecting(boolean isSelect) {
        if (isSelect) {
            return false;
        }
        return true;
    }

    public void render(Graphics g) {
        image.drawCentered((int) camera.globalXToScreenX(super.getX()),
                (int) camera.globalYToScreenY(super.getY()));
        if (!isSelect) {
            g.drawString("mouseX: " + mouseX + " mouseY: " + mouseY + "\nx: "
                    + super.getX() + " y: " + super.getY(), 100, 200);
        }
            if (isSelect) {
                highlight.drawCentered((int) camera.globalXToScreenX(super.getX()),
                        (int) camera.globalYToScreenY(super.getY()));
            }
        }
    }

