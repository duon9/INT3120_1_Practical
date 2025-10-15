package com.example.dessertclicker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dessertclicker.data.DessertUiState
import com.example.dessertclicker.ui.DessertViewModel
import com.example.dessertclicker.ui.theme.DessertClickerTheme
import java.text.NumberFormat
import java.util.Locale

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContent {
            DessertClickerTheme {
                DessertClickerRoot()
            }
        }
    }

    override fun onStart()   { super.onStart();   Log.d(TAG, "onStart") }
    override fun onResume()  { super.onResume();  Log.d(TAG, "onResume") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart") }
    override fun onPause()   { super.onPause();   Log.d(TAG, "onPause") }
    override fun onStop()    { super.onStop();    Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
}

private fun Context.shareSoldDessertsInformation(dessertsSold: Int, revenue: Int) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_text, dessertsSold, revenue)
        )
        type = "text/plain"
    }
    val chooser = Intent.createChooser(sendIntent, null)
    try {
        startActivity(this, chooser, null)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(
            this,
            getString(R.string.sharing_not_available),
            Toast.LENGTH_LONG
        ).show()
    }
}


@Composable
private fun DessertClickerRoot(
    viewModel: DessertViewModel = viewModel()
) {
    val uiState by viewModel.dessertUiState.collectAsState()

    DessertClickerApp(
        uiState = uiState,
        onDessertClicked = viewModel::onDessertClicked
    )
}


@Composable
private fun DessertClickerApp(
    uiState: DessertUiState,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.app_name),
                onShare = {
                    context.shareSoldDessertsInformation(
                        dessertsSold = uiState.dessertsSold,
                        revenue = uiState.revenue
                    )
                }
            )
        }
    ) { padding ->
        DessertClickerScreen(
            revenue = uiState.revenue,
            dessertsSold = uiState.dessertsSold,
            dessertImageId = uiState.currentDessertImageId,
            onDessertClicked = onDessertClicked,
            modifier = modifier.padding(padding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppTopBar(
    title: String,
    onShare: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = onShare) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(R.string.share)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}


@Composable
private fun DessertClickerScreen(
    revenue: Int,
    dessertsSold: Int,
    @DrawableRes dessertImageId: Int,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.bakery_back),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(dessertImageId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .align(Alignment.Center)
                        .clickable { onDessertClicked() },
                    contentScale = ContentScale.Crop
                )
            }
            TransactionInfo(
                revenue = revenue,
                dessertsSold = dessertsSold
            )
        }
    }
}

@Composable
private fun TransactionInfo(
    revenue: Int,
    dessertsSold: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        DessertsSoldRow(dessertsSold)
        RevenueRow(revenue)
    }
}

@Composable
private fun RevenueRow(revenue: Int, modifier: Modifier = Modifier) {
    val currency = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(revenue)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.total_revenue),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = currency,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
private fun DessertsSoldRow(dessertsSold: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.dessert_sold),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = dessertsSold.toString(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DessertClickerPreview() {
    DessertClickerTheme {
        DessertClickerApp(
            uiState = DessertUiState(),
            onDessertClicked = {}
        )
    }
}
