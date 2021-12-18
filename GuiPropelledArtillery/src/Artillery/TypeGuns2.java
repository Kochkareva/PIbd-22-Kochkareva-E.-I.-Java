package Artillery;

import java.awt.*;

public class TypeGuns2 implements IGuns{
    private NumbersGuns dopNumbersGuns;

    public void setNewNumbersGun(int NewNumbersGun){
        dopNumbersGuns = NumbersGuns.SetNumberGuns(NewNumbersGun);
    }
    TypeGuns2(int number){
        setNewNumbersGun(number);
    }
    private Color dopColor;

    public void DrawGun(Graphics g, Color Color, int _startPosX, int _startPosY) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color);
        switch (dopNumbersGuns) {
            case one:
            int[] arrayX = {_startPosX, _startPosX + 15, _startPosX + 30, _startPosX + 30, _startPosX + 15, _startPosX};
            int[] arrayY = {_startPosY + 5, _startPosY, _startPosY + 5, _startPosY + 10, _startPosY + 5, _startPosY + 10};
            Polygon poly = new Polygon(arrayX, arrayY, arrayX.length);
            g.fillPolygon(poly);
            break;
            case three:
                int[] arrayX4 = {_startPosX + 70, _startPosX + 85, _startPosX + 100, _startPosX + 100, _startPosX + 85, _startPosX+70};
                int[] arrayY4 = {_startPosY + 5, _startPosY, _startPosY + 5, _startPosY + 10, _startPosY + 5, _startPosY + 10};
                Polygon poly4 = new Polygon(arrayX4, arrayY4, arrayX4.length);
                g.fillPolygon(poly4);
            case two:
                int[] arrayX2 = {_startPosX, _startPosX + 15, _startPosX + 30, _startPosX + 30, _startPosX + 15, _startPosX};
                int[] arrayY2 = {_startPosY + 5, _startPosY, _startPosY + 5, _startPosY + 10, _startPosY + 5, _startPosY + 10};
                Polygon poly2 = new Polygon(arrayX2, arrayY2, arrayX2.length);
                g.fillPolygon(poly2);

                int[] arrayX3 = {_startPosX, _startPosX + 15, _startPosX + 30, _startPosX + 30, _startPosX + 15, _startPosX};
                int[] arrayY3 = {_startPosY + 15, _startPosY +10, _startPosY + 15, _startPosY + 20, _startPosY + 15, _startPosY + 20};
                Polygon poly3 = new Polygon(arrayX3, arrayY3, arrayX3.length);
                g.fillPolygon(poly3);
        }
    }
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "." + (dopNumbersGuns.ordinal()+1);
    }
}

