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
public class Insurance {
    
    private String insuranceID;
    private String lisencePlate; 
    private LocalDate establishedDate;
    private int insurancePeriod;
    private int insuranceFees;
    private String insuranceOwner;

    public Insurance(String insuranceID, String lisencePlate, LocalDate establishedDate, int insurancePeriod, int insuranceFees, String insuranceOwner) throws Exception {
        setInsuranceID(insuranceID);
        setLisencePlate(lisencePlate);
        setEstablishedDate(establishedDate);
        setInsurancePeriod(insurancePeriod);
        setInsuranceFees(insuranceFees);
        setInsuranceOwner(insuranceOwner);
    }
    
    public Insurance(Insurance insurance) throws Exception {
        setInsuranceID(insurance.insuranceID);
        setLisencePlate(insurance.lisencePlate);
        setEstablishedDate(insurance.establishedDate);
        setInsurancePeriod(insurance.insurancePeriod);
        setInsuranceFees(insurance.insuranceFees);
        setInsuranceOwner(insurance.insuranceOwner);
    }

    public String getInsuranceID() {
        return insuranceID.trim().toUpperCase();
    }

    public String getLisencePlate() {
        return lisencePlate.trim().toUpperCase();
    }

    public LocalDate getEstablishedDate() {
        return establishedDate;
    }

    public int getInsurancePeriod() {
        return insurancePeriod;
    }

    public int getInsuranceFees() {
        return insuranceFees;
    }

    public String getInsuranceOwner() {
        return insuranceOwner.trim();
    }

    public void setInsuranceID(String insuranceID) throws Exception {
        if(!DataValidation.checkStringWithFormat(insuranceID.trim().toUpperCase(), "[A-Z0-9]{4}")) {
            throw new Exception(">>INSURANCE ID IS NOT VALID.");
        }
        this.insuranceID = insuranceID;
    }

    public void setLisencePlate(String lisencePlate) throws Exception {
        if(!DataValidation.checkStringWithFormat(lisencePlate, "5[0-9][TBFCXUHKLNMETGDP][1-9][0-9]{5}")) {
            throw new Exception("PLEASE ENTER THE FORMAT [50-59][TBFCXUHKLNMETGDP][1-9]XXXXX WITH X IS A DIGIT");
        }
        this.lisencePlate = lisencePlate;
    }

    public void setEstablishedDate(LocalDate establishedDate) throws Exception {
        if (establishedDate == null) {
            throw new Exception(">>DATE INVALID");
        }
        this.establishedDate = establishedDate;
    }

    public void setInsurancePeriod(int insurancePeriod) throws Exception {
        if(insurancePeriod != 12 && insurancePeriod != 24 && insurancePeriod != 36) {
            throw new Exception("INSURANCE PERIOD MUST BE ONE OF THE FOLLOWING: 12, 24 OR 36");
        }
        this.insurancePeriod = insurancePeriod;
    }

    public void setInsuranceFees(int insuranceFees) throws Exception {
        if(insuranceFees < 250) {
            throw new Exception("INSURANCE FEES INVALID");
        }
        this.insuranceFees = insuranceFees;
    }

    public void setInsuranceOwner(String insuranceOwner) throws Exception {
        insuranceOwner = toTiteCase(insuranceOwner);
        if (!DataValidation.checkStringLengthInRange(insuranceOwner, 2, 25)) {
            throw new Exception(">>THE INSURANCE OWNER NAME MUST BE INCLUED BETWEEN 2 AND 25 CHARACTERS LONG");
        } else if (insuranceOwner.isEmpty()) {
            throw new Exception(">>THE INSURANCE OWNER NAME CANNOT BE EMPTY");
        }
        this.insuranceOwner = insuranceOwner;
    }
    
    public String toTiteCase(String value) {
        value = value.trim().replaceAll("\\s+", " ");
        return value;
    }
    
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%d,%d,%s", insuranceID,  lisencePlate, establishedDate, 
                insurancePeriod, insuranceFees, insuranceOwner);
    }
}
