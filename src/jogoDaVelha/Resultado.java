package jogoDaVelha;

public class Resultado {
    private ResultadoEnum resultado;
    private int[] sequencia;

    public Resultado(ResultadoEnum resultado, int[] sequencia) {
        this.resultado = resultado;
        this.sequencia = sequencia;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }

    public int[] getSequencia() {
        return sequencia;
    }

    public void setSequencia(int[] sequencia) {
        this.sequencia = sequencia;
    }

}
