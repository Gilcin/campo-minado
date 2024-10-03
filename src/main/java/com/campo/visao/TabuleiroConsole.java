package com.campo.visao;

import com.campo.exception.ExplosaoException;
import com.campo.exception.SairException;
import com.campo.model.Tabuleiro;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Iterator;

public class TabuleiroConsole {
    
    private Tabuleiro tabuleiro;    
    private Scanner scanner = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;

            while (continuar) {
                cicloDoJogo();
                
                System.out.println("Bora mais uma? (S/n)");
                String resposta = scanner.nextLine();
                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (SairException e) {
            System.out.println("Nao se va... :(");
        } finally {
            scanner.close();
        }
    }

    private void cicloDoJogo() {
        try {
            
            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);
                String acao = capturarValorDigitado("Digite (x, y): ");
                
                Iterator<Integer> xy = Arrays.stream(acao.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();

                acao = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
                if ("1".equals(acao)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(acao)) {  
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Voce ganhou!!!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Voce perdeu!");
        }
    }
    
    private String capturarValorDigitado (String texto) {
        System.out.println(texto);
        String acao = scanner.nextLine();
        if ("sair".equalsIgnoreCase(acao)) {
            throw new SairException();
        }
        return acao;
    }
}
