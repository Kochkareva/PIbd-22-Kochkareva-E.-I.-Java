package Artillery;

import  java.util.*;

//Класс-коллекция баз
public class BaseCollection {
    // Словарь (хранилище) с базами
    final Map<String, Base<ITransport, IGuns>> baseStages;

    public ITransport get(String name, int index) {
        if (baseStages.containsKey(name)) {
            return baseStages.get(name).get(index);
        }
        return null;
    }
    public void set(String name, Base<ITransport, IGuns> places){
        if(!baseStages.containsKey(name)) {
            baseStages.put(name, places);
        }
    }

    // Ширина окна отрисовки
    private final int pictureWidth;

    // Высота окна отрисовки
    private final int pictureHeight;

    /**
     * Конструктор
     *
     * @param pictureWidth
     * @param pictureHeight
     */
    public BaseCollection(int pictureWidth, int pictureHeight) {
        baseStages = new HashMap<>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public Set<String> Keys() {
        return baseStages.keySet();
    }

    /**
     * Добавление базы
     *
     * @param name Название базы
     */
    public void AddBase(String name) {
        if (baseStages.containsKey(name)) {
            return;
        }
        baseStages.put(name, new Base<ITransport, IGuns>(pictureWidth, pictureHeight));
    }

    /**
     * Удаление базы
     *
     * @param name Название базы
     */
    public void DelBase(String name) {
        baseStages.remove(name);
    }

    /**
     * Доступ к базе
     * @param name ind
     * @return
     */
    public Base<ITransport, IGuns> get(String name) {
        if (baseStages.containsKey(name)) {
            return baseStages.get(name);
        }
        return null;
    }
}


