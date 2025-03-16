/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import BussinessObject.CarManagement;
import BussinessObject.InsuranceManagement;
import BussinessObject.VehicleInsuranceManagement;
import Core.Interfaces.ICarDAO;
import Core.Interfaces.IInsuranceDAO;
import DataObject.CarDAO;
import DataObject.InsuranceDAO;
import Utilities.DataInput;

/**
 *
 * @author Lukass
 */
public class Program {

    public static void main(String[] args) throws Exception {
        int choice = 0;
        String carFile = "Car.dat";
        String insuranceFile = "Insurance.dat";
        try {
            ICarDAO carDAO = new CarDAO(carFile);
            IInsuranceDAO insuranceDAO = new InsuranceDAO(insuranceFile);
            CarManagement car = new CarManagement(carDAO);
            InsuranceManagement insurance = new InsuranceManagement(insuranceDAO);
            VehicleInsuranceManagement vim = new VehicleInsuranceManagement(carDAO, insuranceDAO);
            do {
                System.out.println("===========================MAIN MENU==========================");
                Menu.print("1. ADD NEW CAR|"
                        + "2. FIND A CAR BY LICENSE PLATE|"
                        + "3. UPDATE CAR INFORMATION|"
                        + "4. DELETE CAR INFORMATION|"
                        + "5. ADD AN INSURANCE STATEMENTS|"
                        + "6. LIST OF INSURANCE STATEMENTS|"
                        + "7. REPORT UNINSURED CARS|"
                        + "8. SHOW ALL CARS|"
                        + "9. SHOW ALL INSURANCE|"
                        + "10. SAVE DATA|"
                        + "11. QUIT|"
                        + "==============================================================|SELECT:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        car.addNewCustomer();
                        break;
                    case 2:
                        car.printCarList(car.findCarByLicensePlate());
                        break;
                    case 3:
                        car.updateCar();
                        break;
                    case 4:
                        car.deleteCar();
                        break;
                    case 5:
                        insurance.addNewInsurance();
                        break;
                    case 6:
                        insurance.dislayInsuranceList(insurance.findInsuranceByYear());
                        break;
                    case 7:
                        vim.printUninsuredCarList(vim.reportUninsuredCars());
                        break;
                    case 8:
                        car.showAll();
                        break;
                    case 9:
                        insurance.showAll();
                        break;
                    case 10:
                        car.saveData();
                        insurance.saveData();
                        System.out.println(">>DATA SAVED SUCCESSFULLY");
                        break;
                    case 11: 
                        System.out.println("Exit Program.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Thanks !!!");
                        System.exit(0);
                        break;
                }
            } while (true);
        } catch (Exception e) {
            throw new Exception(">>ERROR: " + e.getMessage());
        }
    }
}
