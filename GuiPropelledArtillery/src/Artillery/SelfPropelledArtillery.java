package Artillery;

import java.awt.*;

// Класс отрисовки самоходной артиллерийской установки
public class SelfPropelledArtillery {
    // Левая координата отрисовки артиллерийской установки
    private int _startPosX;

    // Правая кооридната отрисовки артиллерийской установки
    private int _startPosY;

    // Ширина окна отрисовки
    private int _pictureWidth;

    // Высота окна отрисовки
    private int _pictureHeight;

    // Ширина отрисовки артиллерийской установки
    private final int artilleryWidth = 100;

    // Высота отрисовки артиллерийской установки
    private final int artilleryHeight = 60;

    // Максимальная скорость
    public int MaxSpeed;
    public int getMaxSpeed() {
        return MaxSpeed;
    }
    public void setMaxSpeed(int newMaxSpeed) {
        MaxSpeed = newMaxSpeed;
    }

    // Вес артиллерийской установки
    public float Weight;
    public float getWeight() {
        return Weight;
    }
    public void setWeight(float newWeight) {
        Weight = newWeight;
    }

    // Основной цвет
    public Color MainColor;
    public Color getMainColor() {
        return MainColor;
    }
    public void setMainColor(Color newMainColor) {
        MainColor = newMainColor;
    }

    // Дополнительный цвет
    public Color DopColor;
    public Color gatDopColor() {
        return DopColor;
    }
    public void setDopColor( Color newDopColor){
        DopColor = newDopColor;
    }

    // Признак наличия боекомплекта
    public boolean Ammunition;
    public boolean getAmmunition(){
        return Ammunition;
    }
    public void setAmmunition(boolean ammunition){
        Ammunition = ammunition;
    }

    // Признак наличия боевого орудия
    public boolean Gun;
    public boolean getGun(){
        return Gun;
    }
    public void setGun(boolean gun){
        Gun = gun;
    }

    /**
     * Доп класс отрисовки оружия
     */
    DopDrawGuns dopDrawGuns;

    /**
     * Инициализация свойств
     * @param maxSpeed Максимальная скорость
     * @param weight Вес артиллерийской установки
     * @param mainColor Основной цвет
     * @param dopColor Дополнительный цвет
     * @param ammunition Признак наличия боекомплекта
     * @param gun Признак наличия боевого орудия
     */
    public void Init(int maxSpeed, float weight, Color mainColor, Color dopColor,
                     boolean ammunition, boolean gun, int dopGuns) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        Ammunition = ammunition;
        Gun = gun;
        dopDrawGuns = new DopDrawGuns(dopGuns);
    }

    /**
     * Установка позиции
     * @param x Координата X
     * @param y Координата Y
     * @param width Ширина картинки
     * @param height Высота картинки
     */
    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    /**
     * Изменение направления пермещения
     * @param direction Направление
     */
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - artilleryWidth) {
                    _startPosX += step;
                }
                break;
            //влево
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (_startPosY - step > 0) {
                    _startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - artilleryHeight) {
                    _startPosY += step;
                }
                break;
        }
    }

    /**
     * Отрисовка артиллерийской установки
     * @param g
     */
    public void DrawArtillery(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(MainColor);
        //тело самоходной артиллерийской установки
        g2d.fillRect(_startPosX, _startPosY + 20, artilleryWidth, 20);
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
        if (Ammunition) {
            g2d.setColor(DopColor);
            g.fillRect(_startPosX + 85, _startPosY + 10, 15, 10);
        }
        if (Gun) {
            dopDrawGuns.DrawGun(g, DopColor, _startPosX, _startPosY);
        }
    }
}

