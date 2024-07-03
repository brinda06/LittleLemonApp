package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class AdditionalItemData(val name: String, val price: Int)

@Composable
fun CartScreen(navController: NavController) {
    var additionalItems by remember { mutableStateOf(listOf(
        AdditionalItemData("avocado", 10),
        AdditionalItemData("seeds", 10),
        AdditionalItemData("pepper", 10)
    )) }

    val saladPrice = 100 // Price of the salad in Rupees
    val totalAmount = remember(additionalItems) {
        saladPrice + additionalItems.sumOf { it.price }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Yellow)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle cart action */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = Color.Yellow)
                    }
                },
                backgroundColor = Color(0xFF495E57)
            )
        },
        backgroundColor = Color(0xFF495E57)
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .padding(bottom = 66.dp)  // Add padding for the checkout button
            ) {
                item {
                    Text("Order Summary", color = Color.Yellow, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    OrderItem(
                        name = "Salad",
                        description = "famous salad with crisp lettuce, pepper, olives and tomato",
                        price = "Rs. $saladPrice",
                        imageRes = R.drawable.greeksalad
                    )
                }

                items(additionalItems) { item ->
                    AdditionalItem(
                        item = item,
                        onRemove = { additionalItems = additionalItems - item }
                    )
                }

                item {
                    AddNewItemButton { newItem ->
                        additionalItems = additionalItems + newItem
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .background(Color(0xFF3A464D)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Order More", color = Color.White, modifier = Modifier.padding(16.dp))
                        Text("Rs. $totalAmount", color = Color.White, modifier = Modifier.padding(16.dp))
                    }
                }

                item {
                    Text("Add More To your Order", color = Color.Yellow, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OrderItem(
                            name = "Salad",
                            description = "famous salad with crisp lettuce, pepper, olives and tomato",
                            price = "Rs. 100",
                            imageRes = R.drawable.greeksalad,
                            isSmall = true
                        )
                        OrderItem(
                            name = "Salad",
                            description = "famous salad with crisp lettuce, pepper, olives and tomato",
                            price = "Rs. 100",
                            imageRes = R.drawable.greeksalad,
                            isSmall = true
                        )
                    }
                }
            }

            Button(
                onClick = { /* Handle checkout */ },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3A464D))
            ) {
                Text("CHECKOUT", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun OrderItem(name: String, description: String, price: String, imageRes: Int, isSmall: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(name, color = Color.Yellow, fontWeight = FontWeight.Bold)
            Text(description, color = Color.White, fontSize = 12.sp)
            Text(price, color = Color.Yellow)
        }
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier.size(if (isSmall) 50.dp else 80.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun AdditionalItem(item: AdditionalItemData, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onRemove) {
                Icon(Icons.Filled.Clear, contentDescription = "Remove", tint = Color.Yellow)
            }
            Text(item.name, color = Color.White)
        }
        Text("Rs. ${item.price}", color = Color.Yellow)
    }
}

@Composable
fun AddNewItemButton(onAdd: (AdditionalItemData) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }
    var newItemPrice by remember { mutableStateOf("") }

    Button(
        onClick = { showDialog = true },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.Black)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Add Item", color = Color.Black)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add New Item") },
            text = {
                Column {
                    TextField(
                        value = newItemName,
                        onValueChange = { newItemName = it },
                        label = { Text("Item Name") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = newItemPrice,
                        onValueChange = { newItemPrice = it },
                        label = { Text("Price") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    val price = newItemPrice.toIntOrNull() ?: 0
                    onAdd(AdditionalItemData(newItemName, price))
                    showDialog = false
                    newItemName = ""
                    newItemPrice = ""
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}