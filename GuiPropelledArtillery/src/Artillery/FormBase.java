package Artillery;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

public class FormBase {
    private JPanel panelDraw;
    private JPanel panelButton;
    private JButton buttonSetCombatvehicle;
    private JButton buttonSetSelfPropilledArtillery;
    private JPanel groupBox;
    private JTextField placeArtillery;
    private JButton buttonTakeCombatVehicle;
    private JPanel MainPanel;
    private JLabel JLabel;
    private JTextField textFieldBase;
    private JButton buttonCreateBase;
    private JButton buttonDeleteBase;
    private JList<String> listBoxBase;
    private JButton buttonTakeToArtilleryForm;
    private JPanel panelMenu;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu baseMenu;
    private JMenuItem loadFileBase;
    private JMenuItem saveFileBase;
    private JMenuItem loadLevelBase;
    private JMenuItem saveLevelBase;

    private BaseCollection baseCollection;
    private Draw draw;
    private DrawBase drawBase;
    private DefaultListModel<String> listModelVehicle;
    private LinkedList<ITransport> dopLinkedList;

    public FormBase() {
        JFrame frame = new JFrame("База");
        frame.setSize(1250, 600);
        frame.setState(JFrame.NORMAL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        baseCollection = new BaseCollection(1073, 558);
        drawBase = new DrawBase(baseCollection);
        frame.add(MainPanel);
        panelDraw.add(drawBase);
        listModelVehicle = new DefaultListModel<>();
        listBoxBase.setModel(listModelVehicle);
        dopLinkedList = new LinkedList<>();
        //Меню для работы с файлами
        menuBar = new JMenuBar();
        //файлы
        fileMenu = new JMenu("Файлы");
        saveFileBase = new JMenuItem("Сохранить");
        saveFileBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFileDialog = new JFileChooser();
                saveFileDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
                int returnVal = saveFileDialog.showSaveDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    if(baseCollection.SaveData(saveFileDialog.getSelectedFile().getPath())){
                        JOptionPane.showMessageDialog(frame, "Файл сохранен", "Файл", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(frame, "Файл не сохранен", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        loadFileBase = new JMenuItem("Загрузить");
        loadFileBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFileDialog = new JFileChooser();
                openFileDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
                int returnVal = openFileDialog.showOpenDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    if(baseCollection.LoadData(openFileDialog.getSelectedFile().getPath())){
                        JOptionPane.showMessageDialog(frame, "Файл загружен", "Файл", JOptionPane.INFORMATION_MESSAGE);
                        reloadLevel();
                        frame.repaint();
                    }else {
                        JOptionPane.showMessageDialog(frame, "Файл не загружен", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        fileMenu.add(saveFileBase);
        fileMenu.add(loadFileBase);
        //базы
        baseMenu = new JMenu("Базы");
        saveLevelBase = new JMenuItem("Сохранить");
        saveLevelBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFileDialog = new JFileChooser();
                saveFileDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
                if(listBoxBase.getSelectedValue() == null){
                    JOptionPane.showMessageDialog(frame, "Выберите уровень базы", "Error", JOptionPane.ERROR_MESSAGE);
                }
                int returnVal = saveFileDialog.showSaveDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    if(baseCollection.SaveBase(saveFileDialog.getSelectedFile().getPath(), listBoxBase.getSelectedValue())){
                        JOptionPane.showMessageDialog(frame, "Файл сохранен", "Файл", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(frame, "Файл не сохранен", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        loadLevelBase = new JMenuItem("Загрузить");
        loadLevelBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFileDialog = new JFileChooser();
                openFileDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
                int returnVal = openFileDialog.showOpenDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    if(baseCollection.LoadBase(openFileDialog.getSelectedFile().getPath())){
                        JOptionPane.showMessageDialog(frame, "Файл загружен", "Файл", JOptionPane.INFORMATION_MESSAGE);
                        reloadLevel();
                        frame.repaint();
                    }else {
                        JOptionPane.showMessageDialog(frame, "Файл не загружен", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        baseMenu.add(saveLevelBase);
        baseMenu.add(loadLevelBase);
        menuBar.add(fileMenu);
        menuBar.add(baseMenu);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
        buttonSetCombatvehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listBoxBase.getSelectedIndex() >= 0) {
                    FormCombatVehicleConfig formCombatVehicleConfig = new FormCombatVehicleConfig(frame);
                    ITransport artillery = formCombatVehicleConfig.getTransport();
                    if(artillery!=null){
                        if(baseCollection.get(listBoxBase.getSelectedValue()).operatorAdd(artillery) > -1){
                            frame.repaint();
                        }else{
                            JOptionPane.showMessageDialog(frame, "База переполнена");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "База не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonTakeCombatVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listBoxBase.getSelectedIndex() >= 0) {
                    if (!placeArtillery.getText().equals("")) {
                        int result = JOptionPane.showConfirmDialog(frame, "Забрать боевую технику?", "Забрать боевую технику", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            try {
                                ITransport transport = baseCollection.get(listBoxBase.getSelectedValue()).operatorDelete(Integer.parseInt(placeArtillery.getText()));
                                if (transport != null) {
                                    dopLinkedList.add(transport);
                                    frame.repaint();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Военной техники не существует","Ошибка" ,JOptionPane.YES_NO_OPTION);
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Военной техники не существует", "Ошибка" ,JOptionPane.YES_NO_OPTION);
                            }
                        }
                    }
                }
            }
        });

        buttonCreateBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFieldBase.getText().equals("")) {
                    baseCollection.AddBase(textFieldBase.getText());
                    reloadLevel();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Введите название базы", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonDeleteBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listBoxBase.getSelectedIndex() >= 0) {
                    int result = JOptionPane.showConfirmDialog(frame, "Удалить базу " + listBoxBase.getSelectedValue() + "?", "Удаление", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        baseCollection.DelBase(listBoxBase.getSelectedValue());
                        reloadLevel();
                        frame.repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Депо не существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        listBoxBase.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                drawBase.setSelectedItem(listBoxBase.getSelectedValue());
                frame.repaint();
            }
        });

        buttonTakeToArtilleryForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dopLinkedList.isEmpty()) {
                    int result = JOptionPane.showConfirmDialog(frame, "Перенести боевую технику на вторую форму?", "Забрать боевую технику", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        FormArtillery formArtillery = new FormArtillery();
                        formArtillery.setArtillery(dopLinkedList.get(0));
                        dopLinkedList.remove(0);
                        frame.repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "LinkedList пуст", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void reloadLevel() {
        int index = listBoxBase.getSelectedIndex();
        listModelVehicle.removeAllElements();
        int i = 0;
        for (String name : baseCollection.Keys()) {
            listModelVehicle.add(i, name);
            i++;
        }
        if (listModelVehicle.size() > 0 && (index < 0 || index >= listModelVehicle.size())) {
            listBoxBase.setSelectedIndex(0);
        } else if (index >= 0 && index < listModelVehicle.size()) {
            listBoxBase.setSelectedIndex(index);
        }
    }
}
