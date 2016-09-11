package com.ziv.textviewutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.ziv.library.TextViewUtils;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        String str = textView1.getText().toString();
        int firstLinkStart = str.indexOf("《");
        int firstLinkEnd = str.indexOf("》");
        int lastLinkStart = str.lastIndexOf("《");
        int lastLinkEnd = str.lastIndexOf("》");
        new TextViewUtils(textView1).setClickableSpan(firstLinkStart, firstLinkEnd + 1, false, Color.BLUE,Color.TRANSPARENT, new TextViewUtils.OnTextClickListen() {
            @Override
            public void onTextClick() {

                Toast.makeText(MainActivity.this, "点击了文章", Toast.LENGTH_SHORT).show();
            }
        }).setClickableSpan(lastLinkStart, lastLinkEnd + 1, false, Color.BLUE,Color.TRANSPARENT, new TextViewUtils.OnTextClickListen() {
            @Override
            public void onTextClick() {

                Toast.makeText(MainActivity.this, "点击了专题", Toast.LENGTH_SHORT).show();
            }
        });


        String str2 = textView2.getText().toString();
        int index = str2.indexOf("回复");
        int end = str2.indexOf(":");
        int smile = str2.length();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //TODO:这里的处理机制可以弄成回调机制，逻辑重用
        new TextViewUtils(textView2).setClickableSpan(0, index, false, Color.BLUE,Color.TRANSPARENT, new TextViewUtils.OnTextClickListen() {
            @Override
            public void onTextClick() {

                Toast.makeText(MainActivity.this, "点击了Ziv", Toast.LENGTH_SHORT).show();
            }
        }).setClickableSpan(index + 2, end, false, Color.BLUE,Color.TRANSPARENT, new TextViewUtils.OnTextClickListen() {
            @Override
            public void onTextClick() {

                Toast.makeText(MainActivity.this, "点击了大大", Toast.LENGTH_SHORT).show();
            }
        }).setImageSpan(smile - 2, smile, this, bitmap, DensityUtil.dip2px(context,24), DensityUtil.dip2px(context,24));/*TODO:使用dp*/


    }
}
