package com.china.one.myroutertest.mvp.ui.adapter;
import android.view.View;
import com.china.one.common.base.BaseHolder;
import com.china.one.common.base.DefaultAdapter;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.mvp.model.entity.User;
import com.china.one.myroutertest.mvp.ui.holder.UserItemHolder;
import java.util.List;

/**
 * Created by jess on 9/4/16 12:57
 * Contact with jess.yan.effort@gmail.com
 */
public class UserAdapter extends DefaultAdapter<User> {
    public UserAdapter(List<User> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<User> getHolder(View v, int viewType) {
        return new UserItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycle_list;
    }
}
