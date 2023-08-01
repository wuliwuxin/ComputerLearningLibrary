package com.asus.evlessleeping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.asus.evlessleeping.user.Login;
import com.asus.evlessleeping.user.UserData;
import com.asus.evlessleeping.user.UserDataManager;


/**
 * Created by asus on 2018/3/4.
 */
public  class ResetpwdActivity extends AppCompatActivity {
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd_old;                        //密码编辑
    private EditText mPwd_new;                        //密码编辑
    private EditText mPwdCheck;                       //密码编辑
    private Button mSureButton;                       //确定按钮
    private Button mCancelButton;                     //取消按钮
    private UserDataManager mUserDataManager;         //用户数据管理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpwd);
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd_old = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwd_new = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_check);

        mSureButton = (Button) findViewById(R.id.resetpwd_btn_sure);
        mCancelButton = (Button) findViewById(R.id.resetpwd_btn_cancel);

        mSureButton.setOnClickListener(m_resetpwd_Listener);      //注册界面两个按钮的监听事件
        mCancelButton.setOnClickListener(m_resetpwd_Listener);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
    }

    View.OnClickListener m_resetpwd_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.resetpwd_btn_sure:                       //确认按钮的监听事件
                    resetpwd_check();
                    break;
                case R.id.resetpwd_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                    Intent intent_Resetpwd_to_Login = new Intent(ResetpwdActivity.this, Login.class);    //切换Resetpwd Activity至Login Activity
                    startActivity(intent_Resetpwd_to_Login);
                    finish();
                    break;
            }
        }
    };

    public void resetpwd_check() {                                //确认按钮的监听事件
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd_old = mPwd_old.getText().toString().trim();
            String userPwd_new = mPwd_new.getText().toString().trim();
            String userPwdCheck = mPwdCheck.getText().toString().trim();
            int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd_old);
            if (result == 1) {                                             //返回1说明用户名和密码均正确,继续后续操作
                if (userPwd_new.equals(userPwdCheck) == false) {           //两次密码输入不一样
                    Toast.makeText(this, getString(R.string.pwd_not_the_same), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    UserData mUser = new UserData(userName, userPwd_new);
                    mUserDataManager.openDataBase();
                    boolean flag = mUserDataManager.updateUserData(mUser);
                    if (flag == false) {
                        Toast.makeText(this, getString(R.string.resetpwd_fail), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, getString(R.string.resetpwd_success), Toast.LENGTH_SHORT).show();
                        mUser.pwdresetFlag = 1;
                        Intent intent_Register_to_Login = new Intent(ResetpwdActivity.this, Login.class);    //切换User Activity至Login Activity
                        startActivity(intent_Register_to_Login);
                        finish();
                    }
                }
            } else if (result == 0) {                                       //返回0说明用户名和密码不匹配，重新输入
                Toast.makeText(this, getString(R.string.pwd_not_fit_user), Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    public boolean isUserNameAndPwdValid() {
        String userName = mAccount.getText().toString().trim();
        //检查用户是否存在
        int count = mUserDataManager.findUserByName(userName);
        //用户不存在时返回，给出提示文字
        if (count <= 0) {
            Toast.makeText(this, getString(R.string.name_not_exist), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_old.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_new.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_new_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}