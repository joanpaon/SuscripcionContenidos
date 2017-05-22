/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.japo.java.components.BackgroundPanel;
import org.japo.java.events.AEM;
import org.japo.java.lib.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {
    
    // Fichero
    private final String FICHERO = "email.txt";
    
    // Referencias Componentes
    JLabel lblEMail;
    JTextField txfEMail;

    // Constructor
    public GUI() {
        // Inicializar GUI - PREVIA
        beforeInit();

        // Construcción - GUI
        initComponents();

        // Inicializar GUI - POSTERIOR
        afterInit();
    }

    // Inicializar GUI - PREVIA
    private void beforeInit() {

    }

    // Inicializar GUI - POSTERIOR
    private void afterInit() {

    }

    // Construcción - GUI
    private void initComponents() {
        // Fuentes
        Font fntLBL = new Font("Calibri", Font.PLAIN, 30);
        Font fntTXF = new Font("Georgia", Font.PLAIN, 40);

        // Bordes
        EmptyBorder brdPNL = new EmptyBorder(20, 20, 20, 20);

        // Tamaños
        Dimension dimFRM = new Dimension(600, 200);
        Dimension dimTXF = new Dimension(0, 70);

        // Gestor de Eventos de Accion
        AEM aem = new AEM(this);

        // Etiqueta
        lblEMail = new JLabel("Entre su EMail");
        lblEMail.setFont(fntLBL);

        // Campo
        txfEMail = new JTextField();
        txfEMail.setFont(fntTXF);
        txfEMail.setPreferredSize(dimTXF);
        txfEMail.setHorizontalAlignment(JTextField.CENTER);
        txfEMail.addActionListener(aem);

        // Imagen Fondo Panel - Recurso
        String rutaIMG = "/img/background.jpg";
        URL url = getClass().getResource(rutaIMG);
        Image img = new ImageIcon(url).getImage();

        // Panel Principal
        JPanel pnlMain = new BackgroundPanel(img);
        pnlMain.setBorder(brdPNL);
        pnlMain.setLayout(new BorderLayout());
        pnlMain.add(lblEMail, BorderLayout.NORTH);
        pnlMain.add(txfEMail, BorderLayout.SOUTH);

        // Ventana principal
        setTitle("Suscripción Contenidos");
        setContentPane(pnlMain);
        setResizable(false);
        setSize(dimFRM);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void procesarAccion(ActionEvent e) {
        // Añade Suscripción
        registrarSuscripcion();
        
        // Termina el programa
        UtilesSwing.terminarPrograma(this);
    }
    
    private void registrarSuscripcion() {
        try (
            FileWriter fw = new FileWriter(FICHERO);
            PrintWriter salida = new PrintWriter(fw)) {

            // Obtiene el email de usuario
            String email = txfEMail.getText();

            // Escribe el EMAIL en el fichero
            salida.write(email);

            // Mensaje informativo
            JOptionPane.showMessageDialog(this, "Suscripción realizada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
    }
}
