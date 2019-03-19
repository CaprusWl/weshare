package com.hqbd.weshare;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hqbd.weshare.fragment.HomeFragment;
import com.hqbd.weshare.fragment.ResourceFragment;
import com.hqbd.weshare.fragment.UserFragment;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button btn_user_center, btn_resource, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sendCode(this);

        setContentView(R.layout.activity_main);

        replaceFragment(new HomeFragment());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hideStatusBar();

        btn_home = findViewById(R.id.btn_home);
        btn_resource = findViewById(R.id.btn_resource);
        btn_user_center = findViewById(R.id.btn_user_center);

        btn_home.setOnClickListener(this);
        btn_resource.setOnClickListener(this);
        btn_user_center.setOnClickListener(this);
    }

    public void sendCode(final Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                } else {
                    Toast.makeText(context, "验证失败请重试！", Toast.LENGTH_SHORT).show();
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }

    public void replaceFragment(Fragment mFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragment, mFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                replaceFragment(new HomeFragment());
                break;
            case R.id.btn_resource:
                replaceFragment(new ResourceFragment());
                break;
            case R.id.btn_user_center:
                replaceFragment(new UserFragment());
                break;
            case R.id.btn_paper:
            case R.id.btn_reply:
            case R.id.btn_top_res:
                replaceFragment(new ResourceFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
