package com.example.laptopapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.laptopapp.Activity.CartActivity;
import com.example.laptopapp.Activity.MainActivity;
import com.example.laptopapp.Activity.ProductDetails;
import com.example.laptopapp.Data.laptopData;
import com.example.laptopapp.Model.Cart;
import com.example.laptopapp.Model.Laptop;
import com.example.laptopapp.R;
import com.example.laptopapp.Session.SessionManagement;
import com.example.laptopapp.dataCart.CartStoge;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class frm_details extends Fragment {
    Laptop laptop;
    ProgressDialog progressDialog;
    TextView tv_name, tv_masp, tv_price;
    YouTubePlayerView youTubePlayerView;
    ImageSlider imageSlider;
    ImageView addtocart;
    String Product_id;
    ArrayList<Cart> listcart = new ArrayList<>();
    CartStoge cartStoge;
    String user_id;
    String user;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.product_details, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SessionManagement sessionManagement = new SessionManagement(root.getContext());
         user_id = sessionManagement.getSession();
         ProductDetails productDetails = (ProductDetails) getActivity();
        imageSlider = root.findViewById(R.id.imagedetail_laptop);
        tv_name = root.findViewById(R.id.tv_namedetail_Laptop);
        tv_masp = root.findViewById(R.id.TV_MaSP);
        tv_price = root.findViewById(R.id.tv_pricedetail_laptop);
        addtocart = root.findViewById(R.id.addto_cartproductdetail);
        youTubePlayerView = root.findViewById(R.id.video);
        Product_id = getArguments().getString("Product_ID");
        cartStoge = new CartStoge(root.getContext());
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_id.equals("-1")) {
                    Toast.makeText(root.getContext(),"B???n ph???i ????ng nh???p ????? mua h??ng",Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkexistproduct = false;
                    int quantilyincart = 0;
                    for (int i = 0; i < listcart.size(); i++) {
                        if (listcart.get(i).getProduct_id().equals(tv_masp.getText().toString())) {
                            checkexistproduct = true;
                            quantilyincart = Integer.parseInt(listcart.get(i).getQuality());
                            break;
                        }
                    }
                    if (checkexistproduct == true) {
                        cartStoge.updateCart(user_id, tv_masp.getText().toString(), String.valueOf(quantilyincart + 1));
                        Intent intent = new Intent(root.getContext(), CartActivity.class);
                        startActivity(intent);
                    } else {
                        laptopData arrayData = new laptopData();
                        arrayData.addtocartlaptop(tv_masp.getText().toString(), user_id, root.getContext());
                       progressDialog= ProgressDialog.show(root.getContext(),"??ang th??m v??o gi??? h??ng","Vui l??ng ch???...",false,false);
                        try {
                            Thread.sleep(1000);
                            progressDialog.dismiss();
                            Intent intent = new Intent(root.getContext(), CartActivity.class);
                            startActivity(intent);
                        }catch (Exception e){

                        }

                    }
                }
            }
        });
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
        listcart = new ArrayList<>();
        listcart = cartStoge.getallcart(user_id);
        laptopData arrayData = new laptopData();
     arrayData.takeallinfo(Product_id,tv_name,tv_price,tv_masp,imageSlider,youTubePlayerView,addtocart);
    }
}
