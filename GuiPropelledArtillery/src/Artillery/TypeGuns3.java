package Artillery;

import java.awt.*;

public class TypeGuns3 implements  IGuns {
    private NumbersGuns dopNumbersGuns;

    public void setNewNumbersGun(int NewNumbersGun) {
        dopNumbersGuns = NumbersGuns.SetNumberGuns(NewNumbersGun);
    }

    TypeGuns3(int number) {
        setNewNumbersGun(number);
    }

    private Color dopColor;

    public void DrawGun(Graphics g, Color Color, int _startPosX, int _startPosY) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color);
        switch (dopNumbersGuns) {
            case one:
                g.fillOval(_startPosX, _startPosY, 11, 10);
                g.fillOval(_startPosX + 10, _startPosY, 11, 10);
                g.fillOval(_startPosX + 20, _startPosY, 11, 10);
                break;
            case three:
                g.fillOval(_startPosX + 70, _startPosY, 11, 10);
                g.fillOval(_startPosX + 80, _startPosY, 11, 10);
                g.fillOval(_startPosX + 90, _startPosY, 11, 10);
            case two:
                g.fillOval(_startPosX, _startPosY - 2, 11, 10);
                g.fillOval(_startPosX + 10, _startPosY - 2, 11, 10);
                g.fillOval(_startPosX + 20, _startPosY - 2, 11, 10);

                g.fillOval(_startPosX, _startPosY + 9, 11, 10);
                g.fillOval(_startPosX + 10, _startPosY + 9, 11, 10);
                g.fillOval(_startPosX + 20, _startPosY + 9, 11, 10);
                break;
        }
    }
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "." + (dopNumbersGuns.ordinal()+1);
    }
}
