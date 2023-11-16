package escalacaoapp;

import java.util.ArrayList;

public class Formacao {
    private ArrayList<Jogador> jogadores;

    // Opções de jogadores
    static final protected String[] FORMACAO_433P = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "MC 1", "MC 2", "MEI", "PD", "ATA", "PE", "Técnico"
    };

    static final protected String[] FORMACAO_433O = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "VOL", "MEI 1", "MEI 2", "PD", "ATA", "PE", "Técnico"
    };

    static final protected String[] FORMACAO_442 = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "LE", "MC 1", "MC 2", "ME", "MD", "ATA 1", "ATA 2", "Técnico"
    };
    
    static final protected String[] FORMACAO_532 = {
        "GOL", "LD", "ZAG 1", "ZAG 2", "ZAG 3", "LE", "MC 1", "MC 2", "MEI", "ATA 1", "ATA 2", "Técnico"
    };
    
    static final protected String[] FORMACAO_343 = {
        "GOL", "ZAG 1", "ZAG 2", "ZAG 3", "VOL 1", "VOL 2", "MEI", "ME", "MD", "SA", "ATA 1", "Técnico"
    };
    
    static final protected String[] FORMACAO_352 = {
        "GOL", "ZAG 1", "ZAG 2", "ZAG 3", "ALAD", "MC 1", "MC 2", "MEI", "ALAE", "ATA 1", "ATA 2", "Técnico"
    };

    // Posição dos botões - ESQUERDA : posição horizontal | DIREITA : posição vertical
    static final protected int[][] COORDS_433P = { { 250, 630 }, // GOL
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

    static final protected int[][] COORDS_433O = { { 250, 630 }, // GOL
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

    static final protected int[][] COORDS_442 = { { 250, 630 }, // GOL
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
    
    static final protected int[][] COORDS_532 = { { 250, 630 }, // GOL
                                                  { 450, 350 }, // LD
                                                  { 410, 490 }, // ZAG 1
                                                  { 250, 470 }, // ZAG 2
                                                  { 90, 490 }, // ZAG 3
                                                  { 45, 350 }, // LE
                                                  { 320, 320 }, // MC 1
                                                  { 180, 320 }, // MC 2
                                                  { 250, 185 }, // MEI
                                                  { 320, 40 }, // ATA 1
                                                  { 180, 40 }, // ATA 2
                                                  { 650, 660 } // Técnico };

    };
    
    static final protected int[][] COORDS_343 = { { 250, 630 }, // GOL
                                                  { 410, 490 }, // ZAG 1
                                                  { 250, 470 }, // ZAG 2
                                                  { 90, 490 }, // ZAG 3
                                                  { 320, 320 }, // VOL 1
                                                  { 180, 320 }, // VOL 2
                                                  { 250, 185 }, // MEI
                                                  { 40, 120 }, // ME
                                                  { 450, 120 }, // MD
                                                  { 320, 50 }, // SA
                                                  { 180, 40 }, // ATA 1
                                                  { 650, 660 } // Técnico };

    };
    
    static final protected int[][] COORDS_352 = { { 250, 630 }, // GOL
                                                  { 410, 490 }, // ZAG 1
                                                  { 250, 470 }, // ZAG 2
                                                  { 90, 490 }, // ZAG 3
                                                  { 450, 300 }, // ALAD
                                                  { 320, 320 }, // MC 1
                                                  { 180, 320 }, // MC 2
                                                  { 250, 185 }, // MEI
                                                  { 45, 300 }, // ALAE
                                                  { 340, 40 }, // ATA 1
                                                  { 160, 40 }, // ATA 2
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
        if (index >= 0 && index < jogadores.size()) {
            return jogadores.get(index);
        } else {
            return null;
        }
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
}
