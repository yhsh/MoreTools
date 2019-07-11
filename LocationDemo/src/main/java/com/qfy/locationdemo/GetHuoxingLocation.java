package com.qfy.locationdemo;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GetHuoxingLocation {
    public static String getLocation(Context context) {
        ModifyOffset mo = null;
       /* try {
            mo = ModifyOffset.getInstance(GetHuoxingLocation.class.getResourceAsStream("axisoffset.dat"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {// 加载数据库
            InputStream is = context.getAssets().open("axisoffset.dat");
            File file = new File(context.getFilesDir(), "axisoffset.dat");
            if (file.exists() && file.length() > 0) {
                Toast.makeText(context, "数据库之前加载完毕", Toast.LENGTH_SHORT).show();
            } else {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                is.close();
                fos.close();
                Toast.makeText(context, "数据库第一次加载完毕", Toast.LENGTH_SHORT).show();
                mo = ModifyOffset.getInstance(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        PointDouble npoint = mo.s2c(new PointDouble(116.29118378f, 40.0433961f));
        return npoint.toString();
    }
}
