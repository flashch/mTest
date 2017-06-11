package com.china.one.myroutertest.mvp.ui.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.china.one.common.utils.UiUtils;
import com.china.one.common.widget.imageloader.ImageLoader;
import com.china.one.common.widget.imageloader.glide.GlideImageConfig;
import com.china.one.common.widget.imageloader.glide.GlideImageLoaderStrategy;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.mvp.model.entity.VideoRecommendHotCate;

import java.util.List;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/17 下午5:37
 **/
public class VideoNgBarAdapter extends BaseAdapter {

    private List<VideoRecommendHotCate> mHomeCate;
    private LayoutInflater mInflater;
    private Context context;
    //    页数下标
    private int mIndex;
    //    每页显示多少
    private int mPagerSize;
    private ImageLoader mImageLoader;

    public VideoNgBarAdapter(Context context, List<VideoRecommendHotCate> homeCate, int mIndex, int mPageSize) {
        this.context = context;
        this.mHomeCate = homeCate;
        this.mIndex = mIndex;
        this.mPagerSize = mPageSize;
        mImageLoader=new ImageLoader(new GlideImageLoaderStrategy());
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mHomeCate.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (mHomeCate.size() - mIndex * mPagerSize);
    }

    @Override
    public Object getItem(int position) {
        return mHomeCate.get(position + mIndex * mPagerSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPagerSize;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_gridview, viewGroup, false);
            holder = new ViewHolder();
            holder.proName = (TextView) view.findViewById(R.id.tv_item_name);
            holder.imgIcon = (ImageView) view.findViewById(R.id.img_item_gridview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final int pos = position + mIndex * mPagerSize;
        if (pos == 15) {
            holder.proName.setText("全部分类");
            holder.imgIcon.setBackgroundResource(R.drawable.ic_launcher);
        } else {
            holder.proName.setText(mHomeCate.get(pos).getTag_name());
            mImageLoader.loadImage(context, GlideImageConfig
                    .builder()
                    .url(mHomeCate.get(pos).getIcon_url())
                    .imageView(holder.imgIcon)
                    .build());
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos == 15) {
                    Toast.makeText(context, "你点击了" + "全部分类", Toast.LENGTH_LONG).show();
                } else {
              //点击
                    UiUtils.makeText("点击条目"+position);
                }
            }
        });
        return view;
    }
    /**
     * 定义ViewHodle
     */
    class ViewHolder {
        private TextView proName;

        private ImageView imgIcon;

    }

}
