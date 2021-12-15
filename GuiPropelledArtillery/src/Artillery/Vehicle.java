package Artillery;

import java.awt.*;

public abstract class Vehicle implements ITransport{
    // Левая координата отрисовки артиллерийской установки
    protected int _startPosX;
    // Правая кооридната отрисовки артиллерийской установки
    protected int _startPosY;

    // Ширина окна отрисовки
    protected int _pictureWidth;

    // Высота окна отрисовки
    protected int _pictureHeight;

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

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public abstract void DrawTransport(Graphics g);

    public abstract void MoveTransport(Direction direction);

    public void SetMainColor(Color color){
        MainColor = color;
    }
}

