package com.baidu.thanksgod.outhor;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.baidu.thanksgod.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_chatList)
    RecyclerView rvChatList;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.bt_send)
    Button btSend;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    private List<Msg> mMsgs;
    private ChatAdapter mChatAdapter;
   /* Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            addMsg(new Msg(null,"来数据了",Msg.TYPE_BLE,"2019-6-12"));
            handler.postDelayed(this,5000);
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MsgDaoUtil msgDaoUtil = new MsgDaoUtil();
        //加载消息记录
        mMsgs = msgDaoUtil.queryAll(this);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvChatList.setLayoutManager(manager);
        mChatAdapter = new ChatAdapter(this, mMsgs);
        rvChatList.setAdapter(mChatAdapter);

        //初始化加载历史记录呈现最新消息
        rvChatList.scrollToPosition(mChatAdapter.getItemCount() - 1);

        msgDaoUtil.setUpdateListener(new MsgDaoUtil.OnDbUpdateListener() {
            @Override
            public void onUpdate(Msg msg) {
                    mChatAdapter.addItem(msg);

                    //屏幕铺满后呈现最新消息
                    rvChatList.scrollToPosition(mChatAdapter.getItemCount() - 1);
            }
        });
        //设置下滑隐藏软键盘
        rvChatList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy  < -10){
                    InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(etContent.getWindowToken(),0);
                }
            }
        });
        //handler.postDelayed(runnable, 5000);
    }
    private boolean addMsg(Msg msg){
        return MsgDaoUtil.insertMsg(this,msg);
    }
    @OnClick(R.id.bt_send)
    public void onViewClicked() {
        String content = etContent.getText().toString();
        addMsg(new Msg(null,content,Msg.TYPE_PHONE,"我的"));
        etContent.getText().clear();
    }
}
