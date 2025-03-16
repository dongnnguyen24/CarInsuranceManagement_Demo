/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Interfaces;

import Core.Entities.Car;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Lukass
 */
public interface ICarDAO {

    List<Car> getCar() throws Exception;

    List<Car> search(Predicate<Car> predicate) throws Exception;

    Car getCarByLicensePlate(String licensePlate) throws Exception;

    void addCar(Car car) throws Exception;

    void updateCar(Car car) throws Exception;

    void deleteCar(Car car) throws Exception;

    void loadData() throws Exception;

    void saveData() throws Exception;

}
