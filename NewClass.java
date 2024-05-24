/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author heito
 */

    
import java.util.Random;
import java.util.Scanner;

/**
 * @author caique.ocastro
 * <p>
 * ********************************************************
 * <p>
 * *
 * <p>
 * Caique de Oliveira Castro *
 * <p>
 * Heitor Pereira de Lucena *
 * <p>
 * Willian Vieira de Sousa *
 */
/**
 * ********************************************************
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

    public static char[][] tabuleiro;
    public static char jogadorAtual;
    public static char player1;
    public static char jogadorComputador;
    public static char player2;
    public static char pontosJogador1;
    public static char pontosJogador2;


    public static void menuPrincipal(Scanner input) {

        boolean taCerto = false;
        do {
            exibirMenu(input);
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

    public static void exibirMenu(Scanner input) {
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

    public static void imprimeMenuPrincipal(Scanner input) {

        boolean taCerto = false;
        do {
            exibirPainel(input);
            int escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    imprimirTabuleiro();
                    jogadorXjogador(input);
                    inicializarTabuleiro();
                    break;
                case 2:
                    imprimirTabuleiro();
                    modoFacil(input);
                    inicializarTabuleiro();
                    break;
                case 3:
                    imprimirTabuleiro();
                    modoDificil(input);
                    inicializarTabuleiro();
                    break;
                case 4:
                    sairJogo(input);
                    taCerto = true;
                    break;
                default:
                    System.out.println("Opção inválida, por favor digite uma opção válida!");
                    break;
            }

        } while (!taCerto);
    }

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
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // responsável por imprimir o tabuleiro
    public static void imprimirTabuleiro() {
        tabuleiro = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public static void escolhaXO(Scanner input) {

        boolean certo = false;
        do {
            System.out.println("Escolha seu símbolo (X ou O): ");
            String dig = input.next();

            switch (dig) {
                case "X":
                case "x":
                    player1 = 'X';
                    player2 = 'O';
                    certo = true;
                    break;
                case "O":
                case "o":
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

    public static int leiaCoordenadaLinha() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a linha (0, 1 ou 2):");
        return scanner.nextInt();
    }

    public static int leiaCoordenadaColuna() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a coluna (0, 1 ou 2):");
        return scanner.nextInt();
    }

    public static boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == '-';
    }


    public static void jogada(int linha, int coluna) {
        boolean jogadaValida = false;

        Scanner input = new Scanner(System.in);
        while (!jogadaValida) {
            System.out.println("Jogador " + jogadorAtual + ", faça sua jogada! (linha e coluna de 1 a 3): ");
            linha = input.nextInt() - 1;
            coluna = input.nextInt() - 1;

            if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
                tabuleiro[linha][coluna] = jogadorAtual;
                jogadaValida = true;
            } else {
                System.out.println("Jogada inválida, tente novamente.");
            }
        }
    }

    public static boolean verificaVelha() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static char verificaVencedor() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != '-') {
                return tabuleiro[i][0];
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i] != '-') {
                return tabuleiro[0][i];
            }
        }
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != '-') {
            return tabuleiro[0][0];
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != '-') {
            return tabuleiro[0][2];
        }
        return '-';
    }


    public static int pontosJogo(int pontosJogador1, int pontosJogador2 ) {

        int pontosJogo = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    pontosJogador1++;
                }
            }
        }
        int pontosJogo1 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    pontosJogador2++;
                }
            }
        }
        return pontosJogo;
    }



    public static void jogadorXjogador(Scanner input) {

        boolean jogoAtivo = true;

        System.out.println("JOGADOR X JOGADOR");
        escolhaXO(input);

        char vencedor = '-';
        while (vencedor == '-' && !verificaVelha()) {
            imprimirTabuleiro();
            System.out.println("Vez do jogador " + jogadorAtual);
            int linha = leiaCoordenadaLinha();
            int coluna = leiaCoordenadaColuna();
            if (posicaoValida(linha, coluna)) {
                jogada(linha, coluna);
                vencedor = verificaVencedor();
                if (vencedor == '-') {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Posição inválida! Tente novamente.");
            }
        }
        imprimirTabuleiro();
        if (vencedor != '-') {
            System.out.println("Jogador " + vencedor + " venceu!");
            if (vencedor == 'X') {
                pontosJogador1++;
            } else {
                pontosJogador2++;
            }
        } else {
            System.out.println("Empate!");
        }
        imprimePontuacao();
    }

    public static void imprimePontuacao() {
        System.out.println("Pontuação:");
        System.out.println("Jogador 1 (X): " + pontosJogador1);
        System.out.println("Jogador 2 (O): " + pontosJogador2);
    }


    public static void modoFacil(Scanner input) {
        System.out.println("MODO FACIL");
        Random jogadaAleatoria = new Random(3);
        escolhaXO(input);
        imprimirTabuleiro();

    }

    public static void modoDificil(Scanner input) {
        System.out.println("MODO DIFICIL");
        escolhaXO(input);
        imprimirTabuleiro();

    }

    public static void sairJogo(Scanner input) {
        System.out.println("\n--------");
        System.out.println("| SAIR |");
        System.out.println("--------\n");
        System.out.println(" Você saiu do Jogo!");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        imprimeMenuPrincipal(input);



    }
}





    
    
