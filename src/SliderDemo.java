import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
public class SliderDemo extends JPanel implements ChangeListener {
    JLabel label;
    JSlider slider;
    SliderDemo(){
        //frame = new JFrame("slider Demo");
        label = new JLabel();
        slider = new JSlider(0,10,0);
        slider.setPreferredSize(new Dimension(50,400));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
        //slider.setPaintTrack(true);
        slider.setMajorTickSpacing(2);
        slider.setPaintLabels(true);
        //slider.setFont(new Font("MV Boli", Font.PLAIN,15));
        slider.setOrientation(SwingConstants.VERTICAL);
        //slider.setOrientation(SwingConstants.HORIZONTAL);
        label.setText("hi "+slider.getValue());
        slider.addChangeListener(this);
        this.add(slider);
        this.add(label);
        //frame.add(panel);
        this.setVisible(true);
        slider.setBackground(Color.black);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("level "+slider.getValue());
    }
}
