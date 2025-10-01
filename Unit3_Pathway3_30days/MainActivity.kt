package com.example.a30daysapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.ui.theme._30daysappTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30daysappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

val tips = listOf(
    "Drink more water",
    "Exercise 10 minutes a day",
    "Read a book",
    "Meditate for 5 minutes",
    "Sleep early",
    "Write a journal",
    "Eat more fruits",
    "Take a short walk",
    "Practice gratitude",
    "Learn a new word"
)

data class DrawableItem(val name: String, val resId: Int)

fun getAllDrawableImages(context: android.content.Context): List<DrawableItem> {
    val drawables = R.drawable::class.java.fields
    return drawables.mapNotNull { field ->
        try {
            val resId = field.getInt(null)
            val name = field.name
            val drawable = context.getDrawable(resId)
            return@mapNotNull when (drawable) {
                is android.graphics.drawable.BitmapDrawable -> DrawableItem(name, resId)
                is android.graphics.drawable.VectorDrawable -> DrawableItem(name, resId)
                is androidx.vectordrawable.graphics.drawable.VectorDrawableCompat -> DrawableItem(name, resId)
                else -> null
            }
        } catch (e: Exception) {
            Log.e("App", "Error loading drawable: ${e.message}")
            null
        }
    }
}

data class DayItem(val day: Int, val imageRes: Int, val tip: String)

@Composable
fun AppContent() {
    val context = LocalContext.current
    val drawableItems = getAllDrawableImages(context)

    val dayItems = List(30) { index ->
        val randomImage = if (drawableItems.isNotEmpty()) {
            drawableItems[Random.nextInt(drawableItems.size)].resId
        } else {
            android.R.drawable.ic_menu_report_image
        }
        val randomTip = tips[Random.nextInt(tips.size)]
        DayItem(index + 1, randomImage, randomTip)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dayItems) { dayItem ->
            DayCard(dayItem)
        }
    }
}

@Composable
fun DayCard(dayItem: DayItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Day ${dayItem.day}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                painter = painterResource(dayItem.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = dayItem.tip,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    _30daysappTheme {
        AppContent()
    }
}
