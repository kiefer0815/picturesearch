package com.example.administrator.picturesearch.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.administrator.picturesearch.R;
import com.example.administrator.picturesearch.base.BaseDataBindingActivity;
import com.example.administrator.picturesearch.databinding.ActivityNavUseBinding;

/**
 * Created by kiefer on 2017/7/7.
 */

public class NavUseActivity  extends BaseDataBindingActivity<ActivityNavUseBinding> {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_nav_use);
                showContentView();
                setTitle("使用须知");


        }



        public static void start(Context mContext) {
                Intent intent = new Intent(mContext, NavUseActivity.class);
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

