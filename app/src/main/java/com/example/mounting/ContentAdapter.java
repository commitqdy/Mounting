package com.example.mounting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentAdapter extends RecyclerView.Adapter {

    List<Integer> listData = new ArrayList<>();

    LayoutInflater mInflater;
    Context ctx;

    public ContentAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_of_content, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        if (listData != null && listData.size() > position) {
            final int index = listData.get(position);
            viewHolder.tvContent.setText("第"+index+"行");
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (listData == null) ? 0 : listData.size();
    }

    public void updateListData(List<Integer> list) {
        this.listData.clear();
        this.listData = list;
        notifyDataSetChanged();
    }



    public class BaseViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.tvContent)
        TextView tvContent;

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
