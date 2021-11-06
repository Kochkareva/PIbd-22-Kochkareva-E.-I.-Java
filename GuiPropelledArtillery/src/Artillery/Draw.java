package Artillery;

import javax.swing.*;
import java.awt.*;

public class Draw extends JComponent {
    private ITransport combatVehicle;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (combatVehicle != null) {
            combatVehicle.DrawTransport(g);
        }
        super.repaint();
    }

    public void setCombatVehicle(ITransport combatVehicle){
        this.combatVehicle = combatVehicle;
    }
    public ITransport getCombatVehicle(){
        return combatVehicle;
    }
}
