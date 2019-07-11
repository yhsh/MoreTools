package com.qfy.locationtest;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private String locationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {//GPRS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {//wifi跟数据流量
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {//表明没有打开GPRS定位或者没有开启wifi或者数据，或者没有相关权限
            Log.e("打印定位方式", "无法获取该设备可用的定位工具");
        }
        Log.e("打印定位方式", locationProvider + "=");
        locationManager.requestLocationUpdates("network", 1000, 1, locationListener);//监听每一秒的位置变化
        LocationListener locationListener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle arg2) {
//位置状态改变时调用该函数，比如：可用，不可应，无服务
            }

            @Override
            public void onProviderEnabled(String provider) {
//定位打开时调用
            }

            @Override
            public void onProviderDisabled(String provider) {
//定位关闭时调用
            }

            @Override
            public void onLocationChanged(Location location) {
                //监听位置变化，位置变化对应location发生变化

            }
        };
        Location location = locationManager.getLastKnownLocation(locationProvider);
//        double latitude = location.getLatitude();//维度
//        double longitude = location.getLongitude();//经度
//        Toast.makeText(this,latitude+"=="+longitude,Toast.LENGTH_LONG).show();
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String addString = null;
            List<Address> addList = null;
            Geocoder ge = new Geocoder(MainActivity.this);
            try {
                addList = ge.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {

                e.printStackTrace();
            }
            if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                    Address ad = addList.get(i);
                    addString = ad.getLocality();//拿到城市
                }
            }
            String locationStr = "维度：" + location.getLatitude()
                    + "经度：" + location.getLongitude();
            Log.i("andly", locationStr + "----" + addString);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //移除监听器
            locationManager.removeUpdates(locationListener);
        }
    }
}
