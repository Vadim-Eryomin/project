package Server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Reader {
    public static String readByLoginAndPassword(String login, String password) throws FileNotFoundException {
        FileReader reader = new FileReader("users.txt");
        Scanner scanner = new Scanner(reader);
        boolean enter = false;
        String name = null;
        String surname = null;
        while (scanner.hasNextLine() && !enter){
            String line = scanner.nextLine();
            String[] data = line.split(",");
            if (login.equals(data[0]) && password.equals(data[1])){
                enter = true;
                name = data[2];
                surname = data[3];
            }
        }
        return name == null ? "0" : name+","+surname;
    }
}
