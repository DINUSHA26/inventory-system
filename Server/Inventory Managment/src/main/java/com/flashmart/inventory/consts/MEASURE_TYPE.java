package com.flashmart.inventory.consts;

public class MEASURE_TYPE {

    public static final int UNIT = 1001;
    public static final int KG = 1002;

    public static String ToMessage(int code){

        switch (code){
            case UNIT -> {
                return "Unit";
            }

            case KG -> {
                return "KG";
            }

            default -> {
                return "";
            }
        }
    }
}
