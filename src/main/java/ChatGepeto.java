public class ChatGepeto {

    protected String nome;
    protected String escolhaChat;
    private int pontoPartida;

    public ChatGepeto(){
        this.nome = "Chat-gepeto";
        this.pontoPartida = 0;
    }

    public int getPontoPartida() {
        return pontoPartida;
    }

    public void setPontoPartida(int pontoPartida) {
        this.pontoPartida = pontoPartida;
    }
}
