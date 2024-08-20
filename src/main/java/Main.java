
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Escolhi a linguagem java por ter mais familiaridade e gostar da mesma
 */

public class Main {

    static Usuario usuario = null;
    static ChatGepeto chatGepeto = null;
    static Placar placarUsuario = null;
    static Placar placarChatgepeto = null;
    public static void main(String[] args) {

        // declaração de variáveis
        int opcaoMenu = 0;
        String nome = null;
        Scanner entrada = new Scanner(System.in);
        ArrayList<Placar> listaPlacarUsuario = new ArrayList<>();
        ArrayList<Placar> listaPlacarChatgepeto = new ArrayList<>();
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

                // instância um novo placar a cada partida
                 placarUsuario = new Placar();
                 placarChatgepeto = new Placar();


                if(Main.usuario == null) {
                    System.out.print("\n");
                    System.out.println("Qual o seu nome?");
                    nome = entrada.next();
                }

                // instancia o usuário e inicia o placar
                Usuario usuario = new Usuario(nome);

                // inicia o jogo
                int qtdRodadas;
                do {

                    System.out.print("\n");
                    System.out.println("Escolha quantas partidas deverá ter a rodada: 1, 3, 5 ou 7");
                    qtdRodadas = entrada.nextInt();

                } while(qtdRodadas != 1 && qtdRodadas != 3 && qtdRodadas != 5 && qtdRodadas != 7);

                for(int i = 0; i < qtdRodadas; i++){

                    iniciarJogo(entrada, usuario, i);

                    processarResultadoRodada(Main.usuario, Main.chatGepeto, placarUsuario, placarChatgepeto);
                }

                processarResultadoPartida(qtdRodadas, entrada, placarUsuario, placarChatgepeto,  listaPlacarUsuario, listaPlacarChatgepeto);

                // aguarda interação do usuário
                pausarFluxo(entrada);

            } else if (opcaoMenu == 2) {

                exibePlacarFinal(usuario, chatGepeto, listaPlacarUsuario, listaPlacarChatgepeto);

                // aguarda interação do usuário
                pausarFluxo(entrada);

            } else if(opcaoMenu == 3) {
                System.out.println("Jogo finalizado!");
            } else {
                System.out.println("Opção inválida");
            }

        } while(opcaoMenu != 3);
    }


    private static void iniciarJogo(Scanner entrada, Usuario usuario, int numeroRodada) {
        int escolhaJogador;

        do {
            System.out.print("\n");
            escolhaJogador = 0;

            // usuário deve escolher entre as opções
            System.out.printf("%d° RODADA", numeroRodada + 1);
            System.out.print("\n");

            System.out.println("Escolha um número: 1 - PEDRA, 2 - PAPEL, 3 - TESOURA");

            escolhaJogador = entrada.nextInt();

        } while (escolhaJogador < 1 || escolhaJogador > 3);

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

    private static void processarResultadoRodada(Usuario usuario, ChatGepeto chatGepeto, Placar placarUsuario, Placar placarChatgepeto) {
        System.out.print("\n");

        // verifica se houve empate
        if(usuario.escolhaJogador.equals(chatGepeto.escolhaChat)) {
            placarUsuario.setEmpatesPartida(1);
            placarChatgepeto.setEmpatesPartida(1);
        } else {

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

            // acrescenta pontuação ao placar da partida
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
        System.out.print("\n");

        // compara o número de vitória e determina o ganhador da rodada.
        if(usuario.getPontoPartida() == chatGepeto.getPontoPartida()){
            System.out.println("Houve empate! ");
            System.out.print("\n");
        } else {

            // informa o ganhador da partida
            System.out.printf("%s GANHA DE %s", usuario.getPontoPartida() == 1 ? usuario.escolhaJogador.toUpperCase() : chatGepeto.escolhaChat.toUpperCase(),
                    chatGepeto.getPontoPartida() == 0 ? chatGepeto.escolhaChat.toUpperCase() : usuario.escolhaJogador.toUpperCase());

            System.out.println("\n");
            System.out.println("O GANHADOR É: " + (usuario.getPontoPartida() > chatGepeto.getPontoPartida() ? usuario.nome : chatGepeto.nome));
            System.out.print("\n");
        }

        // apresenta o placar com os dados de vitórias, empates e derrotas das rodadas
        placarPartida(usuario, chatGepeto);
    }

    private static void processarResultadoPartida(int qtdRodadas, Scanner entrada, Placar placarUsuario, Placar placarChatgepeto, ArrayList<Placar> listaPlacarUsuario,  ArrayList<Placar> listaPlacarChatgepeto) {
        placarRodada(qtdRodadas, placarUsuario, placarChatgepeto, listaPlacarUsuario, listaPlacarChatgepeto);
    }

    // exibe o resutado da partida: vitórias, empates e derrotas
    public static void placarPartida(Usuario usuario, ChatGepeto chatGepeto) {

        System.out.println("PONTUAÇÃO DA PARTIDA: ");
        System.out.printf("Jogador(a) %s: vitórias: %d, empates: %d, derrotas: %d.", usuario.nome, placarUsuario.getVitoriasPartida(), placarUsuario.getEmpatesPartida(), placarUsuario.getDerrotasPartida());
        System.out.print("\n");
        System.out.printf("Jogador %s: vitórias: %d, empates: %d, derrotas: %d.", chatGepeto.nome, placarChatgepeto.getVitoriasPartida(), placarChatgepeto.getEmpatesPartida(), placarChatgepeto.getDerrotasPartida());
        System.out.println("\n");

    }

    // exibe o ganhado da rodada considerando todos as partidas da mesma
    public static void placarRodada(int qtdRodadas, Placar placarUsuario, Placar placarChatgepeto,  ArrayList<Placar> listaPlacarUsuario,  ArrayList<Placar> listaPlacarChatgepeto){

        // valida se houve empate
        if(placarUsuario.getVitoriasPartida() == placarChatgepeto.getVitoriasPartida()
                && placarUsuario.getEmpatesPartida() == placarChatgepeto.getEmpatesPartida()
                && placarUsuario.getDerrotasPartida() == placarChatgepeto.getDerrotasPartida()){
            System.out.println("NÃO A GANHADOR NA RODADA \nHOUVE O MESMO NÚMERO DE VITÓRIAS, EMPATES E DERROTAS");

        } else {

            if (placarUsuario.getVitoriasPartida() > placarChatgepeto.getVitoriasPartida()) {
                placarUsuario.setVitoriaRodada(1);
                placarChatgepeto.setDerrotaRodada(1);
            } else {
                placarChatgepeto.setVitoriaRodada(1);
                placarUsuario.setDerrotaRodada(1);
            }

            // soma as partidas
            System.out.println("##### GANHADOR(A) DA RODADA #####");
            System.out.printf("COM %d VITÓRIAS, %s É O VENCEDOR DA MELHOR DE %d. PARABÉNS!"
                    , Math.max(placarUsuario.getVitoriasPartida(), placarChatgepeto.getVitoriasPartida())
                    , placarUsuario.getVitoriasPartida() > placarChatgepeto.getVitoriasPartida() ? usuario.nome : chatGepeto.nome
                    , qtdRodadas);
        }

        // cria uma lista com os placares das partidas
        listaPlacarUsuario.add(placarUsuario);
        listaPlacarChatgepeto.add(placarChatgepeto);
    }

    // exibe a estetística final do placar com a quantidade de vitórias, empates, derrotas e equivalência em porcentagem
    public static void exibePlacarFinal(Usuario usuario, ChatGepeto chatGepeto, ArrayList<Placar> listaPlacarUsuario,  ArrayList<Placar> listaPlacarChatgepeto) {

        Placar placarUsuario = new Placar();
        Placar placarChatgepeto = new Placar();

        // itera o novo objeto do placar do usuário
        for(Placar placar : listaPlacarUsuario){
            placarUsuario.setVitoriasPartida(placar.getVitoriasPartida());
            placarUsuario.setEmpatesPartida(placar.getEmpatesPartida());
            placarUsuario.setDerrotasPartida(placar.getDerrotasPartida());
        }

        // itera o novo objeto do placar do chat
        for(Placar placar: listaPlacarChatgepeto){
            placarChatgepeto.setVitoriasPartida(placar.getVitoriasPartida());
            placarChatgepeto.setEmpatesPartida(placar.getEmpatesPartida());
            placarChatgepeto.setDerrotasPartida(placar.getDerrotasPartida());
        }

        // encontra a quatidade de partidas ralizadas
        int totalPartidas = placarUsuario.getVitoriasPartida() + placarUsuario.getDerrotasPartida() + placarUsuario.getEmpatesPartida();

        System.out.println("\n");
        System.out.println("################## PLACAR FINAL ##################");
        System.out.printf("Total de partidas jogadas: %d.",  totalPartidas );
        System.out.println("\n");
        System.out.printf("Jogador(a) %s: \n", usuario.nome);
        System.out.printf("Total de vitórias: %d, que equivale a %2f%% \n", placarUsuario.getVitoriasPartida(), (double) (placarUsuario.getVitoriasPartida() * 100) / totalPartidas);
        System.out.printf("Total de empates: %d, que equivale a %2f%% \n" , placarUsuario.getEmpatesPartida(), (double) (placarUsuario.getEmpatesPartida() * 100) / totalPartidas);
        System.out.printf("Total de derrotas: %d, que equivale a %2f%% \n", placarUsuario.getDerrotasPartida(), (double ) (placarUsuario.getDerrotasPartida() * 100) / totalPartidas);
        System.out.print("\n");

        System.out.printf("Jogador(a) %s: \n", chatGepeto.nome);
        System.out.printf("Total de vitórias: %d, que equivale a %2f%% \n", placarChatgepeto.getVitoriasPartida(),(double) (placarChatgepeto.getVitoriasPartida() * 100) / totalPartidas);
        System.out.printf("Total de empates: %d, que equivale a %2f%% \n" , placarChatgepeto.getEmpatesPartida(), (double) (placarChatgepeto.getEmpatesPartida() * 100) / totalPartidas);
        System.out.printf("Total de derrotas: %d, que equivale a %2f%% \n", placarChatgepeto.getDerrotasPartida(), (double) (placarChatgepeto.getDerrotasPartida() * 100) / totalPartidas);
        System.out.print("\n");
        System.out.println("##################################################");

    }

    private static void pausarFluxo(Scanner entrada) {
        System.out.println("\n");
        System.out.println("Aperte ENTER para voltar ao Menu Principal...");

        // limpa buffer e aguar ação do usuário
        entrada.nextLine();
        entrada.nextLine();
    }

}
