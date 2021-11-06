package Artillery;

import java.awt.*;

public class CombatVehicle extends Vehicle{
    // Ширина отрисовки боевой машины
    protected int combatVehicleWidth = 90;

    // Высота отрисовки боевой машины
    protected int combatVehicleHeight = 50;

    /**
     * Конструктор
     * @param maxSpeed Максимальная скорость
     * @param weight Вес боевой машины
     * @param mainColor Основной цвет корпуса
     */
    public CombatVehicle(int maxSpeed, float weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    /**
     * Конструктор с изменением размеров боевой машины
     * @param maxSpeed Максимальная скорость
     * @param weight Вес боевой машины
     * @param mainColor Основной цвет кузова
     * @param combatVehicleWidth Ширина отрисовки боевой машины
     * @param combatVehicleHeight Высота отрисовки боевой машины
     */
    protected CombatVehicle(int maxSpeed, float weight, Color mainColor, int combatVehicleWidth, int combatVehicleHeight)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.combatVehicleWidth = combatVehicleWidth;
        this.combatVehicleHeight = combatVehicleHeight;
    }
    @Override
    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - combatVehicleWidth)
                {
                    _startPosX += step;
                }
                break;
            //влево
            case Left:
                if (_startPosX - step > 0)
                {
                    _startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - combatVehicleHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }
    @Override
    public void DrawTransport(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(MainColor);
        //тело боевой машины
        g2d.fillRect(_startPosX, _startPosY + 20, combatVehicleWidth, 20);
        g.fillRect( _startPosX + 30, _startPosY, 40, 20);
        //гусеница
        g2d.setColor(Color.decode("#008000"));         //цвет гусеницы танка
        g.fillOval(_startPosX, _startPosY + 39, 20, 20);
        g.fillOval(_startPosX + 80, _startPosY + 39, 20, 20);
        g.fillRect(_startPosX + 10, _startPosY + 39, 80, 20);
        g2d.setColor(Color.BLACK); //цвет колес в гусенице
        g.fillOval(_startPosX + 5, _startPosY + 40, 16, 16);
        g.fillOval(_startPosX + 80, _startPosY + 40, 16, 16);
        g.fillOval(_startPosX + 23, _startPosY + 45, 12, 12);
        g.fillOval(_startPosX + 38, _startPosY + 45, 12, 12);
        g.fillOval(_startPosX + 53, _startPosY + 45, 12, 12);
        g.fillOval(_startPosX + 68, _startPosY + 45, 12, 12);
    }
}
