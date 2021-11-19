package Artillery;

import javax.swing.*;
import java.awt.*;

public class DrawBase extends JPanel {
    private Base<ITransport, IDopDrawGuns> baseArtillery;

    public DrawBase(Base<ITransport, IDopDrawGuns> baseArtillery){
        this.baseArtillery = baseArtillery;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(baseArtillery !=null){
            baseArtillery.Draw(g);
        }
        super.repaint();
    }

    public Base<ITransport, IDopDrawGuns> getBaseArtillery(){
        return baseArtillery;
    }
}
