package com.thaison.hyundaithanhcong.controler.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thaison.hyundaithanhcong.R;
import com.thaison.hyundaithanhcong.controler.item.CarFeature;
import com.thaison.hyundaithanhcong.controler.item.CarFeatureItem;
import com.thaison.hyundaithanhcong.controler.others.ConstantStringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H81 on 3/16/2017.
 */

public class CarFeatureAdapter extends BaseAdapter {
    private static final String TAG = CarFeatureAdapter.class.getSimpleName();

    private ArrayList<String> features;

    public CarFeatureAdapter(ArrayList<String> features) {
        this.features = features;
    }

    @Override
    public int getCount() {
        return features.size();
    }

    @Override
    public String getItem(int position) {
        return features.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.item_car_featured, viewGroup, false);
            holder = new ViewHolder();
            holder.tvFeature = (TextView) view.findViewById(R.id.tv_feature);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String feature = features.get(position);
        holder.tvFeature.setText(feature);
        return view;
    }

    private class ViewHolder {
        private TextView tvFeature;
    }
}
