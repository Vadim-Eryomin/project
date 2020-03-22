package Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Frame extends JFrame {
    public static Socket socket;
    public Frame() throws HeadlessException, IOException {
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        socket = new Socket("localhost", 12345);
        add(new Enter());
    }
}
