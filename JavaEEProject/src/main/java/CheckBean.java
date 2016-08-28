import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "check")
@ViewScoped
public class CheckBean implements Serializable {

    private double MainX;
    private String Y;
    private double R;
    private String yClick;
    private String xClick;

    @ManagedProperty(value = "#{imageBean}")
    private ImageBean imageBean;

    @ManagedProperty(value = "#{resultsBean}")
    private ResultsBean resultsBean;


    public CheckBean() {
        R = 1;
    }
    public void setMainX(double X){
        MainX = X;
    }
    public double getMainX(){
        return MainX;
    }
    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }
    public void setResultsBean(ResultsBean resultsBean) {
        this.resultsBean = resultsBean;
    }
    public ResultsBean getResultsBean() {
        return this.resultsBean;
    }
    public ImageBean getImageBean() {return this.imageBean;}

    public String getyClick() {
        return yClick;
    }

    public void setyClick(String yClick) {
        this.yClick = yClick;
    }

    public String getxClick() {
        return xClick;
    }

    public void setxClick(String xClick) {
        this.xClick = xClick;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public void checkPoint() {
        double y = Double.parseDouble(Y.replace(',', '.'));
        double r = R;
        double x = getMainX();
        Point point = new Point(x,y,r,inArea(x,y,r));
        resultsBean.addResult(point);
        imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        imageBean.setRadius(r);
        checkExistedPoints(r);
    }

    public void checkClick() {
        double y = Double.parseDouble(yClick.replace(',', '.'));
        double x = Double.parseDouble(xClick.replace(',', '.'));
        y = Math.rint(100.0 * y) / 100.0;
        x = Math.rint(100.0 * x) / 100.0;
        double r = R;
        Point point = new Point(x, y, r, inArea(x, y, r));
        resultsBean.addResult(point);
        imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        imageBean.setRadius(r);
        checkExistedPoints(r);
    }

    private boolean inArea(double x, double y, double r) {
        return (y>= -r / 2 && y <= 0 && x >= 0 && x <= r / 2 && r * r >= x * x + y * y)
                || (y >= 0 && x <= 0 && x >= -r / 2 && y <= x  + r / 2)
                || (y >= 0 && y <= r / 2 && x >= 0 && x <= r );
    }

    private void checkExistedPoints(double r) {
        for (Point p : imageBean.getPoints()) {
            p.setRadius(r);
            p.setInArea(inArea(p.getxCoord(), p.getyCoord(), r));
        }
    }

    public void changedRadius() {
        imageBean.setRadius(R);
        checkExistedPoints(R);
    }
    public void CommandButtonListener(double radius) {
        setR(radius);
        changedRadius();
        checkPoint();
    }
}
