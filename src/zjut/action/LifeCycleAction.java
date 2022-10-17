package zjut.action;

import org.apache.struts2.ServletActionContext;
import zjut.po.*;
import zjut.pojo.Circle;
import zjut.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class LifeCycleAction {
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
    private IRepairService repair;
    private IScrapService scrap;
    private IIoService io;
    private IBuyService buy;
    private ToolEntity entity;
    private StaffService staffService;
    private List list;
    private ToolEntity tool;
    private IToolService toolService;
    public String get() {
        tool = toolService.findById(entity.getId());
        BuyrecordEntity Start = buy.FindById(entity.getBuyRecordId());
        Circle x = new Circle();
        x.setType("购买入库");
        x.setApplicanter(staffService.FindById(Start.getStaffId()).getName());
        x.setFinaler(staffService.FindById(Start.getFinalId()).getName());
        x.setHandler(staffService.FindById(Start.getHandlerId()).getName());
        x.setFinal_time(Start.getOverTime());
        x.setHandle_time(Start.getHandleTime());
        x.setSubmit_time(Start.getSubmitTime());
        list.add(x);
        List res = io.FindByTool(tool);
        for(int i = 0; i < res.size(); i++) {
            IorecordEntity tp = (IorecordEntity) res.get(i);
            if(tp.getIo() == 1) {
                x.setType("申请入库");
            }
            else  {
                x.setType("申请出库");
            }
            x.setApplicanter(staffService.FindById(tp.getApplicantId()).getName());
            x.setFinaler(null);
            x.setHandler(staffService.FindById(tp.getAuthenticateId()).getName());
            x.setFinal_time(null);
            x.setHandle_time(tp.getOverTime());
            x.setSubmit_time(tp.getSubmitTime());
            list.add(x);
        }
        res = repair.FindByTool(tool);
        for(int i = 0; i < res.size(); i++) {
            RepairrecordEntity tp = (RepairrecordEntity) res.get(i);
            x.setType("维修");
            x.setApplicanter(staffService.FindById(tp.getApplicantId()).getName());
            x.setFinaler(null);
            x.setHandler(staffService.FindById(tp.getHandler()).getName());
            x.setFinal_time(null);
            x.setHandle_time(tp.getOverTime());
            x.setSubmit_time(tp.getSubmitTime());
            list.add(x);
        }

        ScraprecordEntity finish = (ScraprecordEntity) scrap.FindByTool(tool);
        if(finish != null) {
            x.setType("报废");
            x.setApplicanter(staffService.FindById(finish.getApplicantId()).getName());
            x.setFinaler(staffService.FindById(finish.getFinalId()).getName());
            x.setHandler(staffService.FindById(finish.getHandlerId()).getName());
            x.setFinal_time(finish.getOverTime());
            x.setHandle_time(finish.getHandleTime());
            x.setSubmit_time(finish.getSubmitTime());
            list.add(x);
        }
        Collections.sort(list, new Comparator<Circle>() {
            public int compare(Circle o1, Circle o2) {
                int flag = o1.getSubmit_time().compareTo(o2.getSubmit_time());
                return flag;
            }
        });
        return "success";
    }

    public void setRepair(IRepairService repair) {
        this.repair = repair;
    }

    public void setScrap(IScrapService scrap) {
        this.scrap = scrap;
    }

    public void setIo(IIoService io) {
        this.io = io;
    }

    public ToolEntity getEntity() {
        return entity;
    }

    public void setEntity(ToolEntity entity) {
        this.entity = entity;
    }

    public void setBuy(IBuyService buy) {
        this.buy = buy;
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

    public ToolEntity getTool() {
        return tool;
    }

    public void setTool(ToolEntity tool) {
        this.tool = tool;
    }
}
