package com.itheima10.team17.redchild.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima10.team17.redchild.R;

/**
 * Created by asus on 2016-06-17.
 */
public class SearchListView extends ListView {

    private View footerView;
    private int mFooterViewHeight;
    //private onCleanListener mClean;
    private SearchListView.onCleanListener onCleanListener;

    public SearchListView(Context context) {
        super(context);
        init();
    }
    public SearchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        initFooter();//增加脚布局

    }

    private void initFooter() {
        footerView = View.inflate(getContext(), R.layout.search_history_listview_item, null);
        footerView.measure(0,0);
        mFooterViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0,0,0,0);
        this.addFooterView(footerView);

        //获取脚布局对象设置点击事件
        TextView mFootView = (TextView) footerView.findViewById(R.id.tv_searchfragment_footview);
        mFootView.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                onCleanListener.cleanHistory();
            }
        });
    }
    //清空历史数据
    public interface onCleanListener {
       public  void  cleanHistory();
    }

    public void setOnCleanListener(onCleanListener onCleanListener) {
        this.onCleanListener = onCleanListener;
    }
}
