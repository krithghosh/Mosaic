package com.images.krith.mosaic;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by krith on 30/11/16.
 */

public class StaggeredViewAdapter extends ArrayAdapter<String> {
    private static final String TAG = "SampleAdapter";

    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private Context context;

    public StaggeredViewAdapter(Context context, int imgViewResourceId,
                                ArrayList<String> objects) {
        super(context, imgViewResourceId, objects);
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRandom = new Random();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.dynamic_image_view, parent, false);
            vh = new ViewHolder();
            vh.imgView = (DynamicHeightImageView) convertView.findViewById(R.id.imgView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);
        vh.imgView.setHeightRatio(positionHeight);
        Picasso.with(context).load(getItem(position)).placeholder(R.drawable.placeholder).into(vh.imgView);
        return convertView;
    }

    static class ViewHolder {
        DynamicHeightImageView imgView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0;
    }
}
