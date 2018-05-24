package com.example.administrator.picturesearch.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.ViewGroup;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.example.administrator.picturesearch.BuildConfig;
import com.example.administrator.picturesearch.R;
import com.example.administrator.picturesearch.base.BaseRecyclerViewAdapter;
import com.example.administrator.picturesearch.base.BaseRecyclerViewHolder;
import com.example.administrator.picturesearch.bean.RecommendBean;
import com.example.administrator.picturesearch.databinding.RecommendItemBinding;
import com.example.administrator.picturesearch.utils.CommonUtils;
import com.example.administrator.picturesearch.utils.PerfectClickListener;
import com.xiaochen.progressroundbutton.AnimDownloadProgressButton;

import java.io.File;

/**
 * Created by kiefer on 2017/9/19.
 */

public class RecommendAdapter extends BaseRecyclerViewAdapter<RecommendBean> {

        private Activity activity;
        FileDownloadListener fileDownloadListener = new FileDownloadSampleListener(){
                @Override
                protected void started(BaseDownloadTask task) {
                        super.started(task);
                }

                @Override
                protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                }

                @Override
                protected void completed(BaseDownloadTask task) {
                        super.completed(task);

                }
        };

        public RecommendAdapter(Activity activity) {
                this.activity = activity;
        }

        @Override
        public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(parent, R.layout.recommend_item);
        }

        private class ViewHolder extends BaseRecyclerViewHolder<RecommendBean, RecommendItemBinding> {

                ViewHolder(ViewGroup context, int layoutId) {
                        super(context, layoutId);
                }

                @Override
                public void onBindViewHolder(final RecommendBean positionData, final int position) {
                        if (positionData != null) {
                                binding.setRecommendBean(positionData);

                                binding.viewColor.setBackgroundColor(CommonUtils.randomColor());
                                binding.animBtn.setCurrentText("下载");


                                binding.animBtn.setOnClickListener(new PerfectClickListener() {
                                        @Override
                                        protected void onNoDoubleClick(View v) {
                                                FileDownloader.getImpl().create(positionData.getDownload_url())
                                                        .setPath(FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "download"
                                                                + File.separator+positionData.getTitle() +".apk")
                                                        .setListener(new FileDownloadSampleListener(){
                                                                @Override
                                                                protected void started(BaseDownloadTask task) {
                                                                        super.started(task);
                                                                        binding.animBtn.setCurrentText("下载中");
                                                                }

                                                                @Override
                                                                protected void progress(BaseDownloadTask task,
                                                                        int soFarBytes, int totalBytes) {
                                                                        super.progress(task, soFarBytes, totalBytes);
                                                                        float num= (float)soFarBytes/totalBytes;
                                                                        binding.animBtn.setState(AnimDownloadProgressButton.DOWNLOADING);
                                                                        binding.animBtn.setProgressText("" , (num*100));
                                                                }

                                                                @Override
                                                                protected void error(BaseDownloadTask task,
                                                                        Throwable e) {
                                                                        super.error(task, e);
                                                                        binding.animBtn.setCurrentText("下载");
                                                                        binding.animBtn.setState(AnimDownloadProgressButton.NORMAL);
                                                                }

                                                                @Override
                                                                protected void completed(BaseDownloadTask task) {
                                                                        super.completed(task);
                                                                        binding.animBtn.setCurrentText("下载完成");
                                                                        binding.animBtn.setState(AnimDownloadProgressButton.NORMAL);
                                                                        installApk(new File(task.getTargetFilePath()));
                                                                }
                                                        }).start();

                                        }
                                });
                        }

        }

                /**
                 * 安装软件
                 *
                 * @param apkFile
                 */
                private void installApk(File apkFile) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        //判断是否是AndroidN以及更高的版本
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                Uri contentUri = FileProvider
                                        .getUriForFile(activity, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
                                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                        } else {
                                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                        // 执行意图进行安装
                        activity.startActivity(intent);
                }
        }
}

