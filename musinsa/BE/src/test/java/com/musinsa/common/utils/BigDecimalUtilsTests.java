package com.musinsa.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;

public class BigDecimalUtilsTests {

    @Test
    public void 콤마_없는_문자열을_BigDecimal로_변경_할_수_있다(){
        //given
        String given = "10000";
        //when
        BigDecimal change = BigDecimalUtils.parseWithoutCommas(given);

        //then
        Assertions.assertEquals(new BigDecimal(10000), change);
    }

    @Test
    public void 콤마_있는_문자열을_BigDecimal로_변경_할_수_있다() throws ParseException {
        //given
        String given = "10,000";
        //when
        BigDecimal change = BigDecimalUtils.parseWithCommas(given);

        //then
        Assertions.assertEquals(new BigDecimal(10000), change);
    }

    @Test
    public void BigDecimal을_콤마_있는_문자열로_변경_할_수_있다() throws ParseException {
        //given
        BigDecimal given = new BigDecimal(10000);
        //when
        String change = BigDecimalUtils.formatWithCommas(given);

        //then
        Assertions.assertEquals("10,000", change);
    }
}
