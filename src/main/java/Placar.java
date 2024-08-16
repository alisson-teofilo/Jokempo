public class Placar {

    private int vitorias;
    private int derrotas;
    private int empates;

    // inicar o jogo com o placar 0 x 0
    public Placar(){
        this.vitorias = 0;
        this.derrotas = 0;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias += vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas += derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates += empates;
    }
}
