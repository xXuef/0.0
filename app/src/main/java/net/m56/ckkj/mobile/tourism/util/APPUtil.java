package net.m56.ckkj.mobile.tourism.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Create by yue on 2017/4/17.
 */

public class APPUtil {

    private APPUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

  public   enum TYPE{
        UUID,
        simSerialNumber,
        androidId,
        imei
    }

    public static  String getPhoneInfo(Context context, TYPE type) {
        //                uuid
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds") String imei=tm.getDeviceId();
        @SuppressLint("HardwareIds") String simSerialNumber=tm.getSimSerialNumber();
        @SuppressLint("HardwareIds") String androidId = android.provider.Settings.Secure.getString(
                context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        if (TextUtils.isEmpty(simSerialNumber))
            simSerialNumber="15935748620";
        if (TextUtils.isEmpty(androidId))
            androidId="9517538426";
        if (TextUtils.isEmpty(imei))
            imei="1234567890";
            UUID deviceUuid =new UUID(androidId.hashCode(), ((long)imei.hashCode() << 32) | simSerialNumber.hashCode());
        String uniqueIuniqueId  = deviceUuid.toString();
        switch (type){
            case  androidId:
                return  androidId;

            case UUID:
                return  uniqueIuniqueId;
            case  simSerialNumber:
                return  simSerialNumber;
            case  imei:
                return  imei;
        }


        Log.d("type_uuid",uniqueIuniqueId);

        return null;
    }

    public String getPhoneInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();

        sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());//获取设备IMEI信息
        sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
        sb.append("\nLine1Number = " + tm.getLine1Number());
        sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
        sb.append("\nNetworkType = " + tm.getNetworkType());
        sb.append("\nPhoneType = " + tm.getPhoneType());
        sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
        sb.append("\nSimOperator = " + tm.getSimOperator());
        sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
        sb.append("\nSimState = " + tm.getSimState());
        sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
        sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
        return sb.toString();
    }

    public static boolean isServiceRunning(Context c ,String serviceClassName){
        final ActivityManager activityManager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE); //这个value取任意大于1的值，但返回的列表大小可能比这个值小。

        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
//                 runningServiceInfo.service.
                return true;
            }
        }
        return false;
    }

    public static boolean stopServiceRunning(Context c ,String serviceClassName){
        ActivityManager manager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);

        manager.killBackgroundProcesses(serviceClassName);
        return true;
    }


    public static void appendLog(Context c,String data){
        Log.d(""+c.toString(),"data="+data);
    }
    /**
     * 将long类型的文件大小数值转换为带单位的String,精确到Bytes与KB
     *
     * @return 带单位的文件大小String
     */
    public static String convertFileSize(long filesize) {

        String strUnit = "Bytes";
        String strAfterComma = "";

        int intDivisor = 1;
        if (filesize >= 1024 * 1024 * 1024) {
            strUnit = "GB";
            intDivisor = 1024 * 1024 * 1024;
        } else if (filesize >= 1024 * 1024) {
            strUnit = "MB";
            intDivisor = 1024 * 1024;
        } else if (filesize >= 1024) {
            strUnit = "KB";
            intDivisor = 1024;
        }

        if (intDivisor == 1)
            return filesize + " " + strUnit;
        strAfterComma = "" + 100 * (filesize % intDivisor) / intDivisor;
        if (strAfterComma == "")
            strAfterComma = ".0";

        return filesize / intDivisor + "." + strAfterComma + " " + strUnit;

    }
}
