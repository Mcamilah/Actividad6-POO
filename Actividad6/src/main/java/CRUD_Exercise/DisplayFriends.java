package CRUD_Exercise;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DisplayFriends {

    public void displayContacts(JLabel jLabel) {
        try {
            File file = new File("C:\\Users\\Usuario\\Desktop\\Universidad\\POO\\Actividad 6\\Actividad6\\src\\main\\java\\CRUD_Exercise\\contactos.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            StringBuilder resultado = new StringBuilder();
            String nameNumberString;

            // Leer y mostrar todos los contactos
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                resultado.append("Nombre: ").append(name)
                        .append(", Número: ").append(number)
                        .append("\n");
            }

            jLabel.setText("<html>" + resultado.toString().replace("\n", "<br>") + "</html>");

            System.out.println(resultado.toString());

            raf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
