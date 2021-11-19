package Artillery;

import java.awt.*;
/**
 * Параметризованный класс для хранения набора объектов от интерфейса ITransport
 */
public class Base<T extends ITransport, N extends IDopDrawGuns> {
    // Массив объектов, которые храним
    private final Object[] _places;

    // Ширина окна отрисовки
    private final int pictureWidth;

    // Высота окна отрисовки
    private final int pictureHeight;

    // Размер места на базе (ширина)
    private final int _placeSizeWidth = 160;

    /// Размер места на базе (высота)
    private final int _placeSizeHeight = 80;

    /**
     * Конструктор
     *
     * @param picWidth  Рамзер базы - ширина
     * @param picHeight Рамзер базы - высота
     */
    public Base(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;

        _places = new Object[width*height];
       // Object[] _places =(Object[]) places;
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    /**
     * Перегрузка оператора сложения
     * Логика действия: на базу добавляется военная техника
     */
    public int operatorAdd(T combatVehicle) {
        for (int i = 0; i < _places.length; i++) {
            if (_places[i] == null) {
                combatVehicle.SetPosition(i % (pictureWidth / _placeSizeWidth) * _placeSizeWidth + 5,
                        i / (pictureHeight / _placeSizeHeight) * _placeSizeHeight + 10, pictureWidth, pictureHeight);

                _places[i] = combatVehicle;
                return i;
            }
        }
        return -1;
    }

    /**
     * Перегрузка оператора вычитания
     * Логика действия: с базы забираем военную технику
     */
    public T operatorDelete(int index) {
        if (index <= _places.length) {
            T result = (T)_places[index];
            _places[index] = null;
            return result;
        }
        return null;
    }

    public boolean Equal(Base<ITransport, IDopDrawGuns> bases, int x){
        int count=0;
        for(int i=0; i<bases._places.length;i++){
            if(bases._places != null){
                count++;
            }
        }
        return count == x;
    }

    public boolean NotEqual(Base<ITransport, IDopDrawGuns> bases, int x){
        int count=0;
        for(int i=0; i<bases._places.length;i++){
            if(bases._places != null){
                count++;
            }
        }
        return count != x;
    }
    /**
     * Метод отрисовки базы
     * @param g
     */
    public void Draw(Graphics g) {
        DrawMarking(g);
        for(Object place : _places){
            if(place !=null){
                T places_ = (T)place;
                places_.DrawTransport(g);
            }
        }
    }
    /**
     * Метод отрисовки разметки мест на базе
     * @param g
     */
    private void DrawMarking(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j) {//линия рамзетки места
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i *
                        _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth,
                    (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }
}

