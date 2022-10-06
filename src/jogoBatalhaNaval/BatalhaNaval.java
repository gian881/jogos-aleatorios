package jogoBatalhaNaval;

import java.util.Random;

public class BatalhaNaval {

    static Random random = new Random();

    public static void definirPosicoesBarcos(int[][] tabuleiro, int jogador) {
        boolean sair = false;
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        tabuleiro[x][y] = jogador;
        int direcao;
        while (!sair) {
            x = random.nextInt(6);
            y = random.nextInt(6);
            direcao = random.nextInt(2);
            if (direcao == 0 && y + 1 < 6) {
                if (tabuleiro[x][y] == 0 && tabuleiro[x][y + 1] == 0) {
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x][y + 1] = jogador;
                    sair = true;
                }
            } else if (direcao == 1 && x + 1 < 6) {
                if (tabuleiro[x][y] == 0 && tabuleiro[x + 1][y] == 0) {
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x + 1][y] = jogador;
                    sair = true;
                }
            }
        }
        sair = false;
        while (!sair) {
            x = random.nextInt(6);
            y = random.nextInt(6);
            direcao = random.nextInt(2);
            if (direcao == 0 && y + 2 < 6) {
                if (tabuleiro[x][y] == 0 && tabuleiro[x][y + 1] == 0 && tabuleiro[x][y + 2] == 0) {
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x][y + 1] = jogador;
                    tabuleiro[x][y + 2] = jogador;
                    sair = true;
                }
            } else if (direcao == 1 && x + 2 < 6) {
                if (tabuleiro[x][y] == 0 && tabuleiro[x + 1][y] == 0 && tabuleiro[x + 2][y] == 0) {
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x + 1][y] = jogador;
                    tabuleiro[x + 2][y] = jogador;
                    sair = true;
                }
            }
        }
    }

    public static void resetTabuleiro(int[][] tabuleiro, int[][] tabuleiro2) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                tabuleiro[x][y] = 0;
                tabuleiro2[x][y] = 0;
            }
        }
    }

    public static void jogada(int[][] tabuleiro) {
        int x;
        int y;
        boolean sair = false;
        while (!sair) {
            x = random.nextInt(6);
            y = random.nextInt(6);
            if (tabuleiro[x][y] != 9) {
                tabuleiro[x][y] = 9;
                sair = true;
            }
        }
    }

    public static void imprimirTabuleiro(int[][] tabuleiro) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                System.out.print(tabuleiro[x][y]);
            }
            System.out.println();
        }
    }

    public static boolean verificarDerrota(int[][] tabuleiro, int jogador) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                if (tabuleiro[x][y] == jogador)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int tabuleiroAzul[][] = new int[6][6];
        int tabuleiroVermelho[][] = new int[6][6];
        int vitoriasVermelho = 0;
        int vitoriasAzul = 0;
        int vitorias = 0;
        definirPosicoesBarcos(tabuleiroAzul, 1);
        definirPosicoesBarcos(tabuleiroVermelho, 2);

        imprimirTabuleiro(tabuleiroAzul);
        System.out.println();
        imprimirTabuleiro(tabuleiroVermelho);

        while (vitorias < 100000) {
            jogada(tabuleiroVermelho);
            jogada(tabuleiroAzul);

            if (verificarDerrota(tabuleiroAzul, 1) || verificarDerrota(tabuleiroVermelho, 2)) {

                if (verificarDerrota(tabuleiroVermelho, 2))
                    vitoriasAzul++;
                else if (verificarDerrota(tabuleiroAzul, 1))
                    vitoriasVermelho++;

                vitorias++;
                resetTabuleiro(tabuleiroAzul, tabuleiroVermelho);
                definirPosicoesBarcos(tabuleiroAzul, 1);
                definirPosicoesBarcos(tabuleiroVermelho, 2);
            }
        }
        System.out.println("Vitórias azul: " + vitoriasAzul);
        System.out.println("Vitórias vermelho: " + vitoriasVermelho);

    }
}
