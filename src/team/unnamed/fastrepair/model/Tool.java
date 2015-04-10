package team.unnamed.fastrepair.model;

/**
 * Created by cfwloader on 4/6/15.
 */
public class Tool {

    private int id;

    private String toolType;

    private int numberOfAvailable;

    private int companyId;

    private int departmentId;

    private Company company;

    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public int getNumberOfAvailable() {
        return numberOfAvailable;
    }

    public void setNumberOfAvailable(int numberOfAvailable) {
        this.numberOfAvailable = numberOfAvailable;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", toolType='" + toolType + '\'' +
                ", numberOfAvailable=" + numberOfAvailable +
                ", companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", company=" + company +
                ", department=" + department +
                '}';
    }
}
