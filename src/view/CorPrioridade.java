package src.view;
import java.awt.Component;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CorPrioridade
extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(

        JTable tabela,

        Object valor,

        boolean selecionado,

        boolean foco,

        int linha,

        int coluna
    ) {

        Component c =
        super.getTableCellRendererComponent(

            tabela,
            valor,
            selecionado,
            foco,
            linha,
            coluna
        );

        String prioridade =
        valor.toString();

        // SELEÇÃO
        if(selecionado) {

            c.setBackground(
                tabela.getSelectionBackground()
            );

            c.setForeground(
                tabela.getSelectionForeground()
            );

            return c;
        }

        // URGENTE
        if(prioridade.equals("URGENTE")) {

            c.setBackground(
                Color.RED
            );

            c.setForeground(
                Color.WHITE
            );
        }

        // MÉDIA
        else if(
            prioridade.equals("MÉDIA")
        ) {

            c.setBackground(
                Color.YELLOW
            );

            c.setForeground(
                Color.BLACK
            );
        }

        // BAIXA
        else {

            c.setBackground(
                Color.GREEN
            );

            c.setForeground(
                Color.BLACK
            );
        }

        return c;
    }
}