package mx.golondrina.ferreteria.DP03_U1_A2_ERVV.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelEstados extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelEstados() {
        setLayout(new BorderLayout());
        setBackground(Tema.FONDO);

        JLabel titulo = new JLabel(AppInfo.MODULO, SwingConstants.CENTER);
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(Tema.PRIMARIO);
        titulo.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));

        add(titulo, BorderLayout.NORTH);
    }
}
