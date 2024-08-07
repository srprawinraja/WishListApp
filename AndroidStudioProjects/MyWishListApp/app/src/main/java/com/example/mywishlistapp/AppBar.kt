package com.example.mywishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked:()->Unit={}
){
    val navigationIcon: @Composable (() -> Unit) ={
        if (!title.contains("WishList")) {
            IconButton(
                onClick = {
                    onBackNavClicked()
                }){

                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "Back",
                )
            }
        }


    }
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White, // Use Color.Black instead of R.color.black
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        elevation = 3.dp,
        backgroundColor = Color.Red,
        navigationIcon = navigationIcon
    )

}
