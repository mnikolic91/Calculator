package CalculateView;


import CalculateLogic.CalcData;
import DataSaveLoad.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class MenuPanel extends JMenuBar {

    private JMenuItem saveItem;
    private JMenuItem loadItem;
    private JMenuItem exitItem;

    public MenuPanel() {
        JMenu fileMenu = new JMenu("File");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        exitItem = new JMenuItem("Exit");

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(exitItem);

        add(fileMenu);

        activateComps();
    }

    private void activateComps() {
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSaveDialog();
            }
        });

        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoadDialog();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void showSaveDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("ispisi"));
        fileChooser.setDialogTitle("Save File");

        FileNameExtensionFilter binFilter = new FileNameExtensionFilter("Binary Files (.bin)", "bin");
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files (.txt)", "txt");

        fileChooser.addChoosableFileFilter(binFilter);
        fileChooser.addChoosableFileFilter(txtFilter);

        fileChooser.setFileFilter(binFilter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            FileNameExtensionFilter selectedFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
            String selectedExtension = selectedFilter.getExtensions()[0];

            if (!filePath.toLowerCase().endsWith("." + selectedExtension)) {
                filePath += "." + selectedExtension;
            }


            SaveStrategy<String> saveStrategy;
            if (selectedExtension.equals("bin")) {
                saveStrategy = new SaveToBin();
            } else if (selectedExtension.equals("txt")) {
                saveStrategy = new SaveToText();
            } else {
                JOptionPane.showMessageDialog(null, "Format nije podržan.");
                return;
            }

            int counter = 1;
            while (new File(filePath).exists()) {
                filePath = selectedFile.getParent() + File.separator + selectedFile.getName() + "_" + counter + selectedExtension;
                counter++;
            }

            saveStrategy.saveData(ViewPanel.textArea.getText(), filePath);
            JOptionPane.showMessageDialog(null, "File saved to: " + filePath);
        }
    }




    private void showLoadDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("ispisi"));
        fileChooser.setDialogTitle("Load File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Binarni file (.bin), Text file (.txt)", "txt", "bin");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            String extension = filePath.substring(filePath.lastIndexOf('.') + 1);

            LoadStrategy<Object> loadStrategy;

            if (extension.equals("bin")) {
                loadStrategy = new LoadFromBin();
                Object calcData = loadStrategy.loadData(filePath);
                ViewPanel.textArea.setText(calcData.toString());
            } else if (extension.equals("txt")) {
                loadStrategy = new LoadFromText();
                List<CalcData> calcDataList = (List<CalcData>) loadStrategy.loadData(filePath);

                if (calcDataList != null && !calcDataList.isEmpty()) {
                    StringBuilder formattedData = new StringBuilder();
                    for (CalcData calcData : calcDataList) {
                        formattedData.append(calcData.toString());
                    }
                    ViewPanel.textArea.setText(formattedData.toString());
                } else {
                    ViewPanel.textArea.setText("No data loaded from the text file.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Format nije podržan.");
            }
        }
    }

}
