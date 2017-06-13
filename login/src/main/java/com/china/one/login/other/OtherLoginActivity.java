package com.china.one.login.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.china.one.login_module.R;
import com.china.one.login.other.otherlogin.LoginListener;
import com.china.one.login.other.otherlogin.LoginPlatform;
import com.china.one.login.other.otherlogin.LoginResult;
import com.china.one.login.other.otherlogin.result.QQToken;
import com.china.one.login.other.otherlogin.result.QQUser;


public class OtherLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginListener mLoginListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ShareConfig config = ShareConfig.instance()
                .weiboId("2634641653")
                .weiboRedirectUrl("edadfd9ccb445674d2e5b5093ff77309");
        ShareManager.init(config);

        findViewById(R.id.login_qq).setOnClickListener(this);
        findViewById(R.id.login_weibo).setOnClickListener(this);
        findViewById(R.id.login_wx).setOnClickListener(this);

        mLoginListener = new LoginListener() {
            @Override
            public void loginSuccess(LoginResult result) {
                Toast.makeText(OtherLoginActivity.this,
                        result.getUserInfo() != null ? result.getUserInfo().getNickname()
                                : "" + "登录成功", Toast.LENGTH_SHORT).show();

                // 处理result
                switch (result.getPlatform()) {
                    case LoginPlatform.QQ:
                        QQUser user = (QQUser) result.getUserInfo();
                        QQToken token = (QQToken) result.getToken();
                        break;
                    case LoginPlatform.WEIBO:
                        // 处理信息
                        break;
                    case LoginPlatform.WX:
                        break;
                }
            }

            @Override
            public void loginFailure(Exception e) {
                Toast.makeText(OtherLoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginCancel() {
                Toast.makeText(OtherLoginActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.login_qq){
            LoginUtil.login(this, LoginPlatform.QQ, mLoginListener);
        }else if(id==R.id.login_weibo){
            LoginUtil.login(this, LoginPlatform.WEIBO, mLoginListener, false);
        }else if(id==R.id.login_wx){
            LoginUtil.login(this, LoginPlatform.WX, mLoginListener);
        }
    }
}
