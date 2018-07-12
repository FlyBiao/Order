package com.cesaas.android.order;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cesaas.android.order.activity.order.OrderCenterActivity;
import com.cesaas.android.order.activity.order.StatementActivity;
import com.cesaas.android.order.activity.pay.PayActivity;
import com.cesaas.android.order.activity.pay.PayListActivity;
import com.cesaas.android.order.activity.scan.ZBarScanActivity;
import com.cesaas.android.order.activity.user.LoginActivity;
import com.cesaas.android.order.adapter.order.CodeGoodsAdapter;
import com.cesaas.android.order.ammountview.AmountView;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.goods.CodeGoodsBean;
import com.cesaas.android.order.bean.goods.ResultCodeGoodsBean;
import com.cesaas.android.order.bean.pay.ResultCreatePayBean;
import com.cesaas.android.order.bean.user.ResultUserBean;
import com.cesaas.android.order.dialog.MyAlertDialog;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.net.user.UserInfoNet;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.cesaas.android.order.utils.Skip;
import com.cesaas.android.order.utils.SwipeMenuCreatorUtils;
import com.cesaas.android.order.utils.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.tv_add_barcode_shop)
    TextView mTvAddBarcodeShop;
    @BindView(R.id.tv_base_title_left)
    TextView mTvBaseTitleLeft;
    @BindView(R.id.ll_base_title_back)
    LinearLayout mLlBaseTitleBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_base_title_right)
    TextView mTvBaseTitleRight;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.tv_add_vip)
    TextView mTvAddVip;
    @BindView(R.id.tv_goods_count)
    TextView mTvGoodsCount;
    @BindView(R.id.tv_finally_price)
    TextView mTvFinallyPrice;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;

    private ImageView iv_icon;
    private TextView tv_user;
    private TextView tv_shop;

    private Intent intent = new Intent();
    private long exitTime = 0; // 退出点击间隔时间
    private String scanCode;
    private View headerView;

    private int goodsCount = 0;
    private double payMent;
    private double totalPrice;

    List<CodeGoodsBean> codeGoodsList = new ArrayList<>();
    private CodeGoodsAdapter mAdapter;

    private GoodsQuantityDialog mQuantityDialog;

    private UserInfoNet userInfoNet;

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @OnClick({R.id.tv_add_vip, R.id.tv_add_barcode_shop})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_vip://添加会员
                break;
            case R.id.tv_add_barcode_shop://扫描商品条码
                Skip.mActivityResults(mActivity, ZBarScanActivity.class, Constant.REQUEST_CONTACT, Constant.SCAN_ORDER_CODE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);//订阅
        ButterKnife.bind(this);
        initDrawerLayout();
        init();

        userInfoNet=new UserInfoNet(mContext);
        userInfoNet.setData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvent(final ResultUserBean lbean) {
        if(lbean.isSuccess()!=false){
            tv_user.setText(lbean.TModel.NickName);
            tv_shop.setText(lbean.TModel.ShopName);
            if(lbean.TModel.Icon!=null && !"".equals(lbean.TModel.Icon)){
                Glide.with(mContext).load(lbean.TModel.Icon).into(iv_icon);
            }else{
                iv_icon.setImageResource(R.mipmap.ic_launcher);
            }
            prefs.putString("TypeId", lbean.TModel.TypeId);
            prefs.putString("shopName", lbean.TModel.ShopName);
            prefs.putString("ShopId",lbean.TModel.ShopId);
            prefs.putString("VipId",lbean.TModel.VipId+"");
            prefs.putString("Mobile", lbean.TModel.Mobile);
            prefs.putString("Icon", lbean.TModel.Icon);
            prefs.putString("Name",lbean.TModel.Name);
            prefs.putString("NickName",lbean.TModel.NickName);
            prefs.putString("Sex", lbean.TModel.Sex);
            prefs.putString("shopMobile", lbean.TModel.ShopMobile);
            prefs.putString("TypeName",lbean.TModel.TypeName);
            prefs.putString("Ticket",lbean.TModel.Ticket);
            prefs.putString("CounselorId",lbean.TModel.CounselorId);
            prefs.putString("GzNo",lbean.TModel.GzNo);
            prefs.putString("TId",lbean.TModel.tId+"");
            prefs.putString("ShopPostCode",lbean.TModel.ShopPostCode);
            prefs.putString("ShopProvince",lbean.TModel.ShopProvince);
            prefs.putString("ShopAddress",lbean.TModel.ShopAddress);
            prefs.putString("ShopArea",lbean.TModel.ShopArea);
            prefs.putString("ShopCity",lbean.TModel.ShopCity);
        }else{
            ToastUtils.getLongToast(mContext,"获取用户信息失败！"+lbean.getMessage());
        }
    }

    private void init() {
        mTvBaseTitleLeft.setText("更多");
        mTvBaseTitle.setText("在线下单");
        mTvBaseTitleRight.setText("整单作废");
        mTvBaseTitleRight.setVisibility(View.VISIBLE);

        //初始化RecyclerView
        SwipeMenuCreatorUtils.initRecyclerView(mRecyclerView, mContext);

        // 设置菜单创建器。
//        SwipeMenuCreatorUtils menuCreatorUtils = new SwipeMenuCreatorUtils(mContext);
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);

        mLlBaseTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvBaseTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
    }

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
                        .setImage(R.mipmap.ic_action_add) // 图标。
                        .setWidth(150) // 宽度。
                        .setHeight(165); // 高度。
                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(150) // 宽度。
                        .setHeight(165); // 高度。

                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(150) // 宽度。
                        .setHeight(165); // 高度。
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setText("数量")
                        .setTextColor(Color.WHITE)
                        .setWidth(150) // 宽度。
                        .setHeight(165); // 高度。
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(150) // 宽度。
                        .setHeight(165); // 高度。
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                switch (menuPosition) {
                    case 0://删除商品
                        codeGoodsList.remove(adapterPosition);
                        mAdapter.notifyItemChanged(adapterPosition);
                        payMent = payMent - codeGoodsList.get(adapterPosition).getPayMent();
                        totalPrice = totalPrice - codeGoodsList.get(adapterPosition).getPrice();
                        goodsCount = goodsCount - codeGoodsList.get(adapterPosition).getShopCount();

                        mTvGoodsCount.setText(goodsCount + "件商品");
                        mTvFinallyPrice.setText(payMent + "");
                        mTvTotalPrice.setText(totalPrice + "");

                        break;
                    case 1://数量
                        mQuantityDialog=new GoodsQuantityDialog(mContext, R.style.dialog, R.layout.dialog_goods_quantity);
                        mQuantityDialog.show();
                        break;
                    case 2://赠品
                        break;
                }
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void initAdapter(List<CodeGoodsBean> codeGoodsList) {
        mAdapter = new CodeGoodsAdapter(codeGoodsList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDrawerLayout() {
        headerView = mNavigationView.getHeaderView(0);
        iv_icon= (ImageView) headerView.findViewById(R.id.iv_icon);
        tv_user= (TextView) headerView.findViewById(R.id.tv_user);
        tv_shop= (TextView) headerView.findViewById(R.id.tv_shop);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "header View", Toast.LENGTH_SHORT).show();
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_app_update:
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.menu_app_order_settle:
                        mDrawerLayout.closeDrawers();
                        Skip.mNext(mActivity, StatementActivity.class);
                        break;

                    case R.id.menu_app_scan_order:
                        mDrawerLayout.closeDrawers();

                        intent.setClass(MainActivity.this, ZBarScanActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;

                    case R.id.menu_app_message:
                        mDrawerLayout.closeDrawers();
                        intent.setClass(MainActivity.this, OrderCenterActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;

                    case R.id.menu_app_setting:
                        mDrawerLayout.closeDrawers();
                        Skip.mNext(mActivity, PayActivity.class);
                        break;

                    case R.id.menu_app_pay:
                        Skip.mNext(mActivity, PayListActivity.class);
                        break;

                    case R.id.menu_app_exit:
                        mDrawerLayout.closeDrawers();
                        exit();
                        break;
                }

                return false;
            }
        });

    }


    /**
     * 处理扫描Activity返回的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Constant.RESULT_CODE) {
            switch (data.getStringExtra("intentCode")) {
                case "100"://扫描商品条码订单
                    scanCode = data.getStringExtra("resultCode");
                    GetBarcodeCodeNet getBarcodeCodeNet = new GetBarcodeCodeNet(mContext);
                    getBarcodeCodeNet.setData(scanCode);
                    break;
            }
//            if(data.getStringExtra("intentCode")!=null && data.getStringExtra("intentCode").equals("100")){
//
//            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 退出当前用户
     */
    public void exit() {
        new MyAlertDialog(mContext).mInitShow("退出登录", "是否退出登录? 退出后将不能接收最新消息!",
                "我知道", "点错了", new MyAlertDialog.ConfirmListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        myapp.mExecutorService.execute(new Runnable() {

                            @Override
                            public void run() {
                                prefs.cleanAll();
                                Bundle bundle = new Bundle();

                                Skip.mNext(mActivity, LoginActivity.class, true, bundle);
                            }
                        });
                    }
                }, null);
    }


    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();

                } else {
                    for (int i = 0; i < BaseActivity.activityList.size(); i++) {
                        if (null != BaseActivity.activityList.get(i)) {
                            Skip.mBack(BaseActivity.activityList.get(i));
                        }
                    }
                    Skip.mBack(this);
                }
                return true;
            } catch (Exception e) {
                Skip.mBack(this);
                e.printStackTrace();
            }
        }
        return false;
    }

    //===============================自定义Dialog=================================

    public class GoodsQuantityDialog extends Dialog{

        int layoutRes;//布局文件
        Context context;
        AmountView mAmountView;
        public GoodsQuantityDialog(Context context, int theme, int resLayout) {
            super(context, theme);
            this.context = context;
            this.layoutRes=resLayout;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.setContentView(layoutRes);

            initView();
        }

        private void initView() {
            mAmountView = (AmountView) findViewById(R.id.amount_view);
            mAmountView.setGoods_storage(50);
            mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    //===============================网络请求=================================
    /**
     * Author FGB
     * Description 扫描商品条码查询商品Net
     * Created 2017/4/15 15:29
     * Version 1.0
     */
    public class GetBarcodeCodeNet extends BaseNet {
        public GetBarcodeCodeNet(Context context) {
            super(context, true);
            this.uri = Urls.GET_BARCODE_CODE;
        }

        public void setData(String BarcodeCode) {
            try {
                data.put("BarcodeCode", BarcodeCode);
                data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));

            } catch (Exception e) {
                e.printStackTrace();
            }
            mPostNet();
        }

        @Override
        protected void mSuccess(String rJson) {
            super.mSuccess(rJson);
            ResultCodeGoodsBean resultBean = JsonUtils.fromJson(rJson, ResultCodeGoodsBean.class);

            totalPrice = 0.0;
            payMent = 0.0;

            if(codeGoodsList.size()!=0){
                for (int i=0;i<codeGoodsList.size();i++){
                    if(resultBean.TModel.getStyleCode().equals(codeGoodsList.get(i).getStyleCode())){
                        codeGoodsList.get(i).setShopCount(codeGoodsList.get(i).getShopCount() + 1);
                        break;// 中断循环

                    }else{
                        //代表循环到最后的时候，if 不相等 走else ，再添加一行数据
                        if((i+1) == codeGoodsList.size()){
                            //实例化CodeGoodsBean
                            CodeGoodsBean codeGoodsBean = new CodeGoodsBean();
                            codeGoodsBean.setTitle(resultBean.TModel.getTitle());//商品名称
                            codeGoodsBean.setStyleCode(resultBean.TModel.getStyleCode());//商品款号码
                            codeGoodsBean.setShopCount(1);//商品数量
                            codeGoodsBean.setPrice(resultBean.TModel.getPrice());//商品支付价格
                            codeGoodsBean.setPayMent(resultBean.TModel.getPrice());//商品售价
                            codeGoodsList.add(codeGoodsBean);

                            break;
                        }
                    }
                }
            }else{//第一次进来执行
                //实例化CodeGoodsBean
                CodeGoodsBean codeGoodsBean = new CodeGoodsBean();
                codeGoodsBean.setTitle(resultBean.TModel.getTitle());//商品名称
                codeGoodsBean.setStyleCode(resultBean.TModel.getStyleCode());//商品款号码
                codeGoodsBean.setShopCount(1);//商品数量
                codeGoodsBean.setPrice(resultBean.TModel.getPrice());//商品支付价格
                codeGoodsBean.setPayMent(resultBean.TModel.getPrice());//商品售价
                codeGoodsList.add(codeGoodsBean);

                //调用初始化Adapter方法
                initAdapter(codeGoodsList);
            }

            //循环计算商品总价格
            for (int i = 0; i < codeGoodsList.size(); i++) {
                goodsCount += codeGoodsList.get(i).getShopCount();
                payMent += codeGoodsList.get(i).getPayMent() * codeGoodsList.get(i).getShopCount();
                totalPrice += codeGoodsList.get(i).getPrice() * codeGoodsList.get(i).getShopCount();
            }

            //显示商品数量和总价格
            mTvGoodsCount.setText(goodsCount + "件商品");
            mTvFinallyPrice.setText(payMent + "");
            mTvTotalPrice.setText(totalPrice + "");

            //调用初始化Adapter方法
            initAdapter(codeGoodsList);
        }

        @Override
        protected void mFail(HttpException e, String err) {
            super.mFail(e, err);
        }
    }

}
