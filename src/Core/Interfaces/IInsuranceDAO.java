/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Interfaces;

import Core.Entities.Insurance;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Lukass
 */
public interface IInsuranceDAO {

    List<Insurance> getInsurance() throws Exception;

    List<Insurance> search(Predicate<Insurance> predicate) throws Exception;

    Insurance getInsuranceByID(String id) throws Exception;
    
    Insurance getInsuranceByLisencePlate(String lisencePlate) throws Exception;

    void addInsurance(Insurance insurance) throws Exception;

    void updateInsurance(Insurance insurance) throws Exception;

    void deleteInsurance(Insurance insurance) throws Exception;

    void loadData() throws Exception;

    void saveData() throws Exception;

}
