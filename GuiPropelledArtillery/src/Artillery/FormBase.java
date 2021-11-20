package Artillery;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        frame.setVisible(true);

        baseCollection = new BaseCollection(1073, 558);
        drawBase = new DrawBase(baseCollection);
        frame.add(MainPanel);
        panelDraw.add(drawBase);
        listModelVehicle = new DefaultListModel<>();
        listBoxBase.setModel(listModelVehicle);
        dopLinkedList = new LinkedList<>();
        buttonSetCombatvehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listBoxBase.getSelectedIndex() >= 0) {
                    JColorChooser mainColor = new JColorChooser();
                    JOptionPane.showMessageDialog(frame, mainColor, "Выберите главныц цвет", JOptionPane.PLAIN_MESSAGE);
                    if (mainColor.getColor() != null) {
                        ITransport transport = new CombatVehicle(100, 1000, mainColor.getColor());
                        if (baseCollection.get(listBoxBase.getSelectedValue()).operatorAdd(transport) > -1) {
                            frame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(frame, "База переполнена");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "База не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonSetSelfPropilledArtillery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listBoxBase.getSelectedIndex() >= 0) {
                    JColorChooser mainColor = new JColorChooser();
                    JOptionPane.showMessageDialog(frame, mainColor, "Выберите главный цвет", JOptionPane.PLAIN_MESSAGE);
                    if (mainColor.getColor() != null) {
                        JColorChooser dopColor = new JColorChooser();
                        JOptionPane.showMessageDialog(frame, dopColor, "Выберите дополнительный цвет", JOptionPane.PLAIN_MESSAGE);
                        if (dopColor.getColor() != null) {
                            Random rand = new Random();
                            ITransport transport = new SelfPropelledArtillery(100, 1000, mainColor.getColor(), dopColor.getColor(), true, true, rand.nextInt(3), rand.nextInt(3) + 1);
                            if (baseCollection.get(listBoxBase.getSelectedValue()).operatorAdd(transport) > -1) {
                                frame.repaint();
                            } else {
                                JOptionPane.showMessageDialog(frame, "База переполнена");
                            }
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
                                    JOptionPane.showMessageDialog(frame, "Военной техники не существует");

                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Военной техники не существует");
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
