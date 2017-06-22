package com.example.administrator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textView_getPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execShellCmd("input text  'helloworld!' ");
            }
        });
        findViewById(R.id.textView_sendEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                execShellCmd("getevent -p");
//                execShellCmd("sendevent /dev/input/event0 1 158 1");
//                execShellCmd("sendevent /dev/input/event0 1 158 0");
                execShellCmd("input keyevent 3");//home
//                execShellCmd("input text  'helloworld!' ");
//                execShellCmd("input tap 168 252");
//                execShellCmd("input swipe 100 250 200 280");
            }
        });
    }

    /**
     * 执行shell命令
     *
     * @param cmd
     */
    private void execShellCmd(String cmd) {

        Toast.makeText(this, cmd,Toast.LENGTH_LONG).show();
        try {
            // 申请获取root权限，这一步很重要，不然会没有作用
            Process process = Runtime.getRuntime().exec("su");
            // 获取输出流
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
