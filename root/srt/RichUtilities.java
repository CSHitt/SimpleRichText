package srt;
import javax.swing.JFrame;

public class RichUtilities {
    public static void AddRichContainersToJFrameSerially(JFrame jframe, RichContainer[] containers, int VerticalShift, int HorisontalShift){
        int verticalRegister = 0;
        for (int i = 0; i < containers.length; i++){
            containers[i].AddElementsToJFrameSerially(jframe, verticalRegister + VerticalShift, HorisontalShift);
            verticalRegister = verticalRegister + containers[i].GetMaximumRichContainerHeight(containers[i]);
        }
    }
}