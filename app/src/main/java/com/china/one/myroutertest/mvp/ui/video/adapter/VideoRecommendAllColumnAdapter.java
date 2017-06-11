package com.china.one.myroutertest.mvp.ui.video.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.china.one.common.base.BaseHolder;
import com.china.one.common.utils.UiUtils;
import com.china.one.common.widget.imageloader.ImageLoader;
import com.china.one.common.widget.imageloader.glide.GlideImageConfig;
import com.china.one.common.widget.imageloader.glide.GlideImageLoaderStrategy;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.mvp.model.entity.VideoRecommendHotCate;

import java.util.List;

/**
 * 全部栏目
 */
public class VideoRecommendAllColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VideoRecommendHotCate.RoomListEntity> mRommListEntity;
    private Context context;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架

    public VideoRecommendAllColumnAdapter(Context context, List<VideoRecommendHotCate.RoomListEntity> mRommListEntity) {
        this.context = context;
        this.mRommListEntity = mRommListEntity;
        mImageLoader = new ImageLoader(new GlideImageLoaderStrategy());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotColumnHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_recommend_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HotColumnHolder) {
            bindHotColumun((HotColumnHolder) holder, position);
        }
    }

    private void bindHotColumun(HotColumnHolder holder, int position) {
            holder.setData(mRommListEntity.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mRommListEntity.size();
    }

    public class HotColumnHolder extends BaseHolder<VideoRecommendHotCate.RoomListEntity> {
        //        图片
        public ImageView img_item_gridview;
        //        房间名称
        public TextView tv_column_item_nickname;
        //        在线人数
        public TextView tv_online_num;
        //        昵称
        public TextView tv_nickname;
        //        Icon
        public RelativeLayout rl_live_icon;

        public HotColumnHolder(View view) {
            super(view);
            img_item_gridview = (ImageView) view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = (TextView) view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = (TextView) view.findViewById(R.id.tv_online_num);
            tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
            rl_live_icon = (RelativeLayout) view.findViewById(R.id.rl_live_icon);
        }

        @Override
        public void setData(VideoRecommendHotCate.RoomListEntity data, int position) {
            mImageLoader.loadImage(context, GlideImageConfig
                    .builder()
                    .url(data.getRoom_src())
                    .imageView(img_item_gridview)
                    .build());

            tv_column_item_nickname.setText(data.getRoom_name());
            tv_nickname.setText(data.getNickname());
            tv_online_num.setText(data.getOnline()+"");
            if (data.getCate_id().equals("181")) {
                rl_live_icon.setBackgroundResource(R.drawable.search_header_live_type_mobile);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UiUtils.makeText("点击" + position);
                }
            });
        }
    }
}
