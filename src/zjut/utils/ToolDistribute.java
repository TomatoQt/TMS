package zjut.utils;

import zjut.po.ToolEntity;

import java.util.ArrayList;
import java.util.Collections;

//分配原则：优先分配寿命较长的工具，寿命相同则随机分配
public class ToolDistribute {
    private ArrayList<ToolEntity> myList;

    public ArrayList<ToolEntity> getMyList() {
        return myList;
    }

    public void setMyList(ArrayList<ToolEntity> myList) {
        this.myList = myList;
    }

    public ToolDistribute(ArrayList<ToolEntity> list){
        //创建对象时直接排序
        setMyList(list);
        Comparators comparators=new Comparators();
        if (myList!=null)
            myList.sort(comparators);//排序
        else
            System.out.println("myList为空！无法排序");
    }

    public ToolEntity getTool(){
        return myList.get(0);//拿前面的
    }

    public String getToolId(){
        return myList.get(0).getId();//拿前面的工具的id
    }
}
