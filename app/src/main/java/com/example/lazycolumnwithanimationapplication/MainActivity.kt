package com.example.lazycolumnwithanimationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLazyColumn()
        }
    }
}

@Composable
fun MyLazyColumn(viewModel: MyViewModel = MyViewModel()) {
    var update = remember { mutableStateOf(true)}
    for(i in 1..10){
        viewModel.addItem()
    }
Column(Modifier.fillMaxWidth().padding(top=50.dp)) {
    Row {
        Button(onClick = {

            update.value=false
            if(viewModel.getItemCount()>0)
                viewModel.addAt(Random.nextInt(0, viewModel.getItemCount()))
            else
                viewModel.addAt(0)
            update.value=true

        }) {

            Text("Add")
        }
        Button(onClick = {
            update.value=false
            if(viewModel.getItemCount()>0)
                viewModel.removeAt(Random.nextInt(0, viewModel.getItemCount()))
            update.value=true

        }) {
            Text("Remove")
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if(update.value) {
            items(viewModel.items) { item ->
                Text(
                    "$item",
                    modifier = Modifier.padding(8.dp, 4.dp).fillMaxWidth()
                        .background(colorResource(R.color.bgGray)).padding(12.dp)
                )
            }
        }
    }
}

}
