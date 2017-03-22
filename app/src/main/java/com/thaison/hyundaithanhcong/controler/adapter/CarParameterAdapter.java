package com.thaison.hyundaithanhcong.controler.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thaison.hyundaithanhcong.R;
import com.thaison.hyundaithanhcong.controler.item.CarParameter;
import com.thaison.hyundaithanhcong.controler.item.CarParameterItem;
import com.thaison.hyundaithanhcong.controler.others.ConstantStringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H81 on 3/16/2017.
 */

public class CarParameterAdapter extends BaseAdapter {
    private static final String TAG = CarParameterAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<CarParameterItem> carParameterItems;

    public CarParameterAdapter(Context mContext, ArrayList<CarParameterItem> carParameterItems) {
        this.mContext = mContext;
        this.carParameterItems = carParameterItems;
    }


    @Override
    public int getCount() {
        return carParameterItems.size();
    }

    @Override
    public CarParameterItem getItem(int position) {
        return carParameterItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_parameter, viewGroup, false);
            holder = new ViewHolder();
            holder.ivParameterLogo = (ImageView) view.findViewById(R.id.iv_parameter_logo);
            holder.tvDongCo = (TextView) view.findViewById(R.id.tv_dong_co);
            holder.tvDongCoChiTiet = (TextView) view.findViewById(R.id.tv_dong_co_chi_tiet);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CarParameterItem carParameterItem = carParameterItems.get(position);
        holder.ivParameterLogo.setImageResource(carParameterItem.getCarParameterLogo());
        holder.tvDongCo.setText(carParameterItem.getDongCo());
        holder.tvDongCoChiTiet.setText(carParameterItem.getDongCoChiTiet());
        return view;
    }

    private class ViewHolder {
        private ImageView ivParameterLogo;
        private TextView tvDongCo;
        private TextView tvDongCoChiTiet;
    }
}
