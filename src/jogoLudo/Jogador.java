package jogoLudo;

public class Jogador {

    private int[] pecas;

    public Jogador(){
        setPecas(new int[4]);
        for (int i =0; i < 4; i++) getPecas()[i] = -1;
    }

    public int[] getPecas() {
        return pecas;
    }

    public void setPecas(int[] pecas) {
        this.pecas = pecas;
    }
}
