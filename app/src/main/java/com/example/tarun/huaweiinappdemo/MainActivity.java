package com.example.tarun.huaweiinappdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sdk.commplatform.CallbackListener;
import com.sdk.commplatform.Commplatform;
import com.sdk.commplatform.ErrorCode;
import com.sdk.commplatform.entry.AppInfo;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize SDK
        // check network connection first
        AppInfo appInfo = new AppInfo();
        appInfo.setAppId(1000066584); // Application ID
        appInfo.setAppKey("09093333263a45d1aa8a309822bc51ad");// Application key
        appInfo.setCtx(this);

        Commplatform.getInstance().Init(0,appInfo,new CallbackListener<Integer>()
        {
            @Override
            public void callback(int paramInt, Integer paramT)
            {
                if (paramInt == ErrorCode.COM_PLATFORM_SUCCESS)
                {
                   /* startActivity(new Intent(StartActivity.this,
                            WelcomeActivity.class)); // Initialize the game
                    StartActivity.this.finish();*/
                    Log.i(TAG, "Initialization successful");
                    Log.i(TAG, "App Name: "+Commplatform.getInstance().getAppName());
                }
                else if (paramInt == ErrorCode.COM_PLATFORM_ERROR_FORCE_CLOSE)
                {
                    //init failure
                    finish();
                }
                else if (paramInt ==
                        ErrorCode.COM_PLATFORM_ERROR_ONLINE_CHECK_FAILURE)
                {
                    //network not connect
                    finish();
                }
                else
                {
                    Log.i(TAG, "Init other");
                    Toast.makeText(MainActivity.this,
                            "Game init failed!" + paramInt,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
