package CRUD_Exercise;

// Java program to update in the file "friendsContact.txt"
// and change the number of an old contact

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class UpdateFriend {
    public boolean isContactFound = false;  // Indica si el contacto ha sido encontrado
    private String contactNameToUpdate;      // Almacena el nombre del contacto encontrado
    private long contactNumberToUpdate;      // Almacena el número del contacto encontrado


    public void buscarContacto(JTextField txtNombre, JTextField txtNumero, JButton btnActualizar, JLabel lblResultado) {
        try {
            String newName = txtNombre.getText();
            long newNumber = Long.parseLong(txtNumero.getText());

            File file = new File("C:\\Users\\Usuario\\Desktop\\Universidad\\POO\\Actividad 6\\Actividad6\\src\\main\\java\\CRUD_Exercise\\contactos.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            String nameNumberString;
            String name;
            long number;
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                // Si el contacto es encontrado
                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    contactNameToUpdate = name;
                    contactNumberToUpdate = number;
                    isContactFound = true; // Marcamos que el contacto fue encontrado
                    btnActualizar.setText("Actualizar Contacto"); // Cambiar el texto del botón
                    lblResultado.setText("Contacto encontrado: " + name + " (" + number + ") Cambia el nombre y numero");
                    txtNombre.setText("");
                    txtNumero.setText("");
                    break;
                }
            }

            if (!found) {
                lblResultado.setText("El contacto no existe.");
            }

            raf.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
    }

    public void actualizarContacto(JTextField txtNombre, JTextField txtNumero, JLabel lblResultado, JButton btnActualizar) {
        try {
            File file = new File("C:\\Users\\CApat\\OneDrive\\Documentos\\Archivos de clases\\Alura Oracle curso\\Actividad6_POO\\src\\main\\java\\CRUD_Exercise\\contactos.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            File tmpFile = new File("C:\\Users\\CApat\\OneDrive\\Documentos\\Archivos de clases\\Alura Oracle curso\\Actividad6_POO\\src\\main\\java\\CRUD_Exercise\\temp.txt");
            RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

            raf.seek(0);
            String nameNumberString;

            // Leer el archivo original y copiar al archivo temporal
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                // Actualizar si el contacto es el encontrado
                if (name.equals(contactNameToUpdate) && number == contactNumberToUpdate) {
                    String nuevoNombre = txtNombre.getText();
                    long nuevoNumero = Long.parseLong(txtNumero.getText());
                    nameNumberString = nuevoNombre + "!" + nuevoNumero;
                }

                tmpraf.writeBytes(nameNumberString);
                tmpraf.writeBytes(System.lineSeparator());
            }

            // Copiar el contenido actualizado de nuevo al archivo original
            raf.seek(0);
            tmpraf.seek(0);
            while (tmpraf.getFilePointer() < tmpraf.length()) {
                raf.writeBytes(tmpraf.readLine());
                raf.writeBytes(System.lineSeparator());
            }

            raf.setLength(tmpraf.length());
            raf.close();
            tmpraf.close();
            tmpFile.delete();

            lblResultado.setText("Contacto actualizado exitosamente.");
            btnActualizar.setText("Buscar Contacto");
            isContactFound = false; // Reiniciar estado
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
    }
}

