package tdd.example.first.notice.exceptions;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String msg){
        super(msg);
    }
}
