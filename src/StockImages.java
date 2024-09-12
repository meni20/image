import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.function.Consumer;
public class StockImages extends JPanel {
    private Consumer<File> setClientImage;
    public StockImages(Consumer<File> setClientImage){
        this.setClientImage = setClientImage;

        JButton image1 = new JButton("Image 1", new ImageIcon(getScaledImage("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה124.jpg")));
        JButton image2 = new JButton("Image 2" , new ImageIcon(getScaledImage("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה125.jpg")));
        JButton image3 = new JButton("Image 3" , new ImageIcon(getScaledImage("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה126.jpg")));
        JButton image4 = new JButton("Image 4", new ImageIcon(getScaledImage("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה120.jpg")));

        image1.setFocusable(false);
        image2.setFocusable(false);
        image3.setFocusable(false);
        image4.setFocusable(false);

        this.setLayout(new GridLayout(2,2,20,20));
        this.add(image1);
        this.add(image2);
        this.add(image3);
        this.add(image4);
        this.setBounds(200, 100, 900,500);
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        image1.addActionListener(e -> {
            setClientImage.accept(new File("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה124.jpg"));
            this.setVisible(false);
        });
        image2.addActionListener(e -> {
            setClientImage.accept(new File("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה125.jpg"));
            this.setVisible(false);
        });
        image3.addActionListener(e -> {
            setClientImage.accept(new File("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה126.jpg"));
            this.setVisible(false);
        });
        image4.addActionListener(e -> {
            setClientImage.accept(new File("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה120.jpg"));
            this.setVisible(false);
        });
    }
    private Image getScaledImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image originalImage = icon.getImage();
        return originalImage.getScaledInstance(420, 210, Image.SCALE_SMOOTH);
    }

}
