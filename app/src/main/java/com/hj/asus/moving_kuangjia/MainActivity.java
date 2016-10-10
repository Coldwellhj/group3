package com.hj.asus.moving_kuangjia;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

import com.hj.asus.moving_kuangjia.fragment.Fragment_changdi;
import com.hj.asus.moving_kuangjia.fragment.Fragment_dongtai;
import com.hj.asus.moving_kuangjia.fragment.Fragment_haoyou;
import com.hj.asus.moving_kuangjia.fragment.Fragment_home;
import com.hj.asus.moving_kuangjia.fragment.Fragment_huodong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView left_drawer1;
    private RadioGroup rg_tab;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initview();

        initdata();
    }


    private void initview() {

        left_drawer1 = ((ListView) findViewById(R.id.left_drawer1));
        rg_tab = ((RadioGroup) findViewById(R.id.rg_tab));
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
    }

    private void initdata() {

        //默认home显示
        switchFragment(new Fragment_home());
        //left_drawer1.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,new String[]{"item1","item2"}));

        ImageView view1 = new ImageView(MainActivity.this);
        view1.setImageResource(R.drawable.touxiang);
        left_drawer1.addHeaderView(view1);
        int[] iv_photo_srcs = new int[]{R.drawable.circle_blue,R.drawable.compass_blue,R.drawable.home_blue,R.drawable.service_blue,R.drawable.user_blue};

        List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> map = null;
        for(int i = 0;i<iv_photo_srcs.length;i++) {
            map = new HashMap<String,Object>();
            map.put("iv_photo_src", iv_photo_srcs[i]);
            map.put("tv_title", "新闻标题"+i);


            data.add(map);
        }

        String[] from = {"iv_photo_src","tv_title"};
        int[] to = {R.id.iv_photo,R.id.tv_title};

        left_drawer1.setAdapter(
                new SimpleAdapter(
                        MainActivity.this,   //上下文
                        data,               //map结构的数据
                        R.layout.activity_cehua_list_item,// listview item的布局文件
                        from,           // map 的key
                        to              // 布局文件中控件的id
                ));



        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId) {

                    case R.id.rb_home:
                    //    Log.i(TAG,"选中首页tab");
                        fragment = new Fragment_home();
                        break;
                    case R.id.rb_changdi:
                        fragment = new Fragment_changdi();
                        break;
                    case R.id.rb_huodong:
                        fragment = new Fragment_huodong();
                        break;
                    case R.id.rb_dongtai:
                        fragment = new Fragment_dongtai();
                        break;
                    case R.id.rb_haoyou:
                        fragment = new Fragment_haoyou();
                        break;
                }



                switchFragment(fragment);
            }
        });

        left_drawer1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
                drawer.closeDrawers();

            }
        });

    }

    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.fl_content,fragment).commit();
    }
}
