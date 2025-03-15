package exception;

import process.Work;

public class NotEnoughMoneyException extends RuntimeException{
    private Integer money;
    public NotEnoughMoneyException(String message, Throwable cause, Integer money){
        super(message, cause);
        this.money = money;
    }
    public NotEnoughMoneyException(String message){
        super(message);
    }
}
