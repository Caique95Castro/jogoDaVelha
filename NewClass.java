
import java.util.Random;
import java.util.Scanner;

/**
 * @author caique.ocastro
 * <p>
 *
 * /**********************************************************
 */
/**
 * *
 */
/*     Projeto Semestral I                                **/
 /*                                                        **/
 /*                                                        **/
/**
 * Caique de Oliveira Castro *
 */
/**
 * Heitor Pereira de Lucena *
 */
/**
 * Willian Vieira de Sousa *
 */
/*                                                        **/
 /*    Data de Entrega:                                    **/
 /*     27/05/2024                                         **/
 /*                                                        **/
/**
 * ********************************************************
 */
public class NewClass {

    // Declaração de variáveis globais
    public static char[][] tabuleiro; // Matriz que representa o tabuleiro do jogo
    public static char jogadorAtual; // Armazena o jogador atual ('X' ou 'O')
    public static char player1; // jogador 1
    public static char player2; // jogador 2
    public static int pontosJogador1; // Pontuação do jogador 1
    public static int pontosJogador2; // Pontuação do jogador 2

    // Exibe o primeiro menu do jogo e lida com a escolha do jogar ou sair.    
    public static void menuPrincipal(Scanner input) {

        boolean taCerto = false;
        do {
            exibirMenu();
            int opc = input.nextInt();

            switch (opc) {
                case 1:
                    imprimeMenuPrincipal(input);
                    break;
                case 2:
                    sairJogo(input);
                    taCerto = true;
                    break;
                default:
                    System.out.println("Opção inválida, por favor digite uma opção válida!");
                    break;
            }

        } while (!taCerto);
    }

    // Exibe o menu inicial do jogo
    public static void exibirMenu() {
        System.out.println("");
        System.out.println("Seja bem-vindo(a) ao \n");

        // NOME DO JOGO
        System.out.println("JOGO DA VELHA \n");

        // MENU
        System.out.println("-------------------");
        System.out.println("|    [1] Jogar    |");
        System.out.println("|    [2] Sair     |");
        System.out.println("-------------------\n");
        System.out.println("Digite uma opção para prosseguir: ");
    }

    // Exibe o menu principal do jogo e lida com a escolha do usuário
    // e de escolha de modos de jogo
    public static void imprimeMenuPrincipal(Scanner input) {

        boolean taCerto = false;
        do {
            exibirPainel(input);
            int escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    inicializarTabuleiro(); // Arruma o tabuleiro para um novo jogo
                    modoJogador(input); // Inicia o modo Jogador VS Jogador
                    break;
                case 2:
                    inicializarTabuleiro(); // Arruma o tabuleiro para um novo jogo
                    modoFacil(input); // Inicia o modo Jogador VS Máquina (Fácil)
                    break;
                case 3:
                    inicializarTabuleiro(); // Arruma o tabuleiro para um novo jogo
                    modoDificil(input); // Inicia o modo Jogador VS Máquina (Difícil)
                    break;
                case 4:
                    sairJogo(input); // Sai do jogo
                    taCerto = true; // Termina o loop
                    break;
                default:
                    System.out.println("Opção inválida, por favor digite uma opção válida!");
                    break;
            }

        } while (!taCerto);
    }

    // Exibe o painel com os modos de jogo
    public static void exibirPainel(Scanner input) {

        // MENU
        System.out.println("-----------------------------");
        System.out.println("|    [1] Jogador X Jogador    |");
        System.out.println("|    [2] Modo Facil         |");
        System.out.println("|    [3] Modo Dificil       |");
        System.out.println("|    [4] Sair               |");
        System.out.println("-----------------------------\n");
        System.out.println("Digite uma opção para prosseguir: ");

    }

    //responsável por inicializar o tabuleiro
    public static void inicializarTabuleiro() {
        tabuleiro = new char[][]{
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        jogadorAtual = ' ';
    }

    // responsável por imprimir o tabuleiro
    public static void imprimirTabuleiro() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) { // Itera sobre as linhas do tabuleiro
            System.out.print("| ");
            for (int j = 0; j < 3; j++) { // Itera sobre as colunas do tabuleiro
                System.out.print(tabuleiro[i][j] + " | "); // Imprime o conteúdo da célula seguido por " | "
            }
            System.out.println(); // Pula para a próxima linha após imprimir uma linha do tabuleiro
            System.out.println("-------------"); // Imprime a linha separadora entre as linhas do tabuleiro
        }
    }

    // Solicita ao usuário a escolha de seu símbolo (X ou O)
    public static void jogadaUsuario(Scanner input) {

        boolean certo = false;
        do {
            System.out.println("Escolha seu símbolo (X ou O): ");
            String dig = input.next();

            switch (dig.toUpperCase()) {
                case "X":
                    player1 = 'X';
                    player2 = 'O';
                    certo = true;
                    break;
                case "O":
                    player1 = 'O';
                    player2 = 'X';
                    certo = true;
                    break;
                default:
                    System.out.println("Opção Inválida. Escolha seu símbolo (X ou O): ");
                    break;
            }
        } while (!certo);

        jogadorAtual = player1;
        System.out.println("jogador 1 você é: " + player1);
        System.out.println("jogador 2 você é: " + player2);
    }

    // Lê a coordenada da linha fornecida pelo usuário (1 a 3) e ajusta para o índice do array (0 a 2)
    public static int leiaCoordenadaLinha(Scanner scanner) {
        System.out.println("Digite a linha (1, 2 ou 3):");
        int linha = scanner.nextInt();
        return linha - 1;
    }

    // Lê a coordenada da coluna fornecida pelo usuário (1 a 3) e ajusta para o índice do array (0 a 2)
    public static int leiaCoordenadaColuna(Scanner scanner) {
        System.out.println("Digite a coluna (1, 2 ou 3):");
        int coluna = scanner.nextInt();
        return coluna - 1;
    }

    // Verifica se a posição fornecida é válida e está vazia
    public static boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ';
    }

    // Marca a jogada no tabuleiro
    public static void jogar(int linha, int coluna) {
        tabuleiro[linha][coluna] = jogadorAtual;
    }

    // Verifica se o tabuleiro está cheio, indicando um empate
    /* Essa função verifica se o tabuleiro está completamente preenchido, 
    indicando um empate (velha). Ela percorre todas as posições do tabuleiro. 
    Se encontrar uma posição vazia (' '), retorna false. Caso contrário, retorna true, 
    indicando que o tabuleiro está cheio */
    public static boolean verificaVelha() {
        for (int i = 0; i < 3; i++) { // Itera sobre as linhas do tabuleiro
            for (int j = 0; j < 3; j++) { // Itera sobre as colunas do tabuleiro
                if (tabuleiro[i][j] == ' ') { // Verifica se a posição atual está vazia
                    return false; // Se houver pelo menos uma posição vazia, não é velha (empate)
                }
            }
        }
        return true; // Se não houver posições vazias, é velha (empate)
    }

    // Verifica se há um vencedor no jogo
    /* Essa função verifica se há um vencedor no jogo. Ela verifica cada linha, 
    coluna e as duas diagonais do tabuleiro para ver se todos os elementos são iguais (e não vazios). 
    Se encontrar uma linha, coluna ou diagonal com todos os mesmos símbolos (X ou O), retorna o símbolo do 
    vencedor. Se não encontrar nenhum vencedor, retorna um espaço (' '), indicando que ainda não há vencedor. */
    public static char verificaVencedor() {
        for (int i = 0; i < 3; i++) {
            // Verifica as linhas
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != ' ') {
                return tabuleiro[i][0]; // Retorna o símbolo do vencedor na linha
            }
            // Verifica as colunas
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i] != ' ') {
                return tabuleiro[0][i]; // Retorna o símbolo do vencedor na coluna
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != ' ') {
            return tabuleiro[0][0]; // Retorna o símbolo do vencedor na diagonal principal
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != ' ') {
            return tabuleiro[0][2]; // Retorna o símbolo do vencedor na diagonal secundária
        }
        // Se não encontrou vencedor, retorna "espaço"
        return ' ';
    }

    // Modo de jogo Jogador vs Jogador
    /* Essa função gerencia o modo de jogo jogador versus jogador. Solicita aos jogadores que 
    escolham seus símbolos, e em seguida, alterna as jogadas entre os dois jogadores até que 
    haja um vencedor ou o tabuleiro fique cheio (empate). A cada jogada, verifica se a posição é válida
     e atualiza o tabuleiro. Após cada jogada, verifica se há um vencedor. Quando o jogo termina, imprime 
     o resultado e a pontuação atual. */
    public static void modoJogador(Scanner input) {

        System.out.println("JOGADOR X JOGADOR");
        jogadaUsuario(input); // Solicita aos jogadores que escolham seus símbolos

        char vencedor = ' ';
        while (vencedor == ' ' && !verificaVelha()) { // Continua enquanto não houver vencedor e o tabuleiro não estiver cheio
            imprimirTabuleiro(); // Imprime o tabuleiro atual
            System.out.println("Vez do jogador " + jogadorAtual);
            int linha = leiaCoordenadaLinha(input); // Lê a coordenada da linha
            int coluna = leiaCoordenadaColuna(input); // Lê a coordenada da coluna
            if (posicaoValida(linha, coluna)) { // Verifica se a posição é válida
                jogar(linha, coluna);  // Marca a jogada no tabuleiro
                vencedor = verificaVencedor(); // Verifica se há um vencedor
                if (vencedor == ' ') {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X'; // Alterna o jogador atual
                }
            } else {
                System.out.println("Posição inválida! Tente novamente.");
            }
        }
        imprimirTabuleiro(); // Imprime o tabuleiro final
        if (vencedor != ' ') {
            System.out.println("Jogador " + vencedor + " venceu!");
            if (vencedor == player1) {
                pontosJogador1++;
            } else {
                pontosJogador2++;
            }
        } else {
            System.out.println("Empate!");
        }
        imprimePontuacao(); // Imprime a pontuação atual
    }

    // Imprime a pontuação atual dos jogadores
    public static void imprimePontuacao() {
        System.out.println("Pontuação:");
        System.out.println("Jogador 1 (X): " + pontosJogador1);
        System.out.println("Jogador 2 (O): " + pontosJogador2);
    }

    // Modo de jogo Fácil contra a máquina
    /* Essa função gerencia o modo de jogo fácil contra a máquina. Solicita aos jogadores 
    que escolham seus símbolos, e em seguida, alterna as jogadas entre o jogador e a máquina 
    até que haja um vencedor ou o tabuleiro fique cheio (empate). A cada jogada do jogador, 
    verifica se a posição é válida e atualiza o tabuleiro. A jogada da máquina é feita de 
    forma aleatória. Após cada jogada, verifica se há um vencedor. Quando o jogo termina, 
    imprime o resultado e a pontuação atual. */
    public static void modoFacil(Scanner input) {

        System.out.println("MODO FACIL");
        jogadaUsuario(input); // Solicita aos jogadores que escolham seus símbolos

        char vencedor = ' ';

        while (vencedor == ' ' && !verificaVelha()) { // Continua enquanto não houver vencedor e o tabuleiro não estiver cheio
            imprimirTabuleiro(); // Imprime o tabuleiro atual
            if (jogadorAtual == player1) {
                System.out.println("Sua vez! (Jogador " + player1 + ")");
                int linha = leiaCoordenadaLinha(input); // Lê a coordenada da linha
                int coluna = leiaCoordenadaColuna(input); // Lê a coordenada da coluna
                if (posicaoValida(linha, coluna)) { // Verifica se a posição é válida
                    jogar(linha, coluna); // Marca a jogada no tabuleiro
                    vencedor = verificaVencedor(); // Verifica se há um vencedor
                    if (vencedor == ' ') {
                        jogadorAtual = player2; // Alterna para a máquina
                    }
                } else {
                    System.out.println("Posição inválida! Tente novamente.");
                }
            } else {
                System.out.println("Vez da Máquina (Jogador " + player2 + ")");
                jogadaMaquinaFacil(); // Realiza uma jogada aleatória para a máquina
                vencedor = verificaVencedor(); // Verifica se há um vencedor
                if (vencedor == ' ') {
                    jogadorAtual = player1; // Alterna para o jogador
                }
            }
        }
        imprimirTabuleiro(); // Imprime o tabuleiro final
        if (vencedor != ' ') {
            System.out.println("Jogador " + vencedor + " venceu!");
            if (vencedor == player1) {
                pontosJogador1++;
            } else {
                pontosJogador2++;
            }
        } else {
            System.out.println("Empate!");
        }
        imprimePontuacao(); // Imprime a pontuação atual
    }

    // Realiza uma jogada aleatória para a máquina no modo fácil
    /* ssa função realiza uma jogada aleatória para a máquina no modo 
    fácil. Utiliza a classe Random para gerar números aleatórios para as 
    coordenadas da linha e coluna. Verifica se a posição gerada é válida (não ocupada).
     Se a posição for válida, marca a jogada no tabuleiro. Caso contrário, continua 
     gerando novas coordenadas até encontrar uma posição válida. */
    public static void jogadaMaquinaFacil() {
        Random jogadaAleatoria = new Random(); // Cria um objeto Random para gerar jogadas aleatórias
        int linha, coluna;
        do {
            linha = jogadaAleatoria.nextInt(3); // Gera um número aleatório entre 0 e 2 para a linha
            coluna = jogadaAleatoria.nextInt(3); // Gera um número aleatório entre 0 e 2 para a coluna
        } while (!posicaoValida(linha, coluna)); // Continua gerando jogadas até encontrar uma posição válida
        System.out.println("A máquina jogou na linha " + (linha + 1) + " e coluna " + (coluna + 1));
        jogar(linha, coluna); // Marca a jogada no tabuleiro
    }

    // Modo de jogo Difícil contra a máquina (incompleto)
    public static void modoDificil(Scanner input) {
        
        System.out.println("MODO DIFICIL"); // Exibe a mensagem de início do modo difícil
        jogadaUsuario(input); // Permite ao jogador escolher seu símbolo (X ou O)

        char vencedor = ' ';

        while (vencedor == ' ' && !verificaVelha()) { // Continua jogando enquanto não há vencedor e o tabuleiro não está cheio
            imprimirTabuleiro(); // Imprime o estado atual do tabuleiro
            if (jogadorAtual == player1) { // Se for a vez do jogador
                System.out.println("Sua vez! (Jogador " + player1 + ")");
                int linha = leiaCoordenadaLinha(input);  // Lê a linha escolhida pelo jogador
                int coluna = leiaCoordenadaColuna(input); // Lê a coluna escolhida pelo jogador
                if (posicaoValida(linha, coluna)) { // Verifica se a posição escolhida é válida
                    jogar(linha, coluna);  // Marca a jogada no tabuleiro
                    vencedor = verificaVencedor(); // Verifica se há um vencedor após a jogada
                    if (vencedor == ' ') {
                        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X'; // Alterna para a vez da máquina
                    }
                } else {
                    System.out.println("Posição inválida! Tente novamente.");
                }
            } else { // Se for a vez da máquina
                jogadaMaquinaDificil();  // Executa a jogada da máquina
                vencedor = verificaVencedor(); // Verifica se há um vencedor após a jogada
                if (vencedor == ' ') {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X'; // Alterna para a vez do jogador
                }
            }
        }
        imprimirTabuleiro(); // Imprime o tabuleiro final após o fim do jogo
        if (vencedor != ' ') { // Verifica e exibe o resultado do jogo
            System.out.println("Jogador " + vencedor + " venceu!");
            if (vencedor == player1) {
                pontosJogador1++; // Incrementa a pontuação do jogador 1 se ele venceu
            } else {
                pontosJogador2++; // Incrementa a pontuação do jogador 2 se ele venceu
            }
        } else {
            System.out.println("Empate!"); // Exibe mensagem de empate se não houve vencedor
        }
        imprimePontuacao(); // Imprime a pontuação atual dos jogadores

    }
    // Função para implementar a jogada difícil da máquina
    public static void jogadaMaquinaDificil() {
        // Primeiro, verificar se a máquina pode vencer na próxima jogada
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = jogadorAtual;
                    if (verificaVencedor() == jogadorAtual) {
                        return;
                    }
                    tabuleiro[i][j] = ' ';
                }
            }
        }
        // Em seguida, verificar se o jogador 'Humano' pode vencer na próxima jogada, e bloquear essa jogada
        char oponente = (jogadorAtual == 'X') ? 'O' : 'X';  // Determina o símbolo do oponente
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') { // Verifica todas as posições vazias
                    tabuleiro[i][j] = oponente; // Temporariamente marca a posição como a jogada da máquina
                    if (verificaVencedor() == oponente) { // Verifica se esta jogada leva à vitória
                        tabuleiro[i][j] = jogadorAtual; // Se a máquina pode vencer, finaliza a jogada
                        return; // Finaliza a jogada
                    }
                    tabuleiro[i][j] = ' '; // Reverte a jogada temporária
                }
            }
        }

        // Tenta ocupar o centro, se estiver vazio
        if (tabuleiro[1][1] == ' ') {
            tabuleiro[1][1] = jogadorAtual; // Marca o centro se estiver vazio
            return;
        }

        // Tentar ocupar um dos cantos, se estiver vazio
        int[][] cantos = {{0, 0}, {0, 2}, {2, 0}, {2, 2}}; // Define as coordenadas dos cantos
        for (int[] canto : cantos) {
            if (tabuleiro[canto[0]][canto[1]] == ' ') {
                tabuleiro[canto[0]][canto[1]] = jogadorAtual; // Marca um canto se estiver vazio
                return;
            }
        }

        // Tentar ocupar qualquer outra posição
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = jogadorAtual; // Marca a primeira posição vazia encontrada
                    return;
                }
            }
        }
    }
    // Exibe a mensagem de saída do jogo
    public static void sairJogo(Scanner input) {
        System.out.println("\n--------");
        System.out.println("| SAIR |");
        System.out.println("--------\n");
        System.out.println(" Você saiu do Jogo!");
    }

    // Função principal do jogo, inicializa o tabuleiro e exibe o menu principal
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        inicializarTabuleiro();
        menuPrincipal(input);
        input.close();
    }
}
