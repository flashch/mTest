package com.china.one.login_module;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private VerificationCodeView mNetVerifycodeview;
    private ProgressBar mNetPregressbar;
    private Handler handler=new Handler();
    private TextView mTvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mNetVerifycodeview= (VerificationCodeView) findViewById(R.id.net_verifycodeview);
        mNetPregressbar= (ProgressBar) findViewById(R.id.net_pregressbar);
        mTvLogin= (TextView) findViewById(R.id.tv_login);
        mTvLogin.setOnClickListener(this);
        mNetVerifycodeview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.net_verifycodeview:
                initVerifycode();
                break;
            case R.id.tv_login:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                break;
        }
    }

    private void initVerifycode() {
        mNetPregressbar.setVisibility(View.VISIBLE);
        mNetVerifycodeview.setVisibility(View.GONE);
        loadNetCode();
    }
    /**
     * @Name: loadNetCode
     * @Description: 网络请求获取验证码
     * @Author: chenhao
     * @Create Date: 2017/3/9 0009 14:36
     */
    private void loadNetCode() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(1500);
                mNetVerifycodeview.refreshCode();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changeVerification();
                    }
                });
            }
        }.start();
    }

    private void changeVerification() {
            mNetPregressbar.setVisibility(View.GONE);
            mNetVerifycodeview.setVisibility(View.VISIBLE);
        }
}

