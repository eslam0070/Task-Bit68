package com.eso.task.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eso.task.R;
import com.eso.task.adapter.ProductsAdapter;
import com.eso.task.model.Category;
import com.eso.task.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    List<Category> productsList = new ArrayList<>();
    String id, name, image;
    ImageView mCategoryImage;
    TextView mCategoryNameTxt;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("image");
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView = findViewById(R.id.product);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RetrofitClient.getInstance().getApi().getProducts(id).enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NotNull Call<Category> call, @NotNull Response<Category> response) {
                if (response.body() != null && response.isSuccessful()){
                    Category category = response.body();
                    for (int i = 0; i < category.getProducts().size(); i++) {
                        productsList.add(category);
                    }
                    productsAdapter = new ProductsAdapter(ProductsActivity.this, productsList);
                    recyclerView.setAdapter(productsAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Category> call, @NotNull Throwable t) {
                Toast.makeText(ProductsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        mCategoryImage = findViewById(R.id.category_image);
        mCategoryNameTxt = findViewById(R.id.category_name_txt);
        mCategoryNameTxt.setText(name);
        Glide.with(this).load(image).into(mCategoryImage);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_shop) {
            Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void filter(View view) {
        Toast.makeText(this, "Filter", Toast.LENGTH_SHORT).show();
    }

    public void sortBy(View view) {
        Toast.makeText(this, "Sort By", Toast.LENGTH_SHORT).show();
    }
}
