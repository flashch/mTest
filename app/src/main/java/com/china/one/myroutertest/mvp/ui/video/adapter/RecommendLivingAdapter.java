package com.china.one.myroutertest.mvp.ui.video.adapter;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.china.one.common.utils.UiUtils;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.app.refreshview.recyclerview.BaseRecyclerAdapter;
import com.china.one.myroutertest.mvp.model.entity.VideoFaceScoreColumn;
import com.china.one.myroutertest.mvp.model.entity.VideoHotColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2017/1/12 下午3:33
 **/
public class RecommendLivingAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    //    最热栏目
    private List<VideoHotColumn> mHomeHotColumn;
    //    颜值栏目
    private List<VideoFaceScoreColumn> mHomeFaceScoreColumn;
    private Context context;
    //    最热adapter
    private VideoRecommendHotColumnAdapter mHotColumnAdapter;
    //    颜值
    private VideoRecommendFaceScoreColumnAdapter mFaceScoreColumnAdapter;


    /**
     * 指定 type类型
     */
//    最热栏目
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;

    public RecommendLivingAdapter(Context context) {
        this.context = context;
        mHomeHotColumn = new ArrayList<VideoHotColumn>();
        mHomeFaceScoreColumn = new ArrayList<VideoFaceScoreColumn>();
        mFaceScoreColumnAdapter = new VideoRecommendFaceScoreColumnAdapter(context);
    }

    /**
     * 最热栏目
     * @param mHomeHotColumn
     */
    public void getHomeHotColumn(List<VideoHotColumn> mHomeHotColumn) {
        this.mHomeHotColumn.clear();
        this.mHomeHotColumn.addAll(mHomeHotColumn);
        notifyDataSetChanged();
    }

    /**
     * 颜值栏目
     *
     * @param mHomeFaceScoreColumn
     */
    public void getFaceScoreColmun(List<VideoFaceScoreColumn> mHomeFaceScoreColumn) {
        this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        mFaceScoreColumnAdapter.getFaceScoreColumn(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new ColumnViewHolder(view);
    }

    @Override
    public ColumnViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        switch (viewType) {
            case TYPE_1:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_recommend, parent, false));
            case TYPE_2:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_recommend, parent, false));
            case TYPE_3:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_recommend, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof ColumnViewHolder && position == 0) {
            bindColumnHolder((ColumnViewHolder) holder, position);
        } else if (holder instanceof ColumnViewHolder && position == 1) {
            bindFaceScoreColumnHolder((ColumnViewHolder) holder, position, isItem);
        }
    }

    /**
     * 颜值 栏目
     *
     * @param holder
     * @param position
     * @param isItem
     */
    private void bindFaceScoreColumnHolder(ColumnViewHolder holder, int position, boolean isItem) {

        holder.img_column_icon.setImageResource(R.drawable.ic_launcher);
        holder.tv_column_name.setText("颜值");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false));
        holder.rv_column_list.setAdapter(mFaceScoreColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UiUtils.makeText("颜值加载更多");
            }
        });
    }

    @Override
    public int getAdapterItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else if (position == 1) {
            return TYPE_2;
        }
        return TYPE_3;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    /**
     * 栏目
     *
     * @param holder
     * @param position
     */
    private void bindColumnHolder(ColumnViewHolder holder, int position) {
        holder.img_column_icon.setImageResource(R.drawable.ic_launcher);
        holder.tv_column_name.setText("最热");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false));
        mHotColumnAdapter = new VideoRecommendHotColumnAdapter(holder.rv_column_list.getContext(), mHomeHotColumn);
        holder.rv_column_list.setAdapter(mHotColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.makeText("最热加载更多");
            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return 2;
    }

    public class ColumnViewHolder extends RecyclerView.ViewHolder {
        //       栏目 Icon
        public ImageView img_column_icon;
        //        栏目 名称
        public TextView tv_column_name;
        //        加载更多
        public RelativeLayout rl_column_more;
        //        栏目列表
        public RecyclerView rv_column_list;

        public LinearLayout item_home_recommed_girdview;

        public ColumnViewHolder(View itemView) {
            super(itemView);
            img_column_icon = (ImageView) itemView.findViewById(R.id.img_column_icon);
            tv_column_name = (TextView) itemView.findViewById(R.id.tv_column_name);
            rl_column_more = (RelativeLayout) itemView.findViewById(R.id.rl_column_more);
            rv_column_list = (RecyclerView) itemView.findViewById(R.id.rv_column_list);
            item_home_recommed_girdview = (LinearLayout) itemView.findViewById(R.id.item_home_recommed_girdview);
        }
    }
}
