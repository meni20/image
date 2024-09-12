import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpText extends JPanel {
    JTextArea textArea = new JTextArea(20,25);
    JScrollPane scrollPane = new JScrollPane(textArea);
    JButton closeButton = new JButton("Close");
    public HelpText(JFrame parentFrame){
        textArea.setText(generateLongText());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(closeButton,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setBackground(Color.YELLOW);

//        parentFrame.add(this);
//        parentFrame.setComponentZOrder(this, 0);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.remove(HelpText.this);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }
    private String generateLongText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            sb.append("This is line ").append(i + 1).append(" of a very long text. ");
            sb.append("It keeps going on and on to simulate a long message. ");
            sb.append("Scroll down to see more lines.\n");
        }
        return sb.toString();
    }
}

