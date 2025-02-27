import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class improv_login extends JFrame{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JPanel panel;

    public improv_login() {
        setSize(500, 500);
        setContentPane(panel);
        setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = connect.login(textField1.getText(), passwordField1.getText());
                if (user != null) {
                    JOptionPane.showMessageDialog(null, "Successfully logged in as " + user.getFirst_name(), "Login Success", JOptionPane.INFORMATION_MESSAGE);
                    new Welcome(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Invalid credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
