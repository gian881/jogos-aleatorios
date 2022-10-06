package jogoLudo;

import java.util.Random;
// import java.util.Scanner;

public class Tabuleiro {
    // private static Scanner sc = new Scanner(System.in);

    private Jogador azul;
    private static Random random = new Random();
    private Jogador vermelho;
    private Jogador verde;
    private Jogador amarelo;
    private int[] mapa;

    public Tabuleiro() {
        azul = new Jogador();
        vermelho = new Jogador();
        verde = new Jogador();
        amarelo = new Jogador();
        mapa = new int[52];
        mapa[0] = 9; // base azul
        mapa[8] = 9; // estrela azul
        mapa[13] = 9; // base vermelho
        mapa[21] = 9; // estrela vermelho
        mapa[26] = 9; // base verde
        mapa[34] = 9; // estrela verde
        mapa[39] = 9; // base amarelo
        mapa[47] = 9; // estrela amarelo
    }

    public void resetarMapa() {
        for (int i = 0; i < 52; i++) {
            this.mapa[i] = 0;
        }
        mapa[0] = 9; // base azul
        mapa[8] = 9; // estrela azul
        mapa[13] = 9; // base vermelho
        mapa[21] = 9; // estrela vermelho
        mapa[26] = 9; // base verde
        mapa[34] = 9; // estrela verde
        mapa[39] = 9; // base amarelo
        mapa[47] = 9; // estrela amarelo
    }

    public void relatorio() {
        for (int i = 0; i < 52; i++) {
            if (i > 8)
                System.out.print(this.mapa[i] + "  ");
            else
                System.out.print(this.mapa[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 52; i++) {
            System.out.print(i + " ");
        }

        System.out.print("\n\nAzul: ");
        for (int i = 0; i < 4; i++)
            System.out.print(this.azul.getPecas()[i] + " ");
        System.out.print("\nvermelho: ");
        for (int i = 0; i < 4; i++)
            System.out.print(this.vermelho.getPecas()[i] + " ");
        System.out.print("\nverde: ");
        for (int i = 0; i < 4; i++)
            System.out.print(this.verde.getPecas()[i] + " ");
        System.out.print("\namarelo: ");
        for (int i = 0; i < 4; i++)
            System.out.print(this.amarelo.getPecas()[i] + " ");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void jogadaAzul() {
        int dado = random.nextInt(6) + 1;

        int atual = 0;
        int jogada;
        int resultado = 99;
        int inimigo = 0;

        boolean sair = false;
        if (!this.existeJogadaValida(1, dado) && this.alguemnabase(1) && dado != 6)
            sair = true;
        else if (!this.existeJogadaValida(1, dado) && this.noOneInBase(1))
            sair = true;

        while (!sair) {
            jogada = random.nextInt(5);
            if (jogada < 4)
                atual = this.azul.getPecas()[jogada];
            if (jogada < 4)
                resultado = dado + this.azul.getPecas()[jogada];

            if (resultado < 52)
                inimigo = this.mapa[resultado];
            if (inimigo == 1) {
                while (inimigo == 1) {
                    dado = random.nextInt(6) + 1;
                    resultado = dado + this.azul.getPecas()[jogada];
                    if (resultado < 52)
                        inimigo = this.mapa[resultado];
                    else
                        inimigo = 0;
                }
            }
            if (jogada == 4 && dado == 6 && !this.noOneInBase(1)) {
                for (int i = 0; i < 4; i++) {
                    if (this.azul.getPecas()[i] == -1) {
                        this.azul.getPecas()[i] = 0;
                        sair = true;
                        break;
                    }
                }
            }

            else if (jogada == 4)
                ;
            else if (resultado > 95 && resultado <= 100) {
                this.azul.getPecas()[jogada] = resultado;
                sair = true;
            } else if (resultado > 51 && resultado < 95) {
                if (this.mapa[atual] == 9) {
                    this.azul.getPecas()[jogada] = 95 + (resultado - 52);
                    sair = true;
                } else {
                    this.azul.getPecas()[jogada] = 95 + (resultado - 52);
                    this.mapa[atual] = 0;
                    sair = true;
                }
            }

            else if (this.azul.getPecas()[jogada] != -1 && this.azul.getPecas()[jogada] < 52) {
                if (inimigo != 0 && inimigo != 9) {
                    this.returnJogador(inimigo).getPecas()[this.buscarJogador(inimigo, resultado)] = -1;
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 1;
                    this.azul.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 0) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 1;
                    this.azul.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 9) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.azul.getPecas()[jogada] = resultado;
                    sair = true;
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void jogadaVermelho() {
        int dado = random.nextInt(6) + 1;

        int atual = 0;
        int jogada;
        int resultado = 0;
        int inimigo = 0;

        boolean sair = false;
        if (!this.existeJogadaValida(2, dado) && this.alguemnabase(2) && dado != 6)
            sair = true;
        else if (!this.existeJogadaValida(2, dado) && this.noOneInBase(2))
            sair = true;

        while (!sair) {
            jogada = random.nextInt(5);
            if (jogada < 4)
                atual = this.vermelho.getPecas()[jogada];
            if (jogada < 4)
                resultado = dado + this.vermelho.getPecas()[jogada];
            if (resultado >= 52 && resultado < 95)
                resultado = resultado - 52;

            if (resultado < 52)
                inimigo = this.mapa[resultado];
            if (inimigo == 2) {
                while (inimigo == 2) {
                    dado = random.nextInt(6) + 1;
                    resultado = dado + this.vermelho.getPecas()[jogada];
                    if (resultado >= 52 && resultado < 95)
                        resultado = resultado - 52;
                    inimigo = this.mapa[resultado];
                }
            }
            if (jogada == 4 && dado == 6 && !this.noOneInBase(2)) {
                for (int i = 0; i < 4; i++) {
                    if (this.vermelho.getPecas()[i] == -1) {
                        this.vermelho.getPecas()[i] = 13;
                        sair = true;
                        break;
                    }
                }
            }

            else if (jogada == 4)
                ;
            else if (resultado > 95 && resultado <= 100) {
                this.vermelho.getPecas()[jogada] = resultado;
                sair = true;
            } else if (this.vermelho.getPecas()[jogada] < 13 && resultado > 11) {
                if (this.mapa[atual] == 9) {
                    this.vermelho.getPecas()[jogada] = 95 + (resultado - 12);
                    sair = true;
                } else {
                    this.vermelho.getPecas()[jogada] = 95 + (resultado - 12);
                    this.mapa[atual] = 0;
                    sair = true;
                }
            }

            else if (this.vermelho.getPecas()[jogada] != -1 && this.vermelho.getPecas()[jogada] < 52) {
                if (inimigo != 0 && inimigo != 9) {
                    this.returnJogador(inimigo).getPecas()[this.buscarJogador(inimigo, resultado)] = -1;
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 2;
                    this.vermelho.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 0) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 2;
                    this.vermelho.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 9) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.vermelho.getPecas()[jogada] = resultado;
                    sair = true;
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void jogadaVerde() {
        int dado = random.nextInt(6) + 1;

        int atual = 0;
        int jogada;
        int resultado = 0;
        int inimigo = 0;

        boolean sair = false;
        if (!this.existeJogadaValida(3, dado) && this.alguemnabase(3) && dado != 6)
            sair = true;
        else if (!this.existeJogadaValida(3, dado) && this.noOneInBase(3))
            sair = true;

        while (!sair) {
            jogada = random.nextInt(5);
            if (jogada < 4)
                atual = this.verde.getPecas()[jogada];
            if (jogada < 4)
                resultado = dado + this.verde.getPecas()[jogada];
            if (resultado >= 52 && resultado < 95)
                resultado = resultado - 52;

            if (resultado < 52)
                inimigo = this.mapa[resultado];
            if (inimigo == 3) {
                while (inimigo == 3) {
                    dado = random.nextInt(6) + 1;
                    resultado = dado + this.verde.getPecas()[jogada];
                    if (resultado >= 52 && resultado < 95)
                        resultado = resultado - 52;
                    inimigo = this.mapa[resultado];
                }
            }
            if (jogada == 4 && dado == 6 && !this.noOneInBase(3)) {
                for (int i = 0; i < 4; i++) {
                    if (this.verde.getPecas()[i] == -1) {
                        this.verde.getPecas()[i] = 26;
                        sair = true;
                        break;
                    }
                }
            }

            else if (jogada == 4)
                ;
            else if (resultado > 95 && resultado <= 100) {
                this.verde.getPecas()[jogada] = resultado;
                sair = true;
            } else if (this.verde.getPecas()[jogada] < 26 && resultado > 24) {
                if (this.mapa[atual] == 9) {
                    this.verde.getPecas()[jogada] = 95 + (resultado - 25);
                    sair = true;
                } else {
                    this.verde.getPecas()[jogada] = 95 + (resultado - 25);
                    this.mapa[atual] = 0;
                    sair = true;
                }
            }

            else if (this.verde.getPecas()[jogada] != -1 && this.verde.getPecas()[jogada] < 52) {
                if (inimigo != 0 && inimigo != 9) {
                    this.returnJogador(inimigo).getPecas()[this.buscarJogador(inimigo, resultado)] = -1;
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 3;
                    this.verde.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 0) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 3;
                    this.verde.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 9) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.verde.getPecas()[jogada] = resultado;
                    sair = true;
                }
            }
        }
    }

    public void jogadaAmarelo() {
        int dado = random.nextInt(6) + 1;

        int atual = 0;
        int jogada;
        int resultado = 0;
        int inimigo = 0;

        boolean sair = false;
        if (!this.existeJogadaValida(4, dado) && this.alguemnabase(4) && dado != 6)
            sair = true;
        else if (!this.existeJogadaValida(4, dado) && this.noOneInBase(4))
            sair = true;

        while (!sair) {
            jogada = random.nextInt(5);
            if (jogada < 4)
                atual = this.amarelo.getPecas()[jogada];
            if (jogada < 4)
                resultado = dado + this.amarelo.getPecas()[jogada];
            if (resultado >= 52 && resultado < 95)
                resultado = resultado - 52;

            if (resultado < 52)
                inimigo = this.mapa[resultado];
            if (inimigo == 4) {
                while (inimigo == 4) {
                    dado = random.nextInt(6) + 1;
                    resultado = dado + this.amarelo.getPecas()[jogada];
                    if (resultado >= 52 && resultado < 95)
                        resultado = resultado - 52;
                    inimigo = this.mapa[resultado];
                }
            }
            if (jogada == 4 && dado == 6 && !this.noOneInBase(4)) {
                for (int i = 0; i < 4; i++) {
                    if (this.amarelo.getPecas()[i] == -1) {
                        this.amarelo.getPecas()[i] = 39;
                        sair = true;
                        break;
                    }
                }
            }

            else if (jogada == 4)
                ;
            else if (resultado > 95 && resultado <= 100) {
                this.amarelo.getPecas()[jogada] = resultado;
                sair = true;
            } else if (this.amarelo.getPecas()[jogada] < 39 && resultado > 37) {
                if (this.mapa[atual] == 9) {
                    this.amarelo.getPecas()[jogada] = 95 + (resultado - 38);
                    sair = true;
                } else {
                    this.amarelo.getPecas()[jogada] = 95 + (resultado - 38);
                    this.mapa[atual] = 0;
                    sair = true;
                }
            }

            else if (this.amarelo.getPecas()[jogada] != -1 && this.amarelo.getPecas()[jogada] < 52) {
                if (inimigo != 0 && inimigo != 9) {
                    this.returnJogador(inimigo).getPecas()[this.buscarJogador(inimigo, resultado)] = -1;
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 4;
                    this.amarelo.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 0) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.mapa[resultado] = 4;
                    this.amarelo.getPecas()[jogada] = resultado;
                    sair = true;
                } else if (inimigo == 9) {
                    if (this.mapa[atual] != 9)
                        this.mapa[atual] = 0;
                    this.amarelo.getPecas()[jogada] = resultado;
                    sair = true;
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int existeVencedor() {
        if (this.azul.getPecas()[0] == 100 && this.azul.getPecas()[1] == 100 && this.azul.getPecas()[2] == 100
                && this.azul.getPecas()[3] == 100)
            return 1;
        else if (this.vermelho.getPecas()[0] == 100 && this.vermelho.getPecas()[1] == 100
                && this.vermelho.getPecas()[2] == 100
                && this.vermelho.getPecas()[3] == 100)
            return 2;
        else if (this.verde.getPecas()[0] == 100 && this.verde.getPecas()[1] == 100 && this.verde.getPecas()[2] == 100
                && this.verde.getPecas()[3] == 100)
            return 3;
        else if (this.amarelo.getPecas()[0] == 100 && this.amarelo.getPecas()[1] == 100
                && this.amarelo.getPecas()[2] == 100
                && this.amarelo.getPecas()[3] == 100)
            return 4;
        else
            return 0;

    }

    public boolean existeJogadaValida(int time, int dado) {
        switch (time) {
            case 1:
                if ((this.azul.getPecas()[0] + dado > 100 || this.azul.getPecas()[0] == -1)
                        && (this.azul.getPecas()[1] + dado > 100 || this.azul.getPecas()[1] == -1)
                        && (this.azul.getPecas()[2] + dado > 100 || this.azul.getPecas()[2] == -1)
                        && (this.azul.getPecas()[3] + dado > 100 || this.azul.getPecas()[3] == -1))
                    return false;
                break;
            case 2:
                if ((this.vermelho.getPecas()[0] + dado > 100 || this.vermelho.getPecas()[0] == -1)
                        && (this.vermelho.getPecas()[1] + dado > 100 || this.vermelho.getPecas()[1] == -1)
                        && (this.vermelho.getPecas()[2] + dado > 100 || this.vermelho.getPecas()[2] == -1)
                        && (this.vermelho.getPecas()[3] + dado > 100 || this.vermelho.getPecas()[3] == -1))
                    return false;
                break;
            case 3:
                if ((this.verde.getPecas()[0] + dado > 100 || this.verde.getPecas()[0] == -1)
                        && (this.verde.getPecas()[1] + dado > 100 || this.verde.getPecas()[1] == -1)
                        && (this.verde.getPecas()[2] + dado > 100 || this.verde.getPecas()[2] == -1)
                        && (this.verde.getPecas()[3] + dado > 100 || this.verde.getPecas()[3] == -1))
                    return false;
                break;
            case 4:
                if ((this.amarelo.getPecas()[0] + dado > 100 || this.amarelo.getPecas()[0] == -1)
                        && (this.amarelo.getPecas()[1] + dado > 100 || this.amarelo.getPecas()[1] == -1)
                        && (this.amarelo.getPecas()[2] + dado > 100 || this.amarelo.getPecas()[2] == -1)
                        && (this.amarelo.getPecas()[3] + dado > 100 || this.amarelo.getPecas()[3] == -1))
                    return false;
                break;
        }
        return true;
    }

    public boolean noOneInBase(int time) {
        switch (time) {
            case 1:
                if (this.azul.getPecas()[0] != -1 && this.azul.getPecas()[1] != -1
                        && this.azul.getPecas()[2] != -1 && this.azul.getPecas()[3] != -1)
                    return true;
                else
                    return false;
            case 2:
                if (this.vermelho.getPecas()[0] != -1 && this.vermelho.getPecas()[1] != -1
                        && this.vermelho.getPecas()[2] != -1
                        && this.vermelho.getPecas()[3] != -1)
                    return true;
                else
                    return false;
            case 3:
                if (this.verde.getPecas()[0] != -1 && this.verde.getPecas()[1] != -1 && this.verde.getPecas()[2] != -1
                        && this.verde.getPecas()[3] != -1)
                    return true;
                else
                    return false;
            case 4:
                if (this.amarelo.getPecas()[0] != -1 && this.amarelo.getPecas()[1] != -1
                        && this.amarelo.getPecas()[2] != -1
                        && this.amarelo.getPecas()[3] != -1)
                    return true;
                else
                    return false;
            default:
                return false;
        }
    }

    public boolean alguemnabase(int time) {
        switch (time) {
            case 1:
                if (this.azul.getPecas()[0] == -1 || this.azul.getPecas()[1] == -1
                        || this.azul.getPecas()[2] == -1 || this.azul.getPecas()[3] == -1)
                    return true;
                else
                    return false;
            case 2:
                if (this.vermelho.getPecas()[0] == -1 || this.vermelho.getPecas()[1] == -1
                        || this.vermelho.getPecas()[2] == -1 || this.vermelho.getPecas()[3] == -1)
                    return true;
                else
                    return false;
            case 3:
                if (this.verde.getPecas()[0] == -1 || this.verde.getPecas()[1] == -1
                        || this.verde.getPecas()[2] == -1 || this.verde.getPecas()[3] == -1)
                    return true;
                else
                    return false;
            case 4:
                if (this.amarelo.getPecas()[0] == -1 || this.amarelo.getPecas()[1] == -1
                        || this.amarelo.getPecas()[2] == -1 || this.amarelo.getPecas()[3] == -1)
                    return true;
                else
                    return false;
            default:
                return false;
        }
    }

    public void resetarJogo() {
        this.azul.getPecas()[0] = -1;
        this.azul.getPecas()[1] = -1;
        this.azul.getPecas()[2] = -1;
        this.azul.getPecas()[3] = -1;
        this.vermelho.getPecas()[0] = -1;
        this.vermelho.getPecas()[1] = -1;
        this.vermelho.getPecas()[2] = -1;
        this.vermelho.getPecas()[3] = -1;
        this.verde.getPecas()[0] = -1;
        this.verde.getPecas()[1] = -1;
        this.verde.getPecas()[2] = -1;
        this.verde.getPecas()[3] = -1;
        this.amarelo.getPecas()[0] = -1;
        this.amarelo.getPecas()[1] = -1;
        this.amarelo.getPecas()[2] = -1;
        this.amarelo.getPecas()[3] = -1;
        this.resetarMapa();
    }

    public Jogador returnJogador(int jogador) {
        if (jogador == 1)
            return this.azul;
        else if (jogador == 2)
            return this.vermelho;
        else if (jogador == 3)
            return this.verde;
        else if (jogador == 4)
            return this.amarelo;
        else
            return null;
    }

    public int buscarJogador(int time, int posicao) {
        int resultado = 0;
        for (int i = 0; i < 4; i++) {
            if (time == 1 && this.azul.getPecas()[i] == posicao)
                return i;
            else if (time == 2 && this.vermelho.getPecas()[i] == posicao)
                return i;
            else if (time == 3 && this.verde.getPecas()[i] == posicao)
                return i;
            else if (time == 4 && this.amarelo.getPecas()[i] == posicao)
                return i;
        }

        return resultado;
    }

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        int vitorias = 0;
        int azulVitorias = 0;
        int vermelhoVitorias = 0;
        int verdeVitorias = 0;
        int amareloVitorias = 0;
        while (vitorias < 10000) {
            tabuleiro.jogadaAzul();
            tabuleiro.jogadaVermelho();
            tabuleiro.jogadaVerde();
            tabuleiro.jogadaAmarelo();
            if (tabuleiro.existeVencedor() != 0) {
                if (tabuleiro.existeVencedor() == 1)
                    azulVitorias++;
                else if (tabuleiro.existeVencedor() == 2)
                    vermelhoVitorias++;
                else if (tabuleiro.existeVencedor() == 3)
                    verdeVitorias++;
                else if (tabuleiro.existeVencedor() == 4)
                    amareloVitorias++;
                vitorias++;
                // tabuleiro.relatorio();
                tabuleiro.resetarJogo();
            }
        }
        System.out.println("azul vitorias: " + azulVitorias);
        System.out.println("vermelho vitorias: " + vermelhoVitorias);
        System.out.println("verde vitorias: " + verdeVitorias);
        System.out.println("amarelo vitorias: " + amareloVitorias);
    }
}
