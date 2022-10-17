package zjut.filter;

import zjut.po.StaffEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageFilter implements Filter {
    private String excludedPage;
    private String[] excludedPages;

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Access PageFilter executed!");
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();
        StaffEntity staff = (StaffEntity)session.getAttribute("user");

        //获取当前URL
        String ss=request.getRequestURI().replaceFirst(request.getContextPath(),"");
        String []sss=new String[5];
        //O1
        sss[0]="InTool,lookReturnNumber,ReturnApply,ReturnApply,outTool,BorrowApply,lookBorrow,repairTool,RepairApply,checkRepair,lookBuy";
        //O2
        sss[1]="lookBorrow,PermitBorrow,NotPermitBorrow,checkRepair,PermitRepair,NotPermitRepair,buyToolPage,buyToolApply,lookBuy,scrapToolPage,scrapToolApply,lookScrap";
        //SuperVisor
        sss[2]="editToolTypePage,CreateToolType,EditToolType,PermitBuySuper_,lookScrap,PermitScrapSuper_";
        //Manager
        sss[3]="AllStaff,lookBuy,PermitBuyManager_,lookScrap,PermitScrapManager_";
        //Admin
        sss[4]="ChangeStaffInfoAction,AddStaffPage,register,deleteStaff,fireStaffPage,AllStaff,editStaffPage,editStaff";
        //判断用户权限
        try{
            switch (staff.getAuthority()) {
                //O1User
                case 0: {
                    if (sss[0].indexOf(ss) != -1) {
                        break;
                    } else {
//                        response.sendRedirect("HomeOperatorI.jsp");
                        response.sendRedirect("loginAction");
                        return;
                    }
                }
                case 1: {
                    if (sss[1].indexOf(ss) != -1) {
                        break;
                    } else {
                        response.sendRedirect("HomeOperatorII.jsp");
                        return;
                    }
                }
                case 2: {
                    if (sss[2].indexOf(ss) != -1) {
                        break;
                    } else {
                        response.sendRedirect("HomeSupervisor.jsp");
                        return;
                    }
                }
                case 3: {
                    if (sss[3].indexOf(ss) != -1) {
                        break;
                    } else {
                        response.sendRedirect("HomeManager.jsp");
                        return;
                    }
                }
                case 4: {
                    if (sss[4].indexOf(ss) != -1) {
                        break;
                    } else {
                        response.sendRedirect("HomeAdmin.jsp");
                        return;
                    }
                }
            }
            filterChain.doFilter(arg0, arg1);
        }catch (NullPointerException e){
            filterChain.doFilter(arg0, arg1);
        }
//        filterChain.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPage = filterConfig.getInitParameter("ignores");//此处的ignores就是在web.xml定义的名称一样。
        if (excludedPage != null && excludedPage.length() > 0){
            excludedPages = excludedPage.split(",");
        }
    }

}
