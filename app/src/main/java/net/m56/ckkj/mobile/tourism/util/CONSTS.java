package net.m56.ckkj.mobile.tourism.util;

import net.m56.ckkj.mobile.tourism.application.LocalApplication;

/**
 * Created by yue on 2017/09/11.
 */

public class CONSTS {
    public  static String URL = (String) SharedPreferencesUtils.getParam(LocalApplication.applicationContext,"host_url","123.57.247.239");

    public static final String HOST = "http://"+URL+"/smwater/index.php/SystemMap/";
    public static String DEFAULT = "http://"+URL+"/smwater/index.php/RegionManage/getDefaultMap";
    public static String DEVICE = "getMapBindedDevice";
    public static String VALVE = "getMapBindedValve";
    public static String PIPING = "getMapBindedPiping";
    public static String SENSOR = "getMapBindedSensor";
    public static String CHARGE = "http://"+URL+"/smwater/index.php/LargeTable/updateCoordinate";

}
