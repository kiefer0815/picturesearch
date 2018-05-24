package com.example.administrator.picturesearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.administrator.picturesearch.R;
import com.example.administrator.picturesearch.UserManager;
import com.example.administrator.picturesearch.base.BaseDataBindingFragment;
import com.example.administrator.picturesearch.component.log.RsLogger;
import com.example.administrator.picturesearch.component.log.RsLoggerManager;
import com.example.administrator.picturesearch.component.rx.RxBus;
import com.example.administrator.picturesearch.component.rx.RxBusBaseMessage;
import com.example.administrator.picturesearch.component.rx.RxCodeConstants;
import com.example.administrator.picturesearch.databinding.FragmentHomeAccountBinding;
import com.example.administrator.picturesearch.ui.BookSearchActivity;
import com.example.administrator.picturesearch.ui.LockScreenActivity;
import com.example.administrator.picturesearch.ui.menu.NavAboutActivity;
import com.example.administrator.picturesearch.ui.menu.NavDownloadActivity;
import com.example.administrator.picturesearch.ui.menu.NavUseActivity;
import com.example.administrator.picturesearch.ui.menu.NavUserBookActivity;
import rx.functions.Action1;

/**
 * Created by kiefer on 2017/7/31.
 */

public class HomeAccountFragment extends BaseDataBindingFragment<FragmentHomeAccountBinding> implements
        View.OnClickListener{
        RsLogger logger = RsLoggerManager.getLogger();
        public static final String TAG = HomeAccountFragment.class.getSimpleName();

        @Override
        public int setContent() {
                return R.layout.fragment_home_account;
        }
        public static HomeAccountFragment newInstance() {
                return new HomeAccountFragment();
        }

        private Toolbar toolbar;


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                initId();
                initListener();
                showContentView();
                initRxBus();
        }


        private void initId() {
                toolbar = bindingView.toolbar;
        }

        private void initListener(){
                bindingView.llTitleSearch.setOnClickListener(this);
                bindingView.ivAvatar.setOnClickListener(this);
                bindingView.llAbout.setOnClickListener(this);
                bindingView.llExit.setOnClickListener(this);
                bindingView.llScanDownload.setOnClickListener(this);
                bindingView.llUse.setOnClickListener(this);
                bindingView.llUserbook.setOnClickListener(this);
                bindingView.llSetLock.setOnClickListener(this);
                bindingView.llFeedback.setOnClickListener(this);
                bindingView.setUser(UserManager.uniqueInstance().getUser());
        }

        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                case R.id.ll_title_search:
                        BookSearchActivity.start(getActivity());
                        break;
                case R.id.iv_avatar:
                        RxBus.getDefault().post(RxCodeConstants.SHOW_LOGIN, new RxBusBaseMessage());
                        break;
                case R.id.ll_scan_download://扫码下载
                        NavDownloadActivity.start(getActivity());
                        break;
                case R.id.ll_about:// 关于
                        NavAboutActivity.start(getActivity());
                        break;
                case R.id.ll_exit:// 退出
                        UserManager.uniqueInstance().handleLoginOut();
                        bindingView.setUser(UserManager.uniqueInstance().getUser());
                        break;
                case R.id.ll_use:// 使用说明
                        NavUseActivity.start(getActivity());
                        break;
                case R.id.ll_userbook:
                        NavUserBookActivity.start(getActivity());
                        break;
                case R.id.ll_set_lock:
                        LockScreenActivity.start(getActivity(),"0");
                        break;

                default:
                        break;
                }
        }

        private void initRxBus(){
                RxBus.getDefault().toObservable(RxCodeConstants.UPDATE_LOGIN_STATUS, RxBusBaseMessage.class)
                        .subscribe(new Action1<RxBusBaseMessage>() {
                                @Override
                                public void call(RxBusBaseMessage integer) {
                                        bindingView.setUser(UserManager.uniqueInstance().getUser());
                                }
                        });
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
