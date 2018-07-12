package com.cesaas.android.order.bean.goods;

import java.io.Serializable;

/**
 * Author FGB
 * Description
 * Created 2017/4/15 0:00
 * Version 1.0
 */
public class CodeGoodsBean implements Serializable {
    private String Title;//商品名称
    public BarcodeInfo BarcodeInfo;//商品尺码描述信息
    private int ShopStyleId;//商品id
    private double Price;//商品价格
    private double PayMent;//商品支付金额
    private String StyleCode;//商品款号
    private String BarcodeCode;//商品sku
    private String BarcodeId;//商品sku_id
    private String ImageUrl;//商品图片
    private int ShopCount=1;//商品总数


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getShopStyleId() {
        return ShopStyleId;
    }

    public void setShopStyleId(int shopStyleId) {
        ShopStyleId = shopStyleId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getPayMent() {
        return PayMent;
    }

    public void setPayMent(double payMent) {
        PayMent = payMent;
    }

    public String getStyleCode() {
        return StyleCode;
    }

    public void setStyleCode(String styleCode) {
        StyleCode = styleCode;
    }

    public String getBarcodeCode() {
        return BarcodeCode;
    }

    public void setBarcodeCode(String barcodeCode) {
        BarcodeCode = barcodeCode;
    }

    public String getBarcodeId() {
        return BarcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        BarcodeId = barcodeId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getShopCount() {
        return ShopCount;
    }

    public void setShopCount(int shopCount) {
        ShopCount = shopCount;
    }
}
