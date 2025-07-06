package srt;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("My First JFrame App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);

        RichContainer[] ContainerList = new RichContainer[5];
        RichContainer template = new RichContainer();
        template.AddElement(1); // The type should be 0, the text should be "Enter some text into this box"
        template.AddElement(2);
        template.AddElement(0);
        for (int i = 0; i < 5; i++){
            ContainerList[i] = template;
        }
        // Load your image
        ImageIcon icon = new ImageIcon("jockey.jpg"); // Or JPG, GIF, etc.
        
        // Create a label with the image
        JLabel label = new JLabel(icon);
        label.setBounds(200, 200, 100, 100);
        // Create a label
        RichUtilities.AddRichContainersToJFrameSerially(frame, ContainerList, 0, 0);
        // Create a button
        JButton button = new JButton("Click Me!");
        button.setBounds(240, 400, 100, 25);

        // Add a listener to the button

        // Create a layout and add components
        frame.setLayout(null);
        frame.add(label);
        frame.add(button);

        // Show the frame
        frame.setVisible(true);
    }
}