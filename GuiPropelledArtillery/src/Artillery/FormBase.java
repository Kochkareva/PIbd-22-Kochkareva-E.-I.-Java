package Artillery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Base<ITransport, IDopDrawGuns> baseArtillery;
    private Draw draw;
    private DrawBase drawBase;

    public FormBase() {
        JFrame frame = new JFrame("База");
        frame.setSize(1250, 600);
        frame.setState(JFrame.NORMAL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        baseArtillery = new Base<ITransport, IDopDrawGuns>(1073, 558);
        drawBase = new DrawBase(baseArtillery);
        frame.add(MainPanel);
        panelDraw.add(drawBase);
        buttonSetCombatvehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser mainColor = new JColorChooser();
                JOptionPane.showMessageDialog(frame, mainColor, "Выберите главный цвет", JOptionPane.PLAIN_MESSAGE);
                if (mainColor.getColor() != null) {
                    ITransport transport = new CombatVehicle(100, 1000, mainColor.getColor());
                    if (baseArtillery.operatorAdd(transport) != -1) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "База переполнена");
                    }
                }
            }
        });

        buttonSetSelfPropilledArtillery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser mainColor = new JColorChooser();
                JOptionPane.showMessageDialog(frame, mainColor, "Выберите главный цвет", JOptionPane.PLAIN_MESSAGE);
                if (mainColor.getColor() != null) {
                    JColorChooser dopColor = new JColorChooser();
                    JOptionPane.showMessageDialog(frame, dopColor, "Выберите дополнительный цвет", JOptionPane.PLAIN_MESSAGE);
                    if (dopColor.getColor() != null) {
                        Random rand = new Random();
                        ITransport transport = new SelfPropelledArtillery(100, 1000, mainColor.getColor(), dopColor.getColor(), true, true, rand.nextInt(3), rand.nextInt(3) + 1);
                        if (baseArtillery.operatorAdd(transport) != -1) {
                            frame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(frame, "База переполнена");
                        }
                    }
                }
            }
        });
        buttonTakeCombatVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!placeArtillery.getText().equals("")) {
                    try {
                        ITransport transport = baseArtillery.operatorDelete(Integer.parseInt(placeArtillery.getText()));
                        if (transport != null) {
                            FormArtillery formArtillery = new FormArtillery();
                            formArtillery.setArtillery(transport);
                            frame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Военной техники не существует");

                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Военной техники не существует");

                    }
                }
            }
        });
    }
}
