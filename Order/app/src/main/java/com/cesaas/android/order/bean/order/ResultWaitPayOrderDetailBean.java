package com.cesaas.android.order.bean.order;

import com.cesaas.android.order.base.BaseBean;

import java.util.List;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 16:27
 * Version 1.0
 */
public class ResultWaitPayOrderDetailBean extends BaseBean {

    /**
     * TModel : {"Item":[{"PayMent":1200,"StyleNo":"","isDetached":false,"ShopStyleId":514,"Quantity":1,"RetailSubId":208,"ActualConsumption":0,"BarcodeId":20086,"GetPoint":0,"ListPrice":0,"RetailType":0,"RetailId":10140,"RetailSort":0,"isError":false,"RefundQuantity":0,"StyleName":"上衣","BarcodeNo":"YM035LBQ730A2","SellPrice":1200,"CostPrice":0}],"Retail":{"RetailCashier":"阿标","isDetached":false,"ActivityId":0,"RetailDiscount":1,"RetailSure":0,"SaleId":0,"RetailPayment":1200,"RetailCheck":0,"TId":3,"DepotId":0,"WorkShift":"阴","SyncCode":"LS45928021016503401","isError":false,"VipId":0,"IsDel":0,"ShopId":10004,"RetailFrom":0,"CreateTime":"2017-02-10 16:50:42","CreateName":"阿标","RetailEnd":0,"RetailId":10140,"SellType":0,"Weather":"中班","UpdateTime":"0001-01-01 00:00:00","RetailTotal":1200}}
     */
    private TModelEntity TModel;

    public void setTModel(TModelEntity TModel) {
        this.TModel = TModel;
    }

    public TModelEntity getTModel() {
        return TModel;
    }

    public class TModelEntity {
        /**
         * Item : [{"PayMent":1200,"StyleNo":"","isDetached":false,"ShopStyleId":514,"Quantity":1,"RetailSubId":208,"ActualConsumption":0,"BarcodeId":20086,"GetPoint":0,"ListPrice":0,"RetailType":0,"RetailId":10140,"RetailSort":0,"isError":false,"RefundQuantity":0,"StyleName":"上衣","BarcodeNo":"YM035LBQ730A2","SellPrice":1200,"CostPrice":0}]
         * Retail : {"RetailCashier":"阿标","isDetached":false,"ActivityId":0,"RetailDiscount":1,"RetailSure":0,"SaleId":0,"RetailPayment":1200,"RetailCheck":0,"TId":3,"DepotId":0,"WorkShift":"阴","SyncCode":"LS45928021016503401","isError":false,"VipId":0,"IsDel":0,"ShopId":10004,"RetailFrom":0,"CreateTime":"2017-02-10 16:50:42","CreateName":"阿标","RetailEnd":0,"RetailId":10140,"SellType":0,"Weather":"中班","UpdateTime":"0001-01-01 00:00:00","RetailTotal":1200}
         */
        private List<ItemEntity> Item;
        private RetailEntity Retail;

        public void setItem(List<ItemEntity> Item) {
            this.Item = Item;
        }

        public void setRetail(RetailEntity Retail) {
            this.Retail = Retail;
        }

        public List<ItemEntity> getItem() {
            return Item;
        }

        public RetailEntity getRetail() {
            return Retail;
        }

        public class ItemEntity {
            /**
             * PayMent : 1200.0
             * StyleNo :
             * isDetached : false
             * ShopStyleId : 514
             * Quantity : 1
             * RetailSubId : 208
             * ActualConsumption : 0.0
             * BarcodeId : 20086
             * GetPoint : 0
             * ListPrice : 0.0
             * RetailType : 0
             * RetailId : 10140
             * RetailSort : 0
             * isError : false
             * RefundQuantity : 0
             * StyleName : 上衣
             * BarcodeNo : YM035LBQ730A2
             * SellPrice : 1200.0
             * CostPrice : 0.0
             */
            private double PayMent;
            private String StyleNo;
            private boolean isDetached;
            private int ShopStyleId;
            private int Quantity;
            private int RetailSubId;
            private double ActualConsumption;
            private int BarcodeId;
            private int GetPoint;
            private double ListPrice;
            private int RetailType;
            private int RetailId;
            private int RetailSort;
            private boolean isError;
            private int RefundQuantity;
            private String StyleName;
            private String BarcodeNo;
            private double SellPrice;
            private double CostPrice;

            public void setPayMent(double PayMent) {
                this.PayMent = PayMent;
            }

            public void setStyleNo(String StyleNo) {
                this.StyleNo = StyleNo;
            }

            public void setIsDetached(boolean isDetached) {
                this.isDetached = isDetached;
            }

            public void setShopStyleId(int ShopStyleId) {
                this.ShopStyleId = ShopStyleId;
            }

            public void setQuantity(int Quantity) {
                this.Quantity = Quantity;
            }

            public void setRetailSubId(int RetailSubId) {
                this.RetailSubId = RetailSubId;
            }

            public void setActualConsumption(double ActualConsumption) {
                this.ActualConsumption = ActualConsumption;
            }

            public void setBarcodeId(int BarcodeId) {
                this.BarcodeId = BarcodeId;
            }

            public void setGetPoint(int GetPoint) {
                this.GetPoint = GetPoint;
            }

            public void setListPrice(double ListPrice) {
                this.ListPrice = ListPrice;
            }

            public void setRetailType(int RetailType) {
                this.RetailType = RetailType;
            }

            public void setRetailId(int RetailId) {
                this.RetailId = RetailId;
            }

            public void setRetailSort(int RetailSort) {
                this.RetailSort = RetailSort;
            }

            public void setIsError(boolean isError) {
                this.isError = isError;
            }

            public void setRefundQuantity(int RefundQuantity) {
                this.RefundQuantity = RefundQuantity;
            }

            public void setStyleName(String StyleName) {
                this.StyleName = StyleName;
            }

            public void setBarcodeNo(String BarcodeNo) {
                this.BarcodeNo = BarcodeNo;
            }

            public void setSellPrice(double SellPrice) {
                this.SellPrice = SellPrice;
            }

            public void setCostPrice(double CostPrice) {
                this.CostPrice = CostPrice;
            }

            public double getPayMent() {
                return PayMent;
            }

            public String getStyleNo() {
                return StyleNo;
            }

            public boolean isIsDetached() {
                return isDetached;
            }

            public int getShopStyleId() {
                return ShopStyleId;
            }

            public int getQuantity() {
                return Quantity;
            }

            public int getRetailSubId() {
                return RetailSubId;
            }

            public double getActualConsumption() {
                return ActualConsumption;
            }

            public int getBarcodeId() {
                return BarcodeId;
            }

            public int getGetPoint() {
                return GetPoint;
            }

            public double getListPrice() {
                return ListPrice;
            }

            public int getRetailType() {
                return RetailType;
            }

            public int getRetailId() {
                return RetailId;
            }

            public int getRetailSort() {
                return RetailSort;
            }

            public boolean isIsError() {
                return isError;
            }

            public int getRefundQuantity() {
                return RefundQuantity;
            }

            public String getStyleName() {
                return StyleName;
            }

            public String getBarcodeNo() {
                return BarcodeNo;
            }

            public double getSellPrice() {
                return SellPrice;
            }

            public double getCostPrice() {
                return CostPrice;
            }
        }

        public class RetailEntity {
            /**
             * RetailCashier : 阿标
             * isDetached : false
             * ActivityId : 0
             * RetailDiscount : 1.0
             * RetailSure : 0
             * SaleId : 0
             * RetailPayment : 1200.0
             * RetailCheck : 0
             * TId : 3
             * DepotId : 0
             * WorkShift : 阴
             * SyncCode : LS45928021016503401
             * isError : false
             * VipId : 0
             * IsDel : 0
             * ShopId : 10004
             * RetailFrom : 0
             * CreateTime : 2017-02-10 16:50:42
             * CreateName : 阿标
             * RetailEnd : 0
             * RetailId : 10140
             * SellType : 0
             * Weather : 中班
             * UpdateTime : 0001-01-01 00:00:00
             * RetailTotal : 1200.0
             */
            private String RetailCashier;
            private boolean isDetached;
            private int ActivityId;
            private double RetailDiscount;
            private int RetailSure;
            private int SaleId;
            private double RetailPayment;
            private int RetailCheck;
            private int TId;
            private int DepotId;
            private String WorkShift;
            private String SyncCode;
            private boolean isError;
            private int VipId;
            private int IsDel;
            private int ShopId;
            private int RetailFrom;
            private String CreateTime;
            private String CreateName;
            private int RetailEnd;
            private int RetailId;
            private int SellType;
            private String Weather;
            private String UpdateTime;
            private double RetailTotal;

            public void setRetailCashier(String RetailCashier) {
                this.RetailCashier = RetailCashier;
            }

            public void setIsDetached(boolean isDetached) {
                this.isDetached = isDetached;
            }

            public void setActivityId(int ActivityId) {
                this.ActivityId = ActivityId;
            }

            public void setRetailDiscount(double RetailDiscount) {
                this.RetailDiscount = RetailDiscount;
            }

            public void setRetailSure(int RetailSure) {
                this.RetailSure = RetailSure;
            }

            public void setSaleId(int SaleId) {
                this.SaleId = SaleId;
            }

            public void setRetailPayment(double RetailPayment) {
                this.RetailPayment = RetailPayment;
            }

            public void setRetailCheck(int RetailCheck) {
                this.RetailCheck = RetailCheck;
            }

            public void setTId(int TId) {
                this.TId = TId;
            }

            public void setDepotId(int DepotId) {
                this.DepotId = DepotId;
            }

            public void setWorkShift(String WorkShift) {
                this.WorkShift = WorkShift;
            }

            public void setSyncCode(String SyncCode) {
                this.SyncCode = SyncCode;
            }

            public void setIsError(boolean isError) {
                this.isError = isError;
            }

            public void setVipId(int VipId) {
                this.VipId = VipId;
            }

            public void setIsDel(int IsDel) {
                this.IsDel = IsDel;
            }

            public void setShopId(int ShopId) {
                this.ShopId = ShopId;
            }

            public void setRetailFrom(int RetailFrom) {
                this.RetailFrom = RetailFrom;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setCreateName(String CreateName) {
                this.CreateName = CreateName;
            }

            public void setRetailEnd(int RetailEnd) {
                this.RetailEnd = RetailEnd;
            }

            public void setRetailId(int RetailId) {
                this.RetailId = RetailId;
            }

            public void setSellType(int SellType) {
                this.SellType = SellType;
            }

            public void setWeather(String Weather) {
                this.Weather = Weather;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public void setRetailTotal(double RetailTotal) {
                this.RetailTotal = RetailTotal;
            }

            public String getRetailCashier() {
                return RetailCashier;
            }

            public boolean isIsDetached() {
                return isDetached;
            }

            public int getActivityId() {
                return ActivityId;
            }

            public double getRetailDiscount() {
                return RetailDiscount;
            }

            public int getRetailSure() {
                return RetailSure;
            }

            public int getSaleId() {
                return SaleId;
            }

            public double getRetailPayment() {
                return RetailPayment;
            }

            public int getRetailCheck() {
                return RetailCheck;
            }

            public int getTId() {
                return TId;
            }

            public int getDepotId() {
                return DepotId;
            }

            public String getWorkShift() {
                return WorkShift;
            }

            public String getSyncCode() {
                return SyncCode;
            }

            public boolean isIsError() {
                return isError;
            }

            public int getVipId() {
                return VipId;
            }

            public int getIsDel() {
                return IsDel;
            }

            public int getShopId() {
                return ShopId;
            }

            public int getRetailFrom() {
                return RetailFrom;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public String getCreateName() {
                return CreateName;
            }

            public int getRetailEnd() {
                return RetailEnd;
            }

            public int getRetailId() {
                return RetailId;
            }

            public int getSellType() {
                return SellType;
            }

            public String getWeather() {
                return Weather;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public double getRetailTotal() {
                return RetailTotal;
            }
        }
    }

}
