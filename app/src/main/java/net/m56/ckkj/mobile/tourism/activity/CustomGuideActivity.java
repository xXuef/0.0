package net.m56.ckkj.mobile.tourism.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.adapter.ListViewGvJqAdapter;
import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.bean.GvJqBean;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * 2017/11/6 10:40
 */

public class CustomGuideActivity extends BaseActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    //ListView
    private ListView listView;
    private ListViewGvJqAdapter mAdapter;
    private List<GvJqBean> mData;
    private RelativeLayout openRl;
    private ImageView openIv;

    public String[] words = new String[]{" 女士们，先生们，我们现在来到了庞泉沟自然风景区。" +
            "文峪河是山西母亲河汾河的主要支流，它的源头就是我们现在所处的位置──庞泉沟。" +
            "为什么叫庞泉沟呢？庞泉沟是关帝山区的中心地带，属于花岗岩、片麻岩为主的地质区域，地下水源匮乏。" +
            "但是，这里山山有林，沟沟有水，就是找不到文峪河明显的发源之泉。庞泉沟自然保护区面积有15万多亩，" +
            "森林覆盖率达90%以上，广袤茂密的森林成为黄土高原上一颗璀璨的绿色明珠。就是这片森林的存在，形成了文峪河庞大的源泉。" +
            "这是什么原因呢？在天空降雨时，森林的枝叶阻挡了急速下降的雨滴，使雨水不能直接冲击地表土壤，雨水滴落在松软的腐殖质土层上，" +
            "慢慢渗入其中，使林下土层充满水分。郁闭的林木遮掩了日光，林下能够长期保持湿润。而土层以下是不透水的花岗岩层，" +
            "阻隔了水分的继续下渗，当林下土壤中水分超饱和时，它们就顺着山坡形成地表径流，大面积的地表径流分别从各自的山坡汇入沟壑，" +
            "成为数不清的山间小溪，无数条小溪汇入庞泉沟，再由庞泉沟进入文峪河的主河道。也就是说，这条河没有地下水的源泉，" +
            "是关帝山主峰一带浓荫蔽日的山峦沟壑形成它庞大的源泉，“庞泉”之名由此而来。"
    };
    private TextToSpeech textToSpeech; // TTS对象
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private boolean isRunning;
    private Intent intent;


    @Override
    public void initVariable() {
        setContentView(R.layout.activity_custom_guide);

        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText("自助导游");
        mData = new ArrayList<>();
        mData.add(new GvJqBean("卦山风景区", R.mipmap.banner1));
        mData.add(new GvJqBean("庞泉沟风景区", R.mipmap.pqg));
        mData.add(new GvJqBean("玄中寺风景区", R.mipmap.banner3));
        mAdapter = new ListViewGvJqAdapter(this, (ArrayList<GvJqBean>) mData, R.layout.item_guide_layout);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mAdapter);

        openRl = (RelativeLayout) findViewById(R.id.openRl);
        openRl.setVisibility(View.VISIBLE);
        openIv = (ImageView) findViewById(R.id.openIv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplication(), GuideListActivity.class);
                intent.putExtra("name", mData.get(position).getName());
                startActivity(intent);
            }
        });
        initMediaPlayer();
        openIv.setOnClickListener(this);
        textToSpeech = new TextToSpeech(this, this);
    }

    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.guide);
    }

//        private void sayHello() {
//        int size = words.length;
//        返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值。
//        nextInt 的常规协定是，伪随机地生成并返回指定范围中的一个 int 值。所有可能的 n 个 int 值的生成概率（大致）相同。
//        int i = new Random().nextInt(size);
//        String word = words[i];
//        tSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);
//    }

    @Override
    public void toLoad(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.openIv:
//                    tSpeech = new TextToSpeech(getApplicationContext(), this);
//                    sayHello();
                if (!isRunning) {
                    mediaPlayer.start();
                    isRunning = true;
                    openIv.setImageResource(R.mipmap.icon_pause);

                } else {
                    mediaPlayer.pause();
                    isRunning = false;
                    openIv.setImageResource(R.mipmap.start_comment_y);
                }
                if (textToSpeech != null && !textToSpeech.isSpeaking()) {
                    textToSpeech.setPitch(1.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                    textToSpeech.speak(words[0],
                            TextToSpeech.QUEUE_FLUSH, null);
                    sayTofile(words[0]);
                }
                break;
        }

    }

    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        if (textToSpeech!=null){
            textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
            textToSpeech.shutdown(); // 关闭，释放资源
        }
        if (mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    //将朗读结果保存到sd卡中
    private void sayTofile(String text)
    {
        HashMap<String, String> ttsRender = new HashMap<String, String>();
        String destFileName = "/sdcard/tts/"+text+".wav";
        ttsRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, text);
        textToSpeech.synthesizeToFile(text, ttsRender, destFileName);
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
