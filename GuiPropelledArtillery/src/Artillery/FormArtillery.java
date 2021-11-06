package Artillery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FormArtillery {
    private JPanel MainPanel;
    private JPanel PanelButtonCreate;
    private JPanel ButtonPanel;
    private JPanel ButtomPanel;
    private JButton buttonCreateCombatVehicle;
    private JButton buttonRight;
    private JButton buttonDown;
    private JButton buttonUp;
    private JButton buttonLeft;
    private JButton buttonCreateSelfPropelledArtillery;

    private ITransport combatVehicle;
    private Draw draw;

    public FormArtillery() {
        JFrame frame = new JFrame("Self Propelled Artillery");
        frame.setSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        draw = new Draw();
        //Отработка нажатия кнопки создать "Создать боевую машину"
        buttonCreateCombatVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                frame.remove(draw);
                combatVehicle = new CombatVehicle(rnd.nextInt(100), rnd.nextInt(1000), Color.GREEN);
                combatVehicle.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, MainPanel.getWidth(), MainPanel.getHeight() - ButtonPanel.getHeight()-PanelButtonCreate.getHeight());
                draw.setCombatVehicle(combatVehicle);
                MainPanel.add(draw);
                frame.repaint();
                frame.setVisible(true);
            }
        });
        //Обработка нажатия кнопки "Создать самоходную артилерийскую установку"
        buttonCreateSelfPropelledArtillery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                frame.remove(draw);
                combatVehicle = new SelfPropelledArtillery(rnd.nextInt(100), rnd.nextInt(1000), Color.GREEN,
                        Color.GRAY, true, true,rnd.nextInt(3) ,rnd.nextInt(7)+1);
                combatVehicle.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, MainPanel.getWidth(), MainPanel.getHeight() - ButtonPanel.getHeight()-PanelButtonCreate.getHeight());
                draw.setCombatVehicle(combatVehicle);
                MainPanel.add(draw);
                frame.repaint();
                frame.setVisible(true);
            }
        });

        // Обработка нажатия кнопок управления
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object name = e.getSource();
                if (name == buttonDown) {
                    combatVehicle.MoveTransport(Direction.Down);
                }
                if (name == buttonLeft) {
                    combatVehicle.MoveTransport(Direction.Left);
                }
                if (name == buttonRight) {
                    combatVehicle.MoveTransport(Direction.Right);
                }
                if (name == buttonUp) {
                    combatVehicle.MoveTransport(Direction.Up);
                }
            }
        };
        buttonLeft.addActionListener(actionListener);
        buttonDown.addActionListener(actionListener);
        buttonRight.addActionListener(actionListener);
        buttonUp.addActionListener(actionListener);

        frame.add(MainPanel);
        frame.setLocationRelativeTo(null);
        frame.setState(JFrame.NORMAL);
        frame.setVisible(true);
    }
}
