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

    private JButton buttonCreate;
    private JButton buttonLeft;
    private JButton buttonRight;
    private JButton buttonUp;
    private JButton buttonDown;
    private JPanel ButtomPanel;

    private SelfPropelledArtillery artillery;
    private DrawArtillery drawArtillery;

    public FormArtillery() {
        JFrame frame = new JFrame("Self Propelled Artillery");
        frame.setSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawArtillery = new DrawArtillery();
        // Отработка нажатия кнопки создать "Создать"
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                frame.remove(drawArtillery);
                artillery = new SelfPropelledArtillery();
                artillery.Init(rand.nextInt(200) + 100, rand.nextInt(1000) + 1000, Color.GREEN, Color.GRAY, true, true, rand.nextInt(5)+1);
                artillery.SetPosition(rand.nextInt(90) + 10, rand.nextInt(90) + 10, MainPanel.getWidth(), MainPanel.getHeight() - ButtonPanel.getHeight()-PanelButtonCreate.getHeight());
                drawArtillery.setSelf_propelled_artillery(artillery);
                MainPanel.add(drawArtillery);
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
                    artillery.MoveTransport(Direction.Down);
                }
                if (name == buttonLeft) {
                    artillery.MoveTransport(Direction.Left);
                }
                if (name == buttonRight) {
                    artillery.MoveTransport(Direction.Right);
                }
                if (name == buttonUp) {
                    artillery.MoveTransport(Direction.Up);
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


