package Artillery;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Параметризованный класс для хранения набора объектов от интерфейса ITransport
 */
public class Base<T extends ITransport, N extends IGuns> {
    // Список объектов, которые храним
    private final List<T> _places;

    // Ширина окна отрисовки
    private final int pictureWidth;

    // Высота окна отрисовки
    private final int pictureHeight;

    // Размер места на базе (ширина)
    private final int _placeSizeWidth = 160;

    /// Размер места на базе (высота)
    private final int _placeSizeHeight = 80;

    //Максимальное количество мест на базе
    private final int _maxCount;

    public T get(int index) {
        if (index > -1 && index < _places.size()) {
            return _places.get(index);
        }
        return null;
    }
    public void set(int index, T value){
        if(_places.size()>index && index>=0){
            _places.add(index, value);
        }
    }

    /**
     * Конструктор
     * @param picWidth  Рамзер базы - ширина
     * @param picHeight Рамзер базы - высота
     */
    public Base(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _maxCount = width*height;
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        _places=new ArrayList<>();
    }

    /**
     * Перегрузка оператора сложения
     * Логика действия: на базу добавляется военная техника
     */
    public int operatorAdd(T combatVehicle) {
        if(_places.size()<_maxCount){
            _places.add(combatVehicle);
            return _places.size();
        }
        return -1;
    }

    /**
     * Перегрузка оператора вычитания
     * Логика действия: с базы забираем военную технику
     */
    public T operatorDelete(int index) {
        if(index >= 0 && index<_maxCount && _places.get(index) != null){
            T vehicle = _places.get(index);
            _places.remove(index);
            return vehicle;
        }
        return null;
    }
    /**
     * Метод отрисовки базы
     * @param g
     */
    public void Draw(Graphics g) {
        DrawMarking(g);
        for( int i =0;i< _places.size();i++ ){
            _places.get(i).SetPosition(i % (pictureWidth / _placeSizeWidth) * _placeSizeWidth + 5,
                    i / (pictureHeight / _placeSizeHeight) * _placeSizeHeight + 10, pictureWidth, pictureHeight);
            _places.get(i).DrawTransport(g);
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

    public void Clear(){
        _places.clear();
    }
}

