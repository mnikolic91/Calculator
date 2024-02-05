package CalculateView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MenuPanel menuPanel;
    ViewPanel viewPanel;
    CalculationPanel calculationPanel;

    public MainFrame() {
        setTitle("Jednostavni kalkulator");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComps();
        layoutComps();
        activateComps();
        setVisible(true);
    }

    private void initComps() {
        menuPanel = new MenuPanel();
        viewPanel = new ViewPanel();
        calculationPanel = new CalculationPanel();
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);
        add(calculationPanel, BorderLayout.SOUTH);
    }

    private void activateComps() {

    }


}
