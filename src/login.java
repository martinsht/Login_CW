import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends JFrame{
    private JTextField username_input;
    private JPasswordField password_field;
    private JLabel username;
    private JButton login_button;
    private JPanel panel;
    private JLabel message;
    private JTextField email_field;
    private JCheckBox terms_check;
    private JLabel warning;
    private JLabel email;
    private JLabel password;
    boolean isPassedUser;
    boolean isPassedEmail;
    boolean isPassedPass;

    public login() {
        setSize(500,500);
        setContentPane(panel);
        setVisible(true);
        login_button.setEnabled(false);
        login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((!username_input.getText().isEmpty())&&(!email_field.getText().isEmpty())&&(!password_field.getText().isEmpty()) ){
                message.setForeground(Color.green);
                message.setText("Successful registration");}
                else {
                    message.setForeground(Color.red);
                    message.setText("Unsuccessful registration");
                }
            }
        });
        username_input.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(!username_input.getText().isEmpty()){
                if (username_input.getText().length()<5){
                    warning.setForeground(Color.red);
                    warning.setText("username cannot be less than 5 characters");
                    username_input.requestFocus();
                }
                else{
                    isPassedUser =true;
                }
                }
            }
        });
        terms_check.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                login_button.setEnabled(isPassedUser && isPassedEmail && isPassedPass && terms_check.isSelected());
            }
        });
        password_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                char[] chars  = password_field.getText().toCharArray();
                boolean num = false;
                boolean upper = false;
                for (int i=0;i<chars.length;i++){
                    if(Character.isDigit(chars[i])){
                        num =true;
                    }
                    if (Character.isUpperCase(chars[i])){
                        upper = true;
                    }
                }
                if(!password_field.getText().isEmpty()) {

                    if (password_field.getText().length() <= 8) {
                        warning.setForeground(Color.red);
                        warning.setText("password should include more than 8 characters");
                        password_field.requestFocus();
                    } else if (!upper) {
                        warning.setForeground(Color.red);
                        warning.setText("password should include at least one capital letter.");
                        password_field.requestFocus();
                    } else if (!num) {
                        warning.setForeground(Color.red);
                        warning.setText("password should include at least one number.");
                        password_field.requestFocus();
                    }
                    else{isPassedPass=true;
                        warning.setText("");
                    }

                }

            }
        });
        email_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email_field.getText());
                if(!email_field.getText().isEmpty()){
                    if (!matcher.matches()) {
                        warning.setForeground(Color.red);
                        warning.setText("the email should be valid and include @");
                        email_field.requestFocus();}
                    else {
                        isPassedEmail = true;
                    }
                }
            }
        });
    }
}
