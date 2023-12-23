package com.flashmart.order.consts;

public class ORDER_STATUS {

    public static final int CONFIGURING = 4040;
    public static final int PREPARING = 4041;
    public static final int PICKED = 4042;
    public static final int ARRIVED = 4043;
    public static final int DELIVERED = 4044;
    public static final int CANCELLED = 4045;

    public static String toMessage(int status){

        switch (status){
            case CONFIGURING -> {
                return "Configuring";
            }
            case PREPARING -> {
                return "Preparing";
            }
            case PICKED -> {
                return "Picked";
            }
            case ARRIVED -> {
                return "Arrived";
            }
            case DELIVERED -> {
                return "Delivered";
            }
            case CANCELLED -> {
                return "Canceled";
            }

            default -> {
                return "Configuring";
            }
        }
    }

}
