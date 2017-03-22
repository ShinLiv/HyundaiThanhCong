package com.thaison.hyundaithanhcong.controler.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.thaison.hyundaithanhcong.R;
import com.thaison.hyundaithanhcong.controler.item.CarItem;
import com.thaison.hyundaithanhcong.controler.others.ConstantStringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H81 on 3/13/2017.
 */

public class CarAdapter extends BaseAdapter {
    private static final String TAG = CarAdapter.class.getSimpleName();

    private Context mContext;
    private List<CarItem> carItems;

    private ProgressBar pbLoadingCarImagePreview;

    public CarAdapter(Context mContext, ArrayList<CarItem> carItems) {
        this.mContext = mContext;
        this.carItems = carItems;
    }

    @Override
    public int getCount() {
        return carItems.size();
    }

    @Override
    public CarItem getItem(int position) {
        return carItems.get(position);
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
            view = inflater.inflate(R.layout.item_car, viewGroup, false);
            holder = new ViewHolder();
            holder.ivCarImage = (ImageView) view.findViewById(R.id.iv_car_image);
            holder.tvCarName = (TextView) view.findViewById(R.id.tv_car_name);
            holder.tvCarSlogan = (TextView) view.findViewById(R.id.tv_car_slogan);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        CarItem car = carItems.get(position);
        pbLoadingCarImagePreview = (ProgressBar) view.findViewById(R.id.pb_loading_car_image_preview);
        Glide.with(mContext)
                .load(car.getCarImageUrl())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.i(TAG, "Loading");
                        pbLoadingCarImagePreview.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Toast.makeText(mContext, "Loading completed", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Loading completed");
                        pbLoadingCarImagePreview.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(holder.ivCarImage);
        holder.tvCarName.setText(car.getCarName());
        holder.tvCarSlogan.setText(car.getCarSlogan());

        return view;
    }

    private boolean isNetworkConnection() {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // check 3g
        boolean is3G = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();

        // check wifi
        boolean isWiFi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        if (is3G || isWiFi) {
            return true;
        }

        return false;
    }

    private class ViewHolder {
        private ImageView ivCarImage;
        private TextView tvCarName;
        private TextView tvCarSlogan;
    }
}
