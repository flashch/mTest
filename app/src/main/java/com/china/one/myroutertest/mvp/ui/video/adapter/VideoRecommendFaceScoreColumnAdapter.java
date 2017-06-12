package com.china.one.myroutertest.mvp.ui.video.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.china.one.common.utils.UiUtils;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.app.widgetutils.refreshview.recyclerview.BaseRecyclerAdapter;
import com.china.one.myroutertest.mvp.model.entity.VideoFaceScoreColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：  颜值栏目
 * 备注消息：
 * 修改时间：2017/1/17 下午1:44
 **/
public class VideoRecommendFaceScoreColumnAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {
    private List<VideoFaceScoreColumn> mHomeFaceScoreColumn;
    private Context context;

    public VideoRecommendFaceScoreColumnAdapter(Context context) {
        this.context = context;
        this.mHomeFaceScoreColumn = new ArrayList<VideoFaceScoreColumn>();
    }

    public void getFaceScoreColumn(List<VideoFaceScoreColumn> mHomeFaceScoreColumn) {
        this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    public void getFaceScoreColumnLoadMore(List<VideoFaceScoreColumn> mHomeFaceScoreColumn) {
//          this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new FaceScoreColumnHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        return new FaceScoreColumnHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_recommend_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof FaceScoreColumnHolder) {
            bindFaceScoreHolder((FaceScoreColumnHolder) holder, position);
        }
    }

    private void bindFaceScoreHolder(FaceScoreColumnHolder holder, final int position) {
        holder.img_item_gridview.setImageURI(Uri.parse(mHomeFaceScoreColumn.get(position).getVertical_src()));
        holder.tv_column_item_nickname.setText(mHomeFaceScoreColumn.get(position).getNickname());
        holder.tv_online_num.setText("22人");
        holder.img_item_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UiUtils.makeText("点击条目"+position);
            }
        });

    }

    @Override
    public int getAdapterItemCount() {
        return mHomeFaceScoreColumn.size();
    }

    public class FaceScoreColumnHolder extends RecyclerView.ViewHolder {
        public ImageView img_item_gridview;
        public TextView tv_column_item_nickname;
        public TextView tv_online_num;

        public FaceScoreColumnHolder(View view) {
            super(view);
            img_item_gridview = (ImageView) view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = (TextView) view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = (TextView) view.findViewById(R.id.tv_online_num);
        }
    }
}
