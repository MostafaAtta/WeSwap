package com.atta.weswap.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.atta.weswap.R;
import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.Ad;
import com.atta.weswap.model.adapters.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AdDetailsFragment extends Fragment implements View.OnClickListener {

    View root;

    private String[] imageUrls;

    ViewPager viewPager;

    FloatingActionButton floatingActionButton;

    TextView titleTV, swapTv, dateTv, locationTv, phoneTv, conditionTv, brandTv;

    Ad ad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_ad_details, container, false);

        ad = AdDetailsFragmentArgs.fromBundle(getArguments()).getAd();


        String[] finalUrls;
        if (ad.getImagesUrls() != null){

            imageUrls = ad.getImagesUrls();


            finalUrls = new String[imageUrls.length];

            for (int i=0; i < imageUrls.length; i++){
                finalUrls[i] = APIClient.Images_BASE_URL + imageUrls[i];
            }
        }else {
            finalUrls = new String[1];
            finalUrls[0] = APIClient.Images_BASE_URL + "property_tmp.png";
        }

        floatingActionButton = root.findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(this);
        viewPager = root.findViewById(R.id.slider);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(), finalUrls);
        viewPager.setAdapter(adapter);

        titleTV = root.findViewById(R.id.title_tv);
        titleTV.setText(ad.getTitle());
        swapTv = root.findViewById(R.id.swap_tv);
        swapTv.setText("Swap with: " + ad.getSwapSubcategory());
        dateTv = root.findViewById(R.id.date_tv);
        dateTv.setText("Created at: " +ad.getCreationTime());
        locationTv = root.findViewById(R.id.location_tv);
        locationTv.setText("Location: " +ad.getArea());
        phoneTv = root.findViewById(R.id.phone_tv);
        phoneTv.setText("Phone: " +ad.getPhone());
        conditionTv = root.findViewById(R.id.condition_tv);
        conditionTv.setText("Condition: " +ad.getCondition());
        brandTv = root.findViewById(R.id.brand_tv);
        brandTv.setText("Brand: " +ad.getBrand());


        return root;
    }

    @Override
    public void onClick(View view) {
        if (view == floatingActionButton){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + ad.getPhone()));
            startActivity(intent);
        }
    }
}
