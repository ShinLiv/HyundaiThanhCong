package com.thaison.hyundaithanhcong.View.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.thaison.hyundaithanhcong.R;
import com.thaison.hyundaithanhcong.controler.adapter.CarFeatureAdapter;
import com.thaison.hyundaithanhcong.controler.adapter.CarParameterAdapter;
import com.thaison.hyundaithanhcong.controler.item.CarColor;
import com.thaison.hyundaithanhcong.controler.item.CarFeature;
import com.thaison.hyundaithanhcong.controler.item.CarItem;
import com.thaison.hyundaithanhcong.controler.item.CarLogo;
import com.thaison.hyundaithanhcong.controler.item.CarParameter;
import com.thaison.hyundaithanhcong.controler.item.Color;
import com.thaison.hyundaithanhcong.controler.item.Colors;
import com.thaison.hyundaithanhcong.controler.others.ColorDrawableResource;
import com.thaison.hyundaithanhcong.controler.others.ConstantStringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H81 on 3/15/2017.
 */

public class CarDetailActivity extends Activity implements View.OnClickListener {
    private static final String TAG = CarDetailActivity.class.getSimpleName();
    private static final String CAR_ID = "CAR ID";
    private static final String LIST_CAR = "LIST CAR";
    private static final String LIST_CAR_LOGO = "LIST CAR LOGO";
    private static final String LIST_CAR_COLOR = "LIST CAR COLOR";
    private static final String LIST_CAR_FEATURE = "LIST CAR FEATURE";
    private static final String LIST_CAR_PARAMETER = "LIST CAR PARAMETERS";


    private ProgressBar pvLoadingColorImages;
    private LinearLayout llColorImages;
    private List<Integer> colorImages;
    private List<Button> btnColorImages;
    private List<Colors> colorses;
    private int colorId;

    private Button btnCarDetailHome;
    private Button btnCarDetailOverview;
    private Button btnCarDetailExterior;
    private Button btnCarDetailFurniture;
    private Button btnCarDetailParameter;
    private Button btnCarDetailGallery;
    private Button btnCarDetailNews;
    private Button btnCarDetailSalesPromotion;
    private Button btnCarDetailPrime;

    private ImageView ivCarImage;
    private ImageView ivCarLogo;

    private List<Button> menuButtons;

    private ListView lvCarFeature;
    private CarFeatureAdapter carFeatureAdapter;
    private ListView lvCarParameter;
    private CarParameterAdapter carParameterAdapter;

    private ProgressBar pbLoadingCarImage;
    private ProgressBar pbLoadingCarLogo;

    private RequestQueue queue;
    private JsonObjectRequest jsonObjectRequest;

    private int carId;

    private ArrayList<CarItem> cars;
    private ArrayList<CarLogo> carLogos;
    private ArrayList<CarColor> carColors;
    private ArrayList<CarFeature> carFeatures;
    private ArrayList<CarParameter> carParameters;

    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        bundle = getIntent().getExtras();
        carId = bundle.getInt(CAR_ID);
        initViews();
    }

    private void initViews() {
        // init menu button

        btnCarDetailHome = (Button) findViewById(R.id.btn_car_detail_home);
        btnCarDetailHome.setOnClickListener(this);
        btnCarDetailOverview = (Button) findViewById(R.id.btn_car_detail_overview);
        btnCarDetailOverview.setOnClickListener(this);
        btnCarDetailOverview.setPressed(true);
        btnCarDetailExterior = (Button) findViewById(R.id.btn_car_detail_exterior);
        btnCarDetailExterior.setOnClickListener(this);
        btnCarDetailFurniture = (Button) findViewById(R.id.btn_car_detail_furniture);
        btnCarDetailFurniture.setOnClickListener(this);
        btnCarDetailParameter = (Button) findViewById(R.id.btn_car_detail_parameter);
        btnCarDetailParameter.setOnClickListener(this);
        btnCarDetailGallery = (Button) findViewById(R.id.btn_car_detail_gallery);
        btnCarDetailGallery.setOnClickListener(this);
        btnCarDetailNews = (Button) findViewById(R.id.btn_car_detail_news);
        btnCarDetailNews.setOnClickListener(this);
        btnCarDetailSalesPromotion = (Button) findViewById(R.id.btn_car_detail_sales_promotion);
        btnCarDetailSalesPromotion.setOnClickListener(this);
        btnCarDetailPrime = (Button) findViewById(R.id.btn_car_detail_prime);
        btnCarDetailPrime.setOnClickListener(this);

        menuButtons = new ArrayList<>();
        menuButtons.add(btnCarDetailHome);
        menuButtons.add(btnCarDetailOverview);
        menuButtons.add(btnCarDetailExterior);
        menuButtons.add(btnCarDetailFurniture);
        menuButtons.add(btnCarDetailParameter);
        menuButtons.add(btnCarDetailGallery);
        menuButtons.add(btnCarDetailNews);
        menuButtons.add(btnCarDetailSalesPromotion);
        menuButtons.add(btnCarDetailPrime);

        // init car feature list view

        carFeatures = (ArrayList<CarFeature>) bundle.getSerializable(LIST_CAR_FEATURE);
        lvCarFeature = (ListView) findViewById(R.id.lv_car_feature);
        for (int i = 0; i < carFeatures.size(); i++) {
            if (carFeatures.get(i).getId() == carId) {
                carFeatureAdapter = new CarFeatureAdapter(carFeatures.get(i).getFeatures());
                break;
            }
        }
        lvCarFeature.setAdapter(carFeatureAdapter);

        // init car parameter list view

        carParameters = (ArrayList<CarParameter>) bundle.getSerializable(LIST_CAR_PARAMETER);
        lvCarParameter = (ListView) findViewById(R.id.lv_parameters);
        for (int i = 0; i < carParameters.size(); i++) {
            if (carParameters.get(i).getId() == carId) {
                carParameterAdapter = new CarParameterAdapter(this, carParameters.get(i).getCarParameterItems());
            }
        }
        lvCarParameter.setAdapter(carParameterAdapter);

        // init color images

        llColorImages = (LinearLayout) findViewById(R.id.ll_color_images);
        carLogos = (ArrayList<CarLogo>) bundle.getSerializable(LIST_CAR_COLOR);
        for (int i = 0; i < carColors.size(); i++) {
            if (carColors.get(i).getId() == carId) {
                addColorImages(carColors.get(i).getColors());
            }
        }

        // init car image in center

        ivCarImage = (ImageView) findViewById(R.id.iv_car_image);
        cars = (ArrayList<CarItem>) bundle.getSerializable(LIST_CAR);
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarId() == carId) {
                setCarImage(cars.get(i).getCarImageUrl());
                break;
            }
        }

        // init car logo at the top of car parameter list view

        ivCarLogo = (ImageView) findViewById(R.id.btn_car_logo);
        carLogos = (ArrayList<CarLogo>) bundle.getSerializable(LIST_CAR_LOGO);
        for (int i = 0; i < carLogos.size(); i++) {
            if (carLogos.get(i).getId() == carId) {
                setCarLogo(carLogos.get(i).getCarLogoUrl());
            }
        }
    }

    private void setColorses() {
        queue = Volley.newRequestQueue(this);
        String carInfoUrl = ConstantStringURL.getServiceDomain() + ConstantStringURL.getCarsInfoUrl +
                ConstantStringURL.getUsernamePasswordDeviceId();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, carInfoUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray carList = response.getJSONArray("CarList");
                            for (int i = 0; i < carList.length(); i++) {
                                JSONObject car = carList.getJSONObject(i);
                                if (car.getInt("Id") == carId) {
                                    JSONArray colors = car.getJSONArray("Colors");
                                    for (int j = 0; j < colors.length(); j++) {
                                        JSONObject image = colors.getJSONObject(j).getJSONObject("Image");
                                        String url = ConstantStringURL.getServiceDomain()
                                                + image.getString("Url");
                                        int color = colors.getJSONObject(j).getInt("Color");
                                        colorses.add(new Colors(url, color));
                                    }
                                    break;
                                }
                            }
//                            addColorImages();
                            Log.i(TAG, "colorsesSize1 = " + colorses.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CarDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private void addColorImages(ArrayList<Color> colors) {
        for (int i = 0; i < colorses.size(); i++) {
            ImageView ivColor = new ImageView(this);
            ivColor.setBackgroundResource(new ColorDrawableResource(colorses.get(i).getColorId())
                    .getDrawableResource());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, 70);
            llColorImages.addView(ivColor, params);
        }
    }

    private void setCarImage(String carImageUrl) {
        pbLoadingCarImage = (ProgressBar) findViewById(R.id.pb_loading_car_image);
        Glide.with(this)
                .load(carImageUrl)
                .fitCenter()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        pbLoadingCarImage.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbLoadingCarImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(ivCarImage);

    }

    private void setCarLogo(String carLogoUrl) {
        pbLoadingCarLogo = (ProgressBar) findViewById(R.id.pb_loading_car_logo);
        Glide.with(this)
                .load(carLogoUrl)
                .fitCenter()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        pbLoadingCarLogo.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbLoadingCarLogo.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(ivCarLogo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_car_detail_home:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home_active));
                    } else {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_product));
                    }
                    onBackPressed();
                }
                break;

            case R.id.btn_car_detail_overview:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 1) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;

            case R.id.btn_car_detail_exterior:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 2) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;

            case R.id.btn_car_detail_furniture:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 3) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;

            case R.id.btn_car_detail_parameter:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 4) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;

            case R.id.btn_car_detail_gallery:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 5) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;

            case R.id.btn_car_detail_news:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 6) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;

            case R.id.btn_car_detail_sales_promotion:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 7) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;

            case R.id.btn_car_detail_prime:
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_car_detail_home)
                        );
                    } else {
                        if (i == 8) {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product_selected));
                        } else {
                            menuButtons.get(i).setBackground(
                                    getResources().getDrawable(R.drawable.btn_ft_product));
                        }
                    }
                }
                break;
        }
    }
}
