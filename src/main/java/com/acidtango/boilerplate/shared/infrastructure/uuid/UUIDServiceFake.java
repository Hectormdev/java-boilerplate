package com.acidtango.boilerplate.shared.infrastructure.uuid;

import com.acidtango.boilerplate.shared.domain.IUUIDService;

public class UUIDServiceFake implements IUUIDService {

    private int currentNumber;
    private final static String INITIAL_UUID = "00000000-0000-0000-0000-0000000000";

    public UUIDServiceFake(int currentNumber){
        this.currentNumber = currentNumber;
    }
    public UUIDServiceFake(){
        this.currentNumber = 0;
    }

    public String generateUUID() {
        String uuid = INITIAL_UUID+String.format("%02d",currentNumber);
        currentNumber++;
        return uuid;
    }
}
