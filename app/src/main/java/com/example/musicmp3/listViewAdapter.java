package com.example.musicmp3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class listViewAdapter  extends BaseAdapter {
    Context mcontext;
    LayoutInflater inflater;
    List<Model> modelList;
    ArrayList<Model> arrayList;

    public listViewAdapter(Context context,List<Model> modelList){
        mcontext = context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(mcontext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modelList);


    }
    public class ViewHolder{
        TextView mTitleTv,mTenTv;
        ImageView mIconIv;
    }
    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_layout, null);
            holder.mTitleTv = view.findViewById(R.id.txt1);
            holder.mTenTv = view.findViewById(R.id.txt2);
            holder.mIconIv = view.findViewById(R.id.imageview);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTitleTv.setText(modelList.get(position).getTitle());
        holder.mTenTv.setText(modelList.get(position).getTen());
        holder.mIconIv.setImageResource(modelList.get(position).getIcon());

        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modelList.clear();
        if(charText.length()==0){
            modelList.addAll(arrayList);
        }
        else {
            for (Model model : arrayList){
                if(model.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    modelList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
