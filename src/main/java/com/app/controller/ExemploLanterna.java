package com.app.controller;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ExemploLanterna {
    public static void main(String[] args) throws IOException, InterruptedException {
        try{// 1. Cria o terminal padrão (ou emulador se rodar na IDE)
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);

            // 2. Inicia o modo de tela cheia (limpa o terminal original)
            screen.startScreen();

            // 3. Modifica o buffer de memória (coluna 10, linha 5)
            TextColor verde = new TextColor.RGB(0, 255, 0);
            TextColor preto = new TextColor.RGB(0, 0, 0);

            screen.setCharacter(10, 5, new TextCharacter('O', verde, preto));
            screen.setCharacter(11, 5, new TextCharacter('l', verde, preto));
            screen.setCharacter(12, 5, new TextCharacter('á', verde, preto));

            // 4. "Desenha" as modificações no terminal real
            screen.refresh();

            // Aguarda 3 segundos antes de fechar
            Thread.sleep(3000);

            // 5. Restaura o terminal ao estado original
            screen.stopScreen();

        }   catch (Exception e) {
            e.printStackTrace();
        }


    }
}
