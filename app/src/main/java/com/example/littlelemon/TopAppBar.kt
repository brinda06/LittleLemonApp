package com.example.littlelemon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R



@Composable
fun TopAppBar(){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment= Alignment.CenterVertically
    ){
        IconButton(onClick={}){
            Image(painter=painterResource(id= R.drawable.ic_back),
                contentDescription="Menu Icon",
                modifier= Modifier.size(24.dp))

        }
        Image(painter=painterResource(id=R.drawable.littlelemmontext),
            contentDescription="little lemon logo",
            modifier= Modifier.fillMaxWidth(0.5F).padding(horizontal = 20.dp)
            )
       IconButton(onClick ={})
       {
           Image(painter=painterResource(id= R.drawable.ic_cart),
               contentDescription="Cart",
               modifier=Modifier.size(24.dp)
           )
       }
    }
}