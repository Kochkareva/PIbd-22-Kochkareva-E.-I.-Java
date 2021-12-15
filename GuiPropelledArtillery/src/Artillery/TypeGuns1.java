package Artillery;
import java.awt.*;

public class TypeGuns1 implements  IGuns{

    private NumbersGuns dopNumbersGuns;

    public void setNewNumbersGun(int NewNumbersGun){
        dopNumbersGuns = NumbersGuns.SetNumberGuns(NewNumbersGun);
    }

    TypeGuns1(int number){
        setNewNumbersGun(number);
    }
    private Color dopColor;
    public void DrawGun(Graphics g, Color Color, int _startPosX, int _startPosY){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color);
        switch (dopNumbersGuns){
            case one:
                g.fillRect(_startPosX, _startPosY + 5, 30, 5);
                break;
            case three:
                g.fillRect(_startPosX + 70, _startPosY, 30, 5);
            case two:
                g.fillRect(_startPosX, _startPosY, 30, 5);
                g.fillRect(_startPosX,_startPosY + 10, 30,5);
                break;
        }
    }
}
