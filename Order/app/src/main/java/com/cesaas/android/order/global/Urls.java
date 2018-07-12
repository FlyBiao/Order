package com.cesaas.android.order.global;

/**
 * Author FGB
 * Description
 * Created 2017/4/9 21:32
 * Version 1.0
 */
public class Urls {


    public final static String LOGIN="User/Sw/Account/Login";

    public final static String ORDER_CENTER="Order/Sw/Retail/PosOrderList";

    //待支付订单详情
    public static String WAIT_PAY_ORDER="Order/Sw/Retail/Query";

    //账单详情
    public static String PAY_JOURNAL="Order/Sw/Retail/PayJournalList";

    //pos对账查询【数据统计】
    public static String POS_STATISTICS="Order/Sw/Retail/Statistics";

    //扫描商品条码查询商品
    public static String GET_BARCODE_CODE="Marketing/Sw/Style/GetByBarcodeCode";
}
