package zjut.action;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import zjut.po.IorecordEntity;
import zjut.po.RepairrecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;
import zjut.service.IRepairService;
import zjut.service.IStaffService;
import zjut.service.IToolService;
import zjut.service.ToolService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepairAction {
    private RepairrecordEntity entity;
    private IRepairService repairService = null;
    private IStaffService staffService = null;
    private IToolService toolService = null;
    private String password;
    private List list;
    private List waitList;
    private List finishList;
    private String status;
    private String repairid;
    private File upload;
    private String uploadFileName;
    public String apply()  {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity) session.getAttribute("user");
        entity.setSubmitTime(new Date());
        entity.setApplicantId(staff.getId());
        entity.setDeptId(staff.getDeptId());
        entity.setResult("wait");
        try{
            if(upload != null) {
                String path = session.getServletContext().getRealPath("/");
                File diskFile = new File(path + "/repairPicture/" + uploadFileName);
                System.out.println(upload);
                System.out.println(path);
                System.out.println(diskFile);
                FileUtils.copyFile(upload, diskFile);
                entity.setImg("repairPicture/" + uploadFileName);
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "false";
        }
        if (repairService.apply(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String examine() {
        entity.setOverTime(new Date());
        if (repairService.examine(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String delete() {
        if (repairService.delete(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String checkRepair() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");
        ArrayList<RepairrecordEntity> res = (ArrayList<RepairrecordEntity>)repairService.FindByStaff(staff);
        System.out.println();
        if(staff.getAuthority() == 1) {
            list = new ArrayList<RepairrecordEntity>();
            for (int i = 0; i < res.size(); i++) {
                RepairrecordEntity x = res.get(i);
                ToolEntity y = toolService.findById(x.getToolId());
                StaffEntity z = staffService.FindById(x.getApplicantId());
                x.setToolId(y.getName());
                x.setApplicantId(z.getName());
                list.add(x);
            }
            return "o1";
        }
        else {
            finishList = new ArrayList<RepairrecordEntity>();
            waitList = new ArrayList<RepairrecordEntity>();
            for (int i = 0; i < res.size(); i++) {
                RepairrecordEntity x = res.get(i);
                ToolEntity y = toolService.findById(x.getToolId());
                x.setToolId(y.getName());
                StaffEntity z = staffService.FindById(x.getApplicantId());
                x.setApplicantId(z.getName());
                if(x.getHandler() != null) {
                    z = staffService.FindById(x.getHandler());
                    x.setHandler(z.getName());
                }
                if(x.getResult().equals("wait")) {
                    waitList.add(x);
                }
                else {
                    finishList.add(x);
                }
            }
            System.out.println(waitList.size() + "  " + finishList.size());
            return "o0";
        }
    }

    public String examineYes() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        RepairrecordEntity repairrecordEntity=repairService.FindById(repairid);

        repairrecordEntity.setHandler(staff.getId());
        repairrecordEntity.setResult(status);
        repairrecordEntity.setOverTime(new Date());
        if (repairService.examine(repairrecordEntity)) {
            ToolEntity toolEntity = toolService.findById(repairrecordEntity.getToolId());
            toolEntity.setStatus("OK");
            toolService.modifyTool(toolEntity);
            return "success";

        } else {
            return "false";
        }
    }

    public String examineNo() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");
        RepairrecordEntity repairrecordEntity=repairService.FindById(repairid);
        repairrecordEntity.setHandler(staff.getId());
        repairrecordEntity.setResult(status);
        repairrecordEntity.setOverTime(new Date());
        if (repairService.examine(repairrecordEntity)) {
            return "success";
        } else {
            return "false";
        }
    }



    public RepairrecordEntity getEntity() {
        return entity;
    }

    public void setEntity(RepairrecordEntity entity) {
        this.entity = entity;
    }

    public void setRepairService(IRepairService repairService) {
        this.repairService = repairService;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }

    public void setToolService(IToolService toolService) {
        this.toolService = toolService;
    }

    public List getWaitList() {
        return waitList;
    }

    public void setWaitList(List waitList) {
        this.waitList = waitList;
    }

    public List getFinishList() {
        return finishList;
    }

    public void setFinishList(List finishList) {
        this.finishList = finishList;
    }

    public String getRepairid() {
        return repairid;
    }

    public void setRepairid(String repairid) {
        this.repairid = repairid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}