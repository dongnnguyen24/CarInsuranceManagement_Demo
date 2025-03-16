/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataObject;

import Core.Entities.Car;
import Core.Interfaces.ICarDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Lukass
 */
    public class CarDAO implements ICarDAO {
    
    private final List<Car> carList = new ArrayList();
    private final FileManager fileManager;

    public CarDAO(String fileName) throws Exception {
        this.fileManager = new FileManager(fileName);
        loadData();
    }
    
    @Override
    public List<Car> getCar() throws Exception {
        Collections.sort(carList, (e1, e2) -> e1.getLicensePlate().compareTo(e2.getLicensePlate()));
        return carList;
    }

    @Override
    public Car getCarByLicensePlate(String licensePlate) throws Exception {
        Car car = carList.stream().filter(c -> c.getLicensePlate().equalsIgnoreCase(licensePlate)).findAny().orElse(null);
        return car;
    }

    @Override
    public void addCar(Car car) throws Exception {
        if (getCarByLicensePlate(car.getLicensePlate()) != null) {
            throw new Exception(">>THE CAR ALREADY EXIST");
        }
        carList.add(car);
    }

    @Override
    public void updateCar(Car car) throws Exception {
        Car c = getCarByLicensePlate(car.getLicensePlate());
        if (c != null) {
            c.setCarOwner(car.getCarOwner());
            c.setPhoneNumber(car.getPhoneNumber());
            c.setNumberOfSeat(car.getNumberOfSeat());
            c.setCarBrand(car.getCarBrand());
        }
    }

    @Override
    public List<Car> search(Predicate<Car> predicate) throws Exception {
        return carList.stream().filter(car -> predicate.test(car)).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Car car) throws Exception {
        carList.remove(car);
    }

    @Override
    public void loadData() throws Exception {
        try {
            carList.clear();
            List<String> data = fileManager.readDataFromFile();
            for (String string : data) {
                List<String> carData = Arrays.asList(string.split(","));

                Car car = new Car(carData.get(0), carData.get(1), carData.get(2), carData.get(3), Long.parseLong(carData.get(4)), 
                        LocalDate.parse(carData.get(5)), carData.get(6), Integer.parseInt(carData.get(7)));
                addCar(car);
            }
        } catch (Exception e) {
            throw new Exception(">>CAN NOT LOAD DATA FROM FILE");
        }
    }

    @Override
    public void saveData() throws Exception {
        String data = "";
        data = String.join("\n", carList.stream().map(String::valueOf).collect(Collectors.toList()));
        fileManager.writeDataToFile(data);
    }
    
    
}
