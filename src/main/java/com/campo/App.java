package com.campo;

import com.campo.model.Tabuleiro;
import com.campo.visao.TabuleiroConsole;

public class App 
{
    public static void main( String[] args ) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new TabuleiroConsole(tabuleiro);
    }
}
