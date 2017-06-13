package com.itheima10.team17.redchild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima10.team17.redchild.R;
import com.itheima10.team17.redchild.constant.Constant;
import com.itheima10.team17.redchild.view.TitleBar;

public class Checkout_InvoiceInfo extends AppCompatActivity implements TitleBar.OnBtnClickListener, View.OnClickListener {


    private String mCurrInvoiceType;
    private String mCurrInvoiceDesc;
    public static final String INVOICEHead = "INVOICEHead";

    private TitleBar     mMainTitlebar;
    private LinearLayout mLlInvoicePerson;
    private TextView     mTvInvoicePersonDesc;
    private ImageView    mIvInvoicePersonArrow;
    private LinearLayout mLlInvoiceUnit;
    private TextView     mTvInvoiceUnitDesc;
    private ImageView    mIvInvoiceUnitArrow;
    private LinearLayout mLlInvoiceBook;
    private TextView     mTvInvoiceBookDesc;
    private ImageView    mIvInvoiceBookArrow;
    private LinearLayout mLlInvoiceSound;
    private TextView     mTvInvoiceSoundDesc;
    private ImageView    mIvInvoiceSoundArrow;
    private LinearLayout mLlInvoiceGame;
    private TextView     mTvInvoiceGameDesc;
    private ImageView    mIvInvoiceGameArrow;
    private LinearLayout mLlInvoiceSoftware;
    private TextView     mTvInvoiceSoftwareDesc;
    private ImageView    mIvInvoiceSoftwareArrow;
    private LinearLayout mLlInvoiceMaterial;
    private TextView     mTvInvoiceMaterialDesc;
    private ImageView    mIvInvoiceMaterialArrow;
    private EditText mEt_invoiceHead;
    private LinearLayout mLl_Invoice_Noneed;
    private TextView mTv_Invoice_Noneed;
    private ImageView mIv_Invoice_Noneed;
    private TextView mTv_invoice_detail;
    private boolean mNoNeedIsChecked;
    private LinearLayout mLl_invoice_headname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_invoice_info);
        initView();

    }

    private void initView() {
        mMainTitlebar = (TitleBar) findViewById(R.id.main_titlebar);
        mLlInvoicePerson = (LinearLayout) findViewById(R.id.ll_invoice_person);
        mTvInvoicePersonDesc = (TextView) findViewById(R.id.tv_invoice_person_desc);
        mIvInvoicePersonArrow = (ImageView) findViewById(R.id.iv_invoice_person_arrow);
        mLlInvoiceUnit = (LinearLayout) findViewById(R.id.ll_invoice_unit);
        mTvInvoiceUnitDesc = (TextView) findViewById(R.id.tv_invoice_unit_desc);
        mIvInvoiceUnitArrow = (ImageView) findViewById(R.id.iv_invoice_unit_arrow);
        mLlInvoiceBook = (LinearLayout) findViewById(R.id.ll_invoice_book);
        mTvInvoiceBookDesc = (TextView) findViewById(R.id.tv_invoice_book_desc);
        mIvInvoiceBookArrow = (ImageView) findViewById(R.id.iv_invoice_book_arrow);
        mLlInvoiceSound = (LinearLayout) findViewById(R.id.ll_invoice_sound);
        mTvInvoiceSoundDesc = (TextView) findViewById(R.id.tv_invoice_sound_desc);
        mIvInvoiceSoundArrow = (ImageView) findViewById(R.id.iv_invoice_sound_arrow);
        mLlInvoiceGame = (LinearLayout) findViewById(R.id.ll_invoice_game);
        mTvInvoiceGameDesc = (TextView) findViewById(R.id.tv_invoice_game_desc);
        mIvInvoiceGameArrow = (ImageView) findViewById(R.id.iv_invoice_game_arrow);
        mLlInvoiceSoftware = (LinearLayout) findViewById(R.id.ll_invoice_software);
        mTvInvoiceSoftwareDesc = (TextView) findViewById(R.id.tv_invoice_software_desc);
        mIvInvoiceSoftwareArrow = (ImageView) findViewById(R.id.iv_invoice_software_arrow);
        mLlInvoiceMaterial = (LinearLayout) findViewById(R.id.ll_invoice_material);
        mTvInvoiceMaterialDesc = (TextView) findViewById(R.id.tv_invoice_material_desc);
        mIvInvoiceMaterialArrow = (ImageView) findViewById(R.id.iv_invoice_material_arrow);
        mEt_invoiceHead = (EditText) findViewById(R.id.et_checkout_invoicehead);
        mLl_Invoice_Noneed = (LinearLayout) findViewById(R.id.ll_invoice_noneed);
        mTv_Invoice_Noneed = (TextView) findViewById(R.id.tv_invoice_noneed);
        mIv_Invoice_Noneed = (ImageView) findViewById(R.id.iv_invoice_noneed_arrow);
        mTv_invoice_detail = (TextView) findViewById(R.id.tv_invoice_detail);
        mLl_invoice_headname = (LinearLayout) findViewById(R.id.ll_invoice_head_name);

        mMainTitlebar.setOnBtnClickListener(this);
        mLlInvoicePerson.setOnClickListener(this);
        mLlInvoiceUnit.setOnClickListener(this);
        mLlInvoiceBook.setOnClickListener(this);
        mLlInvoiceSound.setOnClickListener(this);
        mLlInvoiceGame.setOnClickListener(this);
        mLlInvoiceSoftware.setOnClickListener(this);
        mLlInvoiceMaterial.setOnClickListener(this);
        mLl_Invoice_Noneed.setOnClickListener(this);


    }

    //发票信息条目点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_invoice_noneed:
                mIv_Invoice_Noneed.setVisibility(View.VISIBLE);
                mIvInvoiceUnitArrow.setVisibility(View.INVISIBLE);
                mIvInvoicePersonArrow.setVisibility(View.INVISIBLE);
                mCurrInvoiceType = "不需要";
                mTv_invoice_detail.setVisibility(View.GONE);
                mLl_invoice_headname.setVisibility(View.GONE);
                mLlInvoiceBook.setVisibility(View.GONE);
                mLlInvoiceSound.setVisibility(View.GONE);
                mLlInvoiceGame.setVisibility(View.GONE);
                mLlInvoiceSoftware.setVisibility(View.GONE);
                mLlInvoiceMaterial.setVisibility(View.GONE);
                mNoNeedIsChecked = true;
                break;
            case R.id.ll_invoice_person:
                mIv_Invoice_Noneed.setVisibility(View.INVISIBLE);
                mIvInvoicePersonArrow.setVisibility(View.VISIBLE);
                mIvInvoiceUnitArrow.setVisibility(View.INVISIBLE);
                mCurrInvoiceType = "个人";
                mTv_invoice_detail.setVisibility(View.VISIBLE);
                mEt_invoiceHead.setVisibility(View.VISIBLE);
                mLlInvoiceBook.setVisibility(View.VISIBLE);
                mLlInvoiceSound.setVisibility(View.VISIBLE);
                mLlInvoiceGame.setVisibility(View.VISIBLE);
                mLlInvoiceSoftware.setVisibility(View.VISIBLE);
                mLlInvoiceMaterial.setVisibility(View.VISIBLE);

                mNoNeedIsChecked = false;
                break;
            case R.id.ll_invoice_unit:
                mIv_Invoice_Noneed.setVisibility(View.INVISIBLE);
                mIvInvoiceUnitArrow.setVisibility(View.VISIBLE);
                mIvInvoicePersonArrow.setVisibility(View.INVISIBLE);
                mCurrInvoiceType = "单位";
                mTv_invoice_detail.setVisibility(View.VISIBLE);
                mEt_invoiceHead.setVisibility(View.VISIBLE);
                mLlInvoiceBook.setVisibility(View.VISIBLE);
                mLlInvoiceSound.setVisibility(View.VISIBLE);
                mLlInvoiceGame.setVisibility(View.VISIBLE);
                mLlInvoiceSoftware.setVisibility(View.VISIBLE);
                mLlInvoiceMaterial.setVisibility(View.VISIBLE);
                mLl_invoice_headname.setVisibility(View.VISIBLE);
                mNoNeedIsChecked = false;
                break;

            case R.id.ll_invoice_book:
                mIvInvoiceBookArrow.setVisibility(View.VISIBLE);
                mIvInvoiceSoundArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceGameArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoftwareArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceMaterialArrow.setVisibility(View.INVISIBLE);
                mCurrInvoiceDesc = "图书";
                break;
            case R.id.ll_invoice_sound:
                mIvInvoiceBookArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoundArrow.setVisibility(View.VISIBLE);
                mIvInvoiceGameArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoftwareArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceMaterialArrow.setVisibility(View.INVISIBLE);
                mCurrInvoiceDesc = "音响";
                break;
            case R.id.ll_invoice_game:
                mIvInvoiceBookArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoundArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceGameArrow.setVisibility(View.VISIBLE);
                mIvInvoiceSoftwareArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceMaterialArrow.setVisibility(View.INVISIBLE);
                mCurrInvoiceDesc = "游戏";
                break;
            case R.id.ll_invoice_software:
                mIvInvoiceBookArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoundArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceGameArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoftwareArrow.setVisibility(View.VISIBLE);
                mIvInvoiceMaterialArrow.setVisibility(View.INVISIBLE);
                mCurrInvoiceDesc = "软件";
                break;
            case R.id.ll_invoice_material:
                mIvInvoiceBookArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoundArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceGameArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceSoftwareArrow.setVisibility(View.INVISIBLE);
                mIvInvoiceMaterialArrow.setVisibility(View.VISIBLE);
                mCurrInvoiceDesc = "材料";
                break;

        }
    }

    @Override
    public void onLeftClick() {
        Intent intent = new Intent();
        //如果点 不需要
        if (mNoNeedIsChecked){
            //只携带不需要的数据//其他 不需要进行非空判定
            intent.putExtra(Constant.INVOICETYPE,mCurrInvoiceType);
            intent.putExtra(Constant.INVOICEDESC,"");
            intent.putExtra(Constant.INVOICE_HEAD,"");
        }else {
            //如果点个人/单位,必须对抬头和内容进行非空检验，在将三个数据返回
            //发票抬头的非空校验
            if (TextUtils.isEmpty(mEt_invoiceHead.getText().toString().trim()) || TextUtils.isEmpty(mCurrInvoiceType)){
                Toast.makeText(Checkout_InvoiceInfo.this, "发票抬头或内容不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra(Constant.INVOICETYPE,mCurrInvoiceType);
            intent.putExtra(Constant.INVOICEDESC,mCurrInvoiceDesc);
            intent.putExtra(Constant.INVOICE_HEAD,mEt_invoiceHead.getText().toString().trim());

        }

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRightClick() {

    }


}
