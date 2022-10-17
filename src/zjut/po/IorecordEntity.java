package zjut.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "iorecord", schema = "tms")
public class IorecordEntity {
    private String id;
    private String toolId;
    private String applicantId;
    private String authenticateId;
    private Byte io;
    private String deptId;
    private String result;
    private Date submitTime;
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
    @Column(name = "toolId")
    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    @Basic
    @Column(name = "applicantId")
    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    @Basic
    @Column(name = "authenticateId")
    public String getAuthenticateId() {
        return authenticateId;
    }

    public void setAuthenticateId(String authenticateId) {
        this.authenticateId = authenticateId;
    }

    @Basic
    @Column(name = "IO")
    public Byte getIo() {
        return io;
    }

    public void setIo(Byte io) {
        this.io = io;
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
        IorecordEntity that = (IorecordEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(toolId, that.toolId) &&
                Objects.equals(applicantId, that.applicantId) &&
                Objects.equals(authenticateId, that.authenticateId) &&
                Objects.equals(io, that.io) &&
                Objects.equals(deptId, that.deptId) &&
                Objects.equals(result, that.result) &&
                Objects.equals(submitTime, that.submitTime) &&
                Objects.equals(overTime, that.overTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, toolId, applicantId, authenticateId, io, deptId, result, submitTime, overTime);
    }
}
