import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Units {
    private String PATH;
    private double SPEED;

    private double x;
    private double y;

    // Initially, we don't need to move at all
    private double targetX = x;
    private double targetY = y;


    private boolean isSelect;

    private Image image;
    private Camera camera;
    private Image highlight;

    private double distace;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getPATH() {
        return PATH;
    }

    public double getSPEED() {
        return SPEED;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public Image getImage() {
        return image;
    }

    public Units(Camera camera, double x, double y, String PATH, double SPEED,
                 double targetX, double targetY,
                 boolean isSelect) throws SlickException {
        image = new Image(PATH);
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.PATH = PATH;
        this.SPEED = SPEED;
        this.targetX = targetX;
        this.targetY = targetY;
        this.isSelect = isSelect;
        highlight = new Image("assets/highlight.png");
//        if(isSelect) {
//            camera.followSprite(this);
//        }
    }

    public void update(World world) {
        Input input = world.getInput();

        // If the mouse button is being clicked, set the target to the cursor location

        if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
            targetX = camera.screenXToGlobalX(input.getMouseX());
            targetY = camera.screenYToGlobalY(input.getMouseY());
        }
            // If we're close to our target, reset to our current position
            if (World.distance(x, y, targetX, targetY) <= SPEED) {
                resetTarget();
            } else {
                // Calculate the appropriate x and y distances
                double theta = Math.atan2(targetY - y, targetX - x);
                double dx = (double) Math.cos(theta) * world.getDelta() * SPEED;
                double dy = (double) Math.sin(theta) * world.getDelta() * SPEED;
                // Check the tile is free before moving; otherwise, we stop moving
                if (world.isPositionFree(x + dx, y + dy)) {
                    x += dx;
                    y += dy;
                } else {
                    resetTarget();
                }
            }

        distace = World.distance(x, y, world.mouseX(), world.mouseY());



    }
    private void resetTarget() {
        targetX = x;
        targetY = y;
    }

    public void render(Graphics g) {
        image.drawCentered((int) camera.globalXToScreenX(x),
                (int) camera.globalYToScreenY(y));
        if(isSelect){
            highlight.drawCentered((int) camera.globalXToScreenX(x),
                    (int) camera.globalYToScreenY(y));
        }
        //image.drawCentered((int)x, (int)y);
        //g.drawString("mousex " + mouseX + "mousey " + mouseY,200, 400);

    }
}
