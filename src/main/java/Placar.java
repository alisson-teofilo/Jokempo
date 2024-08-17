public class Placar {

    private int vitoriaRodada;
    private int derrotaRodada;

    private int vitoriasPartida;
    private int derrotasPartida;
    private int empatesPartida;

    // inicar o jogo com o placar 0 x 0
    public Placar(){
        this.derrotaRodada = 0;
        this.vitoriaRodada = 0;

        this.vitoriasPartida = 0;
        this.derrotasPartida = 0;
        this.empatesPartida = 0;
    }

    public int getVitoriaRodada() {
        return vitoriaRodada;
    }

    public void setVitoriaRodada(int vitoriaRodada) {
        this.vitoriaRodada += vitoriaRodada;
    }

    public int getDerrotaRodada() {
        return derrotaRodada;
    }

    public void setDerrotaRodada(int derrotaRodada) {
        this.derrotaRodada += derrotaRodada;
    }

    public int getVitoriasPartida() {
        return vitoriasPartida;
    }

    public void setVitoriasPartida(int vitoriasPartida) {
        this.vitoriasPartida += vitoriasPartida;
    }

    public int getDerrotasPartida() {
        return derrotasPartida;
    }

    public void setDerrotasPartida(int derrotasPartida) {
        this.derrotasPartida += derrotasPartida;
    }

    public int getEmpatesPartida() {
        return empatesPartida;
    }

    public void setEmpatesPartida(int empatesPartida) {
        this.empatesPartida += empatesPartida;
    }
}
