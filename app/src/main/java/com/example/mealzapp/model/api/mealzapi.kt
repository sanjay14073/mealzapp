package com.example.mealzapp.model.api

import  retrofit2.Call
import com.example.mealzapp.response.MealsCategoryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class mealzapi {
    private lateinit var api:MealsApi

    init {
        val retrofit=Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()
        api=retrofit.create(MealsApi::class.java)
    }
    fun getMeals():Call<MealsCategoryResponse>{
        return api.getMeals()
    }
    interface MealsApi{
        @GET("filter.php?c=Vegetarian")
        fun getMeals():Call<MealsCategoryResponse>
    }
}