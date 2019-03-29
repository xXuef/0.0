package net.m56.ckkj.mobile.tourism.util.retrofit.callback;


import net.m56.ckkj.mobile.tourism.util.retrofit.retrofit.ExceptionHandle;

/**
 * Created by 兔兔 on 2017/09/13.
 */

public abstract class ResultCallBackAdapter<T>  extends  ResultCallBack<T>{
    @Override
    public void postMainOnNext(T t) throws Exception {

    }

    @Override
    public void postMainOnError(ExceptionHandle.ResponeThrowable e) {

    }


}
