package org.example.frisco;

import org.example.frisco.pages.FriscoLogin;
import org.example.frisco.pages.FriscoReservartions;
import org.junit.*;

import java.time.LocalDate;

public class FriscoTestSuite {

    private static FriscoLogin friscoLogin;
    private static FriscoReservartions friscoReservartions;

    @AfterClass
    public static void cleanup() {
        friscoLogin.cleanUp();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        friscoLogin = new FriscoLogin();
        friscoReservartions = friscoLogin.loginToPage();
    }


    @Test
    public void displayCurrentReservationDateTime() {
        friscoReservartions.getCurrentReservationDateTime();
        String reservationDateTime = friscoReservartions.getCurrReservationDate().toString();
        System.out.println(reservationDateTime);
        Assert.assertTrue(!reservationDateTime.isEmpty());
    }

    @Test
    public void checkReservationIsInTheFuture() {
        friscoReservartions.getCurrentReservationDateTime();
        String reservationDateTime = friscoReservartions.getCurrReservationDate().toString();
        Assert.assertTrue(reservationDateTime.compareTo(LocalDate.now().toString()) > 0 );
    }

    @Test
    public void getFirstReservationDate() {
        friscoReservartions.findFirstReservationDate();
        LocalDate date = friscoReservartions.getNextReservationDate();
        System.out.println(date);
    }
}
