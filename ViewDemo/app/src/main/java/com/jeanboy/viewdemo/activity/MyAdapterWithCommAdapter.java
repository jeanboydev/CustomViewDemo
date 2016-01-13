package com.jeanboy.viewdemo.activity;

import android.content.Context;

import com.jeanboy.viewdemo.bean.Bean;
import com.jeanboy.viewdemo.utils.CommonAdapter;
import com.jeanboy.viewdemo.utils.ViewHolder;

import java.util.List;

/**
 * Created by yule on 2016/1/13.
 */
public class MyAdapterWithCommAdapter extends CommonAdapter<Bean> {
    public MyAdapterWithCommAdapter(Context context, List<Bean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    /**
     * 实现public abstract View getView(int position, View convertView, ViewGroup parent);
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
/*	@Override
    public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.item, position);
		Bean bean = mDatas.get(position);

		((TextView)holder.getView(R.id.tv_title)).setText(bean.getTitle());
		((TextView)holder.getView(R.id.tv_desc)).setText(bean.getDesc());
		((TextView)holder.getView(R.id.tv_time)).setText(bean.getTime());
		((TextView)holder.getView(R.id.tv_phone)).setText(bean.getPhone());

		return holder.getConvertView();
	}
*/

    /**
     * 实现public abstract void convert(ViewHolder holder, T t);
     *
     */
    @Override
    public void convert(ViewHolder holder, final Bean bean) {
/*
		((TextView)holder.getView(R.id.tv_title)).setText(bean.getTitle());
		((TextView)holder.getView(R.id.tv_desc)).setText(bean.getDesc());
		((TextView)holder.getView(R.id.tv_time)).setText(bean.getTime());
		((TextView)holder.getView(R.id.tv_phone)).setText(bean.getPhone());
*/
//        holder.setText(R.id.tv_title, bean.getTitle())
//                .setText(R.id.tv_desc, bean.getDesc())
//                .setText(R.id.tv_time, bean.getTime())
//                .setText(R.id.tv_phone, bean.getPhone());
//
//        final CheckBox cBox = (CheckBox) (holder.getView(R.id.cb));
//        if (cBox != null) {
//            cBox.setChecked(bean.isChecked());
//
//            cBox.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    bean.setChecked(cBox.isChecked());
//                }
//            });
//        }
    }
}

