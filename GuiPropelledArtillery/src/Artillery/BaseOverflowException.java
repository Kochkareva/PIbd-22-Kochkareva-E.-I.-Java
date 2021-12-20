package Artillery;

public class BaseOverflowException extends Exception{
    public BaseOverflowException(){
        super("На базе нет свободных мест");
    }
}
