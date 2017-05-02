package weijiangtao.bwie.com.yuekaolian1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import weijiangtao.bwie.com.yuekaolian1.R;
import weijiangtao.bwie.com.yuekaolian1.bean.Bean;

/**
 * Created by asus on 2017/5/2.
 * <p>
 * 未江涛
 */

public class Myadapter extends BaseAdapter {
    
    private List<Bean.DataBean>list;
    private Context context;

    public Myadapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewhouder v;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.activity_item,null);
            v=new Viewhouder();
            v.imageView= (ImageView) convertView.findViewById(R.id.imageview);
            v.textView= (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(v);
        }else {
            v= (Viewhouder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),v.imageView);
        v.textView.setText(list.get(position).getGoods_name());
        
        
        
        return convertView;
    }
    
    class Viewhouder{
        ImageView imageView;
        TextView textView;
    }
    
    
}
