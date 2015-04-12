package team.unnamed.fastrepair.model;

import java.util.Date;

/**
 * Created by cfwloader on 4/10/15.
 */
public class ExpensiveToolLog {

    private int id;

    private int employeeId;

    private int toolId;

    private int quantity;

    private String status;

    private Date lendDate;

    private Date backDate;

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

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExpensiveToolLog{");
        sb.append("id=").append(id);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", toolId=").append(toolId);
        sb.append(", quantity=").append(quantity);
        sb.append(", status='").append(status).append('\'');
        sb.append(", lendDate=").append(lendDate);
        sb.append(", backDate=").append(backDate);
        sb.append('}');
        return sb.toString();
    }
}
