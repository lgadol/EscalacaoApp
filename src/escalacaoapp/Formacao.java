package escalacaoapp;

import java.util.ArrayList;

public class Formacao {
    private ArrayList<Jogador> jogadores;

    // Opções de jogadores
    public static final String[] FORMACAO_433P = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "MC 1", "MC 2", "MEI", "PD", "ATA", "PE", "Técnico"
    };
    
    public static final String[] FORMACAO_433O = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "VOL", "MEI 1", "MEI 2", "PD", "ATA", "PE", "Técnico"
    };
    
    public static final String[] FORMACAO_442 = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "MC 1", "MC 2", "ME", "MD", "ATA 1", "ATA 2", "Técnico"
    };

    // Posição dos botões - ESQUERDA : posição horizontal | DIREITA : posição vertical
    public static final int[][] COORDS_433P = { { 250, 630 }, // GOL
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
                                               { 650, 660 } // Técnico;
    };

    public static final int[][] COORDS_433O = { { 250, 630 }, // GOL
                                               { 450, 440 }, // LD
                                               { 320, 470 }, // ZAG 1
                                               { 180, 470 }, // ZAG 2
                                               { 40, 440 }, // LE
                                               { 250, 320 }, // VOL
                                               { 180, 180 }, // MEI 1
                                               { 320, 180 }, // MEI 2
                                               { 450, 70 }, // PD
                                               { 250, 40 }, // ATA
                                               { 40, 70 }, // PE
                                               { 650, 660 } // Técnico };
    };

    public static final int[][] COORDS_442 = { { 250, 630 }, // GOL
                                               { 450, 440 }, // LD
                                               { 320, 470 }, // ZAG 1
                                               { 180, 470 }, // ZAG 2
                                               { 40, 440 }, // LE
                                               { 320, 250 }, // MC 1
                                               { 180, 250 }, // MC 2
                                               { 40, 120 }, // ME
                                               { 450, 120 }, // MD
                                               { 320, 40 }, // ATA 1
                                               { 180, 40 }, // ATA 2
                                               { 650, 660 } // Técnico };
    };

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
