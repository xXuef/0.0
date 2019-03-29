package net.m56.ckkj.mobile.tourism.util.retrofit.callback;

import com.google.gson.internal.$Gson$Types;

import net.m56.ckkj.mobile.tourism.util.retrofit.retrofit.ExceptionHandle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 兔兔 on 2017/09/08.
 */

public abstract class ResultCallBack<T>  {
    public Type mType;

    public ResultCallBack() {
        mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types
                .canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public abstract  void postMainOnSubscribe();
    public  abstract void postMainOnNext(T t) throws  Exception;
    public  abstract void postMainOnComplete(String  complete);
    public  abstract void postMainOnError(ExceptionHandle.ResponeThrowable e);

}
