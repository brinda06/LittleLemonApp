package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

@Composable
fun GreekSaladSpecificScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    var avocadoCount by remember { mutableStateOf(0) }
    var seedsCount by remember { mutableStateOf(0) }
    var pepperCount by remember { mutableStateOf(0) }

    val totalAdditionalPrice = (avocadoCount + seedsCount + pepperCount) * 10

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.littlelemmontext),
                            contentDescription = "Little Lemon Logo",
                            modifier = Modifier.height(24.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Yellow)
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("cart")}) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = Color.Yellow)
                    }
                },
                backgroundColor = Color(0xFF495E57)
            )
        },
        backgroundColor = Color(0xFF193626)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = R.drawable.greeksalad),
                contentDescription = "Greek Salad",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Salad",
                    color = Color.Yellow,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "famous salad with crisp lettuce, pepper, olives and tomato",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    "Rs. 100",
                    color = Color.Yellow,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Delivery time: 20 minutes",
                    color = Color.White,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Add",
                    color = Color.Yellow,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                AdditionalItem("avocado", avocadoCount) { avocadoCount = it }
                AdditionalItem("seeds", seedsCount) { seedsCount = it }
                AdditionalItem("pepper", pepperCount) { pepperCount = it }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Handle add to cart */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3A464D))
                ) {
                    Text("Add for Rs.$totalAdditionalPrice", color = Color.White, modifier = Modifier.padding(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("cart") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
                ) {
                    Text("CHECKOUT", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun AdditionalItem(name: String, count: Int, onCountChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(name, color = Color.White, fontSize = 16.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (count > 0) onCountChange(count - 1) }) {
                Text("-", color = Color.Yellow, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Text("$count", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp))
            IconButton(onClick = { onCountChange(count + 1) }) {
                Text("+", color = Color.Yellow, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Text("Rs.10", color = Color.Yellow, fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp))
        }
    }
}