import processing.core.PApplet;

abstract class Shape {
    private static final int DEFAULT_COLOR = 255;

    private float x, y;
    private int fillColor;
    private static int shapeCount = 0;

    Shape(float x, float y) {
        this.x = x;
        this.y = y;
        this.fillColor = DEFAULT_COLOR;

        shapeCount++;
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    int getFillColor() {
        return fillColor;
    }

    void setX(float x) {
        this.x = x;
    }

    void setY(float y) {
        this.y = y;
    }

    void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    abstract void draw(PApplet window);

    static void printDebugStatistics() {
        System.out.println(shapeCount);
    }
}

class Circle extends Shape {
    private float radius;
    private float diameter;

    Circle(float x, float y, float radius) {
        super(x, y);

        setRadius(radius);
    }

    void setRadius(float radius) {
        this.radius = radius;
        diameter = radius * 2;
    }

    void draw(PApplet window) {
        window.noStroke();
        window.fill(getFillColor());
        window.circle(getX(), getY(), diameter);
    }
}

class Rectangle extends Shape {
    private float width, height;

    Rectangle(float x, float y, float width, float height) {
        super(x, y);

        this.width = width;
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    void draw(PApplet window) {
        window.noStroke();
        window.fill(getFillColor());
        window.rect(getX(), getY(), width, height);
    }
}

public class OOPExample extends PApplet {
    public void settings() {
        size(700, 400);
    }

    public void setup() {
        surface.setTitle("Super Paint 9000");
        background(0);

        final int CIRCLE_COUNT = 1000;
        final float CIRCLE_MIN_RADIUS = 2;
        final float CIRCLE_MAX_RADIUS = 4;

        final int RECT_COUNT = 1000;
        final float RECT_MIN_SIZE = 10;
        final float RECT_MAX_SIZE = 40;

        Shape[] shapes = new Shape[CIRCLE_COUNT + RECT_COUNT];

        for (int i = 0; i < CIRCLE_COUNT; i++) {
            float x = (float) Math.random() * width;
            float y = (float) Math.random() * height;
            float radius = CIRCLE_MIN_RADIUS + (float) Math.random() * (CIRCLE_MAX_RADIUS - CIRCLE_MIN_RADIUS);
            int color = (int) (Math.random() * 16777216) + 0xFF000000;
            shapes[i] = new Circle(x, y, radius);
            shapes[i].setFillColor(color);
        }
        for (int i = 0; i < RECT_COUNT; i++) {
            float x = (float) Math.random() * width;
            float y = (float) Math.random() * height;
            float rectWidth = RECT_MIN_SIZE + (float) Math.random() * (RECT_MAX_SIZE - RECT_MIN_SIZE);
            float rectHeight = RECT_MIN_SIZE + (float) Math.random() * (RECT_MAX_SIZE - RECT_MIN_SIZE);
            int color = (int) (Math.random() * 16777216) + 0xFF000000;
            shapes[i + CIRCLE_COUNT] = new Rectangle(x, y, rectWidth, rectHeight);
            shapes[i + CIRCLE_COUNT].setFillColor(color);
        }

        for (int i = 0; i < shapes.length; i++) {
            shapes[i].draw(this);
        }

        Shape.printDebugStatistics();
    }

    public static void main(String[] args) {
        PApplet.main("OOPExample");
    }
}
