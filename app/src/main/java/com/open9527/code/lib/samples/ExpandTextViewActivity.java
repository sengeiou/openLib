package com.open9527.code.lib.samples;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.customview.textview.ExpandTextView;
import com.open9527.code.lib.R;

import org.json.JSONObject;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/2 13:42.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ExpandTextViewActivity extends CommonTitleActivity {
    @Override
    public CharSequence bindTitle() {
        return "折叠TextView";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_expand_textview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        String desc = "这是一个可折叠的TextView,有仿微博的<a href='{type:0, desc:\"个人主页\"}'>@用户</a>和<a href='{type:1,desc:\"话题主页\"}'>#热门话题#</a>功能具体配置需要查看自定义属性.用一季幽香,温暖了多少惆怅,让心事穿越时光,谱一曲爱的畅想. 今生不离携手翱翔,红尘有你伴潇湘,千千深情藏, 桃源十里正芳香, 映红汝美妆, 轻舞痴狂, 用三世的美景染软红十丈,做你的衣裳,一澜相思溢心房,夜未央,独凭栏处思绪荡,一帘明月照东墙,几重伤,谁把誓言忘,留我独自徬徨,红尘渡口谁在望,犹记前程渺茫,花落纷纷扬,拈来注酒尝,微醺愁肠,卷帘玎珰,惊了谁的慌,步踉跄,斜坐几案旁,又见红烛泪淌,挥毫赋诗又凄凉,红笺之上小字躺,怎续凤求凰,故人去远方,鸳鸯不成双,心语呢喃伥,累字自己扛,缘分的山岗,印记着你的模样,相思酿,你背起行囊,远走他乡,从此无信航,负情东流怎丈量,谁解开爱的绳缰,是我来不及提防,却被你埋葬,荡不起缘分的桨,望断天涯彼岸夯,不肯喝孟婆的汤,来生化作佛前琳琅,祥瑞降,一生不弃续缘长";

//        String desc = "宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕 1234567890 宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕";
//        String desc = "宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕宝山拉开“南竹新韵——江南丝竹非遗文化公益巡演活动”帷幕 ";
        ExpandTextView expandTextView = findViewById(R.id.expanded_text);
        expandTextView.setText(desc)
                .setExpandEnable(true)  //是否可折叠, 默认为true
                .setTextColor(Color.BLACK)       //内容文本颜色
                .setTextSize(14)        //文本字体大小
                .setAnimationDuration(500)  //动画执行时长
                .setBtnExpandText("收起")   //折叠文字
                .setBtnSpreadText("展开")       //展开文字
                .setBtnGravity(Gravity.END)     //按钮位置
                .setBtnTextColor(Color.GRAY)     //按钮文本颜色
                .setBtnTextSize(12)            //按钮文本大小
                .setShowIcon(true)  //显示箭头
                .setSpanClickable(true, new ExpandTextView.TextSpanClickListener() {  //设置有标签或@某人的点击, 默认为false
                    @Override
                    public void onTextSpanClick(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            int type = jsonObject.getInt("type");
                            String desc = jsonObject.getString("desc");
                            ToastUtils.showShort(desc);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
