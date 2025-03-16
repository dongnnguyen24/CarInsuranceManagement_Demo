/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessObject;

import Core.Entities.Insurance;
import Core.Interfaces.IInsuranceDAO;
import Utilities.DataInput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukass
 */
public class InsuranceManagement {

    IInsuranceDAO insuranceDAO;

    public InsuranceManagement(IInsuranceDAO insuranceDAO) {
        this.insuranceDAO = insuranceDAO;
    }

    public Insurance inputInsuranceInfo() throws Exception {
        String insuranceID = DataInput.getString("ENTER INSURANCE ID: ");
        String lisencePlate = DataInput.getString("ENTER LISENCE PLATE: ");
        LocalDate establishedDate = DataInput.getDate("ENTER ESTABLISHED DATE: ");
        int insurancePeriod = DataInput.getIntegerNumber("ENTER INSURANCE PERIOD: ");
        int vehicleValue = DataInput.getIntegerNumber("ENTER VEHICLE VALUE: ");
        int insuranceFees = 0;
        switch (insurancePeriod) {
            case 12:
                insuranceFees = vehicleValue * 25 / 100;
                break;
            case 24:
                insuranceFees = vehicleValue * 20 / 100;
                break;
            case 36:
                insuranceFees = vehicleValue * 10 / 100;
                break;
            default:
                System.out.println("INSURANCE PERIOD INVALID");
                break;
        }
        String insuranceOwner = DataInput.getString("ENTER INSURANCE OWNER: ");
        return new Insurance(insuranceID, lisencePlate, establishedDate, insurancePeriod, insuranceFees, insuranceOwner);
    }

    public void addNewInsurance() throws Exception {
        try {
            Insurance insurance = inputInsuranceInfo();
            if (insuranceDAO.getInsuranceByID(insurance.getInsuranceID()) != null) {
                System.out.println(">>THE INSURANCE ALREADY EXIST");
            }
            insuranceDAO.addInsurance(insurance);
            System.out.println(">>THE INSURANCE HAS ADDED SUCCESSFULLY");
        } catch (Exception e) {
            throw new Exception(">>ERROR: " + e.getMessage());
        }
    }
    
    //update tat ca cac fields acua Insurance
    public void setNewInsuranceInfo(Insurance insurance) throws Exception {
        String lisencePlate = DataInput.getString("ENTER NEW LISENCE PLATE: ");
        if (!lisencePlate.isEmpty()) {
            insurance.setLisencePlate(lisencePlate);
        }

        LocalDate establishedDate = DataInput.getDate("ENTER NEW ESTABLISHED DATE: ");
        if (establishedDate != null) {
            insurance.setEstablishedDate(establishedDate);
        }

        int insurancePeriod = DataInput.getIntegerNumber("ENTER NEW INSURANCE PERIOD: ");
        insurance.setInsurancePeriod(insurancePeriod);

        int vehicleValue = DataInput.getIntegerNumber("ENTER NEW VEHICLE VALUE: ");
        if (vehicleValue > 0) {
            int insuranceFees = 0;
            switch (insurancePeriod) {
                case 12:
                    insuranceFees = vehicleValue * 25 / 100;
                    break;
                case 24:
                    insuranceFees = vehicleValue * 20 / 100;
                    break;
                case 36:
                    insuranceFees = vehicleValue * 10 / 100;
                    break;
                default:
                    System.out.println("INSURANCE PERIOD INVALID");
                    break;
            }
            insurance.setInsuranceFees(insuranceFees);
        }

        String insuranceOwner = DataInput.getString("ENTER NEW INSURANCE OWNER: ");
        insurance.setInsuranceOwner(insuranceOwner);
    }
    

    public void updateInsurance() throws Exception {
        try {
            String insuranceID = DataInput.getString("ENTER INSURANCE ID:");
            Insurance insurance = insuranceDAO.getInsuranceByID(insuranceID);
            if (insurance == null) {
                throw new Exception(">>THE INSURANCE CAN NOT FOUND");
            }
            Insurance newInsurance = new Insurance(insurance);
            setNewInsuranceInfo(newInsurance);
            insuranceDAO.updateInsurance(newInsurance);
            System.out.println(">>THE INSURANCE HAS UPDATED SUCCESSFULLY");
        } catch (Exception e) {
            throw new Exception(">>ERROR: " + e.getMessage());
        }
    }

    public void deleteInsurance() throws Exception {
        try {
            String insuranceID = DataInput.getString("ENTER INSURANCE ID:");
            Insurance insurance = insuranceDAO.getInsuranceByID(insuranceID);
            if (insurance == null) {
                throw new Exception(">>THE INSURANCE CAN NOT FOUND");
            }
            insuranceDAO.deleteInsurance(insurance);
            System.out.println(">>THE CAR HAS DELETED SUCCESSFULLY");
        } catch (Exception e) {
            throw new Exception(">>ERROR: " + e.getMessage());
        }
    }

    //tim ten customer o List Insurance sau do update owner name 
    public List<Insurance> findCustomerByNameToUpdate() throws Exception {
        String customerName = DataInput.getString("ENTER CUSTOMER NAME: ");
        List<Insurance> list = new ArrayList();
        try {
            list = insuranceDAO.search(i -> i.getInsuranceOwner().toUpperCase().contains(customerName.toUpperCase()));
            
            for (Insurance insurance : list) {
                printInsuranceList(insurance);
                String newcustomerName = DataInput.getString("ENTER NEW CUSTOMER NAME: "); //bo vo fore la nhap tung thang neu search ra nhieu thang
                insurance.setInsuranceOwner(newcustomerName);
                System.out.println("UPDATE SUCCESSFULLY");
            }
        } catch (Exception e) {
            throw new Exception("NO CUSTOMER NAME FOUND");
        }
        return list;
    }
    
    public void findCustomerByNameToDelete() throws Exception {
        String customerName = DataInput.getString("ENTER CUSTOMER NAME: ");
        List<Insurance> list = new ArrayList();
        try {
            list = insuranceDAO.search(i -> i.getInsuranceOwner().toUpperCase().contains(customerName.toUpperCase()));
            
            for (Insurance insurance : list) {
                printInsuranceList(insurance);
                insuranceDAO.deleteInsurance(insurance);
                System.out.println("DELETE SUCCESSFULLY");
            }
        } catch (Exception e) {
            throw new Exception("NO CUSTOMER NAME FOUND");
        }
    }

    public List<Insurance> findInsuranceByYear() throws Exception {
        int year = DataInput.getIntegerNumber("ENTER THE YEAR: ");
        List<Insurance> list = new ArrayList();
        try {
            list = insuranceDAO.search(i -> i.getEstablishedDate().getYear() == year);
            list.sort((Insurance i1, Insurance i2) -> i1.getEstablishedDate().compareTo(i2.getEstablishedDate()));
        } catch (Exception e) {
            throw new Exception("NO YEAR FOUND");
        }
        return list;
    }

    //1 list bat ki - search 
    public void dislayInsuranceList(List<Insurance> list) throws Exception {
        list.sort((Insurance i1, Insurance i2) -> i1.getInsuranceID().toUpperCase().compareTo(i2.getInsuranceID().toUpperCase()));
        System.out.println("===========================INSURANCE LIST==========================");
        System.out.format("%-20s| %-20s| %-20s| %-15s | %-20s| %s\n", "Insurance Id", "Established Date", "License plate", "Customer", "Insurance Period", "Insurance Fees");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (Insurance insurance : list) {
            System.out.format("%-20s| %-20s|%-20s| %-15s | %-20s| %s\n", insurance.getInsuranceID(), insurance.getEstablishedDate(), insurance.getLisencePlate(),
                    insurance.getInsuranceOwner(), insurance.getInsurancePeriod(), insurance.getInsuranceFees());
        }
    }

    //1 insurance 
    public void printInsuranceList(Insurance insurance) {
        try {
            if (insurance == null) {
                throw new Exception(">>LIST INSURANCE IS INVALID");
            }
            System.out.println("===========================INSURANCE LIST==========================");
            System.out.format("%-20s| %-20s| %-20s| %-15s | %-20s| %s\n", "Insurance Id", "Established Date", "License plate", "Customer", "Insurance Period", "Insurance Fees");
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.format("%-20s| %-20s|%-20s| %-15s | %-20s| %s\n", insurance.getInsuranceID(), insurance.getEstablishedDate(), insurance.getLisencePlate(),
                    insurance.getInsuranceOwner(), insurance.getInsurancePeriod(), insurance.getInsuranceFees());
        } catch (Exception e) {
            System.out.println(">>ERROR: " + e.getMessage());
        }
    }
    
    

    //list tat ca
    public void showAll() throws Exception {
        dislayInsuranceList(insuranceDAO.getInsurance());
    }

    public void saveData() {
        try {
            insuranceDAO.saveData();
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }
}
