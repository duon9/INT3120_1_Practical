package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpace()
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var currentArt by remember { mutableIntStateOf(0) }

    val images = listOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )

    val titles = listOf(
        "The Lemon Tree",
        "Squeezing Lemons",
        "Fresh Lemonade",
        "Empty Glass"
    )

    val artists = listOf(
        "by Nature",
        "by You",
        "by Summer",
        "by Restart"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween, // đẩy các phần ra
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        MainImage(
            imageRes = images[currentArt],
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ImageDescription(
            title = titles[currentArt],
            artist = artists[currentArt],
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        ButtonSection(
            onPrevious = {
                currentArt = if (currentArt > 0) currentArt - 1 else images.size - 1
            },
            onNext = {
                currentArt = (currentArt + 1) % images.size
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
    }
}

@Composable
fun MainImage(imageRes: Int, modifier: Modifier = Modifier) {
    Surface(shadowElevation = 4.dp) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = modifier.padding(16.dp)
        )
    }
}

@Composable
fun ImageDescription(title: String, artist: String, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shadowElevation = 2.dp,
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = artist, fontSize = 16.sp)
        }
    }
}

@Composable
fun ButtonSection(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onPrevious) {
            Text("Previous")
        }
        Button(onClick = onNext) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}