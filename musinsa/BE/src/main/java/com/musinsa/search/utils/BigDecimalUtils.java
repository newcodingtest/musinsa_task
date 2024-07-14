package com.musinsa.search.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class BigDecimalUtils {

    public static String formatWithCommas(BigDecimal value) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(value);
    }
}
