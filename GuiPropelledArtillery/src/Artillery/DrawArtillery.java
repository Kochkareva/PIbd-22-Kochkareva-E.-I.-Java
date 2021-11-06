package Artillery;

import javax.swing.*;
import java.awt.*;

public class DrawArtillery extends JComponent {
    private SelfPropelledArtillery self_propelled_artillery;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (self_propelled_artillery != null) {
            self_propelled_artillery.DrawArtillery(g);
        }
        super.repaint();
    }

    public void setSelf_propelled_artillery(SelfPropelledArtillery self_propelled_artillery){
        this.self_propelled_artillery = self_propelled_artillery;
    }
    public SelfPropelledArtillery getSelf_propelled_artillery(){
        return self_propelled_artillery;
    }
}
