package net.m56.ckkj.mobile.tourism.util.retrofit.retrofit;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by 兔兔 on 2017/09/06.
 */

public interface ApiService {
//    public  static  String BASE_URL = "http://123.57.247.239/";
    public  static  String BASE_URL = "https://api.imeizan.cn";
//, @Header("Content-Type") String contentType
    @GET("{url}")
    Flowable<ResponseBody> executeGet(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> maps);
//    , @Header("Content-Type") String contentType
    @FormUrlEncoded
    @POST("{url}")
    Flowable<ResponseBody> executePost(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> maps);
    @Multipart
    @POST("{url}")
    Flowable<ResponseBody>  upLoadFile(@Path(value = "url", encoded = true) String url, @Part("image\\; filename=\\image.jpg") RequestBody avatar);

    @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Path(value = "url", encoded = true) String url,
            @Path("headers") Map<String, String> headers,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps);


    @POST("{url}")
    Flowable<ResponseBody> json(
            @Path(value = "url", encoded = true) String url,
            @Body RequestBody jsonStr);


    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);

}
