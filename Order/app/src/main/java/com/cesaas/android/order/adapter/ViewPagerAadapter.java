package com.cesaas.android.order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.cesaas.android.order.bean.FragmentInfo;
import com.cesaas.android.order.fragment.RefundOrderFragment;
import com.cesaas.android.order.fragment.NotCompleteOrderFragment;
import com.cesaas.android.order.fragment.CompleteOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author FGB
 * Description
 * Created 2017/4/2 0:41
 * Version 1.0
 */
public class ViewPagerAadapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments=new ArrayList<>(4);

    public ViewPagerAadapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments(){
        mFragments.add(new FragmentInfo("已完成",CompleteOrderFragment.class));
        mFragments.add(new FragmentInfo("未完成",NotCompleteOrderFragment.class));
        mFragments.add(new  FragmentInfo("已退款",RefundOrderFragment.class));
//        mFragments.add(new FragmentInfo("分类",CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
