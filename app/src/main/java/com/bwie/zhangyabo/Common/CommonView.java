package com.bwie.zhangyabo.Common;


import com.bwie.zhangyabo.Bean.bean;

import retrofit2.Call;
import retrofit2.Response;

/**
 * User: 张亚博
 * Date: 2017-11-06 09:23
 * Description：
 */
public interface CommonView {
    void onFailue(Call call);
    void onResponse(Call call, Response<bean> response);
}
