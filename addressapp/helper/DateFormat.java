/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressapp.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Howard
 */
public class DateFormat {
    private static final String Date_PATTERN = "mm,dd,yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Date_PATTERN);
    
    /**
     * Convert LocalDate type to String
     * @param date
     * @return String result
     */
    public static String toString(LocalDate date) {
        if(date != null) {
            return DATE_FORMATTER.format(date);
        }
        return null;
    }
    
    /**
     * Convert String to LocalDate type
     * @param inString
     * @return converted LocalDate var
     */
    public static LocalDate toDate(String inString) {
        try{
            return DATE_FORMATTER.parse(inString, LocalDate::from);
        } catch(DateTimeParseException e) {
            return null;
        }
    }
    /**
     * Check if the String is a valid date
     */
    public static boolean isDate(String inString) {
        return DateFormat.toDate(inString) != null;
    }
    
}
