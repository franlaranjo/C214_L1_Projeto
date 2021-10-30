/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Classe utilitária para conversão de Date em LocalDate e vice-versa.
 *
 * @author Francielly
 */
public class ConversorData {

    /**
     *
     * @param date
     * @return localdate
     */
    public static LocalDate converterDateParaLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     *
     * @param date
     * @return date
     */
    public static Date converterLocalDateParaDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
