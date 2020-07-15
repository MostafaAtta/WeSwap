package com.atta.weswap.model;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {


    //The register call
    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    //the signin call
    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("get_categories")
    Call<CategoriesResult> getCategories();


    @GET("get_subcategories/{cat_id}")
    Call<SubcategoriesResult> getSubcategories(
            @Path("cat_id") int catId
    );

    @GET("get_ads/{subcategory_id}")
    Call<AdsResult> getAds(
            @Path("subcategory_id") int subcategoryId
    );

    @GET("get_my_ads/{user_id}")
    Call<AdsResult> getMyAds(
            @Path("user_id") int userId
    );

    @FormUrlEncoded
    @POST("update_user")
    Call<Result> updateProfile(
            @Field("id") int id,
            @Field("name") String name,
            @Field("phone") String phone

    );


    @FormUrlEncoded
    @POST("get_profile")
    Call<Profile> getProfile(
            @Field("user_id") int userId
    );


    @PUT("update_password/{id}/{old_password}/{password}")
    Call<Result> resetPassword(
            @Path("id") int userId,
            @Path("old_password") String oldPassword,
            @Path("password") String password
    );

    @DELETE("remove_from_favorite/{id}")
    Call<Result> removeFromFavorite(
            @Path("id") int id
    );

    @FormUrlEncoded
    @POST("remove_from_favorite")
    Call<Result> removeFromFavorite(
            @Field("user_id") int userId,
            @Field("dish_id") int dishId
    );


    @DELETE("remove_address/{id}")
    Call<Result> deleteAddress(
            @Path("id") int id
    );


    @FormUrlEncoded
    @POST("create_ad")
    Call<Result> createAd(
            @Field("title") String title,
            @Field("user_id") int userId,
            @Field("category_id") int categoryId,
            @Field("subcategory_id") int subcategoryId,
            @Field("swap_category_id") int swapCategoryId,
            @Field("swap_subcategory_id") int swapSubcategoryId,
            @Field("description") String description,
            @Field("condition_id") int conditionId,
            @Field("brand_id") int brandId,
            @Field("area_id") int areaId,
            @Field("phone") String phone,
            @Field("creation_time") String creationTime,
            @FieldMap Map<String, String> images ,
            @FieldMap Map<String, String> imagesNames
    );

    @GET("get_areas")
    Call<AreaResult> getAreas();


}
