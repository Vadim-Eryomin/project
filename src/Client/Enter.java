package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Enter extends JPanel {
    public Enter() {
        setLayout(new GridLayout(5,1));
        JLabel enterLogin = new JLabel("Логин");
        JTextField login = new JTextField();
        JLabel enterPassword = new JLabel("Пароль");
        JTextField password = new JTextField();
        JButton enter = new JButton("Войти");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataOutputStream dos = new DataOutputStream(Frame.socket.getOutputStream());
                    dos.writeUTF("/auth " + login.getText() + " " + password.getText());
                    dos.flush();
                    DataInputStream dis = new DataInputStream(Frame.socket.getInputStream());
                    String entered = dis.readUTF();
                    if (entered.equals("0")){
                        login.setText("");
                        password.setText("");
                        System.out.println("вы не вошли");
                    }
                    else {
                        System.out.println("вы вошли");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(enterLogin);
        add(login);
        add(enterPassword);
        add(password);
        add(enter);


    }
}
