/*import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class ExemploLanterna {

    public static void main(String[] args) {
        // 1. Inicializa o terminal e a tela
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

        try (Terminal terminal = terminalFactory.createTerminal();
             Screen screen = new TerminalScreen(terminal)) {

            screen.startScreen();

            // 2. Cria a interface GUI sobre a tela do Lanterna
            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            // 3. Cria a janela principal do sistema
            BasicWindow window = new BasicWindow("Sistema de Banco de Dados");
            window.setHints(Arrays.asList(Window.Hint.CENTERED)); // Centraliza a janela

            // 4. Cria o painel principal com layout vertical
            Panel mainPanel = new Panel();
            mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

            // Mensagem de boas-vindas
            mainPanel.addComponent(new Label("Selecione uma opção no menu abaixo:"));
            mainPanel.addComponent(new EmptySpace(new TerminalSize(0, 1))); // Espaçamento

            // 5. Botões de ação (Substitua pelas chamadas dos seus DAOs / Banco)
            Button btnCadastrar = new Button("1. Cadastrar Registro", () -> {
                MessageDialog.showMessageDialog(gui, "Cadastro", "Opção de cadastro selecionada!", MessageDialogButton.OK);
            });

            Button btnListar = new Button("2. Listar Registros", () -> {
                MessageDialog.showMessageDialog(gui, "Listagem", "Exibindo lista de registros...", MessageDialogButton.OK);
            });

            Button btnAtualizar = new Button("3. Atualizar Registro", () -> {
                MessageDialog.showMessageDialog(gui, "Atualização", "Opção de atualização selecionada!", MessageDialogButton.OK);
            });

            Button btnDeletar = new Button("4. Deletar Registro", () -> {
                MessageDialog.showMessageDialog(gui, "Exclusão", "Opção de exclusão selecionada!", MessageDialogButton.OK);
            });

            Button btnSair = new Button("0. Sair", () -> {
                window.close(); // Fecha a janela e encerra o loop
            });

            // Adiciona os botões ao painel
            mainPanel.addComponent(btnCadastrar);
            mainPanel.addComponent(btnListar);
            mainPanel.addComponent(btnAtualizar);
            mainPanel.addComponent(btnDeletar);
            mainPanel.addComponent(new EmptySpace(new TerminalSize(0, 1)));
            mainPanel.addComponent(btnSair);

            // Define o painel como conteúdo da janela e exibe
            window.setComponent(mainPanel);
            gui.addWindowAndWait(window);

        } catch (IOException e) {
            System.err.println("Erro ao inicializar o terminal Lanterna: " + e.getMessage());
            e.printStackTrace();
        }
    }
}*/