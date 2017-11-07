package com.bwie.zhangyabo.Modle;

import com.bwie.zhangyabo.Bean.bean;
import com.bwie.zhangyabo.Common.LoggingInterceptor;
import com.bwie.zhangyabo.Retrofit.RequestData;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * User: 张亚博
 * Date: 2017-11-06 09:23
 * Description：
 */
public class RequestModle {
private RequestModleView requestModleView;

    public void setRequestModleView(RequestModleView requestModleView) {
        this.requestModleView = requestModleView;
    }

  public void   getdata(){
      OkHttpClient client=new OkHttpClient.Builder()
              .addInterceptor(new LoggingInterceptor())
              .build();
      Retrofit retrofit=new Retrofit.Builder()
              .baseUrl("http://tingapi.ting.baidu.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .client(client)
              .build();
      final RequestData requestData = retrofit.create(RequestData.class);
      Call<bean> result = requestData.getResult();
      result.enqueue(new Callback<bean>() {
          @Override
          public void onResponse(Call<bean> call, Response<bean> response) {
              requestModleView.onResponse(call,response);

          }

          @Override
          public void onFailure(Call<bean> call, Throwable t) {
            requestModleView.onFailue(call);
          }
      });

  }
}
