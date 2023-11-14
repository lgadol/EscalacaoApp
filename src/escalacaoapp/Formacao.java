package escalacaoapp;

import java.util.ArrayList;

public class Formacao {
    private ArrayList<Jogador> jogadores;
    
    // Opções de jogadores
    public static final String[] FORMACAO_433P = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "MC 1", "MC 2", "MEI", "PD", "ATA", "PE", "Técnico" };
    public static final String[] FORMACAO_433O  = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "VOL 1", "MEI 2", "MEI", "PD", "ATA", "PE", "Técnico" };

    // Posição dos botões
    public static final int[][] COORDS_433 = { { 250, 630 }, // GOL
                                               { 450, 440 }, // LD
                                               { 320, 470 }, // ZAG 1
                                               { 180, 470 }, // ZAG 2
                                               { 40, 440 }, // LE
                                               { 320, 320 }, // MC 1
                                               { 180, 320 }, // MC 2
                                               { 250, 180 }, // MEI
                                               { 450, 70 }, // PD
                                               { 250, 40 }, // ATA
                                               { 40, 70 }, // PE
                                               { 650, 660 } // Técnico };
    };

    public static final int[][] COORDS_442 = { /* suas coordenadas aqui */ };
    public static final int[][] COORDS_352 = { /* suas coordenadas aqui */ };
    // Adicione mais formações aqui

    public Formacao() {
        jogadores = new ArrayList<Jogador>();
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    public Jogador getJogador(int index) {
        return jogadores.get(index);
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
}
