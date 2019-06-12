package com.baidu.thanksgod.outhor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.thanksgod.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Msg> mDatas;
    private final LayoutInflater mLayoutInflater;

    public ChatAdapter(Context mContext, List<Msg> mDatas) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
    }

    //添加消息到集合中
    public void addItem(Msg msg) {
        mDatas.add(msg);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.e("tag", "检查前: "+i );
        RecyclerView.ViewHolder holder = null;
        if (i == Msg.TYPE_BLE) {
            View inflate = mLayoutInflater.inflate(R.layout.item_chat_left, null, false);
            holder = new ChatLeftViewHolder(inflate);
        } else {
            View inflate = mLayoutInflater.inflate(R.layout.item_chat_right, null, false);
            holder = new ChatRightViewHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Msg msg = mDatas.get(i);
        String time = msg.getTime();
        String content = msg.getContent();
        Log.e("tag", "集合中的信息: "+content );
        if (viewHolder instanceof ChatLeftViewHolder) {
            ((ChatLeftViewHolder) viewHolder).tvLeftTime.setText(time);
            ((ChatLeftViewHolder) viewHolder).tvMsgLeft.setText(content);
        }else if (viewHolder instanceof ChatRightViewHolder){
            ((ChatRightViewHolder)viewHolder).tvRightTime.setText(time);
            ((ChatRightViewHolder)viewHolder).tvMsgRight.setText(content);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = mDatas.get(position).getType();
        return type;
    }

    static class ChatLeftViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_left_time)
        TextView tvLeftTime;
        @BindView(R.id.img_ble)
        ImageView imgBle;
        @BindView(R.id.tv_msg_left)
        TextView tvMsgLeft;

        public ChatLeftViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    static class ChatRightViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_right_time)
        TextView tvRightTime;
        @BindView(R.id.img_phone)
        ImageView imgPhone;
        @BindView(R.id.tv_msg_right)
        TextView tvMsgRight;

        public ChatRightViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
