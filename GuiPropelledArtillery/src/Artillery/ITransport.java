package Artillery;

import java.awt.*;

interface ITransport {
    /**
     * Установка позиции
     * @param x Координата X
     * @param y Координата Y
     * @param width Ширина картинки
     * @param height Высота картинки
     */
    void SetPosition(int x, int y, int width, int height);

    /**
     * Изменение направления перемещения
     * @param direction Направление
     */
    void MoveTransport(Direction direction);

    /**
     * Отрисовка артиллерийской установки
     * @param g
     */
    void DrawTransport(Graphics g);
}
