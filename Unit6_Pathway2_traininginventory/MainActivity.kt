package com.example.traininginventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class Item(
    val id: Int,
    var name: String,
    var quantity: Int,
    var location: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainingInventoryApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingInventoryApp() {
    var items by remember { mutableStateOf(listOf<Item>()) }
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var idCounter by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Training Inventory App") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Item Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = {
                    if (name.isNotBlank() && quantity.isNotBlank() && location.isNotBlank()) {
                        val newItem = Item(
                            id = idCounter,
                            name = name,
                            quantity = quantity.toIntOrNull() ?: 0,
                            location = location
                        )
                        items = items + newItem
                        idCounter++
                        name = ""
                        quantity = ""
                        location = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Item")
            }

            Spacer(Modifier.height(20.dp))
            Divider()
            Spacer(Modifier.height(8.dp))

            Text("Inventory List", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            LazyColumn {
                items(items, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text("Name: ${item.name}")
                            Text("Quantity: ${item.quantity}")
                            Text("Location: ${item.location}")
                            Spacer(Modifier.height(8.dp))
                            Button(
                                onClick = { items = items.filterNot { it.id == item.id } },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                            ) {
                                Text("Delete", color = MaterialTheme.colorScheme.onErrorContainer)
                            }
                        }
                    }
                }
            }
        }
    }
}
