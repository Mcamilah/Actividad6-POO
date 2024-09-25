package CRUD_Exercise;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {
    public JPanel panel1;
    private JTextField txtNombre;
    private JTextField txtNumero;
    private JLabel newName;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JLabel lblResultado;
    private UpdateFriend actualizar = new UpdateFriend();

    public Interfaz() {
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            long numero = Long.parseLong(txtNumero.getText());

            AddFriend agregarAmigo = new AddFriend(nombre, numero);
            agregarAmigo.addContact();
            lblResultado.setText("Contacto agregado.");
        });
        btnMostrar.addActionListener(e -> {
            DisplayFriends mostrarAmigos = new DisplayFriends();
            mostrarAmigos.displayContacts(lblResultado);
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!actualizar.isContactFound) {
                    // Primer clic: Buscar el contacto
                    actualizar.buscarContacto(txtNombre, txtNumero, btnActualizar, lblResultado);
                } else {
                    // Segundo clic: Actualizar el contacto
                    actualizar.actualizarContacto(txtNombre, txtNumero, lblResultado, btnActualizar);
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteFriend eliminar = new DeleteFriend();
                eliminar.deleteContact(txtNombre, lblResultado);
            }
        });

    }
}