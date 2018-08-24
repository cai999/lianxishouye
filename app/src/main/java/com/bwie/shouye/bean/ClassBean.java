package com.bwie.shouye.bean;

import java.util.List;

/**
 * Created by ll on 2018/8/20.
 */

public class ClassBean {
    public String msg;
    public String code;
    public List<classBean> data;
    public class classBean{
        public int cid;
        public String name;
    }
}
