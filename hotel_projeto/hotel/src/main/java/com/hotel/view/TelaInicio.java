package com.hotel.view;

import javax.swing.JButton;

import com.hotel.service.HospedeService;
import com.hotel.service.QuartoService;

public class TelaInicio extends javax.swing.JFrame {


    private JButton botaoHospede;
    private JButton botaoQuarto;

    private HospedeService hospedeService;
    private QuartoService quartoService;

    public TelaInicio(HospedeService hospedeService, QuartoService quartoService) {
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
        getContentPane().setBackground(new java.awt.Color(143, 188, 143));
        setLayout(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Inicial");
        setSize(400, 300);
        setLocationRelativeTo(null);

        botaoHospede = new JButton("Gerenciar Hóspedes");
        botaoHospede.addActionListener(e -> {
            TelaHospede telaHospede = new TelaHospede(hospedeService);
            telaHospede.setVisible(true);
            this.dispose();
        });
        botaoHospede.setBounds(100, 80, 200, 30);
        add(botaoHospede);

        botaoQuarto = new JButton("Gerenciar Quartos");
        botaoQuarto.addActionListener(e -> {
            TelaQuarto telaQuarto = new TelaQuarto(quartoService);
            telaQuarto.setVisible(true);
            this.dispose();
        });
        botaoQuarto.setBounds(100, 120, 200, 30);
        add(botaoQuarto);
    }

    /* 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaInicio().setVisible(true);
        });
    }

    */
}
