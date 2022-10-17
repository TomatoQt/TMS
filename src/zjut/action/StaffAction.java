package zjut.action;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import zjut.po.*;
import zjut.service.*;

import static javax.xml.crypto.dsig.DigestMethod.SHA256;

public class StaffAction extends ActionSupport {

    private IStaffService staffService;
    private IRepairService repairService;
    private IBuyService buyService;
    private IIoService ioService;
    private IScrapService scrapService;
    private IToolService toolService;
    private IDeptService deptService;
    //部门列表
    private List deptList;
    //用户列表
    private List users;
    //被操作用户
    private StaffEntity staff;
    //登录用户
    private StaffEntity user;
    //表单
    private List list;
    //工具表单
    private List toolList;
    //登录
    public String login() {
        System.out.println(user.getId() + " " + user.getPassword());
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        user = staffService.login(user);
        if(user == null) {
            System.out.println("user: null");
            session.setAttribute("message", "用户名或者密码错误");
            return "error";
        }
        int f = user.getAuthority();
        System.out.println(f);
        //根据身份转移到不同的页面

        if(f == -1) {
            return "error";
        }
//        user.setPassword(SHA256(user.getPassword()));
        session.setAttribute("user",user);
        if(f == 0) {
            return "o1";
        }
        else if(f == 1) {
            return "o2";
        }
        else if(f == 2) {
            return "supervisor";
        }
        else if(f == 3) {
            return "manager";
        }
        else {//4
            return "admin";
        }
    }

//    public void validateLogin(){
//        String account=user.getId();
//        String password=user.getPassword();
//        if (account == null || account.equals("")){
//            this.addFieldError("user.id","请输入账号!");
//        }
//        if (password == null || password.equals("")){
//            this.addFieldError("user.password","请输入密码!");
//        }
//    }

    private String SHA256(String password) {
        return null;
    }

    //获取需要归还
    public String In() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        staff = (StaffEntity) session.getAttribute("User");
        ArrayList<String> temp = (ArrayList<String>) ioService.FindByOut(staff);
        toolList =  new ArrayList<ToolEntity>();
        for(int i = 0; i < temp.size(); i++) {
            toolList.add(toolService.findById(temp.get(i)));
        }
        return "success";
    }

    public String getAllUsers(){
        users = staffService.findAll();//除了管理员
        deptList = deptService.getAllDept();
        return "success";
    }

    //添加？
    public String register() {
        if(staffService.register(staff)) {
            return "success";
        }
        else {
            return "fail";
        }
    }

    public String editStaff(){
        if (staffService.updateStaffInfo(staff))
            return "success";
        else
            return "false";
    }

    //修改
    public String update() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        staff = (StaffEntity) session.getAttribute("User");
        if(staffService.updateStaffInfo(staff)) {
            return "success";
        }
        else{
            return "fail";
        }
    }

    public String deleteStaff(){
        if(staffService.deleteStaff(staff))
            return "success";
        else
            return "false";
    }

    //查找所有员工
    public String findAll() {
        list = staffService.findAll();
        return "success";
    }
    //修理相关记录
    public String repair() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        staff = (StaffEntity) session.getAttribute("User");
        list = repairService.FindByStaff(staff);
        return "success";
    }
    //购买相关记录
    public String buy() {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        staff = (StaffEntity) session.getAttribute("User");
        list = buyService.FindByStaff(staff);
        return "success";
    }
    //进出库相关记录
    //报废相关记录
    public String scrap() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        staff = (StaffEntity) session.getAttribute("User");
        list = scrapService.FindByStaff(staff);
        return "success";
    }

    public String logout(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        return "success";
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setRepairService(IRepairService repairService) {
        this.repairService = repairService;
    }

    public void setBuyService(IBuyService buyService) {
        this.buyService = buyService;
    }

    public void setIoService(IIoService ioService) {
        this.ioService = ioService;
    }

    public void setScrapService(IScrapService scrapService) {
        this.scrapService = scrapService;
    }

    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public StaffEntity getUser() {
        return user;
    }

    public void setUser(StaffEntity user) {
        this.user = user;
    }

    public List getToolList() {
        return toolList;
    }

    public void setToolList(List toolList) {
        this.toolList = toolList;
    }

    public void setToolService(IToolService toolService) {
        this.toolService = toolService;
    }

    public List getUsers() {
        return users;
    }

    public void setUsers(List users) {
        this.users = users;
    }

    public void setDeptService(IDeptService deptService) {
        this.deptService = deptService;
    }

    public List getDeptList() {
        return deptList;
    }

    public void setDeptList(List deptList) {
        this.deptList = deptList;
    }
}
