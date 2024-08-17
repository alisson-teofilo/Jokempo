
import java.util.Random;
import java.util.Scanner;

/**
 * Escolhi a linguagem java por ter mais familiaridade e gostar da mesma
 */

public class Main {

    static Usuario usuario = null;
    static ChatGepeto chatGepeto = null;
    static Placar placarUsuario = new Placar();
    static Placar placarChatgepeto = new Placar();

    public static void main(String[] args) {

        // declaração de variáveis
        int opcaoMenu = 0;
        Scanner entrada = new Scanner(System.in);
        do {

            // inicia o jogo
            System.out.println("BEM VINDO AO JOKEMPO!");
            System.out.print("\n");
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - INICIAR JOGO");
            System.out.println("2 - EXIBIR PLACAR FINAL");
            System.out.println("2 - SAIR");

            opcaoMenu = entrada.nextInt();

            if(opcaoMenu == 1) {

                // instancia o usuário e inicia o placar
                System.out.print("\n");
                System.out.println("Qual o seu nome?");
                String nome = entrada.next();

                Usuario usuario = new Usuario(nome);

                // inicia o jogo
                System.out.print("\n");
                System.out.println("Escolha quantas rodadas deve ter a partida: 1, 3, 5 ou 7");

                int qtdRodadas = entrada.nextInt();

                for(int i = 0; i < qtdRodadas; i++){

                    iniciarJogo(entrada, usuario, i);
                    processarResultadoRodada(Main.usuario, Main.chatGepeto, entrada);

                }

                processarResultadoPartida(qtdRodadas);

                System.out.println("Aperte ENTER para voltar ao Menu Principal...");

                // limpa buffer e aguar ação do usuário
                entrada.nextLine();
                entrada.nextLine();

            } else if (opcaoMenu == 2) {
                exibePlacarFinal(usuario, chatGepeto);
            } else if(opcaoMenu == 3) {
                System.out.println("Jogo finalizado!");
            } else {
                System.out.println("Opção inválida");
            }

        } while(opcaoMenu != 3);

    }

    private static void iniciarJogo(Scanner entrada, Usuario usuario, int numeroRodada) {

        System.out.print("\n");
        int escolhaJogador;

        System.out.printf("%d° RODADA", numeroRodada + 1);
        System.out.print("\n");

        // usuário deve escolher entre as opções
        System.out.println("Escolha um número: 1 - PEDRA, 2 - PAPEL, 3 - TESOURA");

        escolhaJogador = entrada.nextInt();

        System.out.print("\n");
        System.out.println("## ESCOLHA DOS JOGADORES ##");
        System.out.print("\n");
        System.out.printf("Jogador(a): %s, Escolheu: %s", usuario.nome, escolhaJogador == 1 ? "PEDRA" : escolhaJogador == 2 ? "PAPEL" : "TESOURA");
        System.out.print("\n");

        // atribui escolha ao usuário
        Main.usuario = atribuiEscolhaJogador(escolhaJogador, usuario);

        ChatGepeto chatGepeto = new ChatGepeto();
        Main.chatGepeto = atibuirEscolhaComputador(chatGepeto);

    }

    public static Usuario atribuiEscolhaJogador(int escolhaJogador, Usuario usuario) {
        String[] opcaoEscolha = {"pedra", "papel", "tesoura"};

        for (int i = 1; i <= escolhaJogador; i++){
            if(i == escolhaJogador){
                usuario.escolhaJogador = opcaoEscolha[i - 1];
            }
        }
        return usuario;
    }

    private static ChatGepeto atibuirEscolhaComputador(ChatGepeto chatGepeto) {
        String[] opcaoEscolha = {"pedra", "papel", "tesoura"};

        // gera numero aleatório entre 1 e 3
        Random random = new Random();
        int escolhaAleatoria = random.nextInt(3) ;

        for(int i = 0; i <= escolhaAleatoria; i++){
            if(i == escolhaAleatoria){
                chatGepeto.escolhaChat = opcaoEscolha[i];
            }
        }
        System.out.printf("Jogador(a): %s, Escolheu: %s", chatGepeto.nome, chatGepeto.escolhaChat.toUpperCase());
        return chatGepeto;
    }

    private static void processarResultadoRodada(Usuario usuario, ChatGepeto chatGepeto, Scanner entrada) {
        System.out.print("\n");

        // verifica se houve empate
        if(usuario.escolhaJogador.equals(chatGepeto.escolhaChat)) {
            placarUsuario.setEmpatesPartida(1);
            placarChatgepeto.setEmpatesPartida(1);
        }

        else {

            switch (usuario.escolhaJogador) {

                case "pedra":

                    if (chatGepeto.escolhaChat.equals("papel")) {
                        chatGepeto.setPontoPartida(1);
                    }
                    else {
                        usuario.setPontoPartida(1);
                    }
                    break;

                case "papel":

                    if (chatGepeto.escolhaChat.equals("tesoura")) {
                        chatGepeto.setPontoPartida(1);
                    }
                    else {
                        usuario.setPontoPartida(1);
                    }
                    break;

                case "tesoura":

                    if (chatGepeto.escolhaChat.equals("pedra")) {
                        chatGepeto.setPontoPartida(1);
                    }
                    else {
                        usuario.setPontoPartida(1);
                    }
                    break;

                default:
                    System.out.println("Erro. Não foi possível determinar o ganhador.");
            }
        }

        // valida se não foi empate
        if(!usuario.escolhaJogador.equals(chatGepeto.escolhaChat)) {

            // acrescenta pontuação ao placar da rodada
            if(usuario.getPontoPartida() > chatGepeto.getPontoPartida()) {
                placarUsuario.setVitoriasPartida(1);
                placarChatgepeto.setDerrotasPartida(1);
            }else{
                placarChatgepeto.setVitoriasPartida(1);
                placarUsuario.setDerrotasPartida(1);
            }

        }
        exibePontuacaoRodada(usuario, chatGepeto);
    }

    public static void exibePontuacaoRodada(Usuario usuario, ChatGepeto chatGepeto) {

        // compara o número de vitória e determina o ganhador da rodada.
        if(usuario.getPontoPartida() == chatGepeto.getPontoPartida()){
            System.out.println("Houve empate! ");
        } else {

            // informa o ganhador da partida
            System.out.print("\n");
            System.out.printf("%s GANHA DE %s", placarUsuario.getVitoriasPartida() == 1 ? usuario.escolhaJogador.toUpperCase() : chatGepeto.escolhaChat.toUpperCase(),
                    placarChatgepeto.getDerrotaRodada() == 1 ? chatGepeto.escolhaChat.toUpperCase() : usuario.escolhaJogador.toUpperCase());

            System.out.println("\n");
            System.out.println("O GANHADOR É: " + (usuario.getPontoPartida() > chatGepeto.getPontoPartida() ? usuario.nome : chatGepeto.nome));
            System.out.print("\n");
        }

        // apresenta o placar com os dados de vitórias, empates e derrotas das rodadas
        placarPartida(usuario, chatGepeto);

        // zera pontos da partida
        usuario.setPontoPartida(0);
        chatGepeto.setPontoPartida(0);
    }

    private static void processarResultadoPartida(int qtdRodadas) {
        placarRodada(qtdRodadas);
    }

    public static void placarPartida(Usuario usuario, ChatGepeto chatGepeto) {

        System.out.println("PONTUAÇÃO DA PARTIDA: ");
        System.out.print("\n");
        System.out.printf("Jogador(a) %s: vitórias: %d, empates: %d, derrotas: %d.", usuario.nome, placarUsuario.getVitoriasPartida(), placarUsuario.getEmpatesPartida(), placarUsuario.getDerrotasPartida());
        System.out.print("\n");
        System.out.printf("Jogador %s: vitórias: %d, empates: %d, derrotas: %d.", chatGepeto.nome, placarChatgepeto.getVitoriasPartida(), placarChatgepeto.getEmpatesPartida(), placarChatgepeto.getDerrotasPartida());
        System.out.println("\n");
    }

    public static void placarRodada(int qtdRodadas){

        // processa todas as todadas da partida
        if(placarUsuario.getVitoriasPartida() > placarChatgepeto.getVitoriasPartida()){
            placarUsuario.setVitoriaRodada(1);
            placarChatgepeto.setDerrotaRodada(1);
        } else {
            placarChatgepeto.setVitoriaRodada(1);
            placarUsuario.setDerrotaRodada(1);
        }

        System.out.println("\n");
        System.out.println("##### GANHADOR(A) DA RODADA #####");
        System.out.printf("COM %d VITÓRIAS, %s É O VENCEDOR DA MELHRO DE %d. PARABÉNS!"
                , Math.max(placarUsuario.getVitoriasPartida(), placarChatgepeto.getVitoriasPartida())
                , placarUsuario.getVitoriasPartida() > placarChatgepeto.getVitoriasPartida() ? usuario.nome : chatGepeto.nome
                , qtdRodadas);
    }

    public static void exibePlacarFinal(Usuario usuario, ChatGepeto chatGepeto) {

//        System.out.println("################## PLACAR FINAL ##################");
//        System.out.println("\n");
//        System.out.printf(" %s: total de vitórias: %d, total de empates: %d, total de derrotas: %d.", usuario.nome, placarUsuario.getVitorias(), placarUsuario.getEmpates(), placarUsuario.getDerrotas());
//        System.out.print("\n");
//        System.out.printf("Jogador %s: total de vitórias: %d, total de empates: %d, total de derrotas: %d.", chatGepeto.nome, placarChatgepeto.getVitorias(), placarChatgepeto.getEmpates(), placarChatgepeto.getDerrotas());
//        System.out.println("\n");
    }


}
