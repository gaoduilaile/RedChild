package com.itheima10.team17.redchild.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.CommentEntity;
import com.itheima10.team17.redchild.bean.ProductEntity;
import com.itheima10.team17.redchild.util.ViewHolderUtil;

import java.util.List;

/**
 * Created by yu895 on 2016/6/20.
 */
public class CommentAdapter extends BaseAdapter {
    private List<CommentEntity.ProductBean> mData;
    private Context mContext;
    private TextView mMTvCommentUser;
    private TextView mMTvCommentDetail;

    public CommentAdapter(Context context,List<CommentEntity.ProductBean> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CommentEntity.ProductBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(),R.layout.item_comment, null);
        }
        mMTvCommentUser = ViewHolderUtil.getView(convertView, R.id.tv_comment_user);
        mMTvCommentDetail = ViewHolderUtil.getView(convertView, R.id.tv_comment_detail);
        CommentEntity.ProductBean item = getItem(position);
        mMTvCommentUser.setText(item.getUsername());
        mMTvCommentDetail.setText(item.getContent());

        return convertView;
    }

    public void clear() {
        mData.clear();
    }

    public void addAll(List<CommentEntity.ProductBean> data) {
        mData.addAll(data);
    }

}
