package br.inatel.cdg.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ConversorDataTest {

    @Test
    public void converterDateParaLocalDate() throws ParseException {
        Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/08/2021");
        LocalDate localDate = ConversorData.converterDateParaLocalDate(date);

        Assert.assertEquals(2021, localDate.getYear());
        Assert.assertEquals(8, localDate.getMonthValue());
        Assert.assertEquals(1, localDate.getDayOfMonth());
    }

    @Test
    public void converterLocalDateParaDate(){
        LocalDate localDate = LocalDate.of(2020, 1, 8);
        Date date =  ConversorData.converterLocalDateParaDate(localDate);

        Assert.assertEquals("2020-01-08T03:00:00Z", date.toInstant().toString());
    }

    @Test
    public void converterStringParaLocalDate(){
        LocalDate localDate = ConversorData.converterStringParaLocalDate("01/01/2001");

        Assert.assertEquals(2001, localDate.getYear());
        Assert.assertEquals(1, localDate.getDayOfMonth());
        Assert.assertEquals(1, localDate.getMonthValue());
    }
}
