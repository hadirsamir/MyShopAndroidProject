package com.example.hadirsamir.myshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hadirsamir.myshop.Models.Data;
import com.example.hadirsamir.myshop.Models.DataBaseHelper;
import com.example.hadirsamir.myshop.Views.CartAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 25/02/18.
 */

public class FragmentOne extends Fragment {
    private View myview;
    private ImageView imageView;
    private TextView title, price;

    private RatingBar ratingBar;
    private Button orderbtn;
    private Data data;
    private float Avg_rate = 0;
    private ListView listView;
    private ReviewsAdapter reviewsAdapter;
    ArrayList<Data> dataArrayList;
    CartAdapter cart;
    DataBaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.firstfrag, container, false);
        imageView = (ImageView) myview.findViewById(R.id.imageView);
        title = (TextView) myview.findViewById(R.id.textView);
        price = (TextView) myview.findViewById(R.id.textView3);
        ratingBar = (RatingBar) myview.findViewById(R.id.rating);
        orderbtn = (Button) myview.findViewById(R.id.button);
        listView = (ListView) myview.findViewById(R.id.listView);


        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        data = detailsActivity.data;
        reviewsAdapter = new ReviewsAdapter(data.getReviewsList());


        Picasso.with(getActivity()).load(data.getProductImage()).into(imageView);
        title.setText(data.getProductName());
        price.setText(String.valueOf(data.getProductPrice()));

        for (int i = 0; i < data.getReviewsList().size(); i++) {
            Avg_rate += data.getReviewsList().get(i).getUserProductRating();
        }
        ratingBar.setRating(Avg_rate / data.getReviewsList().size());
        listView.setAdapter(reviewsAdapter);


        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DataBaseHelper(getActivity());
                db.cartInsert(new Data(data.getProductName(), data.getProductPrice()));
                Toast.makeText(getActivity(), "ItemAdded", Toast.LENGTH_LONG).show();

            }
        });


        return myview;
    }

}
