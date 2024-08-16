
import java.util.Random;
import java.util.Scanner;

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
                System.out.println("Escolha quantas rodadas deve ter a partida: 1, 5 ou 7");

                int qtdJogadas = entrada.nextInt();

                for(int i = 0; i < qtdJogadas; i++){

                iniciarJogo(entrada, usuario);
                processarResultado(Main.usuario, Main.chatGepeto, entrada);

                }

                System.out.println("Aperte ENTER para voltar ao Menu Principal...");

                // limpa buffer e aguar ação do usuário
                entrada.nextLine();
                entrada.nextLine();

            } else if (opcaoMenu == 2) {
                System.out.println("Jogo finalizado!");
            } else {
                System.out.println("OPÇÃO INVÁLIDA!!");
            }

        } while(opcaoMenu != 2);

    }

    private static void iniciarJogo(Scanner entrada, Usuario usuario) {
        System.out.print("\n");
        int escolhaJogador;

        // usuário deve escolher entre as opções
        System.out.println("Escolha um número: 1 - PEDRA, 2 - PAPEL, 3 - TESOURA");

        escolhaJogador = entrada.nextInt();

        System.out.print("\n");
        System.out.printf("Jogador(a): %s, escolheu: %s", usuario.nome, escolhaJogador == 1 ? "pedra" : escolhaJogador == 2 ? "papel" : "tesoura");
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
        System.out.printf("Jogador(a): %s, escolheu: %s", chatGepeto.nome, chatGepeto.escolhaChat);
        System.out.print("\n");
        return chatGepeto;
    }

    private static void processarResultado(Usuario usuario, ChatGepeto chatGepeto, Scanner entrada) {

        System.out.print("\n");
        System.out.println("######## PROCESSANDO RESULTADO ########");

        // verifica se houve empate
        if(usuario.escolhaJogador.equals(chatGepeto.escolhaChat)) {
            System.out.println("HOUVE EMPATE");
            placarUsuario.setEmpates(1);
            placarChatgepeto.setEmpates(1);
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

            // acrescenta pontuação ao placar
            if(usuario.getPontoPartida() > chatGepeto.getPontoPartida()) {
                placarUsuario.setVitorias(1);
                placarChatgepeto.setDerrotas(1);
            }else{
                placarChatgepeto.setVitorias(1);
                placarUsuario.setDerrotas(1);
            }

            // determina o ganhador da rodada
            System.out.println("O GANHADOR É: " + (usuario.getPontoPartida() > chatGepeto.getPontoPartida() ? usuario.nome : chatGepeto.nome));
            System.out.print("\n");

            // zera pontos da partida
            usuario.setPontoPartida(0);
            chatGepeto.setPontoPartida(0);
        }

        System.out.println("####### PONTUAÇÃO TOTAL! #######");
        System.out.print("\n");
        System.out.printf("Jogador(a) %s: total de vitórias: %d, total de empates: %d, total de derrotas: %d.", usuario.nome, placarUsuario.getVitorias(), placarUsuario.getEmpates(), placarUsuario.getDerrotas());
        System.out.print("\n");
        System.out.printf("Jogador %s: total de vitórias: %d, total de empates: %d, total de derrotas: %d.", chatGepeto.nome, placarChatgepeto.getVitorias(), placarChatgepeto.getEmpates(), placarChatgepeto.getDerrotas());
        System.out.println("\n");

    }


}
