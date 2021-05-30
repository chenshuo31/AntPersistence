package org.antpersistence.util;

import java.util.concurrent.atomic.AtomicInteger;

public class Utils {

    private static final AtomicInteger SEQ_NUMBER = new AtomicInteger(0);

    public static int generateId() {
        return SEQ_NUMBER.getAndAdd(1);
    }

    public static String numberFormat(int number, int digits) {
        return String.format("%0" + digits +"d", number);
    }

    public static String numberFormat(int number) {
        int digits = String.valueOf(Integer.MAX_VALUE).length();
        return numberFormat(number, digits);
    }

}
