package com.itheima10.team17.redchild.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.bean.Waiter;
import com.itheima10.team17.redchild.util.AndroidMPermissionUtil;
import com.itheima10.team17.redchild.view.TitleBar;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceActivity extends AppCompatActivity implements AndroidMPermissionUtil.PermissionResultListener{

    public static final List<Waiter> Waiters = new ArrayList<>();

    static {
        Waiters.add(new Waiter(R.drawable.kefu0, "帆帆", R.color.red, "13155835360", "单身狗,求拖走"));
        Waiters.add(new Waiter(R.drawable.kefu1, "阿权", R.color.red, "13155835361", "单身狗,求拖走"));
        Waiters.add(new Waiter(R.drawable.kefu2, "小班", R.color.red, "13155835362", "单身狗,求拖走"));
        Waiters.add(new Waiter(R.drawable.kefu3, "阿伟", R.color.red, "13155835363", "单身狗,求拖走"));
        Waiters.add(new Waiter(R.drawable.kefu4, "阿徽", R.color.red, "13155835364", "单身狗,求拖走"));
        Waiters.add(new Waiter(R.drawable.kefu5, "涛涛", R.color.red, "13155835365", "单身狗,求拖走"));
        Waiters.add(new Waiter(R.drawable.kefu6, "可爱多", R.color.red, "13155835366", "单身狗,求拖走"));
        Waiters.add(new Waiter(R.drawable.kefu7, "多可爱", R.color.red, "13155835367", "单身狗,求拖走"));
    }

    private TitleBar        mTb;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;
    private AndroidMPermissionUtil mAndroidMPermissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
        mAndroidMPermissionUtil = new AndroidMPermissionUtil();
        mTb = (TitleBar) findViewById(R.id.titlebar_customerService);
        final ListView friends = (ListView) findViewById(R.id.lv_customerService);

        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        friends.setAdapter(new WaiterAdapter(this, Waiters, settings));
        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAndroidMPermissionUtil.request( CustomerServiceActivity.this, "我要拨打电话权限哦"
                        , Manifest.permission.READ_PHONE_STATE
                        , Manifest.permission.READ_CONTACTS
                        , Manifest.permission.ACCESS_NETWORK_STATE
                        , Manifest.permission.CHANGE_CONFIGURATION
                        , Manifest.permission.CALL_PHONE
                        , Manifest.permission.SYSTEM_ALERT_WINDOW);
                Waiter f = (Waiter) friends.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "1315583536" + position));
                startActivity(intent);
            }
        });



        initListener();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initListener() {
        mTb.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    class WaiterAdapter extends BaseFlipAdapter<Waiter> {

        private final int PAGES = 3;

        public WaiterAdapter(Context context, List<Waiter> items, FlipSettings settings) {
            super(context, items, settings);
        }

        @Override
        public View getPage(int position, View convertView, ViewGroup parent, Waiter friend1, Waiter friend2) {
            final FriendsHolder holder;

            if (convertView == null) {
                holder = new FriendsHolder();
                convertView = getLayoutInflater().inflate(R.layout.friends_merge_page, parent, false);
                holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
                holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);

                holder.infoPage = getLayoutInflater().inflate(R.layout.friends_info, parent, false);
                holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);
                holder.number = ((TextView) holder.infoPage.findViewById(R.id.number));
                holder.desc = ((TextView) holder.infoPage.findViewById(R.id.desc));

                convertView.setTag(holder);
            } else {
                holder = (FriendsHolder) convertView.getTag();
            }

            switch (position) {
                // Merged page with 2 friends
                case 1:
                    holder.leftAvatar.setImageResource(friend1.getAvatar());
                    if (friend2 != null)
                        holder.rightAvatar.setImageResource(friend2.getAvatar());
                    break;
                default:
                    fillHolder(holder, position == 0 ? friend1 : friend2);
                    holder.infoPage.setTag(holder);
                    return holder.infoPage;
            }
            return convertView;
        }

        @Override
        public int getPagesCount() {
            return PAGES;
        }

        private void fillHolder(FriendsHolder holder, Waiter friend) {
            if (friend == null)
                return;

            holder.number.setText(friend.getNumber());
            holder.infoPage.setBackgroundColor(getResources().getColor(friend.getBackground()));
            holder.nickName.setText(friend.getNickname());
            holder.desc.setText(friend.getDesc());
        }

        class FriendsHolder {
            ImageView leftAvatar;
            ImageView rightAvatar;
            View      infoPage;

            TextView number;
            TextView nickName;
            TextView desc;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
