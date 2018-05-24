package com.example.administrator.picturesearch.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.example.administrator.picturesearch.AppContext;
import com.example.administrator.picturesearch.R;
import com.example.administrator.picturesearch.base.BaseDataBindingActivity;
import com.example.administrator.picturesearch.component.download.UpdateManager;
import com.example.administrator.picturesearch.databinding.ActivityNavAboutBinding;
import com.example.administrator.picturesearch.utils.CommonUtils;

/**
 * Created by kiefer on 2017/7/25.
 */

public class NavSupportActivity extends BaseDataBindingActivity<ActivityNavAboutBinding> {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_nav_about);
                showContentView();
                setTitle("关于电子书阅读器");
                bindingView.tvVersionName.setText("当前版本 V" + CommonUtils.getVersionCode(AppContext.applicationContext));

                // 直接写在布局文件里会很耗内存
                Glide.with(this).load(R.mipmap.ic_launcher).into(bindingView.ivIcon);

                initListener();
        }

        private void initListener() {

                bindingView.tvNewVersion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                UpdateManager.getInstance().checkUpdate(true);
                        }
                });
        }


        public static void start(Context mContext) {
                Intent intent = new Intent(mContext, NavAboutActivity.class);
                mContext.startActivity(intent);
        }
        @Override
        public void onResume() {
                super.onResume();
        }
        @Override
        public void onPause() {
                super.onPause();
        }
}
