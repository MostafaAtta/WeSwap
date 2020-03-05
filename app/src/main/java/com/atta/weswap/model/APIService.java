package com.atta.weswap.model;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
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

    @FormUrlEncoded
    @POST("update_user")
    Call<Result> updateProfile(
            @Field("id") int id,
            @Field("name") String name,
            @Field("add_id") int addId,
            @Field("date_of_birth") String DateOfBirth,
            @Field("job") String job,
            @Field("location") String location

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


    @FormUrlEncoded
    @POST("add_address")
    Call<Result> addAddress(
            @Field("user_id") int userId,
            @Field("floor") String floor,
            @Field("apartmentNumber") String apartmentNumber,
            @Field("buildingNumber") String buildingNumber,
            @Field("area") String area,
            @Field("addressName") String addressName,
            @Field("fullAddress") String fullAddress,
            @Field("street") String street,
            @Field("landMark") String landMark,
            @Field("latitude") float latitude,
            @Field("longitude") float longitude

    );


    @PUT("edit_address/{id}/{user_id}/{floor}/{apartmentNumber}/{buildingNumber}/{area}/{addressName}/{fullAddress}/{street}/{landMark}/{latitude}/{longitude}")
    Call<Result> editAddress(
            @Path("id") int id,
            @Path("user_id") int userId,
            @Path("floor") String floor,
            @Path("apartmentNumber") String apartmentNumber,
            @Path("buildingNumber") String buildingNumber,
            @Path("area") String area,
            @Path("addressName") String addressName,
            @Path("fullAddress") String fullAddress,
            @Path("street") String street,
            @Path("landMark") String landMark,
            @Path("latitude") float latitude,
            @Path("longitude") float longitude

    );

    @DELETE("remove_address/{id}")
    Call<Result> deleteAddress(
            @Path("id") int id
    );



}
