package jogoDaVelha;

public enum ResultadoEnum {
    VITORIA_X, VITORIA_O, EMPATE;

    @Override
    public String toString() {
        switch (this) {
            case VITORIA_X:
                return "Vitoria X";
            case VITORIA_O:
                return "Vitoria O";
            case EMPATE:
                return "Empate";
            default:
                return "Resultado desconhecido";
        }
    }
}
