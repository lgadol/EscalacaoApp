import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

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
    ArrayList<String> nomesJogadores = new ArrayList<String>();
    ArrayList<String> pontuacoesJogadores = new ArrayList<String>();

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
        lblBemVindo.setBounds(275, 0, 350, 50);
        panel1.add(lblBemVindo);

        avancarButton = new JButton("Avançar");
        avancarButton.setBounds(350, 750, 100, 50);
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

                final JLabel mediaLabel = new JLabel("Média geral do time: 0");
                mediaLabel.setBounds(10, 10, 200, 30);
                panel2.add(mediaLabel);

                posicoes = new JButton[12];
                int[][] coords = { { 250, 630 }, // GOL
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

                final java.util.List<Integer> pontuacoes = new java.util.ArrayList<>();
                    
                for (int i = 0; i < 12; i++) {
                    final int index = i;
                    final int[][] finalCoords = coords;
                    posicoes[index] = new JButton(nomes[index]);
                    posicoes[index].setLayout(new BorderLayout());
                    posicoes[index].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Cria uma nova janela para inserir os dados do jogador
                            final JButton clickedButton = (JButton) e.getSource();
                            final JDialog dialog = new JDialog(frame, "Dados do Jogador", true);
                            dialog.setLayout(new GridLayout(6, 1)); // Aumente o número de linhas para acomodar o botão de reset
                            dialog.add(new JLabel("Nome do Jogador"));
                            final JTextField nomeField = new JTextField();
                            dialog.add(nomeField);
                            dialog.add(new JLabel("Pontuação do Jogador"));
                            final JTextField pontuacaoField = new JTextField();
                            dialog.add(pontuacaoField);
                            final JLabel imagemLabel = new JLabel();
                            JButton imagemButton = new JButton("Selecionar Imagem");
                            imagemButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // Abre um seletor de arquivos para o usuário escolher a imagem do jogador
                                    JFileChooser fileChooser = new JFileChooser();
                                    int returnValue = fileChooser.showOpenDialog(null);
                                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                                        File selectedFile = fileChooser.getSelectedFile();
                                        imagemLabel.setText(selectedFile.getName());
                                        try {
                                            Image img = ImageIO.read(selectedFile);
                                            Image scaledImg = img.getScaledInstance(clickedButton.getWidth(), clickedButton.getHeight(), Image.SCALE_SMOOTH);
                                            BufferedImage resizedImg = new BufferedImage(clickedButton.getWidth(), clickedButton.getHeight(), BufferedImage.TYPE_INT_ARGB);
                                            Graphics2D g2 = resizedImg.createGraphics();
                                            g2.drawImage(scaledImg, 0, 0, null);
                                            g2.dispose();
                                            clickedButton.setIcon(new ImageIcon(resizedImg));
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            });
                            dialog.add(imagemButton);
                            JButton okButton = new JButton("OK");
                            okButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // Atualiza o botão na formação com os dados inseridos pelo usuário
                                    JLabel label = new JLabel("<html>" + nomeField.getText() + "<br>" + pontuacaoField.getText() + "</html>", SwingConstants.CENTER);
                                    label.setForeground(Color.WHITE);
                                    clickedButton.add(label, BorderLayout.SOUTH);
                                    clickedButton.repaint();
                                    dialog.dispose();
                                    try {
                                        int pontuacao = Integer.parseInt(pontuacaoField.getText());
                                        pontuacoes.add(pontuacao);
                                        // Calcula a média
                                        double soma = 0.0;
                                        for (Integer pont : pontuacoes) {
                                            soma += pont;
                                        }
                                        double media = soma / pontuacoes.size();
                                        mediaLabel.setText(String.format("Média geral do time: %.2f", media));
                                    } catch (NumberFormatException ex) {
                                        // O usuário não inseriu um número válido
                                    }
                                    // Depois que o usuário insere os dados do jogador:
                                    if (nomesJogadores.size() > index) {
                                        nomesJogadores.set(index, nomeField.getText());
                                        pontuacoesJogadores.set(index, pontuacaoField.getText());
                                    } else {
                                        nomesJogadores.add(nomeField.getText());
                                        pontuacoesJogadores.add(pontuacaoField.getText());
                                    }
                                }
                            });
                            dialog.add(okButton);
                            JButton resetButton = new JButton("Resetar");
                            resetButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // Limpa os campos de texto
                                    nomeField.setText("");
                                    pontuacaoField.setText("");
                                    imagemLabel.setText("");
                                    clickedButton.setIcon(null);
                                    if (nomesJogadores.size() > index) {
                                        nomesJogadores.set(index, "");
                                        pontuacoesJogadores.set(index, "");
                                    }
                                }
                            });
                            dialog.add(resetButton);
                            dialog.pack();
                            dialog.setVisible(true);
                        }
                    });
                    
                    posicoes[index].setBounds(finalCoords[index][0], finalCoords[index][1], 100, 130);
                    panel2.add(posicoes[index]);
                    posicoes[index].setOpaque(false);
                }

                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
