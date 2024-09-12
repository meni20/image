import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
public class ImageDisplay extends JPanel  {
    private BufferedImage image;
    private Point[] points = new Point[4];
    private int pointIndex = 0;
    //Rectangle rectangle = new Rectangle(200,200,500,600);
    Rectangle rectangle;
    boolean rectangleChoose = false;
    public ImageDisplay(BufferedImage image, int nom) {
        this.image = image;
        applyEffect(nom);
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        setBackground(Color.black);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pointIndex < points.length) {
                    points[pointIndex] = e.getPoint();
                    pointIndex++;
                    repaint();
                    if (pointIndex == 4){
                        calculateSelectedArea();
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
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
        });
    }
    private void calculateSelectedArea() {
        System.out.println("hi");
            int x = Math.min(Math.min(points[0].x, points[1].x), Math.min(points[2].x, points[3].x));
            int y = Math.min(Math.min(points[0].y, points[1].y), Math.min(points[2].y, points[3].y));
            int width = Math.max(Math.max(points[0].x, points[1].x), Math.max(points[2].x, points[3].x)) - x;
            int height = Math.max(Math.max(points[0].y, points[1].y), Math.max(points[2].y, points[3].y)) - y;
            rectangle = new Rectangle(x, y, width, height);
            //rectangle.setBounds(x, y, width, height);
            rectangleChoose = true;
            //repaint();

    }
        private void applyEffect(int nom){
            if (!rectangleChoose) {
                rectangle = new Rectangle(0, 0, image.getWidth(), image.getHeight());
            }
            switch (nom) {
                case 1 -> {
                }
                case 2 -> negative();
                case 3 -> showBorders();
                case 4 -> blackAndWhite();
                case 5 -> grayscale();
                case 6 -> posterize();
                case 7 -> lighter();
                case 8 -> darker();
                case 9 -> warm();
                case 10 -> cool();
            }
    }
   /* private BufferedImage applyEffectInArea(BufferedImage image, Effect effect) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        if (selectedArea != null) {
            int startX = (int) selectedArea.getX();
            int startY = (int) selectedArea.getY();
            int endX = (int) (selectedArea.getX() + selectedArea.getWidth());
            int endY = (int) (selectedArea.getY() + selectedArea.getHeight());
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                        Color current = new Color(image.getRGB(x, y));
                        Color updated = effect.apply(current);
                        newImage.setRGB(x, y, updated.getRGB());
                    }
                }
            }
        }
        return newImage;
    }

    private interface Effect {
        Color apply(Color color);
    }
    private Color negative(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }*/
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.image, 200, 100, getWidth()-400, getHeight()-300, this);
        for (int i = 0; i < pointIndex; i++) {
            graphics.fillOval(points[i].x - 5, points[i].y - 5, 10, 10);
        }
        if (pointIndex == 4) {
            graphics.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
    }
    private void negative() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color current = new Color(image.getRGB(i, j));
                int negativeRed = 255 - current.getRed();
                int negativeGreen = 255 - current.getGreen();
                int negativeBlue = 255 - current.getBlue();
                Color updated = new Color(negativeRed, negativeGreen, negativeBlue);
                image.setRGB(i, j, updated.getRGB());
            }
        }
    }
    private void showBorders() {
        int MIN_DIFF_FOR_BORDER = 60;
        for (int x = 0; x < image.getWidth() -1; x++) {
            for (int y = 0; y < image.getHeight() -1; y++) {
                boolean border = false;
                Color current = new Color(image.getRGB(x, y));
                Color right = new Color(image.getRGB(x + 1, y));
                Color down = new Color(image.getRGB(x, y + 1));
                int redDiff = Math.abs(current.getRed() - right.getRed());
                int greenDiff = Math.abs(current.getGreen() - right.getGreen());
                int blueDiff = Math.abs(current.getBlue() - right.getBlue());
                int totalDiff = redDiff + greenDiff + blueDiff;
                if (totalDiff > MIN_DIFF_FOR_BORDER) {
                    border = true;
                } else {
                    redDiff = Math.abs(current.getRed() - down.getRed());
                    greenDiff = Math.abs(current.getGreen() - down.getGreen());
                    blueDiff = Math.abs(current.getBlue() - down.getBlue());
                    totalDiff = redDiff + greenDiff + blueDiff;
                    if (totalDiff > MIN_DIFF_FOR_BORDER) {
                        border = true;
                    }
                }
                if (border) {
                    image.setRGB(x, y, Color.BLUE.getRGB());
                } else {
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
    }
    private void blackAndWhite() {
        System.out.println("Rectangle: " + rectangle);
        for (int x = (int) rectangle.getX(); x < (int) (rectangle.getWidth()); x++) {
            for (int y = (int) rectangle.getY(); y < (int) (rectangle.getY() + rectangle.getHeight()); y++) {
                Color current = new Color(image.getRGB(x, y));
                int sum = current.getRed() + current.getGreen() + current.getBlue();
                if (sum / 3 < 128) {
                    image.setRGB(x, y, new Color(0, 0, 0).getRGB());
                } else {
                    image.setRGB(x, y, new Color(255, 255, 255).getRGB());
                }
            }
        }
    }
    private void grayscale(){
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color current = new Color(image.getRGB(x,y));
                int sum = current.getRed() + current.getGreen() + current.getBlue();
                int average = sum/3;
                image.setRGB(x,y,new Color(average,average,average).getRGB());
            }
        }
    }
    private void posterize (){
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color current = new Color(image.getRGB(x,y));
                image.setRGB(x,y,new Color(
                        current.getRed() < 128 ? 0: 255,
                        current.getGreen() < 128 ? 0: 255,
                        current.getBlue() < 128 ? 0: 255).getRGB());
            }
        }
    }
    private void lighter (){
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color current = new Color(image.getRGB(i, j));
                    int sum = current.getRed() + current.getBlue() + current.getGreen();
                    float by = 1.5F;
                    int red = (int) (current.getRed()*by);
                    if (red > 255) {
                        red = 255;
                    }
                    int green = (int) (current.getGreen()*by);
                    if (green > 255) {
                        green = 255;
                    }
                    int blue = (int) (current.getBlue()*by);
                    if (blue > 255) {
                        blue = 255;
                    }
                    image.setRGB(i, j, new Color(red,green,blue).getRGB());
                }
            }
    }
    private void darker (){
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color current = new Color(image.getRGB(i, j));
                int sum = current.getRed() + current.getBlue() + current.getGreen();
                float by = 0.6F;
                int red = (int) (current.getRed()*by);
                if (red < 0) {
                    red = 0;
                }
                int green = (int) (current.getGreen()*by);
                if (green < 0) {
                    green = 0;
                }
                int blue = (int) (current.getBlue()*by);
                if (blue < 0) {
                    blue = 0;
                }
                image.setRGB(i, j, new Color(red,green,blue).getRGB());
            }
        }
    }
    private void warm () {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int outputRed = (int) ((red * 0.393) + (green * 0.769) + (blue * 0.189));
                int outputGreen = (int) ((red * 0.349) + (green * 0.686) + (blue * 0.168));
                int outputBlue = (int) ((red * 0.272) + (green * 0.534) + (blue * 0.131));
                if (outputRed > 255) outputRed = 255;
                if (outputGreen > 255) outputGreen = 255;
                if (outputBlue > 255) outputBlue = 255;
                if (outputRed < 0) outputRed = 0;
                if (outputGreen < 0) outputGreen = 0;
                if (outputBlue < 0) outputBlue = 0;
                Color warmFilter = new Color(outputRed, outputGreen, outputBlue);
                image.setRGB(x, y, warmFilter.getRGB());
            }
        }
    }
    private void cool() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int outputRed = (int) ((red * 0.272) + (green * 0.534) + (blue * 0.131));
                int outputGreen = (int) ((red * 0.349) + (green * 0.686) + (blue * 0.168));
                int outputBlue = (int) ((red * 0.393) + (green * 0.769) + (blue * 0.189));
                if (outputRed > 255) outputRed = 255;
                if (outputGreen > 255) outputGreen = 255;
                if (outputBlue > 255) outputBlue = 255;
                if (outputRed < 0) outputRed = 0;
                if (outputGreen < 0) outputGreen = 0;
                if (outputBlue < 0) outputBlue = 0;
                Color coolFilter = new Color(outputRed, outputGreen, outputBlue);
                image.setRGB(x, y, coolFilter.getRGB());
            }
        }
    }

}
