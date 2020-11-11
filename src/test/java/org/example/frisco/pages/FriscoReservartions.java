package org.example.frisco.pages;


import lombok.Getter;
import org.example.base.BaseSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.example.frisco.ulitilies.DateUtilities.getDateFromFriscoString;

@Getter
public class FriscoReservartions extends BaseSelenium {

    private WebDriver driver;
    private By currentReservation = By.className("date");
    private By reservationHeaderButton = By.className("header_delivery-inner");

    private By currentReservationInfoBy = By.className("reservation-summary_row-value");
    private List<WebElement> reservationInformation;

    private LocalDate currReservationDate;
    private LocalDate nextReservationDate;
    private By newReservationButton = By.xpath("//*[@id=\"wrapper\"]/span/div/div/div[1]/div[2]/div[2]/div[2]");

    private By reservationModal = By.className("checkout_box");
    private By reservationModalButtons = By.className("reservation-selector_tabs-header-tab"); //contains two buttons, for active reservation and all reservations
//    private String activeReservation = "Aktywne";
//    private String allReservations = "Schowek";
    private By reservationSummaries = By.className("reservation-summary");
    private By reservationSummariesRow = By.className("reservation-summary_row");
    private By activateReservationButtons = By.className("reservation-summary_buttons");


    public FriscoReservartions() {
        this.driver = super.driver;
    }

    public void getCurrentReservationDateTime() {
        getReservationInformation();
        currReservationDate = getDateFromFriscoString(reservationInformation.get(1).getText());
    }

    private void getReservationInformation() {
        waitMilliseconds(800);
        driver.findElement(reservationHeaderButton).click();
        reservationInformation = driver.findElements(currentReservationInfoBy);
    }

    public void findFirstReservationDate() {
        if (!isElementDisplayed(reservationModal)) {
            driver.findElement(reservationHeaderButton).click();
        }
        driver.findElements(reservationModalButtons).get(1).click();
        nextReservationDate = findFirstReservationDate(getAllReservationSummaries());
    }


    private List<WebElement> getAllReservationSummaries() {
        return driver.findElements(reservationSummaries);
    }

    private LocalDate findFirstReservationDate(List<WebElement> summaries) {
        List<LocalDate> dates = new ArrayList<>();
        for (WebElement summary : summaries) {
            LocalDate d = getDateFromFriscoString(summary.findElements(reservationSummariesRow).get(1).getText());
            if(d != null){
                dates.add(d);
            }
        }
        Collections.sort(dates);
        return dates.get(0);
    }

}
