package Artillery;

public class BaseNotFoundException extends Exception{
    public BaseNotFoundException(int i){
        super("Не найдена военная техника по месту " + i);
    }
}
