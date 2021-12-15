package Artillery;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class FormCombatVehicleConfig extends JDialog{
    private JPanel MainPanel;
    private JLabel labelCombatVehicle;
    private JPanel groupBox;
    private JLabel labelSelfPropelledArtillery;
    private JPanel groupBoxParameters;
    private JLabel labelSpeed;
    private JSpinner spinnerSpeed;
    private JLabel labelWeight;
    private JSpinner spinnerWeight;
    private JCheckBox checkBoxGun;
    private JCheckBox checkBoxAmmunition;
    private JPanel panelCombatVehicleDraw;
    private JPanel groupBoxColor;
    private JLabel labelBaseColor;
    private JLabel labelDopColor;
    private JPanel Red;
    private JPanel Yellow;
    private JPanel Black;
    private JPanel White;
    private JPanel Gray;
    private JPanel Orange;
    private JPanel Green;
    private JPanel Blue;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JLabel labelParametr;
    private JPanel PanelFormGun;
    private JPanel GunForm1;
    private JPanel GunForm2;
    private JLabel NumberGuns;
    private JSpinner spinnerNumberGuns;
    private JPanel GunForm3;

    private Draw draw;

    private ITransport artillery;

    private Color combatVehicleColor;

    private PanelFormGun1 formGun1;
    private PanelFormGun2 formGun2;
    private PanelFormGun3 formGun3;

    IGuns iGuns;

    public FormCombatVehicleConfig(Frame frame){
        super(frame, true);
        add(MainPanel);
        setSize(810, 390);
        Border border = BorderFactory.createLineBorder(Color.black);

        labelCombatVehicle.setBorder(border);
        labelSelfPropelledArtillery.setBorder(border);

        spinnerWeight.setModel(new SpinnerNumberModel(100, 100, 1000,100));
        spinnerSpeed.setModel(new SpinnerNumberModel(100, 100, 1000,100));

        draw = new Draw();
        draw.setSize(110, 70);
        panelCombatVehicleDraw.add(draw);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setCombatVehicle(null);
                dispose();
            }
        });

        //перетаскивание типа техники
        MouseAdapter combatVehicleType = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel type = (JLabel) e.getSource();
                switch (type.getText()) {
                    case "Броневая машина" -> artillery = new CombatVehicle((int) spinnerSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE);
                    case "Самоходная артиллерийская установка" -> artillery = new SelfPropelledArtillery((int) spinnerWeight.getValue(), (int) spinnerSpeed.getValue(), Color.WHITE, Color.BLACK, checkBoxAmmunition.isSelected(), checkBoxGun.isSelected(), 0, 1);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if (e.getX() + ((JComponent) e.getSource()).getX()  >= panelCombatVehicleDraw.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX()  <= panelCombatVehicleDraw.getX() + panelCombatVehicleDraw.getWidth() &&
                        e.getY() + ((JComponent) e.getSource()).getY()  >= panelCombatVehicleDraw.getY() &&
                        e.getY() + ((JComponent) e.getSource()).getY()<= panelCombatVehicleDraw.getY() + panelCombatVehicleDraw.getHeight()
                ){
                    artillery.SetPosition(20, 20, draw.getWidth(), draw.getHeight());
                    draw.setCombatVehicle(artillery);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                artillery = null;
            }
        };
        labelCombatVehicle.addMouseListener(combatVehicleType);
        labelSelfPropelledArtillery.addMouseListener(combatVehicleType);

        //перетаскивание цвета
        MouseAdapter mouseColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelColor = (JPanel) e.getSource();
                combatVehicleColor = panelColor.getBackground();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (draw.getCombatVehicle() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelBaseColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelBaseColor.getX() + labelBaseColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelBaseColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelBaseColor.getY() + labelBaseColor.getHeight()) {
                        draw.getCombatVehicle().SetMainColor(combatVehicleColor);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelDopColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelDopColor.getX() + labelDopColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelDopColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelDopColor.getY() + labelDopColor.getHeight() &&
                            draw.getCombatVehicle() instanceof SelfPropelledArtillery) {
                        SelfPropelledArtillery selfPropelledArtillery = (SelfPropelledArtillery) draw.getCombatVehicle();
                        selfPropelledArtillery.SetDopColor(combatVehicleColor);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                artillery = null;
            }
        };
        Red.addMouseListener(mouseColor);
        Yellow.addMouseListener(mouseColor);
        Black.addMouseListener(mouseColor);
        White.addMouseListener(mouseColor);
        Gray.addMouseListener(mouseColor);
        Orange.addMouseListener(mouseColor);
        Green.addMouseListener(mouseColor);
        Blue.addMouseListener(mouseColor);

        //доп. параметры
        formGun1 = new PanelFormGun1();
        GunForm1.add(formGun1);

        formGun2 = new PanelFormGun2();
        GunForm2.add(formGun2);

        formGun3 = new PanelFormGun3();
        GunForm3.add(formGun3);

        spinnerNumberGuns.setModel(new SpinnerNumberModel(1, 1, 3,1));

        MouseAdapter listenerDopForm = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelForm = (JPanel) e.getSource();
                switch (panelForm.getComponent(0).getClass().toString()){
                    case "class Artillery.PanelFormGun1" -> iGuns = new TypeGuns1((int) spinnerNumberGuns.getValue());
                    case "class Artillery.PanelFormGun2" -> iGuns = new TypeGuns2((int) spinnerNumberGuns.getValue());
                    case "class Artillery.PanelFormGun3" -> iGuns = new TypeGuns3((int) spinnerNumberGuns.getValue());
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (draw.getCombatVehicle() != null) {
                    if ( e.getX() +((JComponent) e.getSource()).getX() + PanelFormGun.getX()>= panelCombatVehicleDraw.getX() &&
                            e.getX()+    ((JComponent) e.getSource()).getX() +PanelFormGun.getX() <= panelCombatVehicleDraw.getX() + panelCombatVehicleDraw.getWidth() &&
                            e.getY() +  ((JComponent) e.getSource()).getY()+ PanelFormGun.getY()>= panelCombatVehicleDraw.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY()+ PanelFormGun.getY() <= panelCombatVehicleDraw.getY() + panelCombatVehicleDraw.getHeight() &&
                    draw.getCombatVehicle() instanceof SelfPropelledArtillery) {
                        SelfPropelledArtillery selfPropelledArtillery = (SelfPropelledArtillery) draw.getCombatVehicle();
                        selfPropelledArtillery.setDopGuns(iGuns);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                iGuns = null;
            }
        };

        GunForm1.addMouseListener(listenerDopForm);
        GunForm2.addMouseListener(listenerDopForm);
        GunForm3.addMouseListener(listenerDopForm);

        setVisible(true);
    }

    public ITransport getTransport() {
        return draw.getCombatVehicle();
    }
}

class PanelFormGun1 extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g.fillRect(20, 40, 50, 5);
        super.repaint();
    }
}

class PanelFormGun2 extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        int[] arrayX = {20, 45, 70, 70, 45,20};
        int[] arrayY = {45, 40, 45, 50,45, 50};
        Polygon poly = new Polygon(arrayX, arrayY, arrayX.length);
        g.fillPolygon(poly);
        super.repaint();
    }
}

class PanelFormGun3 extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g.fillOval(20, 40, 21, 20);
        g.fillOval(40, 40, 21, 20);
        g.fillOval(60, 40, 21, 20);
        super.repaint();
    }
}