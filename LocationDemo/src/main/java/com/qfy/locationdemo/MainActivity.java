package com.qfy.locationdemo;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private TextView tvLongitude;
    private MyLocationListener myLocationListener;
    private TextView locationChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLongitude = (TextView) findViewById(R.id.tv_longitude);
        locationChange = (TextView) findViewById(R.id.location_change);
//        locationChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        String location = GetHuoxingLocation.getLocation(MainActivity.this);
//                        tvLongitude.setText(location);
//                    }
//                }.start();
//            }
//        });
        //1.获取位置的管理者
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //2.获取定位方式
        //2.1获取所有的定位方式，true:表示返回所有可用定位方式
        List<String> providers = locationManager.getProviders(true);
        for (String string : providers) {
            System.out.println(string);
        }
        //2.2获取最佳的定位方式
        Criteria criteria = new Criteria();
        criteria.setAltitudeRequired(true);//设置是否可以定位海拔,如果设置定位海拔，返回一定是gps
        //criteria : 设置定位属性
        //enabledOnly : true如果定位可用就返回
        String bestProvider = locationManager.getBestProvider(criteria, false);
        System.out.println("最佳的定位方式:" + bestProvider);
        //3.定位
        myLocationListener = new MyLocationListener();
        //provider : 定位的方式
        //minTime : 定位的最小时间间隔
        //minDistance : 定位最小的间隔距离
        //LocationListener : 定位监听
        locationManager.requestLocationUpdates("network", 0, 0, myLocationListener);

    }

    private class MyLocationListener implements LocationListener {
        private String latLongString;

        //当定位位置改变的调用的方法
        //Location : 当前的位置
        @Override
        public void onLocationChanged(Location location) {
            float accuracy = location.getAccuracy();//获取精确位置
            double altitude = location.getAltitude();//获取海拔
            final double latitude = location.getLatitude();//获取纬度，平行
            final double longitude = location.getLongitude();//获取经度，垂直
            tvLongitude.setText("longitude:" + longitude + "  latitude:" + latitude + "精确位置" + accuracy + "海拔" + altitude);
            Log.e("打印经纬度：", "longitude:" + longitude + "  latitude:" + latitude + "精确位置" + accuracy + "海拔" + altitude);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Address> addsList = null;
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try {
                        addsList = geocoder.getFromLocation(latitude, longitude, 10);//得到的位置可能有多个当前只取其中一个
                        Log.e("打印拿到的城市", addsList.toString());
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    if (addsList != null && addsList.size() > 0) {
                        for (int i = 0; i < addsList.size(); i++) {
                            final Address ads = addsList.get(i);
                            latLongString = ads.getLocality();//拿到城市
//                            latLongString = ads.getAddressLine(0);//拿到地址
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("打印拿到的城市的地址", latLongString + ads.getAddressLine(0) + ads.getAddressLine(1) + ads.getAddressLine(4));
                                    locationChange.setText(latLongString + ads.getAddressLine(0) + ads.getAddressLine(1));
                                    Toast.makeText(MainActivity.this, "当前所在的城市为" + latLongString + ads.getAddressLine(0) + ads.getAddressLine(4) + ads.getAddressLine(1), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }
            }).start();
        }

        //当定位状态发生改变的时候调用的方式
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

        //当定位可用的时候调用的方法
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        //当定位不可用的时候调用的方法
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(myLocationListener);//关闭gps,但是高版本中规定打开和关闭gps必须由用户自己主观的去实现，代码已经不允许进行操作
    }
}
