package entitites;

import enumeration.WorkerLevemEnum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static java.util.Calendar.*;

public class WorkerEntity {

    private String name;
    private WorkerLevemEnum level;
    private Double baseSalary;
    //ASSOCIAÇÕES
    private DepartmentEntity department = new DepartmentEntity();
    private List<HourContractEntity> contracts = new ArrayList<>();

    public WorkerEntity() {
    }

    public WorkerEntity(String name, WorkerLevemEnum level, Double baseSalary, DepartmentEntity department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevemEnum getLevel() {
        return level;
    }

    public void setLevel(WorkerLevemEnum level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public List<HourContractEntity> getContracts() {
        return contracts;
    }

    public void addContract(HourContractEntity contract) {
        contracts.add(contract);
    }

    public void removeContract(HourContractEntity contract) {
        contracts.remove(contract);
    }

    public double income(Integer year, Integer month) {
        double sum = baseSalary;
        Calendar calendar = getInstance();

        for (HourContractEntity contract : contracts) {
            calendar.setTime(contract.getDate());
            int contractYear = calendar.get(YEAR);
            int contractMonth = 1 + calendar.get(MONTH);
            if (contractYear == year && contractMonth == month) {
                sum += contract.totalValue();
            }
        }

        return sum;
    }
}
