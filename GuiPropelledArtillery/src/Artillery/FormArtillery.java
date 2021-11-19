package Artillery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FormArtillery {
    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JPanel ButtomPanel;
    private JButton buttonRight;
    private JButton buttonDown;
    private JButton buttonUp;
    private JButton buttonLeft;

    private ITransport combatVehicle;
    private Draw draw;

    public FormArtillery() {
        JFrame frame = new JFrame("Self Propelled Artillery");
        frame.setSize(new Dimension(1000, 600));

        draw = new Draw();

        frame.remove(draw);
        MainPanel.add(draw);
        frame.repaint();
        frame.setVisible(true);

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

    public void setArtillery(ITransport combatVehicle){
        this.combatVehicle =combatVehicle;
        draw.setCombatVehicle(combatVehicle);
    }
}
