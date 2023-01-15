package com.msvc.order.util.exceptions;

public class ServerRestException extends  RuntimeException{
    public ServerRestException(){

    }
    public ServerRestException(String message){
        super(message);
    }
    public ServerRestException(String message, Throwable cause ){
        super(message, cause);
    }
    public ServerRestException(Throwable cause ){
        super(cause);
    }
}
