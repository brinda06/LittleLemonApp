package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ReservationScreen(navController: NavController) {
    val date = remember { mutableStateOf("") }
    val time = remember { mutableStateOf("") }
    val tableFor = remember { mutableStateOf(2) }
    val reservationName = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF495E57),
                contentColor = Color.White,
                elevation = 4.dp,
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { navController.navigate("signIn") }) {
                            Image(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "Back Button"
                            )
                        }
                        Text(
                            "Little Lemon",
                            modifier = Modifier.weight(1f),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        IconButton(onClick = { navController.navigate("cart")}) {
                            Image(
                                painter = painterResource(id = R.drawable.cart),
                                contentDescription = "Cart Button"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF193626))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Reservation Details",
                    color = Color.Yellow,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextField(
                    value = date.value,
                    onValueChange = { date.value = it },
                    placeholder = { Text("Date*", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF1ED), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = time.value,
                    onValueChange = { time.value = it },
                    placeholder = { Text("Time*", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF1ED), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { if (tableFor.value > 1) tableFor.value-- },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Text("-", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        tableFor.value.toString(),
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { tableFor.value++ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Text("+", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = reservationName.value,
                    onValueChange = { reservationName.value = it },
                    placeholder = { Text("Name of the reservation*", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF1ED), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("confirmation") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Submit the Request", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
