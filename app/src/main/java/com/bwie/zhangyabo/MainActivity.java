package com.bwie.zhangyabo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.bwie.zhangyabo.Adapter.RecycleViewAdapter;
import com.bwie.zhangyabo.Bean.bean;
import com.bwie.zhangyabo.Presenter.RequestPresenter;
import com.bwie.zhangyabo.Presenter.RequestPresenterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RequestPresenterView, View.OnClickListener, RecycleViewAdapter.ClickItemListener {

    private RecyclerView mRecycleView;
    private CheckBox cb_qx;//全选
    private Button but_del;//删除按钮
    private List<Integer> positions=new ArrayList<>();
    private List<bean.SongListBean> song_list;
    private RecycleViewAdapter adapter;
    private String TAG="============MainActivity==============";
    private Button but_edit;
    private int clickedit=0;

    private LinearLayout botom;
    private boolean allSelected=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载视图
        initView();
        //请求数据
        initData();
    }

    /**
     * 请求数据方法
     */
    private void initData() {
        RequestPresenter presenter=new RequestPresenter(this);
        presenter.getData();
    }
    /**
     * 加载视图方法
     */
    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.RecycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        cb_qx = (CheckBox) findViewById(R.id.CB_QX);
        cb_qx.setOnClickListener(this);
        but_del = (Button) findViewById(R.id.But_del);
        but_del.setOnClickListener(this);
        but_edit = (Button) findViewById(R.id.But_edit);
        but_edit.setOnClickListener(this);

        botom = (LinearLayout) findViewById(R.id.botom);
    }

    @Override
    public void onFailue(Call call) {

    }

    /**
     * 请求成功方法
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call call, final Response<bean> response) {
        runOnUiThread(new Runnable() {
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                song_list = response.body().getSong_list();
                Log.i("===============MainActivity=================", "run: "+response.body().getSong_list().get(0).getAlbum_title());
                allSonglist(false,false);
                adapter= new RecycleViewAdapter(song_list,MainActivity.this);
                mRecycleView.setAdapter(adapter);

                adapter.setClickItemListener(MainActivity.this);
            }
        });
    }

    /**
     * 点击事件方法a
     * @param view
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.But_del:
                delItem();
                break;
            case R.id.But_edit:
                if (clickedit%2==0) {
                    but_edit.setText("完成");
                    botom.setVisibility(View.VISIBLE);
                    allSonglist(false,true);
                }else {
                    but_edit.setText("编辑");
                    botom.setVisibility(View.GONE);
                    allSonglist(false,false);
                }
                adapter.notifyDataSetChanged();
                clickedit++;
                break;
            case R.id.CB_QX:
              //点击全选
                AllSelected();
                break;
        }

    }
//点击全选执行的操作
    private void AllSelected() {
        if (allSelected) {
            cb_qx.setText("取消全选");
            cb_qx.setChecked(true);
            allSonglist(true,true);
            allSelected=false;
        }else {
            cb_qx.setText("全选");
            cb_qx.setChecked(false);
            allSonglist(false,true);
            allSelected=true;
        }
        adapter.notifyDataSetChanged();

    }

    //执行删除方法
    private void delItem() {
        for (int i=0;i<positions.size();i++){
            int integer = positions.get(i);
            Log.i(TAG, "position: "+integer);
            song_list.get(integer).setSelected(true);
        }

        for (int i=0;i<song_list.size();i++){
            boolean selected = song_list.get(i).isSelected();
            Log.i(TAG, "i: "+i+"selected:"+selected);
            if (selected) {
                song_list.remove(i);
            }else {
                cb_qx.setText("全选");
                cb_qx.setChecked(false);
            }
        }

        if (adapter!=null) {
            adapter.Replace(song_list);
        }
        adapter.notifyDataSetChanged();
        positions.clear();
    }

    /**
     * 点击条目监听方法
     * @param positon
     */
    @Override
    public void onClickitem(int positon) {
        positions.add(positon);
        song_list.get(positon).setSelected(true);
//        song_list.remove(positon);
        Log.i(TAG, "onClickitem: "+positon);
    }

    /**
     * 设置条目的选中，CheckBox的显示与隐藏
     * @param selected
     * @param visible
     */
    private void allSonglist(boolean selected,boolean visible){
        for (bean.SongListBean songListBean : song_list) {
            songListBean.setSelected(selected);
            songListBean.setCBVisibla(visible);
        }
    }
}
