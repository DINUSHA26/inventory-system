package com.flashmart.inventory.consts;

public class INVENTORY_TRANSACTION {

    public static final int SELL_ON_CASH = 8000;
    public static final int SELL_ON_CREDIT = 8001;
    public static final int BUY_ON_CASH = 8002;
    public static final int BUY_ON_CREDIT = 8003;
    public static final int ONHOLD = 8004;
    public static final int RECLAIM = 8005;

    public static String ToMessage(int code){

        switch (code){
            case SELL_ON_CASH -> {
                return "Sell on cash";
            }

            case SELL_ON_CREDIT -> {
                return "Sell on credit";
            }

            case BUY_ON_CASH -> {
                return "Buy on cash";
            }

            case BUY_ON_CREDIT -> {
                return "Buy on credit";
            }

            case ONHOLD -> {
                return "on hold";
            }

            case RECLAIM -> {
                return "reclaim";
            }
            default -> {
                return "";
            }
        }
    }
}
