package com.eso.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eso.task.R;
import com.eso.task.model.Category;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Category> productsList;
    private Context context;

    public ProductsAdapter(Context context, List<Category> productsList) {
        this.context = context;
        this.productsList = productsList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category products = productsList.get(position);
        holder.mNamePro.setText(products.getProducts().get(position).getName());
        holder.mKgPro.setText(products.getProducts().get(position).getWeight());
        holder.mPricePro.setText(products.getProducts().get(position).getPrice());
        Glide.with(context).load(products.getProducts().get(position).getProduct_img()).into(holder.mImagePro);
    }

    @Override
    public int getItemCount() {
        if (productsList == null) return 0;
        return productsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImagePro;
        TextView mNamePro;
        TextView mKgPro;
        TextView mPricePro;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImagePro = itemView.findViewById(R.id.image_pro);
            mNamePro = itemView.findViewById(R.id.name_pro);
            mKgPro = itemView.findViewById(R.id.kg_pro);
            mPricePro = itemView.findViewById(R.id.price_pro);
        }
    }


}
