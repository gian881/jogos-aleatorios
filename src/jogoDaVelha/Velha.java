package jogoDaVelha;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Velha {
    private static Random random = new Random();

    // Um hashmap para armazenar as probabilidades cumulativas de cada jogada
    private static HashMap<Integer, Integer[]> probabilidades = new HashMap<Integer, Integer[]>();

    // Função para preencher o hashmap com as probabilidades
    public static void preencherHashMap() {
        Integer[] prob0 = { 1172, 2196, 3366, 4369, 5673, 6683, 7834, 8856, 10000 };
        Integer[] prob1 = { 1084, 2310, 3371, 4582, 5445, 6682, 7727, 8931, 10000 };
        Integer[] prob2 = { 1167, 2189, 3336, 4365, 5673, 6674, 7835, 8848, 10000 };
        Integer[] prob3 = { 1051, 2294, 3336, 4585, 5431, 6665, 7712, 8922, 10000 };
        Integer[] prob4 = { 1135, 2142, 3295, 4314, 5620, 6653, 7807, 8851, 10000 };
        Integer[] prob5 = { 1049, 2270, 3347, 4551, 5425, 6629, 7693, 8920, 10000 };
        Integer[] prob6 = { 984, 1785, 2772, 3587, 4755, 5579, 6566, 7375, 8364 };
        Integer[] prob7 = { 868, 1886, 2769, 3783, 4512, 5566, 6461, 7482, 8364 };
        Integer[] prob8 = { 507, 751, 1262, 1511, 2337, 2569, 3090, 3336, 3849 };

        probabilidades.put(0, prob0);
        probabilidades.put(1, prob1);
        probabilidades.put(2, prob2);
        probabilidades.put(3, prob3);
        probabilidades.put(4, prob4);
        probabilidades.put(5, prob5);
        probabilidades.put(6, prob6);
        probabilidades.put(7, prob7);
        probabilidades.put(8, prob8);
    }

    public static void imprimirTabuleiro(int[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
            }
            System.out.println();
        }
    }

    // Jogada feita completamente aleatória, sem nenhuma estratégia
    public static int jogadaCompletamenteAleatoria(int[][] tabuleiro, int jogador) {
        boolean sair = false;
        int x = 0;
        int y = 0;
        while (!sair) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            if (tabuleiro[x][y] == 0) {
                tabuleiro[x][y] = jogador;
                sair = true;
            }
        }
        return x * 3 + y;
    }

    // Jogada feita aleatória, mas com uma estratégia de jogar nas posições da
    // primeira coluna
    public static int jogadaComEstrategia1(int[][] tabuleiro, int jogador) {
        boolean sair = false;
        int estrategia = random.nextInt(100) + 1;
        int x = 0;
        int y = 0;

        if (jogador == 1) {
            if (estrategia <= 60) {
                if (tabuleiro[0][0] == 0) {
                    tabuleiro[0][0] = jogador;
                    sair = true;
                } else if (tabuleiro[1][0] == 0) {
                    tabuleiro[1][0] = jogador;
                    sair = true;
                } else if (tabuleiro[2][0] == 0) {
                    tabuleiro[2][0] = jogador;
                    sair = true;
                }
            }
        }

        while (!sair) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            if (tabuleiro[x][y] == 0) {
                tabuleiro[x][y] = jogador;
                sair = true;
            }
        }

        return x * 3 + y;
    }

    // Jogada aleatória, mas com uma estratégia baseada na análise das vitórias do X
    public static int jogadaComEstrategia2(int[][] tabuleiro, int jogador, int jogada) {
        boolean sair = false;
        int x = -1;
        int y = -1;

        Integer[] prob = probabilidades.get(jogada);

        while (!sair) {
            if (prob.length != 1) {
                int numero = random.nextInt(10001);
                for (int i = 0; i <= 8; i++) {
                    if (numero <= prob[i]) {
                        x = i / 3;
                        y = i % 3;
                        break;
                    }
                }

                if (x == -1 || y == -1) {
                    x = random.nextInt(3);
                    y = random.nextInt(3);
                }
            } else {
                x = random.nextInt(3);
                y = random.nextInt(3);
            }

            if (tabuleiro[x][y] == 0) {
                tabuleiro[x][y] = jogador;
                sair = true;
            }
        }

        return x * 3 + y;
    }

    // Checagem de vitória nas linhas
    public static boolean ganhouLinhas(int[][] tabuleiro, int x) {
        int igual = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (tabuleiro[i][j] == x && tabuleiro[i][j] == tabuleiro[i][j + 1])
                    igual++;
            }
            if (igual == 3)
                return true;
            igual = 1;
        }
        return false;
    }

    // Checagem de vitória nas colunas
    public static boolean ganhouColunas(int[][] tabuleiro, int x) {
        int igual = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (tabuleiro[j][i] == x && tabuleiro[j][i] == tabuleiro[j + 1][i])
                    igual++;
            }
            if (igual == 3)
                return true;
            igual = 1;
        }
        return false;
    }

    // Checagem de vitória nas diagonais
    public static boolean ganhouDiagonal(int[][] tabuleiro, int x) {
        if (tabuleiro[0][0] == x && tabuleiro[1][1] == x && tabuleiro[2][2] == x
                || tabuleiro[0][2] == x && tabuleiro[1][1] == x && tabuleiro[2][0] == x)
            return true;
        else
            return false;
    }

    // Checagem de vitória geral
    public static boolean verificarVitoria(int[][] tabuleiro, int x) {
        if (ganhouLinhas(tabuleiro, x) || ganhouColunas(tabuleiro, x) || ganhouDiagonal(tabuleiro, x))
            return true;
        else
            return false;
    }

    // Função que salva os resultados das partidas em um arquivo
    public static void salvarNoArquivo(Resultado[] resultados, String nomeArquivo) throws IOException {
        System.out.println("\nEscrevendo no arquivo...");

        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "/" + nomeArquivo + ".csv", true));
        BufferedWriter writerX = new BufferedWriter(
                new FileWriter(nomeArquivo + "/" + nomeArquivo + "_vitoria_X.csv", true));
        BufferedWriter writerO = new BufferedWriter(
                new FileWriter(nomeArquivo + "/" + nomeArquivo + "_vitoria_O.csv", true));
        BufferedWriter writerE = new BufferedWriter(
                new FileWriter(nomeArquivo + "/" + nomeArquivo + "_empate.csv", true));

        writer.append("RESULTADO,JOGADA_1,JOGADA_2,JOGADA_3,JOGADA_4,JOGADA_5,JOGADA_6,JOGADA_7,JOGADA_8,JOGADA_9");
        writerX.append("RESULTADO,JOGADA_1,JOGADA_2,JOGADA_3,JOGADA_4,JOGADA_5,JOGADA_6,JOGADA_7,JOGADA_8,JOGADA_9");
        writerO.append("RESULTADO,JOGADA_1,JOGADA_2,JOGADA_3,JOGADA_4,JOGADA_5,JOGADA_6,JOGADA_7,JOGADA_8,JOGADA_9");
        writerE.append("RESULTADO,JOGADA_1,JOGADA_2,JOGADA_3,JOGADA_4,JOGADA_5,JOGADA_6,JOGADA_7,JOGADA_8,JOGADA_9");

        for (Resultado resultado : resultados) {
            String resultadoString = "\n" + resultado.getResultado().toString() + ", ";

            for (int i = 0; i < 9; i++) {
                if (i == 8) {
                    resultadoString += resultado.getSequencia()[i];
                } else {
                    resultadoString += resultado.getSequencia()[i] + ", ";
                }
            }

            if (resultado.getResultado() == ResultadoEnum.VITORIA_X) {
                writerX.append(resultadoString);
            } else if (resultado.getResultado() == ResultadoEnum.VITORIA_O) {
                writerO.append(resultadoString);
            } else {
                writerE.append(resultadoString);
            }

            writer.append(resultadoString);
        }

        writer.close();
        writerX.close();
        writerO.close();
        writerE.close();
    }

    // Função que roda o jogo e salva os resultados
    public static void main(String[] args) throws IOException {
        preencherHashMap();

        int PARTIDAS = 100000;
        int vitoriasX = 0;
        int vitoriasO = 0;
        int empates = 0;

        Resultado[] resultados = new Resultado[PARTIDAS];
        // definir número de partidas que serão jogadas
        for (int partidas = 0; partidas < PARTIDAS; partidas++) {
            int[] sequencia = { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
            int jogada_atual = 0;

            // declarações iniciais
            int jogadas = 0;
            int vitorioso = 5;
            int[][] tabuleiro = new int[3][3];
            boolean vitoria = false;

            // loop onde sera executado cada jogo
            while (!vitoria) {
                // Jogadas do jogador X, podendo ser aleatória sem estratégia, com a estratégia
                // 1 ou com a estratégia 2

                sequencia[jogada_atual] = jogadaCompletamenteAleatoria(tabuleiro, 1);
                // sequencia[jogada_atual] = jogadaComEstrategia1(tabuleiro, 1);
                // sequencia[jogada_atual] = jogadaComEstrategia2(tabuleiro, 1, jogada_atual);
                jogada_atual++;

                if (jogadas < 4) {
                    // Jogadas do jogador O, podendo ser aleatória sem estratégia, com a estratégia
                    // 1 ou com a estratégia 2

                    sequencia[jogada_atual] = jogadaCompletamenteAleatoria(tabuleiro, 2);
                    // sequencia[jogada_atual] = jogadaComEstrategia1(tabuleiro, 2);
                    // sequencia[jogada_atual] = jogadaComEstrategia2(tabuleiro, 2, jogada_atual);
                    jogada_atual++;
                }

                // verificando vitoria
                if (jogadas >= 2) {
                    if (verificarVitoria(tabuleiro, 1)) {
                        vitorioso = 1;
                        vitoria = true;
                    } else if (verificarVitoria(tabuleiro, 2)) {
                        vitorioso = 2;
                        vitoria = true;
                    } else if (jogadas == 4) {
                        vitorioso = 3;
                        vitoria = true;
                    }
                }
                jogadas++;
            }

            if (vitorioso == 1) {
                vitoriasX++;
                resultados[partidas] = new Resultado(ResultadoEnum.VITORIA_X, sequencia);
            } else if (vitorioso == 2) {
                vitoriasO++;
                resultados[partidas] = new Resultado(ResultadoEnum.VITORIA_O, sequencia);
            } else {
                empates++;
                resultados[partidas] = new Resultado(ResultadoEnum.EMPATE, sequencia);

            }

        }

        // Imprime resultados gerais
        System.out.println("Vitorias de x: " + vitoriasX);
        System.out.println("Vitorias de O: " + vitoriasO);
        System.out.println("Empates: " + empates);

        // Salva os resultados nos arquivos
        // salvarNoArquivo(resultados, "resultado_velha_aleatorio");
        // salvarNoArquivo(resultados, "resultado_velha_estrategia1");
        // salvarNoArquivo(resultados, "resultado_velha_estrategia2");
    }
}
