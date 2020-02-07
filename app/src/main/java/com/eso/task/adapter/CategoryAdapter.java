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


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Context context;
    private OnItemClickListener mClickListener;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category category = categoryList.get(position);
        holder.mNameCate.setText(category.getName());
        Glide.with(context).load(category.getCategory_img()).into(holder.mImageCate);
    }

    @Override
    public int getItemCount() {
        if (categoryList == null) return 0;
        return categoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageCate;
        TextView mNameCate;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImageCate = itemView.findViewById(R.id.image_cate);
            mNameCate = itemView.findViewById(R.id.name_cate);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }


    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
}
