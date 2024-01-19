package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.Image;
import software.ulpgc.imageviewer.control.NextImageCommand;
import software.ulpgc.imageviewer.control.PreviousImageCommand;
import software.ulpgc.imageviewer.file.FileImageLoader;

import java.io.File;

public class Main {
    public static final String folder = "C:/Users/ncdv2/OneDrive/Imágenes/Saved Pictures";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(folder)).load();
        frame.getImageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.getImageDisplay()));
        frame.add(">", new NextImageCommand(frame.getImageDisplay()));
        frame.setVisible(true);
    }
}
