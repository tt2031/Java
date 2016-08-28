import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ImageBean implements Serializable {
    int width = 600, height = 600;
    int r = 250;
    int padding = 50;
    private List<Point> points = new ArrayList<Point>();
    private StreamedContent image;
    private double radius;

    public ImageBean() {
    }

    public List<Point> getPoints() {
        return points;
    }

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D ig2 = bi.createGraphics();
            drawBasic(ig2);
            drawPoints(ig2);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ImageIO.write(bi, "png", os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
            return image;
        }
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    void drawBasic(Graphics2D ig2) {
        //Background
        ig2.setColor(Color.WHITE);
        ig2.fillRect(0, 0, width, height);
        //Фигура
        ig2.setColor(Color.BLUE);
        ig2.fillRect(width / 2 , height / 2 - r / 2, r , r / 2);
        ig2.fillArc(width / 2 - r / 2, height / 2 - r / 2 , r, r, 0, -90);
        ig2.fillPolygon(new int[]{width / 2, width / 2, width / 2 - r / 2}, new int[]{height / 2, height / 2 - r / 2, height / 2 }, 3);
        //==================================
        //Оси
        ig2.setColor(Color.BLACK);
        ig2.drawLine(width / 2, 0, width / 2, height);
        ig2.drawLine(0, height / 2, width, height / 2);
        //Стрелочки на осях
        ig2.drawLine(width / 2, 0, width / 2 - 7, 10);
        ig2.drawLine(width / 2, 0, width / 2 + 7, 10);
        ig2.drawLine(width, height / 2, width - 10, height / 2 - 7);
        ig2.drawLine(width, height / 2, width - 10, height / 2 + 7);
        //Насечки на осях
        //Для R на OX
        ig2.drawLine(padding, height / 2 - 3, padding, height / 2 + 3);
        ig2.drawLine(width - padding, height / 2 - 3, width - padding, height / 2 + 3);
        //Для R/2 на OX
        ig2.drawLine(width / 2 - r / 2, height / 2 - 3, width / 2 - r / 2, height / 2 + 3);
        ig2.drawLine(width / 2 + r / 2, height / 2 - 3, width / 2 + r / 2, height / 2 + 3);
        //Для R на OY
        ig2.drawLine(width / 2 - 3, padding, width / 2 + 3, padding);
        ig2.drawLine(width / 2 - 3, height - padding, width / 2 + 3, height - padding);
        //Для R/2 на OY
        ig2.drawLine(width / 2 - 3, height / 2 - r / 2, width / 2 + 3, height / 2 - r / 2);
        ig2.drawLine(width / 2 - 3, height / 2 + r / 2, width / 2 + 3, height / 2 + r / 2);
        //Подписи к насечкам
        Font font = new Font("TimesRoman", Font.BOLD, 15);
        ig2.setFont(font);
        String message = "-R";
        FontMetrics fontMetrics = ig2.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(message);
        int stringHeight = fontMetrics.getAscent();
        ig2.drawString(message, padding - stringWidth / 2, height / 2 - 3);
        ig2.drawString(message, width / 2 - stringWidth - 3, height - padding + stringHeight / 2);
        message = "-R/2";
        fontMetrics = ig2.getFontMetrics();
        stringWidth = fontMetrics.stringWidth(message);
        stringHeight = fontMetrics.getAscent();
        ig2.drawString(message, height / 2 - r / 2 - stringWidth / 2, height / 2 - 3);
        ig2.drawString(message, width / 2 - stringWidth - 3, height / 2 + r / 2 + stringHeight / 2);
        message = "R";
        fontMetrics = ig2.getFontMetrics();
        stringWidth = fontMetrics.stringWidth(message);
        stringHeight = fontMetrics.getAscent();
        ig2.drawString(message, width - padding - stringWidth / 2, height / 2 - 3);
        ig2.drawString(message, width / 2 - stringWidth - 3, padding + stringHeight / 2);
        message = "R/2";
        fontMetrics = ig2.getFontMetrics();
        stringWidth = fontMetrics.stringWidth(message);
        stringHeight = fontMetrics.getAscent();
        ig2.drawString(message, height / 2 + r / 2 - stringWidth / 2, height / 2 - 3);
        ig2.drawString(message, width / 2 - stringWidth - 3, height / 2 - r / 2 + stringHeight / 2);
        //Подписи к стрелочкам
        message = "Y";
        fontMetrics = ig2.getFontMetrics();
        stringHeight = fontMetrics.getAscent();
        ig2.drawString(message, width / 2 + 7, stringHeight);
        message = "X";
        fontMetrics = ig2.getFontMetrics();
        stringWidth = fontMetrics.stringWidth(message);
        ig2.drawString(message, width - stringWidth, height / 2 - 7);
    }

    void drawPoints(Graphics2D ig2) {
        for (Point p : points) {
            if (p.isInArea()) {
                drawPoint(Color.green, ig2, p);
            } else {
                drawPoint(Color.red, ig2, p);
            }
        }
    }

    private void drawPoint(Color color, Graphics2D ig2, Point p) {
        ig2.setColor(color);
        double scale = r / radius;
        int xc = width / 2;
        int yc = height / 2;
        int x = xc + (int) (p.getxCoord() * scale) - 5;
        int y = yc - (int) (p.getyCoord() * scale) - 5;
        ig2.fillArc(x, y, 10, 10, 0, 360);
    }

    public void addPoint(Point p) {
        this.points.add(p);
    }
}
