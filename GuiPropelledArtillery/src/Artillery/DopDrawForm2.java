package Artillery;

import java.awt.*;

public class DopDrawForm2 implements  IDopDrawGuns{
    private DopNumbersGuns dopNumbersGuns;

    public void setNewNumbersGun(int NewNumbersGun){
        dopNumbersGuns = DopNumbersGuns.SetNumberGuns(NewNumbersGun);
    }
    DopDrawForm2(int number){
        setNewNumbersGun(number);
    }
    private Color dopColor;

    public void DrawGun(Graphics g, Color Color, int _startPosX, int _startPosY) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color);
        g.fillOval(_startPosX, _startPosY, 11, 10);
        g.fillOval(_startPosX + 10, _startPosY, 11, 10);
        g.fillOval(_startPosX + 20, _startPosY, 11, 10);
    }
}
