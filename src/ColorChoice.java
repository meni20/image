import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.function.Consumer;
public class ColorChoice extends JPanel implements ActionListener {
    JButton button;
    //private Consumer<Color> setColorPen;
    private Color color;

    ColorChoice(/*Consumer<Color> setColorPen*/Color color){
        color = this.color;
        //this.setColorPen = setColorPen;
        button = new JButton("pick a color ");
        button.addActionListener(this);
        button.setSize(100,50);

        this.add(button);
        //this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== button){
            JColorChooser colorChooser = new JColorChooser();
            this.color = JColorChooser.showDialog(null, "Pick a colorvdfdf", Color.black);
            //setColorPen.accept(color);
            repaint();
        }
    }
}
