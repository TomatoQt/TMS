package zjut.utils;

import zjut.po.ToolEntity;

import java.io.Serializable;
import java.util.Comparator;

public class Comparators implements Comparator<ToolEntity>, Serializable {
    @Override
    public int compare(ToolEntity o1, ToolEntity o2) {//比较寿命
        //寿命长的在前
        if (o1.getLife()<o2.getLife())
            return 1;
        else
            return 0;
    }
}
