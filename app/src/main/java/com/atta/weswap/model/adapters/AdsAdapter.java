package com.atta.weswap.model.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.R;
import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.Ad;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.MyViewHolder> {

    private List<Ad> ads;
    private Context context;
    private Activity activity;


    public AdsAdapter(ArrayList<Ad> data, Context context, Activity activity) {

        this.ads = data;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ads_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final Ad ad = ads.get(position) ;

        final int id = ad.getId();
        final String title = ad.getTitle();
        final String[] images = ad.getImagesUrls();
        final String date = ad.getCreationTime();
        final String location = ad.getArea();
        final String swapCategory = ad.getSwapCategory() + "  > " + ad.getSwapSubcategory();

        holder.adTitleTv.setText(title);
        holder.swapCategoryTv.setText(swapCategory);

        holder.dateTv.setText(date);
        holder.locationTv.setText(location);

        if (images[0] != null) {

            final String imageURL = APIClient.Images_BASE_URL + images[0];
            Picasso.get()
                    .load(imageURL)
                    .resize(60, 60)
                    .centerCrop()
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(view -> {

            //Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(HomeFragmentDirections.actionNavHomeToSubCategoriesFragment(category.getId()));
            /*Intent intent = new Intent(context.getApplicationContext(), ProductsActivity.class);
            intent.putExtra("cat_id", category.getId());
            context.startActivity(intent);*/
        });

        holder.favImage.setOnClickListener(view -> {

            //Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(HomeFragmentDirections.actionNavHomeToSubCategoriesFragment(category.getId()));
            /*Intent intent = new Intent(context.getApplicationContext(), ProductsActivity.class);
            intent.putExtra("cat_id", category.getId());
            context.startActivity(intent);*/
        });


    }

    @Override
    public int getItemCount() {
        return ads.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView adTitleTv, swapCategoryTv, dateTv, locationTv;

        ImageView imageView, favImage;

        MyViewHolder(View view) {
            super(view);
            adTitleTv = view.findViewById(R.id.title_tv);
            swapCategoryTv = view.findViewById(R.id.swap_tv);

            imageView = view.findViewById(R.id.ad_imageView);
            favImage = view.findViewById(R.id.add_fav);
            dateTv = view.findViewById(R.id.date_tv);
            locationTv = view.findViewById(R.id.location_tv);

        }
    }

    public List<Ad> getList(){
        return ads;
    }

}
