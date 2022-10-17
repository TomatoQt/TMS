package zjut.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import zjut.po.ToolEntity;
import zjut.service.ScrapService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScrapInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("ScrapInterceptor");
        ActionContext actionContext = actionInvocation.getInvocationContext();
        ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);
        ApplicationContext context1 = WebApplicationContextUtils.getWebApplicationContext(context);
        ScrapService scrapRecordService = (ScrapService) context1.getBean("scrapService");

        HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
        HttpSession session=(HttpSession)request.getSession();
        /*Map session= actionInvocation.getInvocationContext().getSession();*/

        Map formMap = actionInvocation.getInvocationContext().getParameters();
        Set set = formMap.keySet();
        for (Object key:set) {
            System.out.print("key:"+key.toString()+"   ");
            System.out.println(formMap.get(key));
        }
        //获得code和seqID调用purchaseRecordService来查重
        String id = formMap.get("entity.toolId").toString();
        System.out.println("id" + id);
        ToolEntity x = new ToolEntity();
        x.setId(id);
        List scrap = scrapRecordService.FindByTool(x);
        //如果存在则返回ScrapRecordExist
        if (scrap != null) {
            System.out.println("ScrapRecordExist");
            return "ScrapRecordExist";
        }
        return actionInvocation.invoke();
    }
}
