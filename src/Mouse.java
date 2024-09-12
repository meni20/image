/*import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class Mouse implements MouseListener {
    private ImageDisplay imageDisplay;
    //private ArrayList<MyPoint> points;
    private Point[] points = new Point[4];
    private int pointIndex = 0;
    public Mouse(ImageDisplay imageDisplay, Point[] points, int pointIndex){
        this.imageDisplay = imageDisplay;
        this.points = points;
        this.pointIndex = pointIndex;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //MyPoint myPoint = new MyPoint(e.getX(),e.getY());
        System.out.println(e.getPoint());
        if ( pointIndex < 4) {
            points[pointIndex] = e.getPoint();
            pointIndex++;
            imageDisplay.repaint();
            if (pointIndex == 4) {
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}*/
