package com.example.hadirsamir.myshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hadirsamir.myshop.JsonClasses.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 06/03/18.
 */

public class GenderListAdapter  extends RecyclerView.Adapter<GenderListAdapter.MyHolder> {
    private ArrayList<Data> dataList = new ArrayList();
    private Context context;

    public GenderListAdapter (ArrayList<Data> dataList , Context context) {
        this.dataList = dataList;
        this.context=context;
    }

    @Override
    public GenderListAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_category_list,parent,false);
        GenderListAdapter.MyHolder holder =new GenderListAdapter .MyHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(GenderListAdapter.MyHolder holder, int position) {
        Data data = dataList.get(position);
        holder.setData(data);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {


        private ImageView imageView;
        private TextView TitleTextView,PriceTextView;
        private View parent;


        public MyHolder(View itemView) {
            super(itemView);
            imageView =(ImageView) itemView.findViewById(R.id.imageView3);
            TitleTextView=(TextView)itemView.findViewById(R.id.textView3) ;
            PriceTextView =(TextView)itemView.findViewById(R.id.textView5);
            parent = itemView;



        }

        public void setData(final Data data) {
            Picasso.with(context).load(data.getProductImage()).into(imageView);
            TitleTextView.setText(data.getProductName());
            PriceTextView.setText(String.valueOf(data.getProductPrice()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context,DetailsActivity.class);
                    Bundle b= new Bundle();
                    b.putSerializable("dataset",data);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });



        }
    }
}


