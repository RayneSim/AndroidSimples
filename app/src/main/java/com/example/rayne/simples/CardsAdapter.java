package com.example.rayne.simples;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by RayneSim on 17/07/29.
 */

public class CardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Map<String, Object>> mItems;
    View.OnClickListener mOnClickListener;

    CardsAdapter(List<Map<String, Object>> mItems) {
        this.mItems = mItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(itemView);
    }

    //绑定每一项数据
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        final Map<String, Object> map = getItem(position);
        final String titleText = map.get("title").toString();
        viewHolder.title.setText(titleText);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mOnClickListener.onClick(view);
                Toast.makeText(view.getContext(), titleText, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent((Intent) map.get("intent"));
                intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
                Logger.d("onItemClick: intent = " + intent);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private Map<String, Object> getItem(int position){
        return mItems.get(position);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
