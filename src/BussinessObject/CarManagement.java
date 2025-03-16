/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessObject;

import Core.Entities.Car;
import Core.Interfaces.ICarDAO;
import Utilities.DataInput;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Lukass
 */
public class CarManagement {

    ICarDAO carDAO;

    public CarManagement(ICarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car inputCarInfomation() throws Exception {

        String licensePlate = DataInput.getString("ENTER LICENSE PLATE: ");
        String carOwner = DataInput.getString("ENTER CAR OWNER: ");
        String phoneNumber = DataInput.getString("ENTER PHONE NUMBER: ");
        String carBrand = DataInput.getString("ENTER CAR BRAND: ");
        long valueVehicle = DataInput.getLongNumber("ENTER VALUE OF CAR: ");
        LocalDate registrationDate = DataInput.getDate("ENTER REGISTRATION DATE: ");
        String registrationPlace;

        char districtCode = licensePlate.charAt(2);

        switch (districtCode) {
            case 'X':
                registrationPlace = "Thu Duc";
                break;
            case 'S':
                registrationPlace = "Binh Thanh";
                break;
            case 'T':
                registrationPlace = "Quan 1";
                break;
            case 'V':
                registrationPlace = "Go Vap";
                break;
            case 'B':
                registrationPlace = "Binh Tan";
                break;
            case 'P':
                registrationPlace = "Phu Nhuan";
                break;
            case 'N':
                registrationPlace = "Quan 10";
                break;
            case 'M':
                registrationPlace = "Quan 11";
                break;
            case 'Q':
                registrationPlace = "Quan 3";
                break;
            case 'D':
                registrationPlace = "Quan 4";
                break;
            case 'H':
                registrationPlace = "Quan 5";
                break;
            case 'L':
                registrationPlace = "Quan 6";
                break;
            case 'E':
                registrationPlace = "Quan 7";
                break;
            case 'A':
                registrationPlace = "Quan 8";
                break;
            case 'C':
                registrationPlace = "Quan 12";
                break;
            case 'K':
                registrationPlace = "Tan Binh";
                break;
            case 'Y':
                registrationPlace = "Tan Phu";
                break;
            case 'U':
                registrationPlace = "Cu Chi";
                break;
            case 'O':
                registrationPlace = "Hoc Mon";
                break;
            case 'Z':
                registrationPlace = "Binh Chanh";
                break;
            case 'F':
                registrationPlace = "Nha Be";
                break;
            case 'G':
                registrationPlace = "Can Gio";
                break;
            default:
                registrationPlace = "Khong xac dinh";
                break;
        }
        System.out.println(registrationPlace);
        int numberOfSeat = DataInput.getIntegerNumber("ENTER NUMBER OF SEATS: ");
        return new Car(licensePlate, carOwner, phoneNumber, carBrand, valueVehicle, registrationDate, registrationPlace, numberOfSeat);

    }

    public void setNewCarInfo(Car car) throws Exception {

        String carOwner = DataInput.getString("ENTER NEW CAR OWNER: ");
        if (!carOwner.isEmpty()) {
            car.setCarOwner(carOwner);
        }

        String phoneNumber = DataInput.getString("ENTER NEW PHONE NUMBER: ");
        if (!phoneNumber.isEmpty()) {
            car.setPhoneNumber(phoneNumber);
        }

        int numberOfSeat = DataInput.getIntegerNumber("ENTER NEW NUMBER OF SEATS: ");
        car.setNumberOfSeat(numberOfSeat);

        String carBrand = DataInput.getString("ENTER NEW BRAND NAME: ");
        if (!carBrand.isEmpty()) {
            car.setCarBrand(carBrand);
        }

    }

    public void addNewCustomer() {
        try {
            Car car = inputCarInfomation();
            if (carDAO.getCarByLicensePlate(car.getLicensePlate()) != null) {
                System.out.println(">>THE CAR HAS ALREADY EXIST");
                return;
            }
            carDAO.addCar(car);
            System.out.println(">>THE CAR HAS ADDED SUCCESSFULLY");
        } catch (Exception e) {
            System.out.println(">>ERROR: " + e.getMessage());
        }
    }

    public void updateCar() throws Exception {
        try {
            String licensePlate = DataInput.getString("ENTER LICENSE PLATE:");
            Car car = carDAO.getCarByLicensePlate(licensePlate);
            if (car == null) {
                throw new Exception(">>THE CAR CAN NOT FOUND");
            }
            Car newCar = new Car(car);
            setNewCarInfo(newCar);
            carDAO.updateCar(newCar);
            System.out.println(">>THE CAR HAS UPDATED SUCCESSFULLY");
        } catch (Exception e) {
            throw new Exception(">>ERROR: " + e.getMessage());
        }
    }

    public void deleteCar() throws Exception {
        try {
            String licensePlate = DataInput.getString("ENTER LICENSE PLATE:");
            Car car = carDAO.getCarByLicensePlate(licensePlate);
            if (car == null) {
                throw new Exception(">>THE CAR CAN NOT FOUND");
            }
            printCarList(car);
            String s = DataInput.getString("ARE YOU SURE YOU WANT TO DELETE THIS REGISTRATION? (Y/N): ");
            switch (s) {
                case "Y":
                    carDAO.deleteCar(car);
                    System.out.println(">>THE CAR HAS DELETED SUCCESSFULLY");
                    break;
                case "N":
                    System.out.println("DELETE FAILED");
                    break;
            }
        } catch (Exception e) {
            throw new Exception(">>ERROR: " + e.getMessage());
        }
    }

    public void dislayCarList(List<Car> list) throws Exception {

        list.sort((Car c1, Car c2) -> c1.getCarOwner().toUpperCase().compareTo(c2.getCarOwner().toUpperCase()));
        System.out.println("===========================CAR LIST==========================");
        System.out.format("| %-15s | %-16s | %-16s | %-16s | %-16s | %-16s | %-20s|\n", "License plate", "Owner", "Phone",
                "Car Brand", "Value of vehicle", "Number of seat", "Registration date");
        for (Car car : list) {
            System.out.format("| %-15s | %-16s | %-16s | %-16s | %-16s | %-16s | %-20s|\n", car.getLicensePlate(), car.getCarOwner(),
                    car.getPhoneNumber(), car.getCarBrand(), car.getValueVehicle(),
                    car.getNumberOfSeat(), car.getRegistrationDate()
            );
        }
    }

    public void printCarList(Car car) {
        try {
            if (car == null) {
                throw new Exception(">>LIST CAR IS INVALID.");
            }
            System.out.println("-----------------------------------------------------");
            System.out.println("LICENSE PLATE     : " + car.getLicensePlate());
            System.out.println("OWNER            : " + car.getCarOwner());
            System.out.println("PHONE            : " + car.getPhoneNumber());
            System.out.println("CAR BRAND       : " + car.getCarBrand());
            System.out.printf("VALUE OF VEHICLE : %,d%n", car.getValueVehicle());
            System.out.println("NUMBER OF SEATS  : " + car.getNumberOfSeat());
            System.out.println("REGISTRATION DATE: " + car.getRegistrationDate());
            System.out.println("-----------------------------------------------------");
        } catch (Exception e) {
            System.out.println(">>ERROR:" + e.getMessage());
        }
    }

    public void showAll() throws Exception {
        dislayCarList(carDAO.getCar());
    }

    public void findCarByDate() throws Exception {
        LocalDate dateX = DataInput.getDate("ENTER DATE X: ");
        LocalDate dateY = DataInput.getDate("ENTER DATE Y: ");
        int dem = 0;
        List<Car> list = new ArrayList();
        try {
            list = carDAO.search(c -> c.getRegistrationDate().isAfter(dateX) && c.getRegistrationDate().isBefore(dateY));
            for (Car car : list) {
                dem++;
            }
            System.out.println(dem);
        } catch (Exception e) {
            throw new Exception("NO DATE FOUND");
        }
    }

    public Car findCarByLicensePlate() throws Exception {
        String licensePlate = DataInput.getString("ENTER LICENSE PLATE: ");
        Car car = null;
        try {
            car = carDAO.getCarByLicensePlate(licensePlate);
        } catch (Exception e) {
            throw new Exception("NO LICENSE PLATE FOUND");
        }
        return car;
    }

    public List<Car> findCarByCarOwner() throws Exception {
        String carOwner = DataInput.getString("ENTER CAR OWNER: ");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getCarOwner().toUpperCase().contains(carOwner.toUpperCase()));
            list.sort((Car c1, Car c2) -> Integer.compare(c1.getNumberOfSeat(), c2.getNumberOfSeat()));
        } catch (Exception e) {
            throw new Exception("NO CAR OWNER FOUND");
        }
        return list;
    }

    public List<Car> findCarByPhoneNumber() throws Exception {
        String phoneNumber = DataInput.getString("ENTER PHONE NUMBER: ");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getPhoneNumber().contains(phoneNumber));
            list.sort((Car c1, Car c2) -> c1.getPhoneNumber().compareTo(phoneNumber));
        } catch (Exception e) {
            throw new Exception("NO PHONE NUMBER FOUND");
        }
        return list;
    }

    public List<Car> findCarByCarBrand() throws Exception {
        String carBrand = DataInput.getString("ENTER CAR BRAND");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getCarBrand().toUpperCase().contains(carBrand.toUpperCase()));
            list.sort((Car c1, Car c2) -> c1.getRegistrationDate().compareTo(c2.getRegistrationDate()));
        } catch (Exception e) {
            throw new Exception("NO CAR BRAND FOUND");
        }
        return list;
    }

    public List<Car> findCarByValueVehicle1() throws Exception {
        long valueVehicle = DataInput.getLongNumber("ENTER VALUE VEHICLE: ");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getValueVehicle() == valueVehicle);
            list.sort((Car c1, Car c2) -> Long.compare(c1.getValueVehicle(), c1.getValueVehicle()));
        } catch (Exception e) {
            throw new Exception("NO VALUE VEHICLE FOUND");
        }
        return list;
    }

    public List<Car> findCarByValueVehicle2() throws Exception {
        long valueVehicleX = DataInput.getLongNumber("ENTER VALUE VEHICLE X: ");
        long valueVehicleY = DataInput.getLongNumber("ENTER VALUE VEHICLE Y: ");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getValueVehicle() >= valueVehicleX && c.getValueVehicle() <= valueVehicleY);
            list.sort((Car c1, Car c2) -> Long.compare(c1.getValueVehicle(), c2.getValueVehicle()));
        } catch (Exception e) {
            throw new Exception("NO VALUE VEHICLE FOUND");
        }
        return list;
    }

    public List<Car> findCarByRegistrationDate1() throws Exception {
        LocalDate date = DataInput.getDate("ENTER REGISTRATION DATE: ");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getRegistrationDate().equals(date));
            list.sort((Car c1, Car c2) -> Long.compare(c1.getValueVehicle(), c2.getValueVehicle()));
        } catch (Exception e) {
            throw new Exception("NO REGISTRATION DATE FOUND");
        }
        return list;
    }

    public List<Car> findCarByRegistrationDate2() throws Exception {
        LocalDate dateX = DataInput.getDate("ENTER REGISTRATION DATE X: ");
        LocalDate dateY = DataInput.getDate("ENTER REGISTRATIOWN DATE Y: ");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getRegistrationDate().isAfter(dateX) && c.getRegistrationDate().isBefore(dateY));
            list.sort((Car c1, Car c2) -> Long.compare(c1.getValueVehicle(), c2.getValueVehicle()));
        } catch (Exception e) {
            throw new Exception("NO REGISTRATION DATE FOUND");
        }
        return list;
    }

    public List<Car> findCarByCarOwner1() throws Exception {
        String carOwner = DataInput.getString("ENTER CAR OWNER: ");
        List<Car> list = new ArrayList<>();
        long sum = 0;
        try {
            list = carDAO.search(c -> c.getCarOwner().toUpperCase().contains(carOwner.toUpperCase()));
            for (Car car : list) {
                sum = sum + car.getValueVehicle();
            }
            System.out.println(sum);
        } catch (Exception e) {
            throw new Exception("NO CAR OWNER FOUND");
        }
        return list;
    }

    public List<Car> findCarByPhoneNumber1() throws Exception {
        String phoneNumber = DataInput.getString("ENTER PHONE NUMBER: ");
        List<Car> list = new ArrayList<>();
        int count = 0;
        try {
            list = carDAO.search(c -> c.getPhoneNumber().contains(phoneNumber));
            for (Car car : list) {
                count++;
            }
            System.out.println(count);
        } catch (Exception e) {
            System.out.println("NO PHONE NUMBER FOUND");
        }
        return list;
    }

    public List<Car> findCarByNameToUpdate() throws Exception {
        String ownerName = DataInput.getString("ENTER OWNER NAME: ");
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getCarOwner().toUpperCase().contains(ownerName.toUpperCase()));

            list.sort((Car c1, Car c2) -> {
                if (c1.getCarOwner().compareToIgnoreCase(c2.getCarOwner()) == 0) {
                    return c1.getCarOwner().compareTo(c2.getCarOwner());
                }
                return c1.getCarOwner().compareToIgnoreCase(c2.getCarOwner());
            });

            dislayCarList(list);
            for (Car car : list) {
                String newOwnerName = DataInput.getString("ENTER NEW OWNER NAME: ");
                car.setCarOwner(newOwnerName);
                System.out.println("UPDATE SUCCESSFULLY");
            }
        } catch (Exception e) {
            System.out.println("NO CAR OWNER NAME FOUND");
        }
        return list;
    }
    
    public void findCustomerByNameToDelete() throws Exception {
        String customerName = DataInput.getString("ENTER CUSTOMER NAME: ");
        List<Car> list = new ArrayList();
        int count = 0;
        try {
            list = carDAO.search(i -> i.getCarOwner().toUpperCase().contains(customerName.toUpperCase()));
            dislayCarList(list);
            for (Car car : list) {
                carDAO.deleteCar(car);
                count++;
            }
            System.out.println(count);
            System.out.println("DELETE SUCCESSFULLY");
        } catch (Exception e) {
            throw new Exception("NO CUSTOMER NAME FOUND");
        }
    }
    
    public List<Car> searchCarByDateAndSeats() throws Exception {
        int seats = DataInput.getIntegerNumber("ENTER SEATS: ");
        int count = 0;
        List<Car> list = new ArrayList<>();
        try {
            list = carDAO.search(c -> c.getNumberOfSeat() == seats);
            LocalDate date = DataInput.getDate("ENTER NEW REGISTION DATE: ");
            for (Car car : list) {
                car.setRegistrationDate(date);
                count++;
            }
            System.out.println("UPDATE SUCCESSFULLY");
            System.out.println(count);
        } catch (Exception e) {
            System.out.println("NO SEATS FOUND");
        }
        return list;
    }

    public void saveData() {
        try {
            carDAO.saveData();
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

}
