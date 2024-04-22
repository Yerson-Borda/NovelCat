package com.example.gatoputo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gatoputo.ui.theme.GatoPutoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GatoPutoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var name by remember { mutableStateOf("") }
    var screenCount by remember { mutableIntStateOf(0) }

    when (screenCount) {
        0 -> FirstScreen { screenCount++ }
        1 -> SecondScreen(name) { newName ->
            name = newName
            screenCount++
        }
        2 -> ThirdScreen(name) { destination ->
            screenCount = destination
            screenCount++
        }
        in 3..17 -> MainScreens(screenCount - 1) { destination ->
            screenCount = destination
            screenCount++
        }
    }
}

var count = 0
fun counter(): Int{
    count++
    return count
}

@Composable
fun FirstScreen(onNextScreen: () -> Unit) {
    val firstTime = counter()
    val tittle: String
    val start: String

    if (firstTime % 2 == 0) {
        tittle = stringResource(R.string.last_page)
        start = stringResource(R.string.repeat_game)
    } else {
        tittle = stringResource(R.string.tittle)
        start = stringResource(R.string.start)
    }

    val image = painterResource(R.drawable.menu)
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 290.dp)
        ) {
            Surface(
                color = Color(0xFF78acc4)
            ) {
                Text(
                    text = tittle,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 33.dp, bottom = 33.dp)
                        .fillMaxWidth(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(195.dp))

            Button(
                onClick = onNextScreen,
                colors = ButtonDefaults.buttonColors(Color(0xFF28546c)),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = start, color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun SecondScreen(name: String, onNextScreen: (String) -> Unit) {
    val newNameState = remember { mutableStateOf(name) }
    val image = painterResource(R.drawable.menu)
    val cat = painterResource(R.drawable.gato)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Image(
            painter = cat,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp, 900.dp)
                .padding(top = 30.dp)
                .fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 500.dp)
        ) {
            Surface(color = Color(0xFF28546c)) {
                Text(
                    text = stringResource(R.string.lets_get_acquainted),
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            TextField(
                value = newNameState.value,
                onValueChange = { newName ->
                    newNameState.value = newName
                },
                label = { Text("Input your name...") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp)
            )

            Spacer(modifier = Modifier.height(17.dp))

            Button(
                onClick = { onNextScreen(newNameState.value) },
                colors = ButtonDefaults.buttonColors(Color(0xFF28546c)),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Accept", color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun ThirdScreen(name: String?, onNextScreen: (Int) -> Unit) {
    val image = painterResource(R.drawable.menu)
    val cat = painterResource(R.drawable.gato)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Image(
            painter = cat,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp, 900.dp)
                .padding(top = 30.dp)
                .fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 470.dp)
        ) {
            Surface(color = Color(0xFF28546c)) {
                Text(
                    text = "Great $name! What are we going to do?",
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = { onNextScreen(4) },
                colors = ButtonDefaults.buttonColors(Color(0xFF28546c)),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 21.dp)
            ) {
                Text(
                    text = stringResource(R.string.walking), color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = { onNextScreen(5) },
                colors = ButtonDefaults.buttonColors(Color(0xFF28546c)),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 21.dp)
            ) {
                Text(
                    text = stringResource(R.string.hiking), color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = { onNextScreen(6) },
                colors = ButtonDefaults.buttonColors(Color(0xFF28546c)),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 21.dp)
            ) {
                Text(
                    text = stringResource(R.string.go_to_the_field), color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun MainScreens(scene: Int, onNextScreen: (Int) -> Unit) {
    var text = ""; var text1 = "" ; var text2 = ""
    val image = when (scene) {
        4 -> painterResource(R.drawable.walking)
        5 -> painterResource(R.drawable.hiking)
        6 -> painterResource(R.drawable.picnic)
        7, 9, 10 -> painterResource(R.drawable.film)
        8 -> painterResource(R.drawable.halloween)
        11, 12, 13 -> painterResource(R.drawable.costume)
        else -> painterResource(R.drawable.menu)
    }
    when (scene) {
        4 -> text = stringResource(R.string.page_4_tittle)
        5 -> text = stringResource(R.string.page_5_tittle)
        6 -> text = stringResource(R.string.page_6_tittle)
        7 -> text = stringResource(R.string.page_7_tittle)
        8 -> text = stringResource(R.string.page_8_tittle)
        9 -> text = stringResource(R.string.page_9_tittle)
        10 -> text = stringResource(R.string.page_10_tittle)
        11 -> text = stringResource(R.string.page_11_tittle)
        12 -> text = stringResource(R.string.page_12_tittle)
        13 -> text = stringResource(R.string.page_13_tittle)
    }
    when (scene) {
        4 -> text1 = stringResource(R.string.page_4_text1)
        5 -> text1 = stringResource(R.string.page_5_text1)
        6 -> text1 = stringResource(R.string.page_6_text1)
        7 -> text1 = stringResource(R.string.page_7_text1)
        8 -> text1 = stringResource(R.string.page_8_text1)
        9, 10 -> text1 = stringResource(R.string.page_9_10_text1)
        11 -> text1 = stringResource(R.string.page_11_text1)
        12, 13 -> text1 = stringResource(R.string.page_12_13_text1)

    }
    when (scene) {
        4 -> text2 = stringResource(R.string.page_4_text2)
        5 -> text2 = stringResource(R.string.page_5_text2)
        6 -> text2 = stringResource(R.string.page_6_text2)
        7 -> text2 = stringResource(R.string.page_7_text2)
        8 -> text2 = stringResource(R.string.page_8_text2)
        11 -> text2 = stringResource(R.string.page_11_text2)
        9, 10, 12, 13 -> {}
    }
    var pageNumber = 0
    when (scene) {
        4, 5, 6 -> pageNumber = 7
        7 -> pageNumber = 9
        8 -> pageNumber = 7
        11 -> pageNumber = 12
        9, 10, 12, 13 -> pageNumber = 14
    }
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 470.dp)
        ) {
            Surface(color = Color(0xFF28546c)) {
                Text(
                    text = text,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            if (pageNumber == 14) {
                pageNumber = -1
            }
            Button(
                onClick = { onNextScreen(pageNumber) },
                colors = ButtonDefaults.buttonColors(Color(0xFF28546c)),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp)
            ) {
                Text(
                    text = text1, color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
            if (scene == 8 && pageNumber == 7) {
                pageNumber = 10
            }
            if (text2.isNotEmpty()) {
                Button(
                    onClick = { onNextScreen(pageNumber + 1) },
                    colors = ButtonDefaults.buttonColors(Color(0xFF28546c)),
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 21.dp)
                ) {
                    Text(
                        text = text2, color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}