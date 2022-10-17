package zjut.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "buyrecord", schema = "tms")
public class BuyrecordEntity {
    private String id;
    private String staffId;
    private String toolTypeId;
    private Integer quantity;
    private String handlerId;
    private String finalId;
    private String deptId;
    private String result;
    private Date submitTime;
    private Date handleTime;
    private Date overTime;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length = 32, name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "staffId")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "toolTypeId")
    public String getToolTypeId() {
        return toolTypeId;
    }

    public void setToolTypeId(String toolTypeId) {
        this.toolTypeId = toolTypeId;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "handlerId")
    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    @Basic
    @Column(name = "finalId")
    public String getFinalId() {
        return finalId;
    }

    public void setFinalId(String finalId) {
        this.finalId = finalId;
    }

    @Basic
    @Column(name = "dept_id")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "submit_time")
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "handle_time")
    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    @Basic
    @Column(name = "over_time")
    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyrecordEntity that = (BuyrecordEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(staffId, that.staffId) &&
                Objects.equals(toolTypeId, that.toolTypeId) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(handlerId, that.handlerId) &&
                Objects.equals(finalId, that.finalId) &&
                Objects.equals(deptId, that.deptId) &&
                Objects.equals(result, that.result) &&
                Objects.equals(submitTime, that.submitTime) &&
                Objects.equals(handleTime, that.handleTime) &&
                Objects.equals(overTime, that.overTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, staffId, toolTypeId, quantity, handlerId, finalId, deptId, result, submitTime, handleTime, overTime);
    }
}
