package Artillery;

import java.awt.*;

public class DopDrawForm1 implements IDopDrawGuns{
    private DopNumbersGuns dopNumbersGuns;

    public void setNewNumbersGun(int NewNumbersGun){
        dopNumbersGuns = DopNumbersGuns.SetNumberGuns(NewNumbersGun);
    }
    DopDrawForm1(int number){
        setNewNumbersGun(number);
    }
    private Color dopColor;

    public void DrawGun(Graphics g, Color Color, int _startPosX, int _startPosY) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color);
        int[] arrayX = {_startPosX, _startPosX + 15, _startPosX + 30, _startPosX + 30, _startPosX + 15,_startPosX};
        int[] arrayY = {_startPosY + 5, _startPosY, _startPosY+5, _startPosY+10,_startPosY + 5, _startPosY + 10};
        Polygon poly = new Polygon(arrayX, arrayY, arrayX.length);
        g.fillPolygon(poly);
    }
}
