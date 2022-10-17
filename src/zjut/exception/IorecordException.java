package zjut.exception;

public class IorecordException extends Exception{
    public IorecordException(){
        super();
    }

    public IorecordException(String msg){
        super(msg);
    }

    public IorecordException(String msg, Throwable cause){
        super(msg, cause);
    }

    public IorecordException(Throwable cause){
        super(cause);
    }
}
