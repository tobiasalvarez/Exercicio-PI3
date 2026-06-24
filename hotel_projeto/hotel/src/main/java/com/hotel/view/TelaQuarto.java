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

import com.hotel.entity.Quarto;
import com.hotel.service.QuartoService;

public class TelaQuarto extends JFrame {
    
    private JTextField campoId;
    private JTextField campoNumero;
    private JTextField campoTipo;
    private JTextField campoPrecoDiaria;
    private JTextField campoCapacidade;
    private JTextField campoStatus;

    private JButton botaoSalvar;
    private JButton botaoBuscar;
    private JButton botaoLimpar;
    private JButton botaoDeletar;

    @Autowired 
    QuartoService quartoService;

    
    public TelaQuarto(QuartoService quartoService) {

        this.quartoService = quartoService;
        setTitle("Tela Quarto");
        setSize(750, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(143, 188, 143));

        JTextArea areaLista = new JTextArea();
        areaLista.setBounds(20, 55, 590, 150);
        add(areaLista);

        campoId = new JTextField();


    campoId.setBounds(150, 230, 300, 25);
    add(campoId);

    campoNumero = new JTextField();
    campoNumero.setBounds(150, 270, 300, 25);
    add(campoNumero);

    campoTipo = new JTextField();
    campoTipo.setBounds(150, 310, 300, 25);
    add(campoTipo);

    campoPrecoDiaria = new JTextField();
    campoPrecoDiaria.setBounds(150, 350, 300, 25);
    add(campoPrecoDiaria);    

    campoCapacidade = new JTextField();
    campoCapacidade.setBounds(150, 390, 300, 25);
    add(campoCapacidade);    

    campoStatus = new JTextField();
    campoStatus.setBounds(150, 430, 300, 25);
    add(campoStatus);     

    JLabel labelTitulo = new JLabel("Lista de Quartos");
    labelTitulo.setBounds(90, 0, 500, 60);
    labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 30));
    add(labelTitulo);

    
    JLabel labelId = new JLabel("ID");
    labelId.setBounds(30, 230, 200, 25);
    add(labelId);    

    JLabel labelNumero = new JLabel("Número");
    labelNumero.setBounds(30, 270, 200, 25);
    add(labelNumero);
    
    JLabel labelTipo = new JLabel("Tipo");
    labelTipo.setBounds(30, 310, 200, 25);
    add(labelTipo);
        
    JLabel labelPrecoDiaria = new JLabel("Preço da Diária");
    labelPrecoDiaria.setBounds(30, 350, 200, 25);
    add(labelPrecoDiaria);

    JLabel labelCapacidade = new JLabel("Capacidade");
    labelCapacidade.setBounds(30, 390, 200, 25);
    add(labelCapacidade);

    JLabel labelStatus = new JLabel("Status");
    labelStatus.setBounds(30, 430, 200, 25);
    add(labelStatus);

    botaoSalvar = new JButton("Salvar");
    botaoSalvar.addActionListener(e -> {
        try {
            if(!campoId.getText().isBlank()) {
                throw new IllegalArgumentException("ID e gerado automaticamente.");

            }
            if (campoNumero.getText().isBlank() || campoTipo.getText().isBlank() || campoPrecoDiaria.getText().isBlank()|| campoCapacidade.getText().isBlank() || campoStatus.getText().isBlank()) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios.");       
            }

            Quarto quarto = new Quarto();
            quarto.setNumero(campoNumero.getText());
            quarto.setTipo(campoTipo.getText());
            quarto.setPrecoDiaria(Double.parseDouble(campoPrecoDiaria.getText()));
            quarto.setCapacidade(Integer.parseInt(campoCapacidade.getText()));
            quarto.setStatus(campoStatus.getText());
            quartoService.criar(quarto);
            JOptionPane.showMessageDialog(null, "Quarto salvo com sucesso!");
            try{
                areaLista.setText("");
                List<Quarto> quartos = quartoService.listarTodos();
                for (Quarto q : quartos) {
                    areaLista.append("ID: " + q.getId() + " | Número: " + q.getNumero() + " | Tipo: " + q.getTipo() + " | Preço da Diária: " + q.getPrecoDiaria() + " | Capacidade: " + q.getCapacidade() + " | Status: " + q.getStatus() + "\n");
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao carregar quartos: " + ex.getMessage());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar quarto: " + ex.getMessage());
        }
    });
    botaoSalvar.setBounds(30, 460, 100, 30);
    add(botaoSalvar);

    botaoBuscar = new JButton("Buscar");
    botaoBuscar.addActionListener(e -> {
        try {
            Long id = Long.parseLong(campoId.getText());
            Quarto quarto = quartoService.buscarPorId(id);
            campoNumero.setText(quarto.getNumero());
            campoTipo.setText(quarto.getTipo());
            campoPrecoDiaria.setText(String.valueOf(quarto.getPrecoDiaria()));
            campoCapacidade.setText(String.valueOf(quarto.getCapacidade()));
            campoStatus.setText(quarto.getStatus());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar quarto: " + ex.getMessage());
        }
    });
    botaoBuscar.setBounds(140, 460, 100, 30);
    add(botaoBuscar);

    botaoLimpar = new JButton("Limpar");
    botaoLimpar.addActionListener(e -> {
        campoId.setText("");
        campoNumero.setText("");
        campoTipo.setText("");
        campoPrecoDiaria.setText("");
        campoCapacidade.setText("");
        campoStatus.setText("");
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
            Quarto quarto = quartoService.buscarPorId(id);
            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o quarto: " + quarto.getNumero() + "?", "Confirmação de Deleção", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                quartoService.deletar(id);
                JOptionPane.showMessageDialog(null, "Quarto deletado com sucesso!");
                campoId.setText("");
                campoNumero.setText("");
                campoTipo.setText("");
                campoPrecoDiaria.setText("");
                campoCapacidade.setText("");
                campoStatus.setText("");
            }
                try{
                    areaLista.setText("");
                    List<Quarto> quartos = quartoService.listarTodos();
                    for (Quarto q : quartos) {
                        areaLista.append("ID: " + q.getId() + " | Número: " + q.getNumero() + " | Tipo: " + q.getTipo() + " | Preço da Diária: " + q.getPrecoDiaria() + " | Capacidade: " + q.getCapacidade() + " | Status: " + q.getStatus() + "\n");
                    }  
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro ao carregar quartos: " + ex.getMessage());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar quarto: " + ex.getMessage());
            }
    });
    botaoDeletar.setBounds(360, 460, 100, 30);
    add(botaoDeletar);

    try{
                    areaLista.setText("");
                    List<Quarto> quartos = quartoService.listarTodos();
                    for (Quarto q : quartos) {
                        areaLista.append("ID: " + q.getId() + " | Número: " + q.getNumero() + " | Tipo: " + q.getTipo() + " | Preço da Diária: " + q.getPrecoDiaria() + " | Capacidade: " + q.getCapacidade() + " | Status: " + q.getStatus() + "\n");
                    }  
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro ao carregar quartos: " + ex.getMessage());
                }
    }

}
