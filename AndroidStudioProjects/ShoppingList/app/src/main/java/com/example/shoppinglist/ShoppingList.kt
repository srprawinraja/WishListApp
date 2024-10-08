package com.example.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    var id: Int,
    val name: String,
    val quantity: Int,
    val isEditable:Boolean
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(){
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var Sitems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
        Button(
            onClick = {
                showDialog=true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add item")

        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ){
            items(Sitems){

            }

        }
    }
    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog=false },
            confirmButton = { /*TODO*/ },
            title = { Text(text = "Add Shopping Item") },
           text = {
               Column {
                   OutlinedTextField(
                       value =itemName,
                       onValueChange = {itemName=it},
                       singleLine = true,
                       modifier = Modifier.fillMaxWidth().padding(8.dp))
                   OutlinedTextField(
                       value =itemQuantity,
                       onValueChange = {itemQuantity=it},
                       singleLine = true,
                       modifier = Modifier.fillMaxWidth().padding(8.dp))

               }


           }

        )
    }
}
