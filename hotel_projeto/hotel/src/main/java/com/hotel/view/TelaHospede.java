package com.hotel.view;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.hotel.entity.Hospede;
import com.hotel.service.HospedeService;

public class TelaHospede extends JFrame{
    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoEmail;
    private JTextField campoTelefone;

    private JButton botaoSalvar;
    private JButton botaoBuscar;
    private JButton botaoLimpar;
    private JButton botaoDeletar;
    private JButton botaoInicio;
    

    @Autowired
    private HospedeService hospedeService;

    public TelaHospede(HospedeService hospedeService) {
    this.hospedeService = hospedeService;
    
    getContentPane().setBackground(new Color(143, 188, 143));
    setTitle("Tela Hospede");
    setSize(750, 850);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    JTextArea areaLista = new JTextArea();
    areaLista.setBounds(20, 55, 590, 150);
    add(areaLista);

    campoId = new JTextField();
    campoId.setBounds(150, 230, 300, 25);
    add(campoId);

    campoNome = new JTextField();
    campoNome.setBounds(150, 270, 300, 25);
    add(campoNome);

    campoCpf = new JTextField();
    campoCpf.setBounds(150, 310, 300, 25);
    add(campoCpf);

    campoEmail = new JTextField();
    campoEmail.setBounds(150, 350, 300, 25);
    add(campoEmail);    

    campoTelefone = new JTextField();
    campoTelefone.setBounds(150, 390, 300, 25);
    add(campoTelefone);    

    JLabel labelTitulo = new JLabel("Lista de Hóspedes");
    labelTitulo.setBounds(90, 0, 500, 60);
    labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 30));
    add(labelTitulo);

    JLabel labelId = new JLabel("ID");
    labelId.setBounds(30, 230, 200, 25);
    add(labelId);    

    JLabel labelNome = new JLabel("Nome");
    labelNome.setBounds(30, 270, 200, 25);
    add(labelNome);
    
    JLabel labelCpf = new JLabel("CPF");
    labelCpf.setBounds(30, 310, 200, 25);
    add(labelCpf);
        
    JLabel labelEmail = new JLabel("Email");
    labelEmail.setBounds(30, 350, 200, 25);
    add(labelEmail);

    JLabel labelTelefone = new JLabel("Telefone");
    labelTelefone.setBounds(30, 390, 200, 25);
    add(labelTelefone);

    botaoSalvar = new JButton("Salvar");
    botaoSalvar.addActionListener(e -> {
        try {
            if(!campoId.getText().isBlank()) {
                throw new IllegalArgumentException("ID e gerado automaticamente.");

            }
            if (campoNome.getText().isBlank() || campoCpf.getText().isBlank() || campoEmail.getText().isBlank()) {
                throw new IllegalArgumentException("Nome, CPF e Email são obrigatórios.");       
            }

            Hospede hospede = new Hospede();
            hospede.setNome(campoNome.getText());
            hospede.setCpf(campoCpf.getText()); 
            hospede.setEmail(campoEmail.getText());
            hospede.setTelefone(campoTelefone.getText());
            hospedeService.criar(hospede);
            JOptionPane.showMessageDialog(null, "Hóspede salvo com sucesso!");
            try{
                areaLista.setText("");
                List<Hospede> hospedes = hospedeService.listarTodos();
                for (Hospede h : hospedes) {
                    areaLista.append("ID: " + h.getId() + " | Nome: " + h.getNome() + " | CPF: " + h.getCpf() + " | Email: " + h.getEmail() + " | Telefone: " + h.getTelefone() + "\n");
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao carregar hóspedes: " + ex.getMessage());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar hóspede: " + ex.getMessage());
        }
    });
    botaoSalvar.setBounds(30, 460, 100, 30);
    add(botaoSalvar);

    botaoBuscar = new JButton("Buscar");
    botaoBuscar.addActionListener(e -> {
        try {
            Long id = Long.parseLong(campoId.getText());
            Hospede hospede = hospedeService.buscarPorId(id);
            campoNome.setText(hospede.getNome());
            campoCpf.setText(hospede.getCpf());
            campoEmail.setText(hospede.getEmail());
            campoTelefone.setText(hospede.getTelefone());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar hóspede: " + ex.getMessage());
        }
    });
    botaoBuscar.setBounds(140, 460, 100, 30);
    add(botaoBuscar);

    botaoLimpar = new JButton("Limpar");
    botaoLimpar.addActionListener(e -> {
        campoId.setText("");
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoTelefone.setText("");
    });
    botaoLimpar.setBounds(250, 460, 100, 30);
    add(botaoLimpar);

    botaoDeletar = new JButton("Deletar");
    botaoDeletar.addActionListener(e -> {
        try {
            if (campoId.getText().isBlank()) {
                throw new Exception("Informe o Id.");
            }
            Long id = Long.parseLong(campoId.getText());
            Hospede hospede = hospedeService.buscarPorId(id);
            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o hóspede: " + hospede.getNome() + "?", "Confirmação de Deleção", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                hospedeService.deletar(id);
                JOptionPane.showMessageDialog(null, "Hóspede deletado com sucesso!");
                campoId.setText("");
                campoNome.setText("");
                campoCpf.setText("");
                campoEmail.setText("");
                campoTelefone.setText("");
            }
                try{
                    areaLista.setText("");
                    List<Hospede> hospedes = hospedeService.listarTodos();
                    for (Hospede h : hospedes) {
                        areaLista.append("ID: " + h.getId() + " | Nome: " + h.getNome() + " | CPF: " + h.getCpf() + " | Email: " + h.getEmail() + " | Telefone: " + h.getTelefone() + "\n");
                    }  
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro ao carregar hóspedes: " + ex.getMessage());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar hóspede: " + ex.getMessage());
            }
    });
    botaoDeletar.setBounds(360, 460, 100, 30);
    add(botaoDeletar);
    /* 
    botaoInicio = new JButton("Início");
    botaoInicio.addActionListener(e -> {
        TelaInicio telaInicio = new TelaInicio(hospedeService, null);
        telaInicio.setVisible(true);
        this.dispose();
    });
    botaoInicio.setBounds(470, 460, 100, 30);
    add(botaoInicio);
    */



    try{
        List<Hospede> hospedes = hospedeService.listarTodos();
        for (Hospede h : hospedes) {
            areaLista.append("ID: " + h.getId() + " | Nome: " + h.getNome() + " | CPF: " + h.getCpf() + " | Email: " + h.getEmail() + " | Telefone: " + h.getTelefone() + "\n");
        }
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Erro ao carregar hóspedes: " + ex.getMessage());
    }


}
}

