package org.example.frisco.ulitilies;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DateUtilities {

    private static HashMap<String, Integer> monthNumbers = new HashMap<>();
    private static HashMap<String, Integer> monthNumbersConjugated = new HashMap<>();

    public static int getMonthNumberAsString(String monthName) {
        monthNumbers.put("styczeń", 1);
        monthNumbers.put("luty", 2);
        monthNumbers.put("marzec", 3);
        monthNumbers.put("kwiecien", 4);
        monthNumbers.put("maj", 5);
        monthNumbers.put("czerwiec", 6);
        monthNumbers.put("lipiec", 7);
        monthNumbers.put("sierpień", 8);
        monthNumbers.put("wrzesień", 9);
        monthNumbers.put("październik", 10);
        monthNumbers.put("listopad", 11);
        monthNumbers.put("grudzień", 12);
        return monthNumbers.get(monthName);
    }

    public static int getConjugatedMonthNumberAsString(String monthName) {
        monthNumbersConjugated.put("styczenia", 1);
        monthNumbersConjugated.put("lutego", 2);
        monthNumbersConjugated.put("marca", 3);
        monthNumbersConjugated.put("kwietnia", 4);
        monthNumbersConjugated.put("maja", 5);
        monthNumbersConjugated.put("czerwca", 6);
        monthNumbersConjugated.put("lipca", 7);
        monthNumbersConjugated.put("sierpnia", 8);
        monthNumbersConjugated.put("września", 9);
        monthNumbersConjugated.put("października", 10);
        monthNumbersConjugated.put("listopada", 11);
        monthNumbersConjugated.put("grudnia", 12);
        return monthNumbersConjugated.get(monthName);
    }


    public static LocalDate getDateFromFriscoString(String dateString){
        dateString = dateString.replace("Data dostawy", "");
        String[] dateStringsArray = dateString.split(" ");;
        if(dateStringsArray.length < 3){
            return null;
        }
        String month = dateStringsArray[1];
        String day = dateStringsArray[0];
        String year = dateStringsArray[2];
        String date = String.join("-", day, String.valueOf(getConjugatedMonthNumberAsString(month)), year);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
