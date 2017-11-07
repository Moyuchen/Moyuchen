package com.bwie.zhangyabo.Retrofit;

import com.bwie.zhangyabo.Bean.bean;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * User: 张亚博
 * Date: 2017-11-06 09:27
 * Description：
 */
public interface RequestData {
    @POST("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Call<bean> getResult();
//    Observable<bean> getResult();
}
