package net.m56.ckkj.mobile.tourism.util.retrofit.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import net.m56.ckkj.mobile.tourism.util.retrofit.callback.CallBack;
import net.m56.ckkj.mobile.tourism.util.retrofit.callback.ResultCallBack;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 兔兔 on 2017/09/06.
 * compile 'io.reactivex.rxjava2:rxjava:2.0.1'
 * compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
 * compile 'com.google.code.gson:gson:2.7'
 * compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
 * compile 'com.squareup.retrofit2:converter-gson:2.3.0'
 * compile 'com.squareup.retrofit2:retrofit:2.3.0'
 * compile 'com.squareup.okhttp3:logging-interceptor:3.8.1'
 * <p>
 * 引用 jinlibs
 * sourceSets { MainActivity {
 * jniLibs.srcDirs = ['libs']
 * }
 * }
 * 权限
 * <uses-permission android:name="android.permission.INTERNET"/>
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */

public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 20;
    private ApiService apiService;
    private static OkHttpClient okHttpClient;
    private static String baseUrl = ApiService.BASE_URL;
    private static Context mContext;
//    private static RetrofitClient sNewInstance;

    private static Retrofit retrofit;
    private Cache cache = null;
    private File httpCacheDirectory;


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl);
    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .addNetworkInterceptor(
                            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(
                mContext);
    }

    public static RetrofitClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }


        return SingletonHolder.INSTANCE;
    }

    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }

        return new RetrofitClient(context, url);
    }

    public static RetrofitClient getInstance(Context context, String url, Map<String, String> headers) {
        if (context != null) {
            mContext = context;
        }
        return new RetrofitClient(context, url, headers);
    }

    private RetrofitClient() {

    }

    private RetrofitClient(Context context) {

        this(context, baseUrl, null);
    }

    private RetrofitClient(Context context, String url) {

        this(context, url, null);
    }

    private RetrofitClient(Context context, String url, Map<String, String> headers) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "tamic_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cookieJar(new NovateCookieManger(context))
                .cache(cache)
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new CaheInterceptor(context))
                .addNetworkInterceptor(new CaheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();

        if (apiService == null) {
            apiService = create(ApiService.class);
        }


    }

    /**
     * ApiBaseUrl
     *
     * @param newApiBaseUrl
     */
    public static void changeApiBaseUrl(String newApiBaseUrl) {
        baseUrl = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl);
    }

    /**
     * addcookieJar
     */
    public static void addCookie() {
        okHttpClient.newBuilder().cookieJar(new NovateCookieManger(mContext)).build();
        retrofit = builder.client(okHttpClient).build();
    }

    /**
     * ApiBaseUrl
     *
     * @param newApiHeaders
     */
    public static void changeApiHeader(Map<String, String> newApiHeaders) {

        okHttpClient.newBuilder().addInterceptor(new BaseInterceptor(newApiHeaders)).build();
        builder.client(httpClient.build()).build();
    }

    /**
     * create BaseApi  defalte ApiManager
     *
     * @return ApiManager
     */
    public RetrofitClient createBaseApi() {
        if (apiService == null)
            apiService = create(ApiService.class);
        return this;
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    public void get(String url, @Nullable Map<String, String> parameters, final ResultCallBack requestCallBack) {
        if (parameters == null) parameters = new HashMap<>();
        request(apiService.executeGet(url, parameters), requestCallBack);
    }

    public void get(String url, @NonNull Map<String, String> parameters, Subscriber<ResponseBody> subscriber) {
        apiService.executeGet(url, parameters)
                .compose(this.<ResponseBody>schedulersTransformer())
                .subscribe(subscriber);
    }

    public void post(String url, @Nullable Map<String, String> parameters, final ResultCallBack requestCallBack) {
        if (parameters == null) parameters = new HashMap<>();
        request(apiService.executePost(url, parameters), requestCallBack);
    }

    public void post(String url, @NonNull Map<String, String> parameters, Subscriber<ResponseBody> subscriber) {
        apiService.executePost(url, parameters)
                .compose(this.<ResponseBody>schedulersTransformer())
                .subscribe(subscriber);
    }

    public void upload(String url, @NonNull RequestBody parameters, Subscriber<ResponseBody> subscriber) {
        apiService.upLoadFile(url, parameters)
                .compose(this.<ResponseBody>schedulersTransformer())
//                .compose(transformer())
                .subscribe();
    }

    public void uploads(String url, Map<String, String> headers, String description,@NonNull Map<String, RequestBody> parameters, Callback<ResponseBody> subscriber) {
        apiService.uploadFiles(url,headers,description,parameters).enqueue(subscriber);
    }

    public void download(String url, final CallBack callBack) {
        apiService.downloadFile(url)
                .compose(this.<ResponseBody>schedulersTransformer())
//                .compose(transformer())
                .subscribe(new DownSubscriber<ResponseBody>(callBack));
    }


    private void request(Flowable<ResponseBody> flowable, final ResultCallBack requestCallBack) {
        flowable.compose(this.<ResponseBody>schedulersTransformer())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        if (!NetworkUtil.isNetworkAvailable(mContext)) {
                            Toast.makeText(mContext, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show();
                            requestCallBack.postMainOnSubscribe();
                            s.cancel();
                            onComplete();
                        } else {
                            s.request(Long.MAX_VALUE);
                        }
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {

                            if (requestCallBack.mType == String.class) {
                                requestCallBack.postMainOnNext(responseBody.string());
                            } else {
                                Object object = new Gson().fromJson(responseBody.string(), requestCallBack.mType);
                                requestCallBack.postMainOnNext(object);
                            }
                        } catch (Exception e) {
                            onError(e.getCause());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ExceptionHandle.ResponeThrowable) {
                            requestCallBack.postMainOnError((ExceptionHandle.ResponeThrowable) t);
                        } else {
                            requestCallBack.postMainOnError(new ExceptionHandle.ResponeThrowable(t, ExceptionHandle.ERROR.UNKNOWN));
                        }

                    }

                    @Override
                    public void onComplete() {
                        requestCallBack.postMainOnComplete("http is Complete");
                    }
                });

    }


    private <T> FlowableTransformer<T, T> schedulersTransformer() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .throttleFirst(1000, TimeUnit.SECONDS)
//                        .take(1)                 接受第一条数据
//                filter  过滤 ofType 过滤类型 distinct重复 过滤
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    <T> FlowableTransformer<T, T> applySchedulers() {
        return schedulersTransformer();
    }

    public <T> FlowableTransformer<ResponseBody, T> transformer() {
        return new FlowableTransformer<ResponseBody, T>() {
            @Override
            public Publisher<T> apply(Flowable<ResponseBody> upstream) {
                return upstream.map(new HandleFuction<T>()).onErrorResumeNext(new HttpResponseFunction<T>());
            }
        };
    }

    public <T> Flowable<T> switchSchedulers(Flowable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static class HttpResponseFunction<T> implements Function<Throwable, Flowable<T>> {

        @Override
        public Flowable<T> apply(Throwable throwable) throws Exception {
            return Flowable.error(ExceptionHandle.handleException(throwable));
        }
    }

    private class HandleFuction<T> implements Function<ResponseBody, T> {

        @Override
        public T apply(ResponseBody tHttpResult) throws Exception {
            if (tHttpResult.contentLength()==-1)
                throw new RuntimeException("network error!");
            return (T) tHttpResult.string().toString().trim();

        }
    }

    /**
     * /**
     * execute your customer API
     * For example:
     * MyApiService service =
     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
     * <p>
     * RetrofitClient.getInstance(MainActivity.this)
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */

    public static <T> T execute(Flowable<T> flowable, Subscriber<T> subscriber) {
        flowable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return null;
    }

    /**
     * DownSubscriber
     *
     * @param <ResponseBody>
     */
    class DownSubscriber<ResponseBody> implements Subscriber<ResponseBody> {
        CallBack callBack;

        public DownSubscriber(CallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onSubscribe(Subscription s) {
            if (callBack != null) {
                callBack.onStart();
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            DownLoadManager.getInstance(callBack).writeResponseBodyToDisk(mContext, (okhttp3.ResponseBody) responseBody);
        }

        @Override
        public void onError(Throwable t) {
            if (callBack != null) {
                callBack.onError(t);
            }
        }

        @Override
        public void onComplete() {
            if (callBack != null) {
                callBack.onCompleted();
            }
        }
    }

}
