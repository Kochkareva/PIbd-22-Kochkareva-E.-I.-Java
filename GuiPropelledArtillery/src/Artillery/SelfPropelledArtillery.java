package Artillery;

import java.awt.*;

// Класс отрисовки самоходной артиллерийской установки
public class SelfPropelledArtillery extends CombatVehicle{
    /// Дополнительный цвет
    public Color DopColor;
    public Color gatDopColor() {
        return DopColor;
    }
    public void setDopColor( Color newDopColor){
        DopColor = newDopColor;
    }
    /// Признак наличия боекомплекта
    public boolean Ammunition;
    public boolean getAmmunition(){
        return Ammunition;
    }
    public void setAmmunition(boolean ammunition){
        Ammunition = ammunition;
    }

    /// Признак наличия боевого орудия
    public boolean Gun;
    public boolean getGun(){
        return Gun;
    }
    public void setGun(boolean gun){
        Gun = gun;
    }

    IDopDrawGuns dopDrawGuns;
    /**
     * Инициализация свойств
     * @param maxSpeed Максимальная скорость
     * @param weight Вес артиллерийской установки
     * @param mainColor Основной цвет
     * @param dopColor Дополнительный цвет
     * @param ammunition Признак наличия боекомплекта
     * @param gun Признак наличия боевого орудия
     */
    public SelfPropelledArtillery(int maxSpeed, float weight, Color mainColor, Color dopColor,
                                  boolean ammunition, boolean gun,int formNum, int dopGuns) {
        super (maxSpeed, weight, mainColor, 100, 60);

        DopColor = dopColor;
        Ammunition = ammunition;
        Gun = gun;
        switch (formNum){
            case 0: dopDrawGuns = new DopDrawGuns(dopGuns);
                break;
            case 1: dopDrawGuns = new DopDrawForm1(dopGuns);
                break;
            case 2:dopDrawGuns = new DopDrawForm2(dopGuns);
                break;
        }
    }

    /**
     *
     * @param g
     */
    @Override
    public void DrawTransport(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        if (Ammunition)
        {
            g2d.setColor(DopColor);
            g.fillRect(_startPosX + 85, _startPosY + 10, 15, 10);
        }
        if (Gun)
        {
            dopDrawGuns.DrawGun(g, DopColor, _startPosX, _startPosY);
        }
        super.DrawTransport(g);
    }
}

