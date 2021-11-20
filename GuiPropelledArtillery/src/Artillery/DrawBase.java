package Artillery;

import javax.swing.*;
import java.awt.*;

public class DrawBase extends JPanel {
    private final BaseCollection baseCollection;
    private String selectedItem = null;

    public DrawBase(BaseCollection baseCollection) {
        this.baseCollection = baseCollection;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selectedItem != null) {
            if (baseCollection != null) {
                baseCollection.get(selectedItem).Draw(g);
            }
        }
        super.repaint();
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
