package com.example.mounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.Arrays;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rcContent)
    RecyclerView rcContent;
    @BindView(R.id.abToolBar)
    AppBarLayout abToolBar;
    @BindView(R.id.tvSecond)
    TextView tvSecond;

    ContentAdapter mAdapter;
    int barHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        //状态栏颜色设置为透明，展示页面背景色
        ImmersionBar.with(this).statusBarAlpha(0).init();
        //给abToolBar设置顶部边距为状态栏高度，避免刘海屏遮挡
        setMargins(abToolBar,0, ImmersionBar.getNotchHeight(this),0,0);
        setMargins(rcContent,0, 0,0,ImmersionBar.getNotchHeight(this));
        barHeight = abToolBar.getMeasuredHeight();

        //监听abToolBar，设置吸顶区域透明度
        abToolBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (barHeight == 0) {
                barHeight = abToolBar.getMeasuredHeight();
            } else {
                float alpha =(float) (barHeight+verticalOffset) / barHeight;
                abToolBar.setAlpha(alpha);
            }
        });

        mAdapter = new ContentAdapter(this);
        rcContent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcContent.setAdapter(mAdapter);

        tvSecond.setOnClickListener(view -> {
            startActivity(new Intent(this, SecondActivity.class));
        });
    }

    private void initData() {
        ArrayList list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 9, 10, 11, 12, 13));
        mAdapter.updateListData(list);
    }

    private void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    private int getScreenWidth() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    private int getScreenHeight() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}