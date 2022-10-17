package zjut.action;

import org.apache.struts2.ServletActionContext;
import zjut.po.BuyrecordEntity;
import zjut.po.StaffEntity;
import zjut.service.IBuyService;
import zjut.service.IStaffService;
import zjut.service.IToolService;
import zjut.service.IToolTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyAction {
    private BuyrecordEntity entity;
    private IBuyService buyService = null;
    private IToolService toolService = null;
    private IToolTypeService toolTypeService = null;
    private IStaffService staffService = null;
    private String ToolTypeId;//采购时选择的工具类型
    private int quantity;//采购数量
    private List finishList;
    private List waitList;
    private List list;
    private String buyid;

    //申请采购
    public String apply() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        BuyrecordEntity buyrecord = new BuyrecordEntity();

        buyrecord.setStaffId(staff.getId());
        buyrecord.setSubmitTime(new Date());
        buyrecord.setToolTypeId(ToolTypeId);
        buyrecord.setQuantity(quantity);
        buyrecord.setDeptId(staff.getDeptId());
        buyrecord.setResult("wait");
        if (buyService.apply(buyrecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String examineOK() {//Supervisor
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        BuyrecordEntity buyrecord = buyService.FindById(buyid);
        buyrecord.setHandleTime(new Date());
        buyrecord.setHandlerId(staff.getId());
        buyrecord.setResult("pass1");
        if (buyService.examine(buyrecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String examineNO() {//Supervisor
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        BuyrecordEntity buyrecord = buyService.FindById(buyid);

        buyrecord.setHandleTime(new Date());
        buyrecord.setHandlerId(staff.getId());
        buyrecord.setResult("fail1");//卡在supervisor
        if (buyService.examine(buyrecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String finalExamineOK() {//Manager
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        BuyrecordEntity buyrecord = buyService.FindById(buyid);

        buyrecord.setResult("pass2");//通过Manager
        buyrecord.setFinalId(staff.getId());
        buyrecord.setOverTime(new Date());

        if (buyService.buyTools(buyrecord)&&buyService.final_examine(buyrecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String finalExamineNO() {//Manager
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        BuyrecordEntity buyrecord = buyService.FindById(buyid);
        buyrecord.setFinalId(staff.getId());
        buyrecord.setOverTime(new Date());
        buyrecord.setResult("fail2");//卡在Manager
        if (buyService.final_examine(buyrecord)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String delete() {
        if (buyService.delete(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    //查看采购申请
    public String checkBuy(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        ArrayList<BuyrecordEntity> buyrecord = (ArrayList<BuyrecordEntity>)buyService.FindByStaff(staff);

        if (staff.getAuthority()==1){//申请者II级员工
            waitList=new ArrayList<BuyrecordEntity>();
            finishList=new ArrayList<BuyrecordEntity>();
            for (int i=0;i<buyrecord.size();i++){
                BuyrecordEntity x = buyrecord.get(i);
                x.setToolTypeId(toolTypeService.findByToolTypeId(x.getToolTypeId()).getName());
                x.setStaffId(staffService.FindById(x.getStaffId()).getName());
                if (x.getResult().equals("wait")){
                    waitList.add(x);
                }else if (x.getResult().equals("pass1")){
                    x.setHandlerId(staffService.FindById(x.getHandlerId()).getName());
                    waitList.add(x);
                }else if(x.getResult().equals("pass2")){//pass2
                    x.setHandlerId(staffService.FindById(x.getHandlerId()).getName());
                    x.setFinalId(staffService.FindById(x.getFinalId()).getName());
                    finishList.add(x);
                }else if (x.getResult().equals("fail1")){
                    x.setHandlerId(staffService.FindById(x.getHandlerId()).getName());
                    finishList.add(x);
                }else if (x.getResult().equals("fail2")){
                    x.setHandlerId(staffService.FindById(x.getHandlerId()).getName());
                    x.setFinalId(staffService.FindById(x.getFinalId()).getName());
                    finishList.add(x);
                }
            }
            return "o1";
        }else if (staff.getAuthority()==2) {//初审者 Supervisor
            list=new ArrayList<BuyrecordEntity>();
            for (int i=0;i<buyrecord.size();i++){
                BuyrecordEntity x = buyrecord.get(i);
                if (x.getResult().equals("wait")){//已提交
                    x.setStaffId(staffService.FindById(x.getStaffId()).getName());
                    x.setToolTypeId(toolTypeService.findByToolTypeId(x.getToolTypeId()).getName());
                    list.add(x);
                }
            }
            return "o2";
        }else if (staff.getAuthority()==3){//终审者 Manager
            list=new ArrayList<BuyrecordEntity>();
            for (int i=0;i<buyrecord.size();i++){
                BuyrecordEntity x = buyrecord.get(i);
                if (x.getResult().equals("pass1")){//通过初审
                    list.add(x);
                }
            }
            return "o3";
        }

        return "false";
    }

    public BuyrecordEntity getEntity() {
        return entity;
    }

    public void setEntity(BuyrecordEntity entity) {
        this.entity = entity;
    }

    public void setBuyService(IBuyService buyService) {
        this.buyService = buyService;
    }

    public String getToolTypeId() {
        return ToolTypeId;
    }

    public void setToolTypeId(String toolTypeId) {
        ToolTypeId = toolTypeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getBuyid() {
        return buyid;
    }

    public void setBuyid(String buyid) {
        this.buyid = buyid;
    }

    public void setToolService(IToolService toolService) {
        this.toolService = toolService;
    }

    public void setToolTypeService(IToolTypeService toolTypeService) {
        this.toolTypeService = toolTypeService;
    }

    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }
}