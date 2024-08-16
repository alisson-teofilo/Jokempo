public class Usuario {

    protected String nome;
    protected String escolhaJogador;
    private int pontoPartida;

    public Usuario(String nome){
        this.nome = nome;
        this.pontoPartida = 0;
    }

    public int getPontoPartida() {
        return pontoPartida;
    }

    public void setPontoPartida(int pontoPartida) {
        this.pontoPartida = pontoPartida;
    }
}
