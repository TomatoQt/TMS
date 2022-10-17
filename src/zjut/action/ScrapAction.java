package zjut.action;

import org.apache.struts2.ServletActionContext;
import zjut.po.BuyrecordEntity;
import zjut.po.ScraprecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;
import zjut.service.IScrapService;
import zjut.service.IStaffService;
import zjut.service.IToolService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScrapAction {
    private ScraprecordEntity entity;
    private IScrapService scrapService = null;
    private IToolService toolService = null;
    private IStaffService staffService = null;
    private List finishList;
    private List waitList;
    private List list;
    private String scrapid;

    //申请报废
    public String apply() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity) session.getAttribute("user");
        entity.setApplicantId(staff.getId());
        entity.setSubmitTime(new Date());
        entity.setDeptId(staff.getDeptId());
        System.out.println(entity.getToolId());
        ToolEntity tool = toolService.findById(entity.getToolId());

//        TODO 时间计算
        tool.getLife();
//        entity.setRestLife(tool.getLife());
        entity.setResult("wait");
        if (scrapService.apply(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String examineOK() {//Supervisor
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity) session.getAttribute("user");

        ScraprecordEntity scraprecord = scrapService.FindById(scrapid);
        scraprecord.setHandleTime(new Date());
        scraprecord.setHandlerId(staff.getId());
        scraprecord.setResult("pass1");
        if (scrapService.examine(scraprecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String examineNO() {//Supervisor
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity) session.getAttribute("user");

        ScraprecordEntity scraprecord =  scrapService.FindById(scrapid);
        scraprecord.setHandleTime(new Date());
        scraprecord.setHandlerId(staff.getId());
        scraprecord.setResult("fail");
        if (scrapService.examine(scraprecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String finalExamineOK() {//Manager
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity) session.getAttribute("user");
        ScraprecordEntity scraprecord = scrapService.FindById(scrapid);
        scraprecord.setOverTime(new Date());
        scraprecord.setFinalId(staff.getId());
        ToolEntity tool = toolService.findById(scraprecord.getToolId());
        //改工具为不可用
        tool.setStatus("scrap");
        scraprecord.setResult("pass2");

        toolService.modifyTool(tool);
        if (scrapService.final_examine(scraprecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String finalExamineNO() {//Manager
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity) session.getAttribute("user");
        ScraprecordEntity scraprecord = scrapService.FindById(scrapid);
        scraprecord.setOverTime(new Date());
        scraprecord.setFinalId(staff.getId());
        scraprecord.setResult("fail2");
        if (scrapService.final_examine(scraprecord)) {
            return "success";
        } else {
            return "false";
        }

    }

    //查看报废申请
    public String checkScrap() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");
        ArrayList<ScraprecordEntity> scraprecord = (ArrayList<ScraprecordEntity>)scrapService.FindByStaff(staff);
        if(staff.getAuthority() == 1) {//员工II申请者
            waitList=new ArrayList<BuyrecordEntity>();
            finishList=new ArrayList<BuyrecordEntity>();
            for(int i = 0; i < scraprecord.size(); i++) {
                ScraprecordEntity x = scraprecord.get(i);
                x.setApplicantId(staffService.FindById(x.getApplicantId()).getName());
                x.setToolId(toolService.findById(x.getToolId()).getName());
                StaffEntity y = staffService.FindById(x.getHandlerId());
                if(y != null) {
                    x.setHandlerId(y.getName());
                }
                y = staffService.FindById(x.getFinalId());
                if(y != null) {
                    x.setFinalId(y.getName());
                }
                if(x.getResult().equals("pass1") || x.getResult().equals("wait")) {
                    waitList.add(x);
                }
                else {
                    finishList.add(x);
                }
            }
//            System.out.println(222 + waitList.size() + " " + finishList.size());
            return "o1";
        }
        else if(staff.getAuthority() == 2) {
            list = new ArrayList<ScraprecordEntity>();
            for(int i = 0; i < scraprecord.size(); i++) {
                ScraprecordEntity x = scraprecord.get(i);
                x.setApplicantId(staffService.FindById(x.getApplicantId()).getName());
                x.setToolId(toolService.findById(x.getToolId()).getName());
                StaffEntity y = staffService.FindById(x.getHandlerId());
                if(y != null) {
                    x.setHandlerId(y.getName());
                }
                y = staffService.FindById(x.getFinalId());
                if(y != null) {
                    x.setFinalId(y.getName());
                }
                list.add(x);
            }
            return "super";
        }
        else if(staff.getAuthority() == 3) {
            list = new ArrayList<ScraprecordEntity>();
            for(int i = 0; i < scraprecord.size(); i++) {
                ScraprecordEntity x = scraprecord.get(i);
                x.setApplicantId(staffService.FindById(x.getApplicantId()).getName());
                x.setToolId(toolService.findById(x.getToolId()).getName());
                StaffEntity y = staffService.FindById(x.getHandlerId());
                if(y != null) {
                    x.setHandlerId(y.getName());
                }
                y = staffService.FindById(x.getFinalId());
                if(y != null) {
                    x.setFinalId(y.getName());
                }
                list.add(x);
            }
            return "manager";
        }

        return "false";
    }


    public String delete() {
        if (scrapService.delete(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    public ScraprecordEntity getEntity() {
        return entity;
    }

    public void setEntity(ScraprecordEntity entity) {
        this.entity = entity;
    }

    public void setScrapService(IScrapService scrapService) {
        this.scrapService = scrapService;
    }

    public List getFinishList() {
        return finishList;
    }

    public void setFinishList(List finishList) {
        this.finishList = finishList;
    }

    public List getWaitList() {
        return waitList;
    }

    public void setWaitList(List waitList) {
        this.waitList = waitList;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getScrapid() {
        return scrapid;
    }

    public void setScrapid(String scrapid) {
        this.scrapid = scrapid;
    }

    public void setToolService(IToolService toolService) {
        this.toolService = toolService;
    }

    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }
}