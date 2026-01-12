package com.example.secondbeststoryever.ui.screens.map

import MapViewModel
import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MapScreen(viewModel: MapViewModel = viewModel()) {
    val context = LocalContext.current
    val allUsers by viewModel.users.collectAsState()

    var nameInput by remember { mutableStateOf("") }

    // Launcher for requesting location permission
    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val granted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (granted) {
                viewModel.connect()
            } else {
                Toast.makeText(
                    context,
                    "Location permission is required",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nameInput.isNotBlank()) {
                    viewModel.setName(nameInput) // ✅ set name ONCE here

                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            },
            enabled = nameInput.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Connect")
        }


        Spacer(modifier = Modifier.height(24.dp))

        viewModel.currentUser?.let { current ->
            Text("Current User", style = MaterialTheme.typography.titleMedium)
            Text("Name: ${current.name}")
            Text("Latitude: ${current.lat}")
            Text("Longitude: ${current.lon}")

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
        }

        val otherUsers = allUsers.filter { it.userId != viewModel.currentUser?.userId }

        if (otherUsers.isNotEmpty()) {
            otherUsers.forEach { user ->
                Text("Connected User", style = MaterialTheme.typography.titleMedium)
                Text("Name: ${user.name}")
                Text("Latitude: ${user.lat}")
                Text("Longitude: ${user.lon}")

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))
            }
        } else {
            if (viewModel.currentUser != null) {
                Text("No other users connected")
            } else {
                Text("Not connected")
            }
        }
    }
}
