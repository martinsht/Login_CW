import javax.swing.*;

public class Welcome extends JFrame{
    private JPanel panel;
    private JLabel img;
    private JLabel message;

    public Welcome(User user){
        setSize(500, 500);
        setContentPane(panel);
        setVisible(true);

        message.setText("Welcome " + user.getFirst_name());

        // Load image
        ImageIcon icon = user.getImageIcon(); // Ensure user.getImg() returns a valid path
        img.setIcon(icon);
    }
}
