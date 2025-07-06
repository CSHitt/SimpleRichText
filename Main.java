package srt;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("My First JFrame App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);

        RichContainer[] ContainerList = new RichContainer[6];
        RichContainer template = new RichContainer();
        template.AddElement(1); // The type should be 0, the text should be "Enter some text into this box"
        template.AddElement(2);
        template.AddElement(0);
        for (int i = 0; i < 5; i++){
            ContainerList[i] = template;
        }
        ContainerList[5] = new RichContainer(); // Add the image in its own container
        ContainerList[5].AddElement(3, "jockey.jpg"); // The image, specifically inside the jar
        
        // Add the Containers from ContainerList
        RichUtilities.AddRichContainersToJFrameSerially(frame, ContainerList, 0, 0);

        // Create a layout and add components
        frame.setLayout(null); // No organization

        // Show the frame
        frame.setVisible(true);
    }
}