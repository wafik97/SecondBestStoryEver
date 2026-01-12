package com.example.secondbeststoryever.ui.screens.map

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.secondbeststoryever.data.model.UserLocation
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapScreen(
    context: Context,
    userLocations: List<UserLocation> = emptyList()
) {
    // Initialize OSMDroid config
    Configuration.getInstance().userAgentValue = context.packageName

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            MapView(ctx).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(12.0)
                controller.setCenter(GeoPoint(1.3521, 103.8198)) // default center (Singapore)

                // Add markers for each user
                userLocations.forEach { user ->
                    val marker = Marker(this).apply {
                        position = GeoPoint(user.lat, user.lon)
                        title = user.name
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon?.setTint(user.color.toInt() or 0xFF000000.toInt()) // tint with user color
                    }
                    overlays.add(marker)
                }
            }
        }
    )
}

// Dummy users for testing
val dummyUsers = listOf(
    UserLocation("1", "Alice", 1.3521, 103.8198, 0xFFFF0000.toInt()), // red
    UserLocation("2", "Bob", 1.3600, 103.8200, 0xFF00FF00.toInt())    // green
)
