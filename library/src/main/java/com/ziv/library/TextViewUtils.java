package com.ziv.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Ziv on 2016/9/9.
 */

public class TextViewUtils {

    private static final String TAG = "TextViewColorUtil2";
    private TextView mTextView;
    private SpannableString mSpannableString;
    private int flag = Spanned.SPAN_INCLUSIVE_EXCLUSIVE;

    public TextViewUtils(TextView textView) {

        if (textView == null) {
            throw new IllegalArgumentException("textView is null");
        }
        this.mTextView = textView;
        mSpannableString = new SpannableString(textView.getText().toString());
    }

    /**
     * @param flag Spanned.SPAN_EXCLUSIVE_EXCLUSIVE --- 不包含两端start和end所在的端点
     *             Spanned.SPAN_EXCLUSIVE_INCLUSIVE --- 不包含端start，但包含end所在的端点
     *             Spanned.SPAN_INCLUSIVE_EXCLUSIVE --- 包含两端start，但不包含end所在的端点
     *             Spanned.SPAN_INCLUSIVE_INCLUSIVE--- 包含两端start和end所在的端点
     * @return
     */
    public TextViewUtils setFlag(int flag) {

        this.flag = flag;
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param start 初始位置
     * @param end   结束位置
     * @param color 颜色值
     * @return
     */
    public TextViewUtils setForegroundColor(int start, int end, int color) {
        ForegroundColorSpan fcs = new ForegroundColorSpan(color);
        mSpannableString.setSpan(fcs, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 设置字体背景色
     *
     * @param start 初始位置
     * @param end   结束位置
     * @param color 颜色值
     * @return
     */
    public TextViewUtils setBackgroundColor(int start, int end, int color) {
        BackgroundColorSpan bcs = new BackgroundColorSpan(color);
        mSpannableString.setSpan(bcs, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 字体相对大小
     *
     * @param start 初始位置
     * @param end   结束位置
     * @param size  相对尺寸值，1为原始大小，小于1缩小，大于1放大
     * @return
     */
    public TextViewUtils setAbsoluteSizeSpan(int start, int end, int size, boolean dip) {

        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, dip);
        mSpannableString.setSpan(ass, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 字体相对大小
     *
     * @param start 初始位置
     * @param end   结束位置
     * @param size  相对尺寸值，1为原始大小，小于1缩小，大于1放大
     * @return
     */
    public TextViewUtils setRelativeSize(int start, int end, float size) {

        RelativeSizeSpan rss = new RelativeSizeSpan(size);
        mSpannableString.setSpan(rss, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 基于x轴缩放(变瘦了)
     *
     * @param start      初始位置
     * @param end        结束位置
     * @param proportion 相对X轴尺寸值，1为原始大小，小于1缩小，大于1放大
     * @return
     */
    public TextViewUtils setScaleXSpan(int start, int end, float proportion) {

        ScaleXSpan rss = new ScaleXSpan(proportion);
        mSpannableString.setSpan(rss, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 删除线（中划线）
     *
     * @param start 初始位置
     * @param end   结束位置
     * @return
     */
    public TextViewUtils setStrikethrough(int start, int end) {

        StrikethroughSpan sts = new StrikethroughSpan();
        mSpannableString.setSpan(sts, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 下划线
     *
     * @param start 初始位置
     * @param end   结束位置
     * @return
     */
    public TextViewUtils setUnderlineSpan(int start, int end) {

        UnderlineSpan uls = new UnderlineSpan();
        mSpannableString.setSpan(uls, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 上标
     *
     * @param start 初始位置
     * @param end   结束位置
     * @return
     */
    public TextViewUtils setSuperscriptSpan(int start, int end) {

        SuperscriptSpan sup = new SuperscriptSpan();
        mSpannableString.setSpan(sup, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 下标
     *
     * @param start 初始位置
     * @param end   结束位置
     * @return
     */
    public TextViewUtils setSubscriptSpan(int start, int end) {

        SubscriptSpan sup = new SubscriptSpan();
        mSpannableString.setSpan(sup, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 文字样式
     *
     * @param start 初始位置
     * @param end   结束位置
     * @param style Typeface.ITALIC、Typeface.BOLD等
     * @return
     */
    public TextViewUtils setStyleSpan(int start, int end, int style) {

        StyleSpan styleSpan = new StyleSpan(style);
        mSpannableString.setSpan(styleSpan, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 文本图片
     *
     * @param start   初始位置
     * @param end     结束位置
     * @param context context
     * @param bitmap  替换后的图片
     * @return
     */
    public TextViewUtils setImageSpan(int start, int end, Context context, Bitmap bitmap) {

        ImageSpan imageSpan = new ImageSpan(context, bitmap);
        mSpannableString.setSpan(imageSpan, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 文本图片,指定图片宽高
     *
     * @param start     初始位置
     * @param end       结束位置
     * @param context   context
     * @param bitmap    替换后的图片
     * @param newWidth  替换后的图片宽度
     * @param newHeight 替换后的图片高度
     * @return
     */

    public TextViewUtils setImageSpan(int start, int end, Context context, Bitmap bitmap, int newWidth,
                                      int newHeight) {

        ImageSpan imageSpan = new ImageSpan(context, zoomImg(bitmap, newWidth, newHeight));
        mSpannableString.setSpan(imageSpan, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 文本图片,根据正则替换匹配到的所有字符为指定图片
     *
     * @param context           context
     * @param regularExpression 正则表达式
     * @param bitmap            替换后的图片
     * @param newWidth          替换后的图片宽度
     * @param newHeight         替换后的图片高度
     * @return
     */
    public TextViewUtils setImageSpan(Context context, String regularExpression, Bitmap bitmap, int newWidth,
                                      int newHeight) {

        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(mSpannableString);
        int lastEnd = 0; // 上一次替换的emoji(重复emoji的处理)
        while (matcher.find()) {
            String emojiStr = matcher.group();
            // Bitmap bitmap =
            // BitmapFactory.decodeStream(context.getAssets().open(emojiPath));
            ImageSpan imageSpan = new ImageSpan(context, zoomImg(bitmap, newWidth, newHeight));
            int start = mSpannableString.toString().indexOf(emojiStr, lastEnd);
            int end = start + emojiStr.length();
            lastEnd = end;
            mSpannableString.setSpan(imageSpan, start, end, flag);
        }
        mTextView.setText(mSpannableString);
        return this;
    }

    /**
     * 可点击的文本
     *
     * @param start         初始位置
     * @param end           结束位置
     * @param highlightColor 文本被选中时背景色
     * @param clickableSpan 自定义ClickableSpan
     * @return
     */
    public TextViewUtils setClickableSpan(int start, int end,  final int highlightColor,ClickableSpan clickableSpan) {

        mSpannableString.setSpan(clickableSpan, start, end, flag);
        mTextView.setText(mSpannableString);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }


    /**
     * 可点击的文本
     * @param start 初始位置
     * @param end  结束位置
     * @param isUnderline 是否需要下划线
     * @param textColor 字体颜色
     * @param highlightColor 文本被选中时背景色
     * @param onTextClickListen 点击事件
     * @return
     */
    public TextViewUtils setClickableSpan(int start, int end, final boolean isUnderline, final int textColor, final int highlightColor,final OnTextClickListen onTextClickListen) {

        mSpannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onTextClickListen.onTextClick();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(textColor);
                ds.setUnderlineText(isUnderline);

            }

        }, start, end, flag);

        mTextView.setText(mSpannableString);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mTextView.setHighlightColor(highlightColor);
        return this;
    }


    /**
     * 給所有网络链接添加点击事件
     *
     * @param clickableSpan 自定义点击事件
     */
    public void setClickableSpan(ClickableSpan clickableSpan) {

        URLSpan[] urls = mSpannableString.getSpans(0, mSpannableString.length() - 1, URLSpan.class);
        // mSpannableString.clearSpans();// should clear old spans
        for (URLSpan url : urls) {
            mSpannableString.setSpan(clickableSpan, mSpannableString.getSpanStart(url),
                    mSpannableString.getSpanEnd(url), flag);
        }

        mTextView.setText(mSpannableString);
    }

    /**
     * 给指定文本添加超链接
     *
     * @param start 初始位置
     * @param end   结束位置
     * @param url   URL
     * @return
     */
    public TextViewUtils setURLSpan(int start, int end, String url) {
        URLSpan urlSpan = new URLSpan(url);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        // textView.setMovementMethod(LinkMovementMethod.getInstance());
        // textView.setHighlightColor(Color.parseColor("#36969696"));
        mSpannableString.setSpan(urlSpan, start, end, flag);
        mTextView.setText(mSpannableString);
        return this;
    }


    /**
     * 处理图片
     *
     * @param bm        所要转换的bitmap
     * @param newWidth  新的宽
     * @param newHeight 新的高
     * @return 指定宽高的bitmap
     */
    private Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片 www.2cto.com
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    /**
     * 一个TextView设置单种颜色
     *
     * @param textView textView
     * @param start    开始位置
     * @param end      结束位置
     * @param color
     */
    private static void setColor(TextView textView, int start, int end, int color) {
        SpannableString ss = new SpannableString(textView.getText().toString());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        if (textView == null || !(textView instanceof TextView)) {
            Log.e(TAG, "方法参数不合法");
            return;
        }
        if (start > end || start < 0 || end <= 0) {
            Log.e(TAG, "下标不合法");
            return;
        }
        ss.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
    }

    /**
     * 设置多个颜色值
     *
     * @param textView
     * @param start    起始小标数组
     * @param end      结束下标数组
     * @param color    颜色值数组 三个数组长度必须一致，否则颜色设置不成功
     */
    private static void setMultiColor(TextView textView, int[] start, int[] end, int[] color) {
        if (textView == null || !(textView instanceof TextView) || start == null || end == null || color == null) {
            Log.e(TAG, "方法参数不合法");
            return;
        }
        int lenth = start.length;
        if (end.length != lenth || color.length != lenth) {
            Log.e(TAG, "方法参数不合法");
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
        for (int i = 0; i < start.length; i++) {
            // ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
            ForegroundColorSpan span = new ForegroundColorSpan(color[i]);
            builder.setSpan(span, start[i], end[i], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(builder);
    }


    /**
     * 文本点击事件
     */
    public interface OnTextClickListen {

        public void onTextClick();
    }


}

