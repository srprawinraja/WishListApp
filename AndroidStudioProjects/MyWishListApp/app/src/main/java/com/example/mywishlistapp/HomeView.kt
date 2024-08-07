package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mywishlistapp.ui.theme.MyWishListAppTheme
import com.example.mywishlistapp.ui.theme.WishViewModel

@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
    Scaffold(
        topBar = { AppBarView(title = "WishList", {

        })},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(20.dp),
                backgroundColor = Color.Black,
                onClick = {
                    navController.navigate(Screen.AddScreen.route)
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White )
            }
        }
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            items(DummyList.wishList){
                wish->
                WishItem(wish = wish) {

                }
            }


        }
    }
}
@Composable
fun WishItem(wish: Wish, onClick:()->Unit){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp
            )
            .clickable {
                onClick
            },
        elevation = 10.dp,
        backgroundColor = Color.White


    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text =wish.title, fontWeight = FontWeight.ExtraBold )
            Text(text = wish.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    MyWishListAppTheme {
        HomeView(NavController(context = LocalContext.current), WishViewModel())
    }
}
