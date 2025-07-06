package srt;
import java.awt.*;
import java.awt.image.BufferedImage; // For BufferedImage type
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList; // For ImageIcon type
import java.util.List; // For File type
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;

public class RichContainer {
    public RichContainer(){ // Will need to add stuff here later, like RichContainer ID
    }
    public void AddElement(){
        ElementArray.add(new Element()); // Add element. This function exists as a layer of abstraction so I don't have to write
    }                                    // 'RichContainer.Element element = container.new Element();'. Instead I can just write 'container.AddElement(...);'
    public void AddElement(int ElementType){
        ElementArray.add(new Element(ElementType)); // Add element with ElementType
    }
    public void AddElement(int ElementType, int Width, int Height, int CanvasType){
        ElementArray.add(new Element(ElementType, Width, Height, CanvasType)); // Add element with ElementType, Width, Height, CanvasType
    }
    public void AddElement(int ElementType, File FileLocation){
        ElementArray.add(new Element(ElementType, FileLocation)); // Add element with ElementType
    }
    public void AddElement(int ElementType, String PathInsideJar){
        ElementArray.add(new Element(ElementType, PathInsideJar)); // Add element with ElementType
    }
    public static void AddElementToJFrame(Element element, JFrame frame){ // Downside of using this function is that you cannot access the element in the JFrame once it
        frame.add(CreateJLabelFromElement(element));               // has been added
    }
    public void AddElementsToJFrameSerially(JFrame jframe, int VerticalShift){ // I have found this to be a good way to implement optional parameters in java
        AddElementsToJFrameSerially(jframe, VerticalShift, 0);
    }
    public void AddElementsToJFrameSerially(JFrame jframe, int VerticalShift, int HorisontalShift){ // Based on AddElementToJFrame; Adds each item in the RichContainer serially.
                                                                                                    // They will appear one after the other horisontally
        int horisontalRegister = 0;
        for (int i = 0; i < ElementArray.size(); i++){ // Repeat until the end of the List
            JLabel element = CreateJLabelFromElement(ElementArray.get(i));
            Rectangle bounds = element.getBounds();
            element.setBounds(bounds.x + horisontalRegister + HorisontalShift, bounds.y + VerticalShift, bounds.width, bounds.height);
            jframe.add(element);
            horisontalRegister = horisontalRegister + bounds.width;
        } 
    }
    public Element GetElement(int Index){ // Get data of specific element. Like AddElement, it exists as a layer of abstraction so I don't have to write as much code
        return ElementArray.get(Index);
    }
    public static int GetElementHeight(Element element){ // Get font heigh for text without font modification
        String defaultFontName = UIManager.getFont("Label.font").getFontName(); // get standard font data
        int defaultFontSize = UIManager.getFont("Label.font").getSize();
        int height = 0;
        Font selectedFont;
        FontMetrics metrics;
        switch (element.Type) {
            case 0: // Plaintext font size
                selectedFont = new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize)); // Create new font with correct size
                metrics = Toolkit.getDefaultToolkit().getFontMetrics(selectedFont); // Note: .getFontMetrics is deprecated
                height = metrics.getHeight(); // Get height
                break;
            case 1: // Title font size
                selectedFont = new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize * 2));
                metrics = Toolkit.getDefaultToolkit().getFontMetrics(selectedFont); // Note: .getFontMetrics is deprecated
                height = metrics.getHeight(); // Get height
                break;
            case 2: // Subtitle font size
                selectedFont = new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize * 1.5f));
                metrics = Toolkit.getDefaultToolkit().getFontMetrics(selectedFont); // Note: .getFontMetrics is deprecated
                height = metrics.getHeight(); // Get height
                break;
            case 3:
                ImageIcon temp = (ImageIcon)element.Content;
                height = temp.getIconHeight();
                break;
            default:
                throw new IllegalArgumentException("The element provided does not match one of the accepted types (0-3)"); // throw error if it is not the right type
        }
        return height;
    }
    public static int GetElementWidth(Element element){ // Similar execution to GetElementHeight
        String defaultFontName = UIManager.getFont("Label.font").getFontName(); 
        int defaultFontSize = UIManager.getFont("Label.font").getSize();
        Font selectedFont;
        FontMetrics metrics;
        int width;
        switch (element.Type) {
            case 0: // Plaintext font size
                selectedFont = new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize));
                metrics = Toolkit.getDefaultToolkit().getFontMetrics(selectedFont); // Note: .getFontMetrics is deprecated
                width = metrics.stringWidth(element.Content.toString()); // get width instead
                break;
            case 1: // Title font size
                selectedFont = new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize * 2));
                metrics = Toolkit.getDefaultToolkit().getFontMetrics(selectedFont); // Note: .getFontMetrics is deprecated
                width = metrics.stringWidth(element.Content.toString()); // get width instead
                break;
            case 2: // Subtitle font size
                selectedFont = new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize * 1.5f));
                metrics = Toolkit.getDefaultToolkit().getFontMetrics(selectedFont); // Note: .getFontMetrics is deprecated
                width = metrics.stringWidth(element.Content.toString()); // get width instead
                break;
            case 3:
                ImageIcon temp = (ImageIcon)element.Content;
                width = temp.getIconWidth();
                break;
            default:
                throw new IllegalArgumentException("The element provided does not match one of the accepted types (0-2)");
        }
        return width;
    }
    public static int GetMaximumRichContainerHeight(RichContainer container){
        int heightRegister = 0; // Keeps store of the current max height
        for (int i = 0; i < container.ElementArray.size(); i++){ // iterates through all elements of the container
            if (GetElementHeight(container.ElementArray.get(i)) > heightRegister) { // if an element is taller than the current recorded height...
                heightRegister = GetElementHeight(container.ElementArray.get(i)); // its height becomes the new max height
            }
        }
        return heightRegister;
    }
    public int ElementType(int Index){ // Returns the element type. Useful for interpreting the Content variable
        return ElementArray.get(Index).Type;
    }
    public void SetElement(int Index, Element element){ // Layer of abstraction like AddElement and GetElement
        ElementArray.set(Index, element); 
    }
    public void SetElementID(int Index, int id){ // Sets the ID for formating info
        ElementArray.get(Index).ID = id;
    }
    public void RemoveElement(int Index){ // Same thing again
        ElementArray.remove(Index);
    }
    public int NumberOfElements(){ // Same thing again
        return ElementArray.size();
    }
    public static JLabel CreateJLabelFromElement(Element element){
        JLabel label;
        String defaultFontName = UIManager.getFont("Label.font").getFontName();
        int defaultFontSize = UIManager.getFont("Label.font").getSize();
        switch(element.Type){
            case 0:
                label = new JLabel(element.Content.toString()); // Get text from the element
                label.setBounds(0, 0, GetElementWidth(element), GetElementHeight(element)); // Set label size
                break;
            case 1:
                label = new JLabel(element.Content.toString());
                label.setFont(new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize * 2))); // Font size is twice as large
                label.setBounds(0, 0, GetElementWidth(element), GetElementHeight(element));
                break;
            case 2: 
                label = new JLabel(element.Content.toString());
                label.setFont(new Font(defaultFontName, Font.PLAIN, (int)(defaultFontSize * 1.5f))); // Font size is 1.5x as large
                label.setBounds(0, 0, GetElementWidth(element), GetElementHeight(element));
                break;
            case 3:
                label = new JLabel((ImageIcon)(element.Content)); // Create label from specified image in element
                label.setBounds(0, 0, GetElementWidth(element), GetElementHeight(element));
                break;
            // Implement 3,4 later
            default:
                throw new IllegalArgumentException("The element that was inputted is corrupted");
        }
        return label;
    }
    public class Element { 
        int Type;// 0 = Plaintext (small), 1 = Title (large text), 2 = Subtitle (medium text), 3 = Static Canvas (image), 4 = Dynamic Canvas (imagebuffer)
        int ID; // Used for relating elements to formating information, such as font size, font type, color, etc
        Object Content; // Contains the content of the element, whether it be text, images, etc. WARNING: This is not type safe, and requires casting
        public Element(){ // Default initialization of Element
            Type = 0;
            Content = "Enter some text into this box"; // Type of String
        }
        public Element(int ElementType){
            Type = ElementType;
            switch (ElementType){
                case 0:
                case 1:
                case 2:
                    Content = "Enter some text into this box"; // Type of String
                    break;
                case 3:
                    Content = new ImageIcon(); // Type of ImageIcon
                    break;
                case 4:
                    // Throw exception if no image data is provided but element type is 4
                    throw new IllegalArgumentException("An element of Type 4 (Dynamic Canvas) needs arguments refering to the image's height, width, and type");
                default:
                    // Throw exception if type is not correct
                    throw new IllegalArgumentException("The element type provided is not a valid type (0-4)");
            }
        }
        
        Element(int ElementType, int Width, int Height, int CanvasType){ // Only for Type 4. CanvasType is the same as imageType
            if (ElementType != 4){ // This constructor is only for Type 4
                throw new IllegalArgumentException("Only an element of Type 4 (Dynamic Canvas) may take a Width, Height, and Canvas type");
            } else {
                Type = ElementType;
                Content = new BufferedImage(Width, Height, CanvasType);
            }
            
        }

        Element(int ElementType, File FileLocation){ // Only for Type 3. CanvasType is the same as imageType
            if (ElementType != 3){ // This constructor is only for Type 3
                throw new IllegalArgumentException("Only an element of Type 3 (Static Canvas) may take a File Location");
            } else if (FileLocation.canRead() != true){
                throw new IllegalArgumentException("The program is unable to access the file specified");
            } else {
                Type = ElementType;
                Content = new ImageIcon(FileLocation.getPath());
            }
            
        }

        Element(int ElementType, String PathInsideJar){ // For type 3, but for files in the .jar. PathInsideJar is relative to the RichContainer class
            if (ElementType != 3){ // This constructor is only for Type 4
                throw new IllegalArgumentException("Only an element of Type 3 (Static Canvas) may take a File Location");
            } else {
                try {
                    InputStream input = RichContainer.class.getResourceAsStream(PathInsideJar); // Get the InputStream to the image
                    BufferedImage img = ImageIO.read(input); // Get the Image type from the image
                    Content = new ImageIcon(img); // Set content to be the ImageIcon of Image
                } catch (Exception e) {
                    throw new IllegalArgumentException("The program is unable to access the file specified"); // Happens if the file is not accessable;
                                                                                                              // Also solves checked exceptions
                }
                Type = ElementType; // element variable Type
                
            }
            
        }
    }
    List<Element> ElementArray = new ArrayList<Element>(); // Contains all Elements in the container
}
    