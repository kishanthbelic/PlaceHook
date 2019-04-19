package com.example.kishanthprab.placehook.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kishanthprab.placehook.R;
import com.example.kishanthprab.placehook.Recycler.ReviewRecyclerAdapter;
import com.example.kishanthprab.placehook.Recycler.ReviewRecyclerListItem;

import java.util.ArrayList;



public class PlaceDetailsDialog extends DialogFragment implements View.OnClickListener {

    private Callback callback;
    private static final String TAG = "PLaceDetailsDialog";

    ImageButton close, navigate;
    TextView txt_placeName, txt_totRating, txt_distance, txt_address, txt_numOfReviews;
    ImageView imgV_placePhoto;

    RecyclerView review_recyclerView;
    ReviewRecyclerAdapter review_recyclerAdapter;

    ArrayList<ReviewRecyclerListItem> reviewsArrayList;

    Context mContext =null;

    //public

    public static PlaceDetailsDialog newInstance() {

        return new PlaceDetailsDialog();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_placedetails, container, false);

        close = (ImageButton) view.findViewById(R.id.plcDetails_dialog_Close);
        navigate = (ImageButton) view.findViewById(R.id.plcDetails_navigate);
        txt_placeName = (TextView) view.findViewById(R.id.plcDetails_txt_plcName);
        txt_totRating = (TextView) view.findViewById(R.id.plcDetails_totRating);
        txt_address = (TextView) view.findViewById(R.id.plcDetails_address);
        txt_numOfReviews = (TextView) view.findViewById(R.id.plcDetails_NumOfReviews);
        imgV_placePhoto = (ImageView) view.findViewById(R.id.plcDetails_imgV);


        close.setOnClickListener(this);
        navigate.setOnClickListener(this);


        //recycler view
        review_recyclerView = (RecyclerView) view.findViewById(R.id.rev_recyclerView);
        review_recyclerView.setHasFixedSize(true);
        review_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        reviewsArrayList = new ArrayList<>();
        reviewsArrayList.clear();

        review_recyclerAdapter = new ReviewRecyclerAdapter(reviewsArrayList,getActivity());
        review_recyclerView.setAdapter(review_recyclerAdapter);

        callback.onActionCLickForAllViews(txt_placeName, txt_totRating, txt_address, txt_numOfReviews,reviewsArrayList);
        callback.setImageView(imgV_placePhoto);


        return view;
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.plcDetails_dialog_Close:
                dismiss();
                break;

            case R.id.plcDetails_navigate:

                // callback.onActionClick("Whatever");
                //callback.onActionClick(txt_placeName);

                // dismiss();
                break;

        }

    }

    public interface Callback {

        //void onActionClick(String name);
        void onActionClick(TextView txtV);

        void onActionCLickForAllViews(
                TextView txt_placeName,
                TextView txt_totRating,
                TextView txt_address,
                TextView txt_numOfReviews,
                ArrayList<ReviewRecyclerListItem> reviewsList

        );

        void setImageView(
                ImageView imageView
        );


    }

}

