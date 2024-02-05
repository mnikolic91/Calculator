package CalculateView;


import CalculateLogic.CalcData;
import CalculateLogic.CalculationStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationPanel extends JPanel {

    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JList<String> calculationList;
    private JTextArea resultTextArea;
    private JButton calculateButton;
    private JLabel firstNumberLabel;
    private JLabel secondNumberLabel;

    private CalcData calcData;

    public CalculationPanel() {
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        firstNumberField = new JTextField();
        secondNumberField = new JTextField();
        calculationList = new JList<>(new String[]{"AdditionCalculation", "SubtractionCalculation", "MultiplicationCalculation", "DivisionCalculation", "PowerToCalculation", "UserCalc"});
        resultTextArea = new JTextArea();
        calculateButton = new JButton("Izračunaj");
        firstNumberLabel = new JLabel("Prvi broj:");
        secondNumberLabel = new JLabel("Drugi broj:");
    }

    private void layoutComps() {
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder("Calculation"));
        setPreferredSize(new Dimension(600, 160));

        firstNumberLabel.setBounds(20, 30, 100, 25);
        firstNumberField.setBounds(160, 30, 100, 25);
        secondNumberLabel.setBounds(20, 70, 120, 25);
        secondNumberField.setBounds(160, 70, 100, 25);

        JScrollPane listScrollPane = new JScrollPane(calculationList);
        listScrollPane.setBounds(300, 30, 200, 70);
        calculationList.setSelectedIndex(0);

        calculateButton.setBounds(550, 110, 100, 25);
        resultTextArea.setBounds(550, 70, 150, 30);
        resultTextArea.setEditable(false);

        add(firstNumberLabel);
        add(firstNumberField);
        add(secondNumberLabel);
        add(secondNumberField);
        add(listScrollPane);
        add(calculateButton);
        add(resultTextArea);
    }


    private void activateComps() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedCalculation = calculationList.getSelectedValue();
                    String firstNumberText = firstNumberField.getText().trim();
                    String secondNumberText = secondNumberField.getText().trim();

                    if (firstNumberText.isEmpty() || secondNumberText.isEmpty()) {
                        throw new IllegalArgumentException("Polja ne smiju biti prazna.");
                    }

                    double firstNumber = Double.parseDouble(firstNumberText);
                    double secondNumber = Double.parseDouble(secondNumberText);

                    if (selectedCalculation.equals("DivisionCalculation")) {
                        if (secondNumber == 0) {
                            throw new IllegalArgumentException("Drugi broj (b) ne smije biti 0.");
                        }
                    }

                    CalculationStrategy calculationStrategy = CalcData.createCalculationStrategy(selectedCalculation);
                    calcData = new CalcData(firstNumber, secondNumber, calculationStrategy);

                    double result = calcData.performCalculation();
                    resultTextArea.setText(String.valueOf(result));

                    String text = calcData.toString();
                    ViewPanel.textArea.append(text);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CalculationPanel.this, "Molim unesite ispravne brojeve.", "Greška", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(CalculationPanel.this, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }



}
