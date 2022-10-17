package zjut.action;

import zjut.service.IDeptService;

import java.util.List;

public class DeptAction {
    private IDeptService deptService = null;
    private List DeptList;

    public String getAllDept(){
        DeptList = deptService.getAllDept();
        return "success";
    }

    public List getDeptList() {
        return DeptList;
    }

    public void setDeptList(List deptList) {
        DeptList = deptList;
    }

    public void setDeptService(IDeptService deptService) {
        this.deptService = deptService;
    }
}
