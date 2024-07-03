package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MainHomeScreen(navController: NavHostController) {
    var selectedCategory by remember { mutableStateOf("Dinner") }
    val items = getMenuItems(selectedCategory)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF495E57),
                contentColor = Color.White,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.littlelemmontext),
                        contentDescription = "Little Lemon Logo",
                        modifier = Modifier.height(24.dp)
                    )
                    IconButton(onClick = { navController.navigate("cart") }) {
                        Image(
                            painter = painterResource(id = R.drawable.cart),
                            contentDescription = "Cart"
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF495E57))
                .padding(paddingValues)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Little Lemon",
                        color = Color(0xFFF4CE14),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "India",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "We are a family-owned restaurant, focused on traditional recipes served with a modern twist.",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { navController.navigate("reservation") },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "RESERVE A TABLE",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Order for delivery",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("Lunch", "Main", "Dessert", "Dinner").forEach { item ->
                            Chip(
                                text = item,
                                onClick = { selectedCategory = item }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "This week's Special!",
                        color = Color(0xFFF4CE14),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            items(items) { item ->
                SpecialItem(navController, item)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun Chip(text: String, onClick: () -> Unit) {
    Surface(
        color = Color(0xFF3D8361),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

data class SpecialMenuItem(
    val name: String,
    val description: String,
    val price: String,
    val imageResource: Int,
    val category: String
)

fun getMenuItems(category: String): List<SpecialMenuItem> {
    val allItems = listOf(
        SpecialMenuItem("Greek Salad", "The famous Greek salad of crispy lettuce, peppers, olives and our Chicago...", "$12.99", R.drawable.greeksalad, "Lunch"),
        SpecialMenuItem("Bruschetta", "Grilled bread topped with fresh tomatoes, basil, garlic, and olive oil...", "$8.99", R.drawable.greeksalad, "Main"),
        SpecialMenuItem("Lemon Dessert", "A tangy and sweet lemon dessert to cleanse your palate...", "$5.99", R.drawable.greeksalad, "Dessert")
    )
    return if (category == "Dinner") {
        allItems
    } else {
        allItems.filter { it.category == category }
    }
}

@Composable
fun SpecialItem(navController: NavHostController, item: SpecialMenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                when (item.name) {
                    "Greek Salad" -> navController.navigate("greeksalad_specific")
                    "Bruschetta" -> navController.navigate("bruschetta_specific")
                    "Lemon Dessert" -> navController.navigate("lemondessert_specific")
                }
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageResource),
            contentDescription = item.name,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = item.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.price,
                color = Color(0xFFF4CE14),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
