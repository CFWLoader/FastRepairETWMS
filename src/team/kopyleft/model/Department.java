package team.kopyleft.model;

/**
 * Created by cfwloader on 4/6/15.
 */

enum DepartmentType {
    CONSTRUCTION_DEVICE(1), AUTOMOBILE(2), APPLIANCE(3), COMPUTER(4), SPECIALIST(5), ADMIN(6), UNKNOWN(7);

    private int value;

    private DepartmentType(int value){
        this.value = value;
    }

    public int intValue(){
        return this.value;
    }

    public static DepartmentType valueOf(int rawValue){
        switch (rawValue){
            case 1 : return CONSTRUCTION_DEVICE;
            case 2 : return AUTOMOBILE;
            case 3 : return APPLIANCE;
            case 4 : return COMPUTER;
            case 5 : return SPECIALIST;
            case 6 : return ADMIN;
            default: return UNKNOWN;
        }
    }
};

public class Department {

    private int id;

    private int companyId;

    private DepartmentType departmentType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }
}
