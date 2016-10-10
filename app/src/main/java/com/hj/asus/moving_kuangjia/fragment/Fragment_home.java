package com.hj.asus.moving_kuangjia.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hj.asus.moving_kuangjia.R;
import com.hj.asus.moving_kuangjia.contantData.HttpUtils;
import com.hj.asus.moving_kuangjia.contantData.ToastUtil;
import com.hj.asus.moving_kuangjia.pojo.ListActivityBean;
import com.hj.asus.moving_kuangjia.utils.xUtilsImageUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/9/19.
 */
public class Fragment_home extends Fragment{
    private static final String TAG = "Fragment_home";

    private ListView lv_zixun;
    private ListView lv_zixun_basketball;
    private ListView lv_zixun_swimming;
    private ListView lv_zixun_fitness;
    private ListView lv_zixun_running;
    private ListView lv_zixun_roller;
    private ListView lv_zixun_pingpang;
   // private BaseAdapter adapter;
    View view;
    int page = 1;//第一页
    private ViewPager vp_zixun;
    private RadioGroup rg_zixuns_top;
    private RadioButton rb_zixun_one;
    private RadioButton rb_zixun_two;
    private RadioButton rb_zixun_three;
    private RadioButton rb_zixun_four;
    private RadioButton rb_zixun_five;
    private RadioButton rb_zixun_six;
    private RadioButton rb_zixun_seven;
    MyAdapter adapter1;
    MyAdapter adapter2;
    MyAdapter adapter3;
    private List<ListActivityBean.Zixun> zixunlist = new ArrayList<ListActivityBean.Zixun>();
    private  List<View> listView= new ArrayList<View>();
    private List<Integer> choiceZan = new ArrayList<Integer>();
    ToastUtil toastUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.vp_home_listview, null);
        //自定义toast布局
        View v=inflater.inflate(R.layout.layout_toast_view,null);
        toastUtil=new ToastUtil(getActivity(),v,200);
        initView();
        initlistviews();
      return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initlistviews() {


        View view1 = View.inflate(getActivity(), R.layout.fragment_home, null);
        lv_zixun = ((ListView)view1.findViewById(R.id.lv_zixun));

        adapter1 =new MyAdapter();
        //lv_zixun.setAdapter(adapter1);
       // getZixunlist();
        listView.add(view1);


        View view2 = View.inflate(getActivity(), R.layout.fragment_home_basketball, null);
        lv_zixun_basketball = ((ListView)view2.findViewById(R.id.lv_zixun_basketball));

        adapter2 =new MyAdapter();
      //  lv_zixun_basketball.setAdapter(adapter2);
      //  getZixunlist_basketball();

        listView.add(view2);
        View view3 = View.inflate(getActivity(), R.layout.fragment_home_swim, null);
        lv_zixun_swimming = ((ListView)view3.findViewById(R.id.lv_zixun_swim));

        adapter3=new MyAdapter();
     //   lv_zixun_swimming.setAdapter(adapter3);
      //  getZixunlist_swim();
        listView.add(view3);
        View view4 = View.inflate(getActivity(), R.layout.fragment_home_fitness, null);
        lv_zixun_fitness = ((ListView)view4.findViewById(R.id.lv_zixun_fitness));
  ;
//        lv_zixun.setAdapter(new MyAdapter());
        //getZixunlist_swim();
        listView.add(view4);
        View view5 = View.inflate(getActivity(), R.layout.fragment_home_running, null);
        lv_zixun_running = ((ListView)view5.findViewById(R.id.lv_zixun_running));

//        lv_zixun_running.setAdapter(new MyAdapter());
        //getZixunlist_swim();
        listView.add(view5);
        View view6 = View.inflate(getActivity(), R.layout.fragment_home_roller, null);
        lv_zixun_roller = ((ListView)view6.findViewById(R.id.lv_zixun_roller));

//        lv_zixun_roller.setAdapter(new MyAdapter());
      //  getZixunlist_swim();
        listView.add(view6);
        View view7 = View.inflate(getActivity(), R.layout.fragment_home_pingpang, null);
        lv_zixun_pingpang = ((ListView)view7.findViewById(R.id.lv_zixun_pingpang));

//        lv_zixun_pingpang.setAdapter(new MyAdapter());
       // getZixunlist_swim();
        listView.add(view7);

    }

    private void initView() {
       vp_zixun = ((ViewPager) view.findViewById(R.id.vp_zixun));
        rg_zixuns_top = ((RadioGroup) view.findViewById(R.id.rg_zixuns_top));
        rb_zixun_one = ((RadioButton) view.findViewById(R.id.rb_zixun_one));
        rb_zixun_two = ((RadioButton) view.findViewById(R.id.rb_zixun_two));
        rb_zixun_three = ((RadioButton) view.findViewById(R.id.rb_zixun_three));
        rb_zixun_four = ((RadioButton) view.findViewById(R.id.rb_zixun_four));
        rb_zixun_five = ((RadioButton) view.findViewById(R.id.rb_zixun_five));
        rb_zixun_six = ((RadioButton) view.findViewById(R.id.rb_zixun_six));
        rb_zixun_seven = ((RadioButton) view.findViewById(R.id.rb_zixun_seven));


    }


    private void initData() {
        rb_zixun_one.setChecked(true);

       lv_zixun.setAdapter(adapter1);
        getZixunlist();
        rg_zixuns_top.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 //Toast.makeText(getActivity(),checkedId+"",Toast.LENGTH_SHORT).show();
               // toastUtil.Short(getActivity(),"这是第"+(checkedId+1)+"页").show();
                switch (checkedId) {
                    case R.id.rb_zixun_one:
                        lv_zixun.setAdapter(adapter1);
                        getZixunlist();
                        vp_zixun.setCurrentItem(0);
                        break;
                    case R.id.rb_zixun_two:
                        //Toast.makeText(getActivity(), zixunlist.size() + "", Toast.LENGTH_SHORT).show();

                        lv_zixun_basketball.setAdapter(adapter2);
                       getZixunlist_basketball();
                       vp_zixun.setCurrentItem(1);
                        break;
                    case R.id.rb_zixun_three:

                        lv_zixun_swimming.setAdapter(adapter3);
                         getZixunlist_swim();
                        vp_zixun.setCurrentItem(2);
                        break;
                    case R.id.rb_zixun_four:
                        vp_zixun.setCurrentItem(3);
                        break;
                    case R.id.rb_zixun_five:
                        vp_zixun.setCurrentItem(4);
                        break;
                    case R.id.rb_zixun_six:
                        vp_zixun.setCurrentItem(5);
                        break;
                    case R.id.rb_zixun_seven:
                        vp_zixun.setCurrentItem(6);
                        break;
                    default:
                        break;


                }

            }
        });
//        vp_zixun.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                return true;
//            }
//        });

        vp_zixun.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toastUtil.Short(getActivity(),"这是第"+(position+1)+"页").show();

                switch (position) {
                    case 0:
                        rb_zixun_one.setChecked(true);


                        break;
                    case 1:
                        rb_zixun_two.setChecked(true);
                        break;
                    case 2:
                        rb_zixun_three.setChecked(true);
                        break;
                    case 3:
                        rb_zixun_four.setChecked(true);
                        break;
                    case 4:
                        rb_zixun_five.setChecked(true);
                        break;
                    case 5:
                        rb_zixun_six.setChecked(true);
                        break;
                    case 6:
                        rb_zixun_seven.setChecked(true);
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        MyPAdapter adapter =new MyPAdapter(listView);
        //vp_zixun .setOffscreenPageLimit(2);
        vp_zixun.setAdapter(adapter);

    }


    private void getZixunlist() {

        RequestParams params = new RequestParams(HttpUtils.host+"toappmain");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                ListActivityBean bean=gson.fromJson(result, ListActivityBean.class);
                //System.out.println(bean.status);
                //System.out.println(bean.zixunlist.size());
                //Log.i(TAG,bean.status+"---------");
                //Log.i(TAG,bean.zixunlist.size()+"---------");
                zixunlist.clear();
                zixunlist.addAll(bean.zixunlist);
                //通知listView更新界面
                adapter1.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               // Log.i(TAG,ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    private void getZixunlist_basketball() {

        RequestParams params = new RequestParams(HttpUtils.host+"toappmain?type=篮球");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                ListActivityBean bean=gson.fromJson(result, ListActivityBean.class);
                //System.out.println(bean.status);
                //System.out.println(bean.zixunlist.size());
                //Log.i(TAG,bean.status+"---------");
                //Log.i(TAG,bean.zixunlist.size()+"---------");
                zixunlist.clear();
                zixunlist.addAll(bean.zixunlist);
                //通知listView更新界面
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                // Log.i(TAG,ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    private void getZixunlist_swim() {

        RequestParams params = new RequestParams(HttpUtils.host+"toappmain?type=游泳");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                ListActivityBean bean=gson.fromJson(result, ListActivityBean.class);
                //System.out.println(bean.status);
                //System.out.println(bean.zixunlist.size());
                //Log.i(TAG,bean.status+"---------");
                //Log.i(TAG,bean.zixunlist.size()+"---------");
                zixunlist.clear();
                zixunlist.addAll(bean.zixunlist);
                //通知listView更新界面
                adapter3.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                // Log.i(TAG,ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    private class MyPAdapter extends PagerAdapter {
        private   List<View> listView;
        public MyPAdapter( List<View> listView) {
            this.listView = listView;//构造方法，参数是我们的页卡，这样比较方便。
        }

        @Override
        public int getCount() {
            return listView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            //container.removeView((View) object);
            System.out.println(position);
            container.removeView(listView.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {



            container.addView(listView.get(position));
            //System.out.println(position);
            return listView.get(position);
        }
    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
           // System.out.println(zixunlist.size());
            return zixunlist.size();
        }

        @Override
        public Object getItem(int position) {
            return zixunlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i(TAG, "加载ListView item_position:" + position);

            //打气筒
            ViewHolder viewHolder=null;
            if(convertView==null){
                viewHolder =new ViewHolder();
                convertView= View.inflate(getActivity(), R.layout.activity_zi_xun, null);
                viewHolder.iv_photo = ((ImageView) convertView.findViewById(R.id.iv_photo));
                viewHolder.tv_type = ((TextView) convertView.findViewById(R.id.tv_type));
                viewHolder.tv_name = ((TextView) convertView.findViewById(R.id.tv_name));

               // viewHolder.tv_chenghao = ((TextView) convertView.findViewById(R.id.tv_chenghao));
                viewHolder.iv_zan = ((ImageView) convertView.findViewById(R.id.iv_dianzan));
                viewHolder.tv_dianzanshu = ((TextView) convertView.findViewById(R.id.tv_dianzanshu));
                viewHolder.iv_zan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(choiceZan.contains((Integer)(((ImageView)v).getTag()))){
                            ((ImageView) v).setImageResource(R.drawable.collect_icon_unselected);

                            choiceZan.remove((Integer)(((ImageView)v).getTag()));
                        }else {
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.collect_icon_selected);
                            ((ImageView) v).setImageBitmap(bitmap);

                            choiceZan.add((Integer) (((ImageView) v).getTag()));
                        }
                    }
                });
                convertView.setTag(viewHolder);//缓存对象
            }else{
                viewHolder=(ViewHolder)convertView.getTag();
            }

            ListActivityBean.Zixun zixun = zixunlist.get(position);
            try {
                viewHolder.tv_type.setText(URLDecoder.decode(zixun.type,"utf-8"));
                viewHolder.tv_name.setText(URLDecoder.decode(zixun.name,"utf-8"));

                xUtilsImageUtils.display(viewHolder.iv_photo,HttpUtils.host+URLDecoder.decode(zixun.photoImg,"utf-8"));
                //    tv_chenghao.setText(URLDecoder.decode(zixun.chenghao,"utf-8"));

                viewHolder.tv_dianzanshu.setText(zixun.dianzan+"");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            viewHolder.iv_zan.setTag(position);//设置一个唯一的标识，建议主键

            //页面显示处理
            if(choiceZan.contains(position)){
                viewHolder.iv_zan.setImageResource(R.drawable.collect_icon_selected);

            }else{
                viewHolder.iv_zan.setImageResource(R.drawable.collect_icon_unselected);

            }
            return convertView;
        }
    }
    private static class ViewHolder{
        ImageView iv_photo;
        TextView tv_type;
        TextView tv_name;

        TextView tv_dianzanshu;
        ImageView iv_zan;
    }
}
