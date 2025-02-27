import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class User {
    private String actor_id;
    private String first_name;
    private InputStream img;  // Use InputStream for image data

    public User(String id, String first_name, InputStream img){
        this.actor_id = id;
        this.first_name = first_name;
        this.img = img;
    }

    public String getFirst_name(){
        return first_name;
    }


    public ImageIcon getImageIcon() {
        try {
            // Convert InputStream to BufferedImage
            BufferedImage image = ImageIO.read(img);
            // Return ImageIcon from the BufferedImage
            return new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
            return null;
        }
    }
}