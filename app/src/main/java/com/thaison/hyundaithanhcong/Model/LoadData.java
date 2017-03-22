package com.thaison.hyundaithanhcong.Model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thaison.hyundaithanhcong.R;
import com.thaison.hyundaithanhcong.controler.item.CarColor;
import com.thaison.hyundaithanhcong.controler.item.CarFeature;
import com.thaison.hyundaithanhcong.controler.item.CarItem;
import com.thaison.hyundaithanhcong.controler.item.CarLogo;
import com.thaison.hyundaithanhcong.controler.item.CarParameter;
import com.thaison.hyundaithanhcong.controler.item.CarParameterItem;
import com.thaison.hyundaithanhcong.controler.item.Color;
import com.thaison.hyundaithanhcong.controler.others.ConstantStringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by H81 on 3/18/2017.
 */

public class LoadData {
    private static final String CAR_DATA_URL = ConstantStringURL.getServiceDomain()
            + ConstantStringURL.getCarsInfoUrl
            + ConstantStringURL.getUsernamePasswordDeviceId();
    private static final String DONG_CO = "Động cơ";
    private static final String TANG_TOC = "Tăng tốc";
    private static final String VAN_TOC_TOI_DA = "Vận tốc tối đa";

    private ArrayList<CarItem> cars;
    private ArrayList<CarLogo> carLogos;
    private ArrayList<CarColor> carColors;
    private ArrayList<CarFeature> carFeatures;
    private ArrayList<CarParameter> carParameters;

    private Context mContext;
    private RequestQueue queue;
    private JsonObjectRequest jsonObjectRequest;

    private boolean isLoadCompleted;

    public LoadData(Context mContext) {
        this.mContext = mContext;
        isLoadCompleted = false;
        cars = new ArrayList<>();
        carLogos = new ArrayList<>();
        carColors = new ArrayList<>();
        carFeatures = new ArrayList<>();
        carParameters = new ArrayList<>();
        getCarInfo();
    }

    public void getCarInfo() {
        queue = Volley.newRequestQueue(mContext);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, CAR_DATA_URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int i;
                            int j;

                            // list all cars

                            JSONArray carList = response.getJSONArray("CarList");
                            for (i = 0; i < carList.length(); i++) {
                                JSONObject car = carList.getJSONObject(i);
                                boolean isShow = car.getBoolean("ShowInHomePage");
                                // list all car is showed
                                if (isShow) {
                                    int id = car.getInt("Id");

                                    // cars & image car center

                                    JSONObject image = car.getJSONObject("Image");
                                    String carImageUrl = ConstantStringURL.getServiceDomain()
                                            + image.getString("Url");
                                    String name = car.getString("Name");
                                    String slogan = car.getString("Slogan");
                                    cars.add(new CarItem(id, carImageUrl, name, slogan));

                                    // car logo

                                    JSONObject logo = car.getJSONObject("Logo");
                                    String carLogoUrl = ConstantStringURL.getServiceDomain()
                                            + logo.getString("Url");
                                    carLogos.add(new CarLogo(id, carLogoUrl));

                                    // car color

                                    ArrayList<Color> colorList = new ArrayList<>();
                                    JSONArray colors = car.getJSONArray("Colors");
                                    for (j = 0; j < colors.length(); j++) {
                                        int colorId = colors.getJSONObject(j).getInt("Color");
                                        colorList.add(new Color(colorId));
                                        JSONObject colorImage = colors.getJSONObject(j).getJSONObject("Image");
                                        String colorImageUrl = ConstantStringURL.getServiceDomain()
                                                + colorImage.getString("Url");
                                        carColors.add(new CarColor(id, colorList));
                                    }

                                    // carFeatures

                                    JSONArray featuredList = car.getJSONArray("FeaturedList");
                                    ArrayList<String> features = new ArrayList<>();
                                    for (j = 0; j < featuredList.length(); j++) {
                                        Log.i("fesges", "j = " + j);
                                        String feature = featuredList.getString(j);
                                        features.add(feature);
                                    }
                                    carFeatures.add(new CarFeature(id, features));

                                    // parameters

                                    JSONObject compares = car.getJSONObject("Compares");
                                    ArrayList<CarParameterItem> carParameterItems = new ArrayList<>();
                                    String dongCo = compares.getString("DongCo");
                                    String tangToc = compares.getString("TangToc");
                                    String vanTocToiDa = compares.getString("VanTocToiDa");
                                    carParameterItems.add(new CarParameterItem(R.drawable.ic_left1, DONG_CO, dongCo));
                                    carParameterItems.add(new CarParameterItem(R.drawable.ic_left2, TANG_TOC, tangToc));
                                    carParameterItems.add(new CarParameterItem(R.drawable.ic_left3, VAN_TOC_TOI_DA, vanTocToiDa));
                                    carParameters.add(new CarParameter(id, carParameterItems));
                                }
                            }
                            isLoadCompleted = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public ArrayList<CarItem> getCars() {
        return cars;
    }

    public ArrayList<CarLogo> getCarLogos() {
        return carLogos;
    }

    public ArrayList<CarColor> getCarColors() {
        return carColors;
    }

    public ArrayList<CarFeature> getCarFeatures() {
        return carFeatures;
    }

    public ArrayList<CarParameter> getCarParameters() {
        return carParameters;
    }

    public boolean isLoadCompleted() {
        return isLoadCompleted;
    }
}
