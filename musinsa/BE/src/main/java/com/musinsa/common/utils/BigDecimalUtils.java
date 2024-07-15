package com.musinsa.common.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class BigDecimalUtils {

    public static String formatWithCommas(BigDecimal value) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(value);
    }

    /**
     * 10,000 으로 콤마와 함께 들어오는 경우
     * */
    public static BigDecimal parseWithCommas(String formattedValue) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        // Parse 메서드를 사용하여 문자열에서 쉼표를 제거하고 BigDecimal로 변환
        return new BigDecimal(numberFormat.parse(formattedValue).toString());
    }

    /**
     * 10000 으로 콤마 없이 들어오는 경우
     * */
    public static BigDecimal parseWithoutCommas(String valueWithoutCommas) {
        // 문자열을 BigDecimal로 직접 변환
        return new BigDecimal(valueWithoutCommas);
    }
}
