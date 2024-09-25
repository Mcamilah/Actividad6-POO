package CRUD_Exercise;

// Java program to create a file "friendsContact.txt"
// and add a new contact in the file

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.setContentPane(interfaz.panel1);
        interfaz.setSize(700, 400);
        interfaz.setVisible(true);
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
