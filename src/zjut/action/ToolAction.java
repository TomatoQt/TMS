package zjut.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import constants.StaffStatus;
import constants.ToolStatus;
import zjut.po.StaffEntity;
import zjut.po.ToolEntity;
import zjut.service.IToolService;

public class ToolAction {

    private ToolEntity tool;

    private IToolService toolService;

    private List toolList;

    public ToolEntity getTool() {
        return this.tool;
    }

    public void setTool(ToolEntity tool) {
        this.tool = tool;
    }

//    public String applyTool() {
//        tool.setStatus(ToolStatus.APPLY_STATUS);
//        toolService.addTool(tool);
//        return "success";
//    }

    public String modifyTool() {
        toolService.modifyTool(tool);
        return "success";
    }

    public String loadTool() {
        ToolEntity entity = toolService.loadByID(tool.getId());
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("tool", entity);
        return "success";
    }

    public String getAll() {
        toolList = toolService.loadAll();
        return "success";
    }


    public void setToolService(IToolService toolService) {
        this.toolService = toolService;
    }

    public List getToolList() {
        return toolList;
    }

    public void setToolList(List toolList) {
        this.toolList = toolList;
    }
}
