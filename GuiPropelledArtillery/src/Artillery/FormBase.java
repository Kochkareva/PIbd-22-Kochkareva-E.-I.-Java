package Artillery;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.security.KeyException;
import java.util.LinkedList;
import java.util.Random;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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

    private Logger logger;

    public FormBase() {
        JFrame frame = new JFrame("База");
        frame.setSize(1250, 600);
        frame.setState(JFrame.NORMAL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        logger = LogManager.getLogger(FormBase.class);
        PropertyConfigurator.configure("src/log4j2.properties");

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
        logger.fatal("Файл с базами не сохранен");
        saveFileBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFileDialog = new JFileChooser();
                saveFileDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
                int returnVal = saveFileDialog.showSaveDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        baseCollection.SaveData(saveFileDialog.getSelectedFile().getPath());
                        logger.info("Базы сохранены в файл" + saveFileDialog.getSelectedFile().getPath());
                        JOptionPane.showMessageDialog(frame, "Файл сохранен", "Сохранено", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        logger.fatal("Файл с базами не сохранен" + ex.getMessage());
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Непредвиденная ошибка при сохранении баз в файл", JOptionPane.ERROR_MESSAGE);
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
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        baseCollection.LoadData(openFileDialog.getSelectedFile().getPath());
                        logger.info("Базы загружены из файла" + openFileDialog.getSelectedFile().getPath());
                        reloadLevel();
                        frame.repaint();
                    } catch (FileNotFoundException ex) {
                        logger.error("Файл не найден");
                        JOptionPane.showMessageDialog(frame, "Файл не найден", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        logger.error("Выбран неверный формат файла");
                        JOptionPane.showMessageDialog(frame, "Выбран неверный формат файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (BaseOverflowException ex) {
                        logger.warn("База переполнена");
                        JOptionPane.showMessageDialog(frame, "База переполнена при загрузке", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        logger.fatal("Непредвиденная ошибка при загрузке баз с файла");
                        JOptionPane.showMessageDialog(frame, "Непредвиденная ошибка при загрузке баз с файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
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
                if (listBoxBase.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(frame, "Выберите уровень базы", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int returnVal = saveFileDialog.showSaveDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        baseCollection.SaveBase(saveFileDialog.getSelectedFile().getPath(), listBoxBase.getSelectedValue());
                        logger.info("База" + listBoxBase.getSelectedValue()+ "сохранена в файл" + saveFileDialog.getSelectedFile().getPath());
                        JOptionPane.showMessageDialog(frame, "Файл сохранен c базой", "Файл", JOptionPane.INFORMATION_MESSAGE);
                    }catch (KeyException ex){
                        logger.error("Сохранение несуществующей базы");
                        JOptionPane.showMessageDialog(frame, "Сохранение несуществующей базы", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        logger.fatal("Непредвиденная ошибка при загрузке баз в файла");
                        JOptionPane.showMessageDialog(frame, "Непредвиденная ошибка при загрузке баз в файла", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
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
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        baseCollection.LoadBase(openFileDialog.getSelectedFile().getPath());
                        logger.info("База " + listBoxBase.getSelectedValue() + "загружена из файла " + openFileDialog.getSelectedFile().getPath());
                        JOptionPane.showMessageDialog(frame, "Файл загружен", "Файл", JOptionPane.INFORMATION_MESSAGE);
                        reloadLevel();
                        frame.repaint();
                    } catch (FileNotFoundException ex) {
                        logger.error("Файл не найден");
                        JOptionPane.showMessageDialog(frame, "Файл не найден", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        logger.error("Выбран неверный формат файла");
                        JOptionPane.showMessageDialog(frame, "Выбран неверный формат файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (BaseOverflowException ex) {
                        logger.warn("База переполнена");
                        JOptionPane.showMessageDialog(frame, "База переполнена при загрузке", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        logger.fatal("Непредвиденная ошибка при загрузке баз с файла");
                        JOptionPane.showMessageDialog(frame, "Непредвиденная ошибка при загрузке баз с файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
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
                if (listBoxBase.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(frame, "База не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    FormCombatVehicleConfig formCombatVehicleConfig = new FormCombatVehicleConfig(frame);
                    ITransport artillery = formCombatVehicleConfig.getTransport();
                    if (artillery != null) {
                        if (!baseCollection.get(listBoxBase.getSelectedValue()).operatorAdd(artillery)) {
                            logger.info("Добавлена военная техника" + artillery);
                            frame.repaint();
                        }
                    }
                } catch (BaseOverflowException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "База переполнена", JOptionPane.ERROR_MESSAGE);
                    logger.warn("База переполнена");
                } catch (Exception ex) {
                    logger.fatal("Непредвиденная ошибка при добавлении военной техники на базу" + ex.getMessage());
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Непредвиденная ошибка", JOptionPane.ERROR_MESSAGE);
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
                                    logger.info("Забрали боевую технику "+transport + " в лист");
                                    frame.repaint();
                                }
                            }catch (BaseNotFoundException ex){
                                JOptionPane.showMessageDialog(frame, "База не найдена", "Ошибка", JOptionPane.YES_NO_OPTION);
                                logger.warn("База не найдена");
                            }
                            catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Непредвиденная ошибка", "Ошибка", JOptionPane.YES_NO_OPTION);
                                logger.fatal("Непредвиденная ошибка при изъятии военной техники с базы" + ex.getMessage());
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
                    logger.info("Добавили базу " + textFieldBase.getText());
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
                        logger.info("Удалили базу " + listBoxBase.getSelectedValue());
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
                if(listBoxBase.getSelectedValue() != null){
                    logger.info("Перешли на базу " + listBoxBase.getSelectedValue());
                }
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
                        logger.info("Боевая техника "+ dopLinkedList.get(0) + "перенесена на первую форму");
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
