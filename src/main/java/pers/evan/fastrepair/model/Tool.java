package pers.evan.fastrepair.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by cfwloader on 4/6/15.
 */
@Entity
public class Tool {

    @Id
    @GeneratedValue
    private int id;

    private String toolName;

    private boolean isExpensive;

    private int numberOfAvailable;

    private int companyId;

    private int departmentId;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public boolean isExpensive() {
        return isExpensive;
    }

    public void setIsExpensive(boolean isExpensive) {
        this.isExpensive = isExpensive;
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
        final StringBuilder sb = new StringBuilder("Tool{");
        sb.append("id=").append(id);
        sb.append(", toolName='").append(toolName).append('\'');
        sb.append(", isExpensive=").append(isExpensive);
        sb.append(", numberOfAvailable=").append(numberOfAvailable);
        sb.append(", companyId=").append(companyId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", company=").append(company);
        sb.append(", department=").append(department);
        sb.append('}');
        return sb.toString();
    }
}
