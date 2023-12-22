package com.example.mealzapp.model

import com.example.mealzapp.model.api.mealzapi
import com.example.mealzapp.response.MealsCategoryResponse
import com.example.mealzapp.response.MealsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealzRepository (private val webService:mealzapi=mealzapi()){
    //Veg  api
    //https://www.themealdb.com/api/json/v1/1/filter.php?c=Vegetarian
    //fun getMeals():MealsCategoryResponse= MealsCategoryResponse(arrayListOf())
    fun getMeals(successCallback:(response:MealsCategoryResponse?)->Unit){
        return webService.getMeals().enqueue(object :Callback<MealsCategoryResponse>{
            override fun onResponse(
                call: Call<MealsCategoryResponse>,
                response: Response<MealsCategoryResponse>
            ) {
                if(response.isSuccessful) {
                    successCallback(response.body());
                }
            }
            override fun onFailure(call: Call<MealsCategoryResponse>, t: Throwable) {
                //TODO("Not yet implemented")
            }
        })
    }
}