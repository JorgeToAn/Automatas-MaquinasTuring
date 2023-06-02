public class Resultado {
    private boolean aceptado;
    private String salida;

    public Resultado(boolean aceptado, String salida) {
        this.aceptado = aceptado;
        this.salida = salida;
    }

    public boolean getAceptado() {
        return this.aceptado;
    }

    public String getSalida() {
        return this.salida;
    }
}
