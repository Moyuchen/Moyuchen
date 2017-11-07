package com.bwie.zhangyabo.Presenter;

import com.bwie.zhangyabo.Bean.bean;
import com.bwie.zhangyabo.Modle.RequestModle;
import com.bwie.zhangyabo.Modle.RequestModleView;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Response;

/**
 * User: 张亚博
 * Date: 2017-11-06 09:36
 * Description：
 */
public class RequestPresenter implements RequestModleView {
    private RequestPresenterView requestPresenterView;
    private final RequestModle requestModle;
    private WeakReference<RequestPresenterView> weakReference;

    public RequestPresenter(RequestPresenterView requestPresenterView) {
        this.requestPresenterView = requestPresenterView;
        weakReference=new WeakReference<RequestPresenterView>(requestPresenterView);
        requestModle = new RequestModle();
        requestModle.setRequestModleView(this);
    }

   public void getData(){
        requestModle.getdata();
    }


    @Override
    public void onFailue(Call call) {
//        requestPresenterView.onFailue(call);
        weakReference.get().onFailue(call);
    }

    @Override
    public void onResponse(Call call, Response<bean> response) {
        weakReference.get().onResponse(call,response);
    }

    public void Disattach(){
        weakReference.clear();
    }


}
