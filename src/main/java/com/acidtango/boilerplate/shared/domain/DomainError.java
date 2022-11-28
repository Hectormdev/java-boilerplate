package com.acidtango.boilerplate.shared.domain;

public class DomainError extends Exception{

    private DomainErrorCode code;


    public DomainError(DomainErrorCode code, String message){
        super(message);
        this.code = code;
    }


    public DomainErrorCode getCode(){
        return this.code;
    }
}
