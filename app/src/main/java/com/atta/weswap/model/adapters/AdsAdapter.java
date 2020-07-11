package com.atta.weswap.model.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.R;
import com.atta.weswap.model.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.MyViewHolder> {

    private List<Subcategory> subcategories;
    private Context context;
    private Activity activity;


    public AdsAdapter(ArrayList<Subcategory> data, Context context, Activity activity) {

        this.subcategories = data;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcat_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final Subcategory subcategory = subcategories.get(position) ;

        final int id = subcategory.getId();
        final String name = subcategory.getSubcategory();
        final String noOfAds = subcategory.getNumOfAds() + " Ads";

        holder.subcategoryName.setText(name);
        holder.subcategoryCont.setText(noOfAds);


        holder.itemView.setOnClickListener(view -> {

            //Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(HomeFragmentDirections.actionNavHomeToSubCategoriesFragment(category.getId()));
            /*Intent intent = new Intent(context.getApplicationContext(), ProductsActivity.class);
            intent.putExtra("cat_id", category.getId());
            context.startActivity(intent);*/
        });



    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subcategoryName, subcategoryCont;

        MyViewHolder(View view) {
            super(view);
            subcategoryName = view.findViewById(R.id.title_tv);
            subcategoryCont = view.findViewById(R.id.count_tv);

        }
    }

    public List<Subcategory> getList(){
        return subcategories;
    }

}
