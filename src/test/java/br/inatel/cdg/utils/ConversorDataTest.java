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
import java.util.Calendar;
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

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int diaDoMes = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        Assert.assertEquals(2020, ano);
        Assert.assertEquals(Calendar.JANUARY, mes); //no Calendar janeiro é o número 0
        Assert.assertEquals(8, diaDoMes);
    }

    @Test
    public void converterStringParaLocalDate(){
        LocalDate localDate = ConversorData.converterStringParaLocalDate("01/01/2001");

        Assert.assertEquals(2001, localDate.getYear());
        Assert.assertEquals(1, localDate.getDayOfMonth());
        Assert.assertEquals(1, localDate.getMonthValue());
    }
}
