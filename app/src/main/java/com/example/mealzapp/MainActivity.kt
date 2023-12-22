package com.example.mealzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.mealzapp.model.MealzRepository
import com.example.mealzapp.response.MealsResponse
import com.example.mealzapp.ui.meals.viewm.MealzViewModel
import com.example.mealzapp.ui.meals.viewm.viewModel
import com.example.mealzapp.ui.theme.MealzAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ) {
                   // Greeting("Android")
                    //Scaffold(topBar = { AppBar(title = "Mealz App") }, content = {padding ->  MealzCategoriesScreen()})
                    MealzCategoriesScreen()
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealzCategoriesScreen(){
    val mealzRepository = MealzRepository()
    val viewModel: MealzViewModel = viewModel(mealzRepository)
    val rememberedMeals = remember {
        mutableStateOf(emptyList<MealsResponse>())
    }
    viewModel.getMeals { response ->
        val mealsFromTheApi = response?.meals.orEmpty()
        rememberedMeals.value = mealsFromTheApi
    }
//
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text("Find Tasty Vegetarian Dishes", textAlign = TextAlign.Center)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.DarkGray
                )
            )
        }
         ,content = {padding->
        LazyColumn(contentPadding = PaddingValues(all = 16.dp), modifier = Modifier.padding(padding)) {
            items(rememberedMeals.value) { meal ->
                // Text(text = meal.name) // Replace "name" with the actual property you want to display
                CCard(name = meal.name, image = meal.imageUrl)
            }
        }
    })

 
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CCard(name: String, image: String) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(size = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(data = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp) // Set the desired height
                    .clip(RoundedCornerShape(size = 2.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = name, style = MaterialTheme.typography.titleLarge, modifier =Modifier.padding(start = 8.dp, top = 2.5.dp, bottom = 1.5.dp))
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealzCategoriesScreen()
    }
}