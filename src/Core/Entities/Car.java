/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Entities;

import Utilities.DataValidation;
import java.time.LocalDate;

/**
 *
 * @author Lukass
 */
public class Car {
    
    private String licensePlate;
    private String carOwner;
    private String phoneNumber;
    private String carBrand;
    private long valueVehicle;
    private LocalDate registrationDate;
    private String registrationPlace;
    private int numberOfSeat;

    public Car(String licensePlate, String carOwner, String phoneNumber, String carBrand, long valueVehicle, LocalDate registrationDate, String registrationPlace, int numberOfSeat) throws Exception {
        setLicensePlate(licensePlate);
        setCarOwner(carOwner);
        setPhoneNumber(phoneNumber);
        setCarBrand(carBrand);
        setValueVehicle(valueVehicle);
        setRegistrationDate(registrationDate);
        setRegistrationPlace(registrationPlace);
        setNumberOfSeat(numberOfSeat);
    }

    public Car(Car car) throws Exception {
        setLicensePlate(car.licensePlate);
        setCarOwner(car.carOwner);
        setPhoneNumber(car.phoneNumber);
        setCarBrand(car.carBrand);
        setValueVehicle(car.valueVehicle);
        setRegistrationDate(car.registrationDate);
        setRegistrationPlace(car.registrationPlace);
        setNumberOfSeat(car.numberOfSeat);
    }

    public Car() {
    }
    

    public String getLicensePlate() {
        return licensePlate.trim().toUpperCase();
    }

    public String getCarOwner() {
        return carOwner.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber.trim();
    }

    public String getCarBrand() {
        return carBrand.trim();
    }

    public long getValueVehicle() {
        return valueVehicle;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getRegistrationPlace() {
        return registrationPlace.trim();
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setLicensePlate(String licensePlate) throws Exception {
        if(!DataValidation.checkStringWithFormat(licensePlate, "5[0-9][TBFCXUHKLNMETGDP][1-9][0-9]{5}")) {
            throw new Exception("PLEASE ENTER THE FORMAT [50-59][TBFCXUHKLNMETGDP][1-9]XXXXX WITH X IS A DIGIT");
        }
        this.licensePlate = licensePlate;
    }

    public void setCarOwner(String carOwner) throws Exception {
        carOwner = toTiteCase(carOwner);
        if (!DataValidation.checkStringLengthInRange(carOwner, 2, 25)) {
            throw new Exception(">>THE CAR OWNER NAME MUST BE INCLUED BETWEEN 2 AND 25 CHARACTERS LONG");
        } else if (carOwner.isEmpty()) {
            throw new Exception(">>THE CAR OWNER NAME CANNOT BE EMPTY");
        }
        this.carOwner = carOwner;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        if (!DataValidation.checkStringWithFormat(phoneNumber.trim(), "^(0(8[1-8]|9[0-8]|3[2-9]|7[0-9]|5[6-9]))\\d{7}$")) {
            throw new Exception(">>THE PHONE NUMBER MUST BELONG TO A VALID VIETNAMESE NETWORK OPERATION");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setCarBrand(String carBrand) throws Exception {
        carBrand = toTiteCase(carBrand);
        if (!DataValidation.checkStringLengthInRange(carBrand, 5 , 12)) {
            throw new Exception(">>THE BRAND NAME MUST BE INCLUED BETWEEN 5 AND 12 CHARACTERS LONG");
        } else if (carBrand.isEmpty()) {
            
            throw new Exception(">>THE BRAND NAME CANNOT BE EMPTY");
        }
        this.carBrand = carBrand;
    }

    public void setValueVehicle(long valueVehicle) throws Exception {
        if(valueVehicle < 999) {
            throw new Exception(">>THE VALUE OF CAR MUST BE GREATER THAN 999");
        }
        this.valueVehicle = valueVehicle;
    }

    public void setRegistrationDate(LocalDate registrationDate) throws Exception {
        if (registrationDate == null) {
            throw new Exception(">>DATE INVALID");
        }
        this.registrationDate = registrationDate;
    }

    public void setRegistrationPlace(String registrationPlace) throws Exception {
    if (registrationPlace == null || registrationPlace.length() < 3) {
        throw new Exception("REGISTRATION PLACE INVALID");
    }
    this.registrationPlace = registrationPlace;

}
    public void setNumberOfSeat(int numberOfSeat) throws Exception {
        if(numberOfSeat < 4 || numberOfSeat > 36) {
            throw new Exception(">>THE NUMBER OF SEATS MUST BE FROM 4 TO 36");
        }
        this.numberOfSeat = numberOfSeat;
    }
    
    public String toTiteCase(String value) {
        value = value.trim().replaceAll("\\s+", " ");
        return value;
    }
    
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%d,%s,%s,%d", licensePlate,  carOwner, phoneNumber, 
                carBrand, valueVehicle, registrationDate,registrationPlace, numberOfSeat);
    }
    
    
}
