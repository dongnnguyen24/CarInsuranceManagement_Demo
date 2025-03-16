/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessObject;

import Core.Entities.Car;
import Core.Entities.Insurance;
import Core.Interfaces.ICarDAO;
import Core.Interfaces.IInsuranceDAO;
import Utilities.DataInput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lukass
 */
public class VehicleInsuranceManagement {

    ICarDAO carDAO;
    IInsuranceDAO insuranceDAO;

    public VehicleInsuranceManagement(ICarDAO carDAO, IInsuranceDAO insuranceDAO) {
        this.carDAO = carDAO;
        this.insuranceDAO = insuranceDAO;

    }

    public List<Car> reportUninsuredCars() throws Exception {
        List<Car> uninsuredCars = new ArrayList<>();
        for (Car car : carDAO.getCar()) {
            if (insuranceDAO.getInsuranceByLisencePlate(car.getLicensePlate()) == null) {
                uninsuredCars.add(car);
            }
        }
        return uninsuredCars;
    }
    
    //search carOwner, sort theo gia tri 
    public List<Car> searchByCarOwner () throws Exception {
        String carOnwer = DataInput.getString("ENTER CAR OWNER: ");
        List<Car> list = reportUninsuredCars();
        try {
            list = list.stream().filter(c -> c.getCarOwner().toUpperCase().contains(carOnwer.toUpperCase())).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(">>ERROR");
        }
        return list;
    }
    
    //search carOwner, update
    public List<Car> searchByCarOwner2 () throws Exception {
        String carOnwer = DataInput.getString("ENTER CAR OWNER: ");
        List<Car> list = reportUninsuredCars();
        try {
            list = list.stream().filter(c -> c.getCarOwner().toUpperCase().contains(carOnwer.toUpperCase())).collect(Collectors.toList());
            for (Car car : list) {
                carDAO.deleteCar(car);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void printUninsuredCarList(List<Car> list) {
        try {
            if (list == null) {
                throw new Exception(">>LIST CAR IS INVALID.");
            }
            if (list.isEmpty()) {
                System.out.println(">>NO DATA IN SYSTEMS.");
                return;
            }
            
            list.sort((Car c1, Car c2) -> Long.compare(c1.getValueVehicle(), c2.getValueVehicle()));

            System.out.println("===========================INSURANCE LIST==========================");
            System.out.format("%-18s| %-20s| %-20s| %-15s| %-20s| %-15s\n", "License plate", "Registration Date", "Vehicle Owner", "Brand", "Number of seats", "Value of vehicle");
            System.out.println("------------------------------------------------------------------");

            for (Car car : list) {
                System.out.format("%-18s| %-20s| %-20s| %-15s| %-20s| %-15s\n", car.getLicensePlate(), car.getRegistrationDate(), car.getCarOwner(),
                        car.getCarBrand(), car.getNumberOfSeat(), car.getValueVehicle());
            }
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }
}
