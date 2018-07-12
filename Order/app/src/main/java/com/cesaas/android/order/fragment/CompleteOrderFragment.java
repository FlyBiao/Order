package com.cesaas.android.order.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cesaas.android.order.R;
import com.cesaas.android.order.activity.order.AccountDetailActivity;
import com.cesaas.android.order.activity.order.WaitPayOrderDetailActivity;
import com.cesaas.android.order.adapter.order.OrderCenterAdapter;
import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.OrderCenterBean;
import com.cesaas.android.order.bean.order.ResultOrderCenterBean;
import com.cesaas.android.order.bean.user.LoginBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.listener.OnItemClickListener;
import com.cesaas.android.order.net.order.CompleteOrderNet;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.cesaas.android.order.utils.Skip;
import com.cesaas.android.order.utils.ToastUtils;
import com.cesaas.android.order.view.ListViewDecoration;
import com.lidroid.xutils.exception.HttpException;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * Author FGB
 * Description
 * Created 2017/4/2 0:49
 * Version 1.0
 */
public class CompleteOrderFragment extends Fragment {

    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeMenuRecyclerView mSwipeMenuRecyclerView;

    private int page = 1;

    private CompleteOrderNet mOrderCenterNet;
    private OrderCenterAdapter mOrderCenterAdapter;
    private List<OrderCenterBean> mOrderCenterBeanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mOrderCenterNet = new CompleteOrderNet(getContext());
        mOrderCenterNet.setData("", "", page);
    }

    /**
     * 接收登录结果消息
     * @param msg 消息实体类
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(ResultOrderCenterBean msg) {
        if(msg.isSuccess()==true){

            mOrderCenterBeanList=new ArrayList<>();
            mOrderCenterBeanList.addAll(msg.TModel);
            initAdapter(mOrderCenterBeanList);

        }else{
            ToastUtils.getLongToast(getContext(),"获取数据失败！"+msg.getMessage());
        }
    }


    private void initAdapter(List<OrderCenterBean> mOrderCenterBeanList) {
        mOrderCenterAdapter = new OrderCenterAdapter(getContext(), mOrderCenterBeanList);
        mSwipeMenuRecyclerView.setAdapter(mOrderCenterAdapter);
        mOrderCenterAdapter.setOnItemClickListener(onItemClickListener);
    }


    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.recycler_order_center_view);
        mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));// 布局管理器。
        mSwipeMenuRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mSwipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mSwipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。
        // 添加滚动监听。
//        mSwipeMenuRecyclerView.addOnScrollListener(mOnScrollListener);

    }


    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {


            if(mOrderCenterBeanList.get(position).getRetailCheck()!=1){
                //跳转待支付详情页面
                Bundle bundle=new Bundle();
                bundle.putString("OrderId",mOrderCenterBeanList.get(position).getRetailId());
                Skip.mNextFroData(getActivity(),WaitPayOrderDetailActivity.class,bundle);

            }else{
                Bundle bundle=new Bundle();
                bundle.putString("OrderId",mOrderCenterBeanList.get(position).getRetailId());
                bundle.putString("CreateName",mOrderCenterBeanList.get(position).getCreateName());
                bundle.putDouble("PayAmount",mOrderCenterBeanList.get(position).getPayAmount());
                bundle.putInt("IsRefund",mOrderCenterBeanList.get(position).getIsRefund());
                //跳转到账单详情
                Skip.mNextFroData(getActivity(),AccountDetailActivity.class,bundle);
            }
        }
    };

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mSwipeMenuRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //重新加载数据
                    mOrderCenterNet = new CompleteOrderNet(getContext());
                    mOrderCenterNet.setData("", "", page);
                    //取消加载圈圈
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 2000);
        }
    };


    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。

//                Toast.makeText(RefreshLoadMoreActivity.this, "滑到最底部了，加载更多数据！", Toast.LENGTH_SHORT).show();
//                size += 20;
//                for (int i = size - 20; i < size; i++) {
//                    mStrings.add("我是第" + i + "个。");
//                }
//                mMenuAdapter.notifyDataSetChanged();
            }
        }
    };










    /**
     * Author FGB
     * Description
     * Created 2017/4/9 23:05
     * Version 1.0
     */
    public class CompleteOrderNet extends BaseNet {
        public CompleteOrderNet(Context context) {
            super(context, true);
            this.uri= Urls.ORDER_CENTER;
        }

        public void setData(String start_date,String end_date,int page){
            try {
                data.put("PageIndex", page);
                data.put("PageSize", 30);
                data.put("start_date", start_date);//开始时间
                data.put("end_date", end_date);//结束时间
//            data.put("Sort",dateArray);
                data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mPostNet(); // 开始请求网络
        }


        @Override
        protected void mSuccess(String rJson) {
            super.mSuccess(rJson);
            ResultOrderCenterBean lbean= JsonUtils.fromJson(rJson,ResultOrderCenterBean.class);
//            EventBus.getDefault().post(lbean);

            if(lbean.isSuccess()==true){

                mOrderCenterBeanList=new ArrayList<>();
                mOrderCenterBeanList.addAll(lbean.TModel);
                initAdapter(mOrderCenterBeanList);

            }else{
                ToastUtils.getLongToast(getContext(),"获取数据失败！"+lbean.getMessage());
            }
        }

        @Override
        protected void mFail(HttpException e, String err) {
            super.mFail(e, err);
            Log.i(Constant.TAG, "CheckAccountsNet===" + e + "********=err==" + err);
        }


    }

}