import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class FrontPage extends JPanel implements ActionListener {
    private File clientImage;
    private StockImages stockImages;
    private ImageDisplay imageDisplay;
    SliderDemo sliderDemo = new SliderDemo();
    ColorChoice colorChoice;
    Color colorPen;
    private int nom7 = 1;
    JButton[] buttons = new JButton[10];
    String[] buttonNames = {"reset", "negative", "showBorders", "blackWhite", "grayscale",
            "posterize", "lighter", "darker", "warm","cool"};
    JButton stockImagesB = new JButton("Stock images", new ImageIcon("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה131.png"));
    JButton fileUploadingB = new JButton("Select file from desk", new ImageIcon("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\121.png"));
    JButton selectArea = new JButton("select area ", new ImageIcon("C:\\Users\\User\\IdeaProjects\\ImageProcessing\\src\\Images0\\תמונה 130.jpg"));
    FrontPage(int WIDTH, int HEIGHT){
        stockImages = new StockImages(this::setClientImage);
        colorChoice = new ColorChoice(colorPen);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            buttons[i].addActionListener(this);
        }
        for (int i = 0; i < buttons.length; i++) {
            int x = (i < 6) ? 0 : 100;
            int y = (i % 6) * 100;
            buttons[i].setBounds(x, y, 100, 100);
            add(buttons[i]);
            buttons[i].setFocusable(true);
        }
        fileUploadingB.setBounds(240, 200, 350, 250);
        fileUploadingB.addActionListener(this);
        stockImagesB.setBounds(610, 200, 350, 250);
        stockImagesB.addActionListener(this);
        selectArea.setBounds(200, 580, 130, 130);
        selectArea.addActionListener(this);

        stockImages.setVisible(false);
        sliderDemo.setBounds(1090,150,50,400);
        colorChoice.setBounds(500,0,100,30);
        add(colorChoice);
        add(sliderDemo);
        add(stockImages);
        add(fileUploadingB);
        add(stockImagesB);
        add(selectArea);





        this.setSize(WIDTH,HEIGHT);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        //Image backgroundImage = new ImageIcon("C:\\Users\\User\\Desktop\\FileExamples\\תמונה129.jpg").getImage();
        if (colorPen != null) {
            this.setBackground(colorPen);
        } else {
            this.setBackground(Color.darkGray);
        }

    }


    public void actionPerformed (ActionEvent e)  {
        if (e.getSource() == fileUploadingB ) {
            JFileChooser fileChooser = new JFileChooser();
            int respones = fileChooser.showOpenDialog(null);
            if (respones == JFileChooser.APPROVE_OPTION) {
                clientImage = new File(fileChooser.getSelectedFile().getAbsolutePath());// בחוץ תעשה private File clientImage;
                System.out.println(clientImage);
                try {
                    updateImage(clientImage, 1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                fileUploadingB.setVisible(false);
                stockImagesB.setVisible(false);
            }
        }
        else if (e.getSource() == stockImagesB) {
            stockImages.setVisible(true);
            fileUploadingB.setVisible(false);
            stockImagesB.setVisible(false);

        }
        else {
            for (int i = 0; i < buttons.length; i++) {
                if (e.getSource() == buttons[i]) {
                    try {
                        updateImage(clientImage, i + 1);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
            }
        }
    }
    public void setClientImage(File clientImage){
        this.clientImage = clientImage;
        try {
            updateImage(clientImage,1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateImage(File file, int nom) throws IOException{
        BufferedImage image = ImageIO.read(file);
        if (image == null){
            System.out.println("Failed to losd image");
            return;
        }
        if (imageDisplay != null){
            this.remove(imageDisplay);
        }
        imageDisplay = new ImageDisplay(image, nom);
        this.add(imageDisplay, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
