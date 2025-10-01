package com.example.buildgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.buildgrid.ui.theme.BuildGridTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuildGridTheme {
                BuildGrid()
            }
        }
    }
}

data class DrawableItem(val name: String, val resId: Int)

fun getAllDrawableImages(context: Context): List<DrawableItem> {
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
            null
        }
    }
}


@Composable
fun BuildGrid(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val drawableItems = getAllDrawableImages(context)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
    ) {
        items(drawableItems) { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.name, modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = item.resId),
                        contentDescription = item.name,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuildGridTheme {
        BuildGrid()
    }
}