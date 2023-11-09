import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.imageio.*;

public class EscalacaoApp {
    private JFrame frame;
    private JPanel panel1, panel2;
    private JButton avancarButton;
    private JButton[] posicoes;
    private String[] nomes = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "MC 1", "MC 2", "MEI", "PD", "ATA", "PE", "Técnico" };
    private Image backgroundImage;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EscalacaoApp window = new EscalacaoApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EscalacaoApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setSize(800, 850); // Define o tamanho da janela
        frame.setResizable(false); // Impede que a janela seja maximizada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            backgroundImage =
                ImageIO.read(new File("C:\\Users\\PedroGado\\Documents\\Java Dev\\My Dev\\EscalacaoApp\\lib\\img\\campo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel1 = new JPanel();
        frame.getContentPane().add(panel1, BorderLayout.CENTER);
        panel1.setLayout(null);

        final JLabel lblBemVindo = new JLabel("Bem-vindo ao Portal de escalação Torcedor!");
        lblBemVindo.setBounds(275, 0, 250, 50);
        panel1.add(lblBemVindo);

        avancarButton = new JButton("Avançar");
        avancarButton.setBounds(350, 500, 100, 50);
        panel1.add(avancarButton);
        avancarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel1);
                panel2 = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
                };
                frame.getContentPane().add(panel2, BorderLayout.CENTER);
                panel2.setLayout(null);
                posicoes = new JButton[12];
                int[][] coords = {

                    // Número à esquerda (x) = posição horizontal | Número à direita (y) = posição vertical
                    { 250, 630 }, // GOL
                    { 450, 440 }, // LD
                    { 320, 470 }, // ZAG 1
                    { 180, 470 }, // ZAG 2
                    { 40, 440 }, // LE
                    { 320, 320 }, // MC 1
                    { 180, 320 }, // MC 2
                    { 250, 180 }, // MEI
                    { 40, 70 }, // PD
                    { 250, 40 }, // ATA
                    { 450, 70 }, // PE
                    { 650, 660 } // Técnico

                };
                for (int i = 0; i < 12; i++) {
                    posicoes[i] = new JButton(nomes[i]);
                    posicoes[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Cria uma nova janela para inserir os dados do jogador
                            final JDialog dialog = new JDialog(frame, "Dados do Jogador", true);
                            dialog.setLayout(new GridLayout(4, 1));
                            final JTextField nomeField = new JTextField("Nome do Jogador");
                            final JTextField pontuacaoField = new JTextField("Pontuação do Jogador");
                            JButton imagemButton = new JButton("Selecionar Imagem");
                            imagemButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // Abre um seletor de arquivos para o usuário escolher a imagem do jogador
                                }
                            });
                            JButton okButton = new JButton("OK");
                            okButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // Atualiza o botão na formação com os dados inseridos pelo usuário
                                    JButton source = (JButton) e.getSource();
                                    source.setText(nomeField.getText() + "\n" + pontuacaoField.getText());
                                    dialog.dispose();
                                }
                            });
                            dialog.add(nomeField);
                            dialog.add(pontuacaoField);
                            dialog.add(imagemButton);
                            dialog.add(okButton);
                            dialog.pack();
                            dialog.setVisible(true);
                        }
                    });
                    posicoes[i].setBounds(coords[i][0], coords[i][1], 100, 130);
                    panel2.add(posicoes[i]);
                }
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
