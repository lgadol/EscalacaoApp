package escalacaoapp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

import javax.imageio.ImageIO;

public class EscalacaoApp {
    private JFrame frame;
    private JPanel panel1, panel2;
    private JButton avancarButton;
    private JButton[] posicoes;
    private Image backgroundImage;
    private Time time;
    private String[] formacaoAtual = Formacao.FORMACAO_433P;
    private int[][] coordsAtual = Formacao.COORDS_433P;
    private JLabel mediaLabel;

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
        time = new Time(new Formacao(), "Técnico");
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setSize(800, 850); // Define o tamanho da janela
        frame.setResizable(false); // Impede que a janela seja maximizada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Adicione o código aqui para definir o ícone
        frame.setLocationRelativeTo(null);  // Isto centraliza a janela

        try {
            Image iconeTitulo =
                ImageIO.read(new File("C:\\Users\\PedroGado\\Documents\\Java Dev\\My Dev\\EscalacaoApp\\lib\\img\\icon.ico"));
            frame.setIconImage(iconeTitulo);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

                // Média de pontuação do time
                frame.getContentPane().add(panel2, BorderLayout.CENTER);
                panel2.setLayout(null);
                mediaLabel = new JLabel("Média geral do time: 0");
                mediaLabel.setBounds(10, 10, 200, 30);
                panel2.add(mediaLabel);

                // Crie um JComboBox com todas as formações disponíveis
                String[] formacoes = { "4-3-3 P", "4-3-3 O", "4-4-2", "5-3-2", "3-4-3", "3-5-2" };
                final JComboBox<String> formacaoComboBox = new JComboBox<>(formacoes);
                formacaoComboBox.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        String formacaoSelecionada = (String) formacaoComboBox.getSelectedItem();

                        // Atualize a formação e as coordenadas atuais com base na formação selecionada
                        switch (formacaoSelecionada) {

                        case "4-3-3 P":
                            formacaoAtual = Formacao.FORMACAO_433P;
                            coordsAtual = Formacao.COORDS_433P;
                            break;

                        case "4-3-3 O":
                            formacaoAtual = Formacao.FORMACAO_433O;
                            coordsAtual = Formacao.COORDS_433O;
                            break;

                        case "4-4-2":
                            formacaoAtual = Formacao.FORMACAO_442;
                            coordsAtual = Formacao.COORDS_442;
                            break;

                        case "5-3-2":
                            formacaoAtual = Formacao.FORMACAO_532;
                            coordsAtual = Formacao.COORDS_532;
                            break;

                        case "3-4-3":
                            formacaoAtual = Formacao.FORMACAO_343;
                            coordsAtual = Formacao.COORDS_343;
                            break;

                        case "3-5-2":
                            formacaoAtual = Formacao.FORMACAO_352;
                            coordsAtual = Formacao.COORDS_352;
                            break;

                        }

                        // Atualize os botões de posição com a nova formação e coordenadas
                        atualizarBotoesPosicao(formacaoAtual, coordsAtual);
                    }
                });

                formacaoComboBox.setBounds(650, 10, 100, 20); // Posicione o JComboBox no canto superior direito
                panel2.add(formacaoComboBox);

                final JLabel mediaLabel = new JLabel("Média geral do time: 0");
                mediaLabel.setBounds(10, 10, 200, 30);
                panel2.add(mediaLabel);

                criarBotoesPosicao();

                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private void criarBotoesPosicao() {

        // Crie os botões de posição apenas uma vez
        posicoes = new JButton[12];

        for (int i = 0; i < 12; i++) {

            final int index = i;
            final JTextField nomeField = new JTextField();
            final JTextField pontuacaoField = new JTextField();

            posicoes[index] = new JButton();
            posicoes[index].setLayout(new BorderLayout());
            posicoes[index].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    // Cria uma nova janela para inserir os dados do jogador
                    final JButton clickedButton = (JButton) e.getSource();
                    final JDialog dialog = new JDialog(frame, "Dados do Jogador", true);
                    dialog.setLayout(new GridLayout(6, 1));

                    dialog.add(new JLabel("Nome do Jogador"));
                    dialog.add(nomeField);

                    dialog.add(new JLabel("Pontuação do Jogador"));
                    dialog.add(pontuacaoField);
                    
                    dialog.setLocationRelativeTo(null);  // Isto centraliza a janela

                    // Se o botão já tem um jogador associado, preencha os campos de texto com os dados desse jogador
                    Jogador jogador = time.getFormacao().getJogador(index);
                    if (jogador != null) {
                        nomeField.setText(jogador.getNome());
                        pontuacaoField.setText(String.valueOf(jogador.getPontuacao()));
                    }

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

                                // Aqui é onde nós pegamos o nome do arquivo e dividimos em nome e pontuação
                                String fileName = selectedFile.getName();
                                if (fileName.contains(".")) {
                                    // Verifica se o nome do arquivo contém um ponto
                                    String[] parts = fileName.split("\\.");
                                    // Note que precisamos escapar o ponto porque é um caractere especial em expressões regulares
                                    if (parts.length >= 2) {
                                        nomeField.setText(parts[0]);
                                        pontuacaoField.setText(parts[1]);
                                    }
                                }

                                try {
                                    Image img = ImageIO.read(selectedFile);
                                    Image scaledImg =
                                        img.getScaledInstance(clickedButton.getWidth(), clickedButton.getHeight(),
                                                              Image.SCALE_SMOOTH);
                                    BufferedImage resizedImg =
                                        new BufferedImage(clickedButton.getWidth(), clickedButton.getHeight(),
                                                          BufferedImage.TYPE_INT_ARGB);
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
                            JLabel label =
                                new JLabel("<html>" + nomeField.getText() + "<br>" + pontuacaoField.getText() +
                                           "</html>", SwingConstants.CENTER);
                            label.setForeground(Color.WHITE);

                            // Limpa o conteúdo do botão antes de adicionar o novo JLabel
                            clickedButton.removeAll();
                            clickedButton.add(label, BorderLayout.SOUTH);
                            clickedButton.repaint();
                            dialog.dispose();

                            // Adiciona o jogador à formação
                            // Verifica se o campo de pontuação está vazio antes de tentar analisá-lo
                            int pontuacao =
                                pontuacaoField.getText().isEmpty() ? 0 : Integer.parseInt(pontuacaoField.getText());
                            Jogador jogador = new Jogador(nomeField.getText(), pontuacao);
                            time.getFormacao().adicionarJogador(jogador);

                            // Calcula a média
                            double soma = 0.0;
                            for (Jogador jog : time.getFormacao().getJogadores()) {
                                soma += jog.getPontuacao();
                            }
                            double media = soma / time.getFormacao().getJogadores().size();
                            mediaLabel.setText(String.format("Média geral do time: %.2f", media));
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

                            // Remove o jogador da formação
                            Jogador jogador = time.getFormacao().getJogador(index);
                            if (jogador != null) {
                                time.getFormacao().removerJogador(jogador);
                            }
                        }
                    });

                    dialog.add(resetButton);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            });

            posicoes[index].setOpaque(false);
            panel2.add(posicoes[index]);
        }
    }

    private void atualizarBotoesPosicao(String[] formacao, int[][] coords) {

        // Atualize o texto e a posição dos botões de posição existentes
        for (int i = 0; i < 12; i++) {
            posicoes[i].setText(formacao[i]);
            posicoes[i].setBounds(coords[i][0], coords[i][1], 100, 130);
        }
    }
}
