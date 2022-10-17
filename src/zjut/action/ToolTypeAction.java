package zjut.action;

import zjut.po.TooltypeEntity;
import zjut.service.IToolTypeService;

import java.util.List;

public class ToolTypeAction {
    private TooltypeEntity entity;
    private List list;
    private IToolTypeService toolTypeService = null;
    private String toolTypeId;
    private String title;
    private String desc;

    public String getAll() {
        System.out.println(1);
        list = toolTypeService.findAll();
        return "success";
    }


    public String create() {
        TooltypeEntity tooltype = new TooltypeEntity();
        tooltype.setName(title);
        tooltype.setDescription(desc);
        if (toolTypeService.create(tooltype)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String update() {
        entity = toolTypeService.findByToolTypeId(toolTypeId);
        entity.setDescription(desc);
        if (toolTypeService.update(entity)) {
            return "success";
        } else {
            return "false";
        }
    }

    public String delete() {
        if (toolTypeService.delete(entity)) {
            return "success";
        } else {
            return "false";
        }
    }


    public TooltypeEntity getEntity() {
        return entity;
    }

    public void setEntity(TooltypeEntity entity) {
        this.entity = entity;
    }

    public void setToolTypeService(IToolTypeService toolTypeService) {
        this.toolTypeService = toolTypeService;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getToolTypeId() {
        return toolTypeId;
    }

    public void setToolTypeId(String toolTypeId) {
        this.toolTypeId = toolTypeId;
    }
}