package com.atta.weswap.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.ProductsActivity;
import com.atta.weswap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private List<Category> categories;
    private Context context;


    public CategoriesAdapter(ArrayList<Category> data, Context context) {

        this.categories = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final Category category = categories.get(position) ;

        final int id = category.getId();
        final String image = category.getImage();
        final String name = category.getCategory();

        holder.categoryName.setText(name);


        if (category.getImage() != null) {

            final String imageURL = APIClient.Images_BASE_URL + image;
            Picasso.get()
                    .load(imageURL)
                    .resize(80, 80)
                    .centerCrop()
                    .into(holder.categoryImage);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ProductsActivity.class);
                intent.putExtra("cat_id", category.getId());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;

        MyViewHolder(View view) {
            super(view);
            categoryName = view.findViewById(R.id.name_txt);
            categoryImage = view.findViewById(R.id.imageView);

        }
    }

    public List<Category> getList(){
        return categories;
    }

}
