package com.itheima10.team17.redchild.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.ui.base.BaseActivity;
import com.itheima10.team17.redchild.view.TitleBar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FeedBackActivity extends BaseActivity {

    private int i = -1;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        mButton = (Button) findViewById(R.id.btn_feed_back_commit);
        TitleBar mTitlebarFeedback = (TitleBar) findViewById(R.id.titlebar_feedback);
        EditText mEtFeedBackText = (EditText) findViewById(R.id.et_feed_back_text);
        EditText mEtFeedBackNumber = (EditText) findViewById(R.id.et_feed_back_number);

        mTitlebarFeedback.setOnBtnClickListener(new TitleBar.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {
                
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEtFeedBackText.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(FeedBackActivity.this, "您还未填写评论", Toast.LENGTH_SHORT).show();
                    return;
                }
                String number = mEtFeedBackNumber.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(FeedBackActivity.this, "您还未填写联系方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                SweetAlertDialog dialog = new SweetAlertDialog(FeedBackActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("提交成功");
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
               dialog.show();
            }
        });
    }
}
