package squidpony.squidmath;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Tommy Ettinger on 8/12/2015.
 */
public class Coord implements java.io.Serializable {
    private static final long serialVersionUID = 300L;
    public final int x;
    public final int y;

    protected Coord()
    {
        this(0, 0);
    }
    protected Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public static Coord get(int x, int y)
    {
        if(x >= -3 && y >= -3 && x < POOL.length - 3 && y < POOL[x + 3].length - 3)
            return POOL[x + 3][y + 3];
        else return new Coord(x, y);
    }

    public Coord getLocation()
    {
        return this;
    }
    public Coord translate(int x, int y)
    {
        return get(this.x + x, this.y + y);
    }
    public double distance(double x2, double y2)
    {
        return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
    }
    public double distance(Coord co)
    {
        return Math.sqrt((co.x - x) * (co.x - x) + (co.y - y) * (co.y - y));
    }
    public double distanceSq(double x2, double y2)
    {
        return (x2 - x) * (x2 - x) + (y2 - y) * (y2 - y);
    }
    public double distanceSq(Coord co)
    {
        return (co.x - x) * (co.x - x) + (co.y - y) * (co.y - y);
    }

    public int getX() {
        return x;
    }

    public Coord setX(int x) {
        return get(x, this.y);
    }

    public int getY() {
        return y;
    }

    public Coord setY(int y) {
        return get(this.x, y);
    }

    @Override
    public String toString()
    {
        return "Coord (x " + x + ", y " + y + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 113 * hash + x;
        hash = 113 * hash + y;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coord) {
            Coord other = (Coord) o;
            return x == other.x && y == other.y;
        } else {
            return false;
        }
    }
    private static Coord[][] POOL = new Coord[256][256];
    static {
        int width = POOL.length, height = POOL[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                POOL[i][j] = new Coord(i - 3, j - 3);
            }
        }
    }
    public static void expandPool(int xIncrease, int yIncrease)
    {
        if(xIncrease < 0 || yIncrease < 0)
            return;
        int width = POOL.length, height = POOL[0].length;
        POOL = Arrays.copyOf(POOL, width + xIncrease);
        for (int i = 0; i < width + xIncrease; i++) {
            POOL[i] = Arrays.copyOf(POOL[i], height + yIncrease);
            for (int j = 0; j < height + yIncrease; j++) {
                if(POOL[i][j] == null) POOL[i][j] = new Coord(i - 3, j - 3);
            }
        }
    }
}