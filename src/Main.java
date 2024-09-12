import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Main extends JFrame implements ActionListener {
    HelpText helptextt;
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu;
    JMenuItem[] menuItems = new JMenuItem[3];
    String[] menuItemsNames = {"time", "help", "exit"};
    public Main() {
        FrontPage frontPage = new FrontPage(1200,800);
        add(frontPage);
         fileMenu = createMenu("File");
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i] = createMenuItem(menuItemsNames[i]);
            menuItems[i].addActionListener(this);
            fileMenu.add(menuItems[i]);
        }
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
        this.setSize(1200,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);
    }
    private JMenu createMenu(String title) {
        JMenu menu = new JMenu(title);
        menu.setFont(new Font("Arial", Font.PLAIN, 20));
        return menu;
    }
    private JMenuItem createMenuItem(String title) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Arial", Font.PLAIN, 20));
        return menuItem;
    }
    public static String getCurrentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItems[0]) {
            System.out.println(getCurrentTime());
        } else if (e.getSource() == menuItems[2]) {
            System.exit(0);
        } else if (e.getSource() == menuItems[1]){
            if (helptextt == null || !helptextt.isDisplayable()){
                helptextt = new HelpText(this);
                this.getContentPane().add(helptextt,BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}



