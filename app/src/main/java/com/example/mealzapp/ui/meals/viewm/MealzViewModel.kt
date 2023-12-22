package com.example.mealzapp.ui.meals.viewm

import androidx.lifecycle.ViewModel
import com.example.mealzapp.model.MealzRepository
import com.example.mealzapp.response.MealsCategoryResponse
import com.example.mealzapp.response.MealsResponse
import retrofit2.Callback

class MealzViewModel(private val repository: MealzRepository= MealzRepository()): ViewModel() {
    fun getMeals(successCallback: (response:MealsCategoryResponse?)->Unit){
        repository.getMeals {
            response ->successCallback(response)
        }
    }
}
fun viewModel(repository: MealzRepository): MealzViewModel {
    // Create and return an instance of MealzViewModel
    return MealzViewModel(repository)
}