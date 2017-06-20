package com.example.administrator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int width = getWindowManager().getDefaultDisplay().getWidth();
        final int height = getWindowManager().getDefaultDisplay().getHeight();

        //每10s产生一次点击事件，点击的点坐标为(0.2W - 0.8W,0.2H - 0.8 H),W/H为手机分辨率的宽高.
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    //生成点击坐标
                    int x = (int) (Math.random() * width * 0.6 + width * 0.2);
                    int y = (int) (Math.random() * height * 0.6 + height * 0.2);
                    //利用ProcessBuilder执行shell命令
                    String[] order = {
                            "input",
                            "tap",
                            "" + x,
                            "" + y
                    };
                    try {
                        new ProcessBuilder(order).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //线程睡眠10s
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**
     * 打印点击的点的坐标
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        Toast.makeText(this, "X at " + x + ";Y at " + y, Toast.LENGTH_SHORT).show();
        return true;
    }
}
