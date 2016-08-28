public class Point {
    double xCoord;
    double yCoord;
    double radius;
    boolean inArea;

    public Point(double x, double yCoord, double r, boolean inArea) {
        this.xCoord = x;
        this.yCoord = yCoord;
        this.radius = r;
        this.inArea = inArea;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setInArea(boolean inArea) {
        this.inArea = inArea;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isInArea() {
        return inArea;
    }
}