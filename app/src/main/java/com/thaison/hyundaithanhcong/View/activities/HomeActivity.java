package com.thaison.hyundaithanhcong.View.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.thaison.hyundaithanhcong.R;
import com.thaison.hyundaithanhcong.controler.adapter.CarAdapter;
import com.thaison.hyundaithanhcong.controler.item.CarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H81 on 3/9/2017.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final String CAR_ID = "CAR ID";
    private static final String LIST_CAR = "LIST CAR";

    private Button btnHome;
    private Button btnAgencySystem;
    private Button btnNews;
    private Button btnInstallmentLoan;
    private Button btnCsi;
    private Button btnSsi;
    private ListView lvCars;

    private List<Button> menuButtons;
    private CarAdapter carAdapter;

    private ArrayList<CarItem> cars;

    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bundle = getIntent().getExtras();
        cars = (ArrayList<CarItem>) bundle.getSerializable(LIST_CAR);
        Log.i(TAG, "Size: " + cars.size());
        initViews();
    }

    private void initViews() {
        btnHome = (Button) findViewById(R.id.btn_home);
        btnHome.setOnClickListener(this);
        btnHome.setPressed(true);
        btnAgencySystem = (Button) findViewById(R.id.btn_agency_system);
        btnAgencySystem.setOnClickListener(this);
        btnNews = (Button) findViewById(R.id.btn_news);
        btnNews.setOnClickListener(this);
        btnInstallmentLoan = (Button) findViewById(R.id.btn_installment_loan);
        btnInstallmentLoan.setOnClickListener(this);
        btnCsi = (Button) findViewById(R.id.btn_csi);
        btnCsi.setOnClickListener(this);
        btnSsi = (Button) findViewById(R.id.btn_ssi);
        btnSsi.setOnClickListener(this);

        menuButtons = new ArrayList<>();
        menuButtons.add(btnHome);
        menuButtons.add(btnAgencySystem);
        menuButtons.add(btnNews);
        menuButtons.add(btnInstallmentLoan);
        menuButtons.add(btnCsi);
        menuButtons.add(btnSsi);

        lvCars = (ListView) findViewById(R.id.lv_cars);
        carAdapter = new CarAdapter(this, cars);
        lvCars.setAdapter(carAdapter);
        lvCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int carId = carAdapter.getItem(position).getCarId();
                startCarDetailActivity(carId);
            }
        });
    }

    private void startCarDetailActivity(int carId) {
        intent = new Intent(this, CarDetailActivity.class);
        bundle.putInt(CAR_ID, carId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_gohome_active));
                    } else {
                        menuButtons.get(i).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_product));
                    }
                }
                break;

            case R.id.btn_agency_system:
                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        menuButtons.get(0).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_gohome));
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

            case R.id.btn_news:
                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        menuButtons.get(0).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_gohome));
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

            case R.id.btn_installment_loan:
                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        menuButtons.get(0).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_gohome));
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

            case R.id.btn_csi:
                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        menuButtons.get(0).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_gohome));
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

            case R.id.btn_ssi:
                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        menuButtons.get(0).setBackground(
                                getResources().getDrawable(R.drawable.btn_ft_gohome));
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
        }
    }
}
