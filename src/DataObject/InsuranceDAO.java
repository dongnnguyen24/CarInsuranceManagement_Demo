/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataObject;

import Core.Entities.Insurance;
import Core.Interfaces.IInsuranceDAO;
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
public class InsuranceDAO implements IInsuranceDAO {

    private final List<Insurance> insuranceList = new ArrayList();
    private final FileManager fileManager;

    public InsuranceDAO(String fileName) throws Exception {
        this.fileManager = new FileManager(fileName);
        loadData();
    }

    @Override
    public List<Insurance> getInsurance() throws Exception {
        Collections.sort(insuranceList, (e1, e2) -> e1.getInsuranceID().compareToIgnoreCase(e2.getInsuranceID()));
        return insuranceList;
    }

    @Override
    public List<Insurance> search(Predicate<Insurance> predicate) throws Exception {
        return insuranceList.stream().filter(customer -> predicate.test(customer)).collect(Collectors.toList());
    }

    @Override
    public Insurance getInsuranceByID(String id) throws Exception {
        Insurance insurance = insuranceList.stream().filter(c -> c.getInsuranceID().equalsIgnoreCase(id)).findAny().orElse(null);
        return insurance;
    }

    @Override
    public void addInsurance(Insurance insurance) throws Exception {
        if (getInsuranceByID(insurance.getInsuranceID()) != null) {
            throw new Exception(">>THE INSURANCE ALREADY EXIST");
        }
        insuranceList.add(insurance);
    }

    @Override
    public void updateInsurance(Insurance insurance) throws Exception {
        Insurance i = getInsuranceByID(insurance.getInsuranceID());
        if (i != null) {
            i.setInsuranceOwner(insurance.getInsuranceOwner());
        }
    }

    @Override
    public void deleteInsurance(Insurance insurance) throws Exception {
        insuranceList.remove(insurance);
    }

    @Override
    public void loadData() throws Exception {
        try {
            insuranceList.clear();
            List<String> data = fileManager.readDataFromFile();
            for (String string : data) {
                List<String> insuranceData = Arrays.asList(string.split(","));

                Insurance insurance = new Insurance(insuranceData.get(0), insuranceData.get(1), LocalDate.parse(insuranceData.get(2)),
                         Integer.parseInt(insuranceData.get(3)), Integer.parseInt(insuranceData.get(4)), insuranceData.get(5));
                addInsurance(insurance);
            }
        } catch (Exception e) {
            throw new Exception(">>CAN NOT LOAD DATA FROM FILE");
        }
    }

    @Override
    public void saveData() throws Exception {
        String data = "";
        data = String.join("\n", insuranceList.stream().map(String::valueOf).collect(Collectors.toList()));
        fileManager.writeDataToFile(data);
    }

    @Override
    public Insurance getInsuranceByLisencePlate(String lisencePlate) throws Exception {
        Insurance insurance = insuranceList.stream().filter(c -> c.getLisencePlate().equalsIgnoreCase(lisencePlate)).findAny().orElse(null);
        return insurance;
    }

}
