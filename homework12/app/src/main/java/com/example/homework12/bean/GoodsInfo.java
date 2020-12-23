package com.example.homework12.bean;

import com.example.homework12.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc1; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号
    public int pic_id;
    public String title;
    public boolean bPressed;
    public int id;
    private static int seq = 0;
    public String desc;
    public GoodsInfo(int pic_id, String title, String desc) {
        rowid = 0L;
        sn = 0;
        name = "";
        desc1 = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
        this.pic_id = pic_id;
        this.title = title;
        this.desc = desc;
        this.bPressed = false;
        this.id = this.seq;
        this.seq++;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "HUAWEI  3", "华为荣耀X10", "novel 7 SE", "小米5G Pro",
            "华为麒麟 820 5G", "华为荣耀 X20","华为荣耀 30S","HONOR"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = new String[]{
            "半开放式主动降噪，麒麟A1芯片，抗干扰，低时延，无线快充，佩戴稳固舒适，震撼低音",
            "荣耀X10，超能科技，5G风暴，超强性能，超全5G频段，全方位流畅体验",
            "nova 7系列 你在焦点在。nova 7 Pro前置追焦双摄，50倍潜望式变焦，麒麟985芯片。",
            "小米最自豪的作品，凝聚创新技术，120X超远变焦、120W秒充科技、120Hz专业屏幕",
            "荣耀820，美由“芯”生，麒麟820 5G芯片，6400万四摄，3倍光学变焦，40W超级快充 ",
            "荣耀20青春版 AMOLED屏幕指纹 4000mAh大电池 20W快充 4800万 手机 6GB+64GB 蓝水翡翠",
            "荣耀30系列,光影之美,一瞬倾心,50倍超稳远摄,超感光高清夜拍,麒麟990 5G旗舰芯片,5G双模全国通,双超级快充 ",
            "华为/HUAWEI 畅享10 Plus 6GB+128GB 翡冷翠 超清全视屏 超广角AI三摄 移动联通电信4G全面屏全网通手机 ",
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {599, 399, 299, 499, 169, 259,58,99};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.heiyi_s, R.drawable.huaer_s, R.drawable.huawei_s, R.drawable.keai_s,R.drawable.qiling_s ,
            R.drawable.rongyao_s, R.drawable.suling_s, R.drawable.tianmao_s
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.heiyi, R.drawable.huaer,R.drawable.huawei, R.drawable.keai, R.drawable.qiling,
            R.drawable.rongyao, R.drawable.suling, R.drawable.tianmao
    };

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc1 = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
    private static int[] gridImageArray = {R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03
            , R.drawable.pic_04, R.drawable.pic_05, R.drawable.pic_06, R.drawable.pic_07
            , R.drawable.pic_08, R.drawable.pic_09, R.drawable.pic_10};
    private static String[] gridTitleArray = {"商场", "超市", "百货", "便利店",
            "地摊", "食杂店", "饭店", "餐厅", "会所", "菜市场"};

    public static ArrayList<GoodsInfo> getDefaultGrid() {
        ArrayList<GoodsInfo> gridArray = new ArrayList<GoodsInfo>();
        for (int i = 0; i < gridImageArray.length; i++) {
            gridArray.add(new GoodsInfo(gridImageArray[i], gridTitleArray[i], null));
        }
        return gridArray;
    }
    private static int[] stagImageArray = {R.drawable.skirt01, R.drawable.skirt02, R.drawable.skirt03
            , R.drawable.skirt04, R.drawable.skirt05, R.drawable.skirt06, R.drawable.skirt07
            , R.drawable.skirt08, R.drawable.skirt09, R.drawable.skirt10, R.drawable.skirt11
            , R.drawable.skirt12, R.drawable.skirt13, R.drawable.skirt14, R.drawable.skirt15
            , R.drawable.skirt16, R.drawable.skirt17, R.drawable.skirt18, R.drawable.skirt19
            , R.drawable.skirt20, R.drawable.skirt21, R.drawable.skirt22, R.drawable.skirt23};
    private static String[] stagTitleArray = {"促销价", "惊爆价", "跳楼价", "白菜价", "清仓价", "割肉价",
            "实惠价", "一口价", "满意价", "打折价", "腰斩价", "无人问津", "算了吧", "大声点",
            "嘘嘘", "嗯嗯", "呼呼", "呵呵", "哈哈", "嘿嘿", "嘻嘻", "嗷嗷", "喔喔"};

    public static ArrayList<GoodsInfo> getDefaultStag() {

        ArrayList<GoodsInfo> stagArray = new ArrayList<GoodsInfo>();
        for (int i = 0; i < stagImageArray.length; i++) {
            stagArray.add(new GoodsInfo(stagImageArray[i], stagTitleArray[i], null));
        }
        return stagArray;
    }
}
