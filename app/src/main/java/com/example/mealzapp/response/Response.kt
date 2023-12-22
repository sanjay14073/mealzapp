package com.example.mealzapp.response
import  com.google.gson.annotations.SerializedName
data class MealsCategoryResponse(public val meals: List<MealsResponse>){

}
data class MealsResponse(@SerializedName("idMeal") public val id:String,@SerializedName("strMeal") public val  name:String,@SerializedName("strMealThumb") public val imageUrl:String)