package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SummitSystem {
    static ArrayList<Socket> sockets = new ArrayList<>();
    static Request lastRequest;

    public SummitSystem() throws IOException {
        ServerSocket server = new ServerSocket(12345);
        while (true) {
            Socket socket = server.accept();
            sockets.add(socket);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        read(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void read(Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        while (true) {
            String line = dis.readUTF();
            //     /set China 2 22 23
            String[] data = line.split(" ");
            if (data[0].equals("/auth")) {
                // /auth login password
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(Reader.readByLoginAndPassword(data[1], data[2]));
                dos.flush();
            }

            if (data[0].equals("/set")) {
                Request request = new Request(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), sockets.size());
                lastRequest = request;
                for (int i = 0; i < sockets.size(); i++) {
                    ObjectOutputStream dos = new ObjectOutputStream(sockets.get(i).getOutputStream());
                    dos.writeObject(request);
                    dos.flush();
                    dos.close();
                }


            }
            if (data[0].equals("/check")) {
                //    /check +/-
                if (data[1].equals("+")) {
                    if (lastRequest != null) {
                        lastRequest.count++;
                        if (lastRequest.count == lastRequest.max) {
                            for (int i = 0; i < sockets.size(); i++) {
                                ObjectOutputStream dos = new ObjectOutputStream(sockets.get(i).getOutputStream());
                                dos.writeObject(lastRequest);
                                dos.flush();
                                dos.close();
                            }
                        }
                    }
                } else {
                    lastRequest = null;
                }
            }
        }
    }
}
