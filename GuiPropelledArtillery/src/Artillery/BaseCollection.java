package Artillery;

import java.io.FileWriter;
import java.security.KeyException;
import  java.util.*;
import  java.io.*;

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

    public void set(String name, Base<ITransport, IGuns> places) {
        if (!baseStages.containsKey(name)) {
            baseStages.put(name, places);
        }
    }

    // Ширина окна отрисовки
    private final int pictureWidth;

    // Высота окна отрисовки
    private final int pictureHeight;

    private final String separator = ":";

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
     *
     * @param name ind
     * @return
     */
    public Base<ITransport, IGuns> get(String name) {
        if (baseStages.containsKey(name)) {
            return baseStages.get(name);
        }
        return null;
    }

    /**
     * Сохранение информации по военной технике на базах в файл
     *
     * @param filename Путь и имя файла
     */
    public void SaveData(String filename) throws IOException {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write("BaseCollection\n");
            for (Map.Entry<String, Base<ITransport, IGuns>> level : baseStages.entrySet()) {
                fw.write("Base" + separator + level.getKey() + "\n");
                ITransport artillery = null;
                for (int i = 0; (artillery = level.getValue().get(i)) != null; i++) {
                    if (artillery != null) {
                        if (artillery.getClass().getSimpleName().equals("CombatVehicle")) {
                            fw.write("CombatVehicle" + separator);
                        } else if (artillery.getClass().getSimpleName().equals("SelfPropelledArtillery")) {
                            fw.write("SelfPropelledArtillery" + separator);
                        }
                        fw.write(artillery.toString() + "\n");
                    }
                }
            }
        }
    }

    /**
     * Загрузка иформации по военной технике на базах из файла
     *
     * @param filename Путь и имя файла
     */
    public void LoadData(String filename) throws IOException, BaseOverflowException {
        if (!(new File(filename).exists())) {
            throw new FileNotFoundException("Файл" + filename + "не найден");
        }
        try (FileReader fr = new FileReader(filename)) {
            Scanner sc = new Scanner(fr);
            if (sc.nextLine().contains("BaseCollection")) {
                baseStages.clear();
            } else {
                throw new IllegalArgumentException("Неверный формат файла");
            }
            ITransport artillery = null;
            String key = "";
            String strs;
            while (sc.hasNextLine()) {
                strs = sc.nextLine();
                if (strs.contains("Base")) {
                    key = strs.split(separator)[1];
                    baseStages.put(key, new Base<ITransport, IGuns>(pictureWidth, pictureHeight));
                } else if (strs.contains(separator)) {
                    if (strs.contains("CombatVehicle")) {
                        artillery = new CombatVehicle(strs.split(separator)[1]);
                    } else if (strs.contains("SelfPropelledArtillery")) {
                        artillery = new SelfPropelledArtillery(strs.split(separator)[1]);
                    }
                    if (!baseStages.get(key).operatorAdd(artillery)) {
                        throw new BaseOverflowException();
                    }
                }
            }
        }
    }

    public void SaveBase(String filename, String key) throws IOException, KeyException {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        if (!baseStages.containsKey(key)) {
            throw new KeyException();
        }
        try (FileWriter fw = new FileWriter(filename, true)) {
            if (baseStages.containsKey(key)) {
                fw.write("Base" + separator + key + "\n");
            }
            ITransport artillery = null;
            for (int i = 0; (artillery = baseStages.get(key).get(i)) != null; i++) {
                if (artillery != null) {
                    if (artillery.getClass().getSimpleName().equals("CombatVehicle")) {
                        fw.write("CombatVehicle" + separator);
                    } else if (artillery.getClass().getSimpleName().equals("SelfPropelledArtillery")) {
                        fw.write("SelfPropelledArtillery" + separator);
                    }
                    fw.write(artillery.toString() + "\n");
                }
            }
        }
    }

    public void LoadBase(String filename) throws IOException, BaseOverflowException {
        if (!(new File(filename).exists())) {
            throw new FileNotFoundException("Файл" + filename + "не найден");
        }
        try (FileReader fr = new FileReader(filename)) {
            Scanner sc = new Scanner(fr);
            String key = "";
            String strs;
            strs = sc.nextLine();
            if (strs.contains("Base")) {
                key = strs.split(separator)[1];
                if (baseStages.containsKey(key)) {
                    baseStages.get(key).Clear();
                } else {
                    baseStages.put(key, new Base<ITransport, IGuns>(pictureWidth, pictureHeight));
                }
            } else {
                throw new IllegalArgumentException("Неверный формат файла");
            }
            ITransport artillery = null;
            while (sc.hasNextLine()) {
                strs = sc.nextLine();
                if (strs.contains(separator)) {
                    if (strs.contains("CombatVehicle")) {
                        artillery = new CombatVehicle(strs.split(separator)[1]);
                    } else if (strs.contains("SelfPropelledArtillery")) {
                        artillery = new SelfPropelledArtillery(strs.split(separator)[1]);
                    }
                    if (!baseStages.get(key).operatorAdd(artillery)) {
                        throw new BaseOverflowException();
                    }
                }
            }
        }
    }
}



