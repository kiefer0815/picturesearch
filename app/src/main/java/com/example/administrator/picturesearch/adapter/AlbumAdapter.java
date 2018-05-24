package com.example.administrator.picturesearch.adapter;

import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.picturesearch.R;
import com.example.administrator.picturesearch.base.BaseRecyclerViewAdapter;
import com.example.administrator.picturesearch.base.BaseRecyclerViewHolder;
import com.example.administrator.picturesearch.bean.AlbumBean;
import com.example.administrator.picturesearch.databinding.ItemAlbumBinding;
import com.example.administrator.picturesearch.ui.AlbumDetailActivity;

/**
 * Created by kiefer on 2017/8/2.
 */

public class AlbumAdapter extends BaseRecyclerViewAdapter<AlbumBean>{

        @Override
        public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(parent, R.layout.item_album);
        }

        private class ViewHolder extends BaseRecyclerViewHolder<AlbumBean, ItemAlbumBinding> {

                ViewHolder(ViewGroup parent, int item_android) {
                        super(parent, item_android);
                }

                @Override
                public void onBindViewHolder(final AlbumBean bean, int position) {
                        binding.setBean(bean);
                        /**
                         * 当数据改变时，binding会在下一帧去改变数据，如果我们需要立即改变，就去调用executePendingBindings方法。
                         */
                        binding.executePendingBindings();
                        binding.ivTopPhoto.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        AlbumDetailActivity.start(v.getContext(),bean);
                                }
                        });
                }
        }
}
