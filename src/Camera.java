
/**
 * This class should be used to restrict the game's view to a subset of the entire world.
 *
 * You are free to make ANY modifications you see fit.
 * These classes are provided simply as a starting point. You are not strictly required to use them.
 */
public class Camera {
    private double x = 300;
    private double y = 300;
    private Units target;

    public void followSprite(Units target) {
        this.target = target;
    }

    public double globalXToScreenX(double x) {
        return x - this.x;
    }
    public double globalYToScreenY(double y) {
        return y - this.y;
    }

    public double screenXToGlobalX(double x) {
        return x + this.x;
    }
    public double screenYToGlobalY(double y) {
        return y + this.y;
    }

    public void update(World world) {
        double targetX = target.getX() - App.WINDOW_WIDTH / 2;
        double targetY = target.getY() - App.WINDOW_HEIGHT / 2;

        x = Math.min(targetX, world.getMapWidth() -	 App.WINDOW_WIDTH);
        x = Math.max(x, 0);
        y = Math.min(targetY, world.getMapHeight() - App.WINDOW_HEIGHT);
        y = Math.max(y, 0);
    }
}
