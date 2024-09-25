package com.example.lazycolumnwithanimationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyLazyColumn(viewModel: MyViewModel = MyViewModel()) {
    for (i in 1..10) {
        viewModel.addItem()
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = {
                if (viewModel.getItemCount() > 0)
                    viewModel.addAt(Random.nextInt(0, viewModel.getItemCount()))
                else
                    viewModel.addAt(0)

            }) {

                Text("Add")
            }
            Button(onClick = {
                if (viewModel.getItemCount() > 0)
                    viewModel.removeAt(Random.nextInt(0, viewModel.getItemCount()))

            }) {
                Text("Remove")
            }
            Button(onClick = {
                if (viewModel.getItemCount() > 0)
                    viewModel.reorder()

            }) {
                Text("Reorder")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                viewModel.items,
                key = {
                    it
                }
            ) { item ->
                Text(
                    item,
                    modifier = Modifier
                        .animateItemPlacement(tween(5000))
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth()
                        .shadow(6.dp, shape = RoundedCornerShape(13.dp))
                        .clip(RoundedCornerShape(13.dp))
                        .background(Color.White)
                        .padding(12.dp)
                )
            }
        }
    }

}
