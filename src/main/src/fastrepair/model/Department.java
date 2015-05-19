package fastrepair.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by cfwloader on 4/6/15.
 */

/*
public enum Department {
    CONSTRUCTION_DEVICE(1), AUTOMOBILE(2), APPLIANCE(3), COMPUTER(4), SPECIALIST(5), ADMIN(6), HR(7), UNKNOWN(8);

    private int value;

    private Department(int value){
        this.value = value;
    }

    public int intValue(){
        return this.value;
    }

    public static Department valueOf(int rawValue){
        switch (rawValue){
            case 1 : return CONSTRUCTION_DEVICE;
            case 2 : return AUTOMOBILE;
            case 3 : return APPLIANCE;
            case 4 : return COMPUTER;
            case 5 : return SPECIALIST;
            case 6 : return ADMIN;
            case 7 : return HR;
            default: return UNKNOWN;
        }
    }
};
*/

@Entity
public class Department {

    private int id;

    //private int companyId;

    private String departmentType;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    */

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentType='" + departmentType + '\'' +
                '}';
    }
}

