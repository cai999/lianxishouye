package com.bwie.shouye.bean;

import java.util.List;

/**
 * Created by ll on 2018/8/16.
 */

public class ShouBean {
    public String msg;
    public String code;
    public Home data;

    public class Home {
        public List<Banner> banner;
        public List<Fenlei> fenlei;
        public class Banner {
            public String icon;
            public String title;
        }
        public class Fenlei {
            public String name;
            public String icon;
        }

    }

}
