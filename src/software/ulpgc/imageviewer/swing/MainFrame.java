package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageDisplay;
import software.ulpgc.imageviewer.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        this.setTitle("Image viewer");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        add(createImageDisplay());
        add(createToolBar(), BorderLayout.SOUTH);
    }

    private Component createToolBar() {
        JPanel panel = new JPanel();
        panel.add(createButton("<"));
        panel.add(createButton(">"));
        return panel;
    }

    private Component createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(e -> commands.get(label).execute());
        return button;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }
}
