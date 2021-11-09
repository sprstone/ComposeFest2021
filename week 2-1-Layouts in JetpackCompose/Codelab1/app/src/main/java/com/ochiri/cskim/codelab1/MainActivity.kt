package com.ochiri.cskim.codelab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ochiri.cskim.codelab1.ui.theme.Codelab1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Codelab1Theme {
                // A surface container using the 'background' color from the theme
                /*Surface(color = MaterialTheme.colors.background) {
                    PhotographerCard()
                }*/

                LayoutsCodelab()
            }
        }
    }
}



@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) {
            ScrollingList()
            /*innerPadding ->
        BodyContent(Modifier.padding(innerPadding).padding(8.dp))*/
    }
}

@ExperimentalCoilApi
@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier/*.padding(8.dp)*/) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")

        //Simplist()
        //Lazylist()
    }
}

@ExperimentalCoilApi
@Composable
fun ScrollingList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(
                onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            },
            modifier = Modifier.padding(start = 8.dp)) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
}

@Composable
fun Simplist(){

    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100){
            Text("Item #$it")
        }
    }
}

@ExperimentalCoilApi
@Composable
fun Lazylist(){
    
    val scrollState = rememberLazyListState()
    
    LazyColumn(state = scrollState){
        items(100){
            //Text("Item #$it")
            ImageListItem(index = it)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier){
     Row(
         modifier
             .padding(8.dp)
             .clip(RoundedCornerShape(4.dp))
             .background(MaterialTheme.colors.surface)
             .clickable(onClick = {

             })
             .padding(16.dp)
     ) {
         Surface(
             modifier = Modifier.size(50.dp),
             shape = CircleShape,
             color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
         ) {

         }
         Column(
             modifier = Modifier
                 .padding(start = 8.dp)
                 .align(Alignment.CenterVertically)
         ) {
             Text("Alfred Sisley", fontWeight = FontWeight.Bold)
             CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                 Text("3 minutes ago", style = MaterialTheme.typography.body2)
             }
         }
     }
}

@Preview
@Composable
fun PhotographerCardPreview(){
    Codelab1Theme {
        LayoutsCodelab()
        //PhotographerCard()
    }
}