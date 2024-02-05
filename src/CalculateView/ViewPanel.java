package CalculateView;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

    public static JTextArea textArea;

    public ViewPanel() {
        initComps();
        layoutComps();
    }

    private void initComps() {
        textArea = new JTextArea(20, 60);
        textArea.setEditable(false);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(new JLabel("Results:"), BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
