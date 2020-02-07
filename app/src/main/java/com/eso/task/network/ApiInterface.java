package com.eso.task.network;

import com.eso.task.model.Category;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("task/categories")
    Call<List<Category>> getCategories();

    @GET("task/categories/{id}")
    Call<Category> getProducts(@Path("id")String id);

}
