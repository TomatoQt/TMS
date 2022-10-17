package zjut.utils;

import zjut.po.ToolEntity;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;

//管理工具寿命
public class Longevity {
    public int getNewToolLife(ToolEntity toolEntity, int days){
        int life=toolEntity.getLife();
        Date systemTime=new Date();//系统时间
        //更新寿命
        //使用时损耗倍率假定为10%
        //寿命更新方法：当前寿命-使用时损耗倍率*(当前时间-出库时间)
        life=life-(int)(1.1*days);
        return life;
    }
}
