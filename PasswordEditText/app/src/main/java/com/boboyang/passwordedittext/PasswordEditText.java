package com.boboyang.passwordedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by BoBoYang on 2016/6/7.
 */
public class PasswordEditText extends EditText {

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    private int pwdLength = 6;
    private int borderWidth = 1;
    private int pwdWidth = 6;
    private int roundConerRadius = 3;
    private Paint passwordPaint;
    private Paint borderPaint;
    private Paint insidePaint;
    private int width;
    private int height;
    private int textLength;

    //初始化
    private void init() {
        float density = getResources().getDisplayMetrics().density;
        borderWidth *= density;
        pwdWidth *= density;
        roundConerRadius *= density;
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        passwordPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        insidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setColor(Color.GRAY);
        borderPaint.setStrokeWidth(borderWidth);
        passwordPaint.setColor(Color.BLACK);
        insidePaint.setStrokeWidth(pwdWidth);
        insidePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = getWidth();
        height = getHeight();

        //绘制外框
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectF, roundConerRadius, roundConerRadius, borderPaint);

        //绘制内背景
        RectF inRectF = new RectF(rectF.left + borderWidth, rectF.top + borderWidth, rectF.right - borderWidth, rectF.bottom - borderWidth);
        canvas.drawRoundRect(inRectF, roundConerRadius, roundConerRadius, insidePaint);

        //绘制内分割线
        for (int i = 1; i < pwdLength; i++) {
            int x = width * i / pwdLength;
            canvas.drawLine(x, 0, x, height, borderPaint);
        }

        //绘制密码
        float cx, cy = height >> 1;
        float half = width / pwdLength >> 1;
        for (int i = 0; i < textLength; i++) {
            cx = width / pwdLength * i + half;
            canvas.drawCircle(cx, cy, pwdWidth, passwordPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        textLength = text.length();
        invalidate();
    }
}


