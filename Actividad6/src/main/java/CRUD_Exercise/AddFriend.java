package CRUD_Exercise;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AddFriend {
    private String newName;
    private long newNumber;

    public AddFriend(String newName, long newNumber) {
        this.newName = newName;
        this.newNumber = newNumber;
    }

    public void addContact() {
        try {
            // Abrir el archivo donde se almacenan los contactos
            File file = new File("C:\\Users\\Usuario\\Desktop\\Universidad\\POO\\Actividad 6\\Actividad6\\src\\main\\java\\CRUD_Exercise\\contactos.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            boolean found = false;
            String nameNumberString;

            // Verificar si el contacto ya existe
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            // Si no existe, agregar el nuevo contacto
            if (!found) {
                nameNumberString = newName + "!" + newNumber;
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());
                System.out.println("Contacto agregado.");
            } else {
                System.out.println("El contacto ya existe.");
            }

            raf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

