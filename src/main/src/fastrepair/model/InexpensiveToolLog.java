package fastrepair.model;

import java.util.Date;

/**
 * Created by cfwloader on 4/10/15.
 */
public class InexpensiveToolLog {

    private int id;

    private int employeeId;

    private int toolId;

    private int quantity;

    private String status;

    private Date logDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InexpensiveToolLog{");
        sb.append("id=").append(id);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", toolId=").append(toolId);
        sb.append(", quantity=").append(quantity);
        sb.append(", status='").append(status).append('\'');
        sb.append(", logDate=").append(logDate);
        sb.append('}');
        return sb.toString();
    }
}
