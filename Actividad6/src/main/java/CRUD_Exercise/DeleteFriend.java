package CRUD_Exercise;

// Java program to delete a contact
// from the file "friendsContact.txt"

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class DeleteFriend {
    public void deleteContact(JTextField txtNombre, JLabel lblResultado){
        try {
            // Get the name of the contact to be updated
            // from the Command line argument
            String nuevoNombre = txtNombre.getText();

            String nameNumberString;
            String name;
            long number;
            int index;

            // Using file pointer creating the file.
            File file = new File("C:\\Users\\Usuario\\Desktop\\Universidad\\POO\\Actividad 6\\Actividad6\\src\\main\\java\\CRUD_Exercise\\contactos.txt");

            if (!file.exists()) {

                // Create a new file if not exists.
                file.createNewFile();
            }

            // Opening file in reading and write mode.
            RandomAccessFile raf
                    = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Checking whether the name of contact exists.
            // getFilePointer() give the current offset
            // value from start of the file.
            while (raf.getFilePointer() < raf.length()) {

                // reading line from the file.
                nameNumberString = raf.readLine();

                // splitting the string to get name and
                // number
                String[] lineSplit
                        = nameNumberString.split("!");

                // separating name and number.
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                // if condition to find existence of record.
                if (name.equals(nuevoNombre)) {
                    found = true;
                    break;
                }
            }

            // Delete the contact if record exists.
            if (found == true) {

                // Creating a temporary file
                // with file pointer as tmpFile.
                File tmpFile = new File("C:\\Users\\CApat\\OneDrive\\Documentos\\Archivos de clases\\Alura Oracle curso\\Actividad6_POO\\src\\main\\java\\CRUD_Exercise\\temp.txt");

                // Opening this temporary file
                // in ReadWrite Mode
                RandomAccessFile tmpraf
                        = new RandomAccessFile(tmpFile, "rw");

                // Set file pointer to start
                raf.seek(0);

                // Traversing the friendsContact.txt file
                while (raf.getFilePointer()
                        < raf.length()) {

                    // Reading the contact from the file
                    nameNumberString = raf.readLine();

                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(
                            0, index);

                    // Check if the fetched contact
                    // is the one to be deleted
                    if (name.equals(nuevoNombre)) {

                        // Skip inserting this contact
                        // into the temporary file
                        continue;
                    }

                    // Add this contact in the temporary
                    // file
                    tmpraf.writeBytes(nameNumberString);

                    // Add the line separator in the
                    // temporary file
                    tmpraf.writeBytes(
                            System.lineSeparator());
                }

                // The contact has been deleted now
                // So copy the updated content from
                // the temporary file to original file.

                // Set both files pointers to start
                raf.seek(0);
                tmpraf.seek(0);

                // Copy the contents from
                // the temporary file to original file.
                while (tmpraf.getFilePointer()
                        < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                // Set the length of the original file
                // to that of temporary.
                raf.setLength(tmpraf.length());

                // Closing the resources.
                tmpraf.close();
                raf.close();

                // Deleting the temporary file
                tmpFile.delete();
                lblResultado.setText("Contacto eliminado");
                System.out.println(" Friend deleted. ");
            }

            // The contact to be deleted
            // could not be found
            else {

                // Closing the resources.
                raf.close();

                lblResultado.setText("El nombre ingresado no existe");
                // Print the message
                System.out.println(" Input name"
                        + " does not exists. ");
            }
        }

        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}


