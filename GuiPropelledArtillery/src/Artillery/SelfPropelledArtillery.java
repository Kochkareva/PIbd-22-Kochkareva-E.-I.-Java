package Artillery;

import java.awt.*;

// Класс отрисовки самоходной артиллерийской установки
public class SelfPropelledArtillery extends CombatVehicle{
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

    IGuns dopDrawGuns;
    public void setDopGuns(IGuns dopDrawGuns){
        this.dopDrawGuns=dopDrawGuns;
    }
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
            case 0: dopDrawGuns = new TypeGuns1(dopGuns);
                break;
            case 1: dopDrawGuns = new TypeGuns2(dopGuns);
                break;
            case 2:dopDrawGuns = new TypeGuns3(dopGuns);
                break;
        }
    }

    /**
     * Конструктор для загрузки с файла
     * @param info
     */
    public SelfPropelledArtillery(String info)
    {
        super(info);
        String[] strs = info.split(separator);
        if (strs.length == 7)
        {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Float.parseFloat(strs[1]);
            MainColor = Color.decode(strs[2]);
            DopColor = Color.decode(strs[3]);
            Ammunition = Boolean.parseBoolean(strs[4]);
            Gun = Boolean.parseBoolean(strs[5]);
            if(strs[6].contains("null")){
                dopDrawGuns = null;
            }else{
                String[] strsTypeGun = strs[6].split("\\.");
                int numberGun = Integer.parseInt(strsTypeGun[1]);
                switch (strsTypeGun[0]){
                    case "TypeGuns1": dopDrawGuns = new TypeGuns1(numberGun);
                        break;
                    case "TypeGuns2": dopDrawGuns = new TypeGuns2(numberGun);
                        break;
                    case "TypeGuns3":dopDrawGuns = new TypeGuns3(numberGun);
                        break;
                }
            }
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
        g2d.setColor(DopColor);
        if (Ammunition)
        {
            g.fillRect(_startPosX + 85, _startPosY + 10, 15, 10);
        }
        if (Gun)
        {
            dopDrawGuns.DrawGun(g, DopColor, _startPosX, _startPosY);
        }
        super.DrawTransport(g);
    }

    public void SetDopColor(Color color){
        DopColor = color;
    }

    @Override
    public String toString(){
        return  super.toString() + separator + DopColor.getRGB() + separator + Ammunition + separator + Gun + separator +  dopDrawGuns ;
    }
}

