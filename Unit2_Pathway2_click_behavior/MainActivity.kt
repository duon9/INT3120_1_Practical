package com.example.clickbehavior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clickbehavior.ui.theme.ClickBehaviorTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickBehaviorTheme {
                ClickBehavior()
            }
        }
    }
}

@Composable
fun ClickBehavior(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(1) }

    val imageStates: List<Int> = listOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )


    val textStates: List<String> = listOf(
        "Tap the lemon tree to select a lemon",
        "Keep tapping the lemon tree to squeeze it",
        "Tap the lemonade to drink it",
        "Tap the empty glass to start again"
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageStates[state - 1]),
            contentDescription = textStates[state - 1],
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    state = if (state < 4) state + 1 else 1
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(textStates[state - 1])
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClickBehaviorTheme {
        ClickBehavior()
    }
}