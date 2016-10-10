package com.hj.asus.moving_kuangjia.pojo;

import java.util.List;

/**
 * Created by asus on 2016/9/14.
 */
public class ListActivityBean {
    public int status;
    public List<Zixun> zixunlist;

    public static class Zixun{

        public Integer zixunId;
        public String photoImg;
        public String name;
        public String chenghao;
        public String type;
        public String time;
        public String content;
        public Integer dianzan;
    }
}
