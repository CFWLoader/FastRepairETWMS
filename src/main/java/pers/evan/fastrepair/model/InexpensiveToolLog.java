package pers.evan.fastrepair.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by cfwloader on 4/10/15.
 */
@Entity
public class InexpensiveToolLog extends ToolLog {

    /*
    @Id
    @GeneratedValue
    private long id;

    private int employeeId;

    private int toolId;

    private int quantity;

    private String status;
    */

    private Date logDate;

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /*
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    */
}
