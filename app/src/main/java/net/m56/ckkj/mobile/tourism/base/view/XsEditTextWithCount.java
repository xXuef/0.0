package net.m56.ckkj.mobile.tourism.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.m56.ckkj.tourism.tourism.R;


/**
 * Created by ShiXiong on 2016/3/9.
 */
public class XsEditTextWithCount extends RelativeLayout {
    private RelativeLayout layout = null;
    private EditText contentEt = null;
    private TextView countTv = null;
    private TextView gradeTv = null;
    private int num = 200;

    public XsEditTextWithCount(Context context) {
        super(context);
        initWidget(context);
    }

    public XsEditTextWithCount(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.EditTextWithCount);
        num = array.getInteger(R.styleable.EditTextWithCount_count, 200);

        array.recycle();
        initWidget(context);
    }

    public XsEditTextWithCount(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWidget(context);
    }

    public String getInputContent() {
        return contentEt.getText().toString();
    }

    public void setInputContent(String content) {
        contentEt.setText(content);
    }

    public void setFocusable() {

        contentEt.setFocusable(true);// 在EditView 设置焦点
        contentEt.requestFocus();
    }
    public void setNoFocusable() {

        contentEt.setFocusable(false);// 在EditView 设置焦点
        contentEt.requestFocus();
    }
    public void setGradeContent(String str) {
        gradeTv.setText(str);
    }

    public String getGradeContent() {
        return gradeTv.getText().toString();
    }

    public void initWidget(Context context) {
        layout = (RelativeLayout) LayoutInflater.from(context).inflate(
                R.layout.edittext_count, this);
        contentEt = (EditText) layout.findViewById(R.id.conEt);
        contentEt.setPadding(20, 22, 20, 22);
        contentEt.setFocusable(true);
        countTv = (TextView) layout.findViewById(R.id.countTv);
        countTv.setText(0 + "/" + num);
        gradeTv = (TextView) layout.findViewById(R.id.gradeTv);

        contentEt.setTextColor(Color.parseColor("#ffc0c0c0"));
        contentEt.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                temp = s;
            }

            public void afterTextChanged(Editable s) {
                countTv.setText(s.length() + "/" + num);
                selectionStart = contentEt.getSelectionStart();
                selectionEnd = contentEt.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    contentEt.setText(s);
                    contentEt.setSelection(tempSelection);
                }
            }
        });
    }

}
