package com.ubs.opsit.interviews.helper;

import org.junit.Assert;
import org.junit.Test;

public class BerlinClockHelperTest {
    private String inputStringPass1 = "00:00:00";
    private String inputStringPass2 = "13:17:01";
    private String inputStringPass3 = "23:59:59  ";
    private String inputStringPass4 = "24:00:00  ";
    private String inputStringFail1 = "24:59:59";
    private String inputStringFail2 = "00:010:00";
    private String inputStringFail3 = "0 0:00: 00";


    @Test
    public void testSplitAndValidatePassForProperInput() {
        String[] expected1 = {"00", "00", "00"};
        String[] expected2 = {"13", "17", "01"};
        String[] expected3 = {"23", "59", "59"};
        String[] expected4 = {"24", "00", "00"};

        Assert.assertArrayEquals(expected1, BerlinClockHelper.splitAndValidateTimeString(inputStringPass1));
        Assert.assertArrayEquals(expected2, BerlinClockHelper.splitAndValidateTimeString(inputStringPass2));
        Assert.assertArrayEquals(expected3, BerlinClockHelper.splitAndValidateTimeString(inputStringPass3));
        Assert.assertArrayEquals(expected4, BerlinClockHelper.splitAndValidateTimeString(inputStringPass4));

    }

    @Test
    public void testSplitAndValidateFailForWrongInput() {
        Assert.assertNull(BerlinClockHelper.splitAndValidateTimeString(inputStringFail1));
        Assert.assertNull(BerlinClockHelper.splitAndValidateTimeString(inputStringFail2));
        Assert.assertNull(BerlinClockHelper.splitAndValidateTimeString(inputStringFail3));
    }

    @Test
    public void testGetMinutesString() {
        Assert.assertEquals("OOOOOOOOOOO\r\nOOOO", BerlinClockHelper.getMinutesString(0));
        Assert.assertEquals("YYROOOOOOOO\r\nYYOO", BerlinClockHelper.getMinutesString(17));
        Assert.assertEquals("YYRYYRYYRYY\r\nYYYY", BerlinClockHelper.getMinutesString(59));
    }

    @Test
    public void testGetHoursString() {
        Assert.assertEquals("OOOO\r\nOOOO", BerlinClockHelper.getHoursString(0));
        Assert.assertEquals("RROO\r\nRRRO", BerlinClockHelper.getHoursString(13));
        Assert.assertEquals("RRRR\r\nRRRO", BerlinClockHelper.getHoursString(23));
        Assert.assertEquals("RRRR\r\nRRRR", BerlinClockHelper.getHoursString(24));
    }

    @Test
    public void testGetSecondsString() {
        Assert.assertEquals("Y", BerlinClockHelper.getSecondsString(0));
        Assert.assertEquals("Y", BerlinClockHelper.getSecondsString(54));
        Assert.assertEquals("O", BerlinClockHelper.getSecondsString(1));
        Assert.assertEquals("O", BerlinClockHelper.getSecondsString(23));
    }
}