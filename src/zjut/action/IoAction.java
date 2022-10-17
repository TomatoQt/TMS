package zjut.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import zjut.po.IorecordEntity;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;
import zjut.service.IIoService;
import zjut.service.IStaffService;
import zjut.service.IToolService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IoAction extends ActionSupport {
    private IorecordEntity entity = null;
    private String ToolTypeId;
    private IIoService ioService = null;
    private IStaffService staffService = null;
    private IToolService toolService = null;
    private String password;
    private List list;
    private List finishList;
    private List waitList;
    private int quantity;
    private String status;
    private String ioid;
    private List UnReturnToolList;
    private String optionId;//获取还工夹具时选中的工夹具id

    //入库 IO=1
    public String applyIn() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        IorecordEntity iorecord = new IorecordEntity();

        ToolEntity toolEntity = toolService.findById(optionId);

        iorecord.setDeptId(staff.getDeptId());
        iorecord.setSubmitTime(new Date());
        iorecord.setDeptId(staff.getDeptId());
        iorecord.setApplicantId(staff.getId());
        iorecord.setToolId(toolEntity.getId());
        iorecord.setIo((byte) 1);
        iorecord.setResult("pass1");
        if (ioService.apply(iorecord)) {
            toolEntity.setStatus("OK");
            toolService.modifyTool(toolEntity);
            return "success";
        } else {
            return "false";
        }
    }

    //查看有几个工夹具没有还
    public String lookReturnNumber(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");
        ArrayList<IorecordEntity> res = (ArrayList<IorecordEntity> )ioService.FindByStaffAndIo(staff, 0);

        UnReturnToolList = new ArrayList<>();

        for (IorecordEntity iorecord : res){
            //TODO 更新ToolEntity status状态位后要修改
            if (toolService.findById(iorecord.getToolId()).getStatus().equals("OUT")&&iorecord.getResult().equals("pass1")){//意思就是正在申请出库
                if (!UnReturnToolList.contains(toolService.findById(iorecord.getToolId())))//加入的就不要再加了
                    UnReturnToolList.add(toolService.findById(iorecord.getToolId()));
            }
        }

        return "success";
    }

//    public void validateApplyOut(){
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpSession session = request.getSession();
//        StaffEntity staff = (StaffEntity)session.getAttribute("user");
//
//        if (!password.equals(staff.getPassword())){
//            System.out.println("validateApplyOut");
//            this.addFieldError("password","请输入正确的用户密码!");
//        }
//    }

    //出库 IO=0
    public String applyOut() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        //根据排序函数获取最优的type_id=ToolTypeId的空闲的工夹具

        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        try {
            for (int i=0;i<quantity;i++){
                System.out.println("test applyOut");
                IorecordEntity iorecordEntity = new IorecordEntity();
                String toolId=ioService.getATool(ToolTypeId,staff.getDeptId());
                ToolEntity toolEntity = toolService.findById(toolId);
                toolEntity.setStatus("OUT");//出库了
                toolService.modifyTool(toolEntity);//更新tool状态
                iorecordEntity.setToolId(toolId);
                iorecordEntity.setSubmitTime(new Date());
                iorecordEntity.setApplicantId(staff.getId());
                iorecordEntity.setDeptId(staff.getDeptId());
                iorecordEntity.setIo((byte) 0);
                iorecordEntity.setResult("wait");
                ioService.apply(iorecordEntity);
                System.out.println("iorecord++");
            }
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "false";
        }

//        entity.setToolId(ioService.getATool(ToolTypeId));

//        entity.setSubmitTime(new Date());
//        entity.setApplicantId(staff.getId());
//        entity.setIo((byte) 0);
//        entity.setResult("wait");
//        if (ioService.apply(entity)) {
//            return "success";
//        } else {
//            return "false";
//        }
    }

    public String CheckBorrow() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        ArrayList<IorecordEntity> res = (ArrayList<IorecordEntity> )ioService.FindByStaffAndIo(staff, 0);
        if(staff.getAuthority() == 1) {
            list = new ArrayList<IorecordEntity>();
            for (int i = 0; i < res.size(); i++) {
                IorecordEntity x = res.get(i);
                StaffEntity y = staffService.FindById(x.getApplicantId());
                ToolEntity z = toolService.findById(x.getToolId());
                x.setApplicantId(y.getName());
                x.setToolId(z.getName());
                list.add(x);
            }
            return "o1";
        }
        else if(staff.getAuthority() == 0) {
            finishList = new ArrayList<IorecordEntity>();
            waitList = new ArrayList<IorecordEntity>();
            for(int i = 0; i < res.size(); i++) {
                IorecordEntity x = res.get(i);
                StaffEntity y = staffService.FindById(x.getApplicantId());
                x.setApplicantId(y.getName());
                ToolEntity z = toolService.findById(x.getToolId());
                x.setToolId(z.getName());
                if(x.getAuthenticateId() != null) {
                    y = staffService.FindById(x.getAuthenticateId());
                    x.setAuthenticateId(y.getName());
                }
                if(res.get(i).getResult().equals("wait")) {
                    waitList.add(x);
                }
                else {
                   finishList.add(x);
                }
            }
            System.out.println("size of waitList:"+waitList.size());
            System.out.println("size of waitList:"+finishList.size());
            return "o0";
        }
        return "success";//没有设置success的返回页面
    }

    //IO初审通过
    public String examineYes() {
        System.out.println("execute examine");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        IorecordEntity iorecordEntity=ioService.findByIOID(ioid);

        iorecordEntity.setAuthenticateId(staff.getId());
        iorecordEntity.setResult(status);
        iorecordEntity.setOverTime(new Date());
        if (ioService.examine(iorecordEntity)) {
            return "success";
        } else {
            return "false";
        }
    }

    //IO初审不通过
    public String examineNo() {
        System.out.println("execute examine");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        IorecordEntity iorecordEntity=ioService.findByIOID(ioid);

        iorecordEntity.setAuthenticateId(staff.getId());
        iorecordEntity.setResult(status);
        iorecordEntity.setOverTime(new Date());
        if (ioService.examine(iorecordEntity)) {
            ToolEntity toolEntity=toolService.findById(iorecordEntity.getToolId());
            toolEntity.setStatus("OK");//不通过直接回库
            toolService.modifyTool(toolEntity);
            return "success";
        } else {
            return "false";
        }
    }

    public String delete() {
        if (ioService.delete(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    public IorecordEntity getEntity() {
        return entity;
    }

    public void setEntity(IorecordEntity entity) {
        this.entity = entity;
    }

    public void setIoService(IIoService ioService) {
        this.ioService = ioService;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToolTypeId() {
        return ToolTypeId;
    }

    public void setToolTypeId(String toolTypeId) {
        ToolTypeId = toolTypeId;
    }

    public void setToolService(IToolService toolService) {
        this.toolService = toolService;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIoid() {
        return ioid;
    }

    public void setIoid(String ioid) {
        this.ioid = ioid;
    }

    public List getUnReturnToolList() {
        return UnReturnToolList;
    }

    public void setUnReturnToolList(List unReturnToolList) {
        UnReturnToolList = unReturnToolList;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }
}
