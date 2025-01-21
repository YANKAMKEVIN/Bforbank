package com.kevin.multiapiapp.presentation.ui.mapbox

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

@Composable
fun MapboxMap(latitude: Double, longitude: Double) {
    AndroidView(
        factory = { context ->
            MapView(context).apply {
                mapboxMap.loadStyle(Style.MAPBOX_STREETS) {
                    mapboxMap.setCamera(
                        CameraOptions.Builder()
                            .center(Point.fromLngLat(longitude, latitude))
                            .zoom(14.0)
                            .pitch(0.0)
                            .bearing(0.0)
                            .build()
                    )

                    val annotationApi = annotations.createPointAnnotationManager()
                    val pointAnnotationOptions = PointAnnotationOptions()
                        .withPoint(Point.fromLngLat(longitude, latitude))
                        .withIconImage(MAPBOX_ICON_URL)
                        .withIconSize(100.0)
                    annotationApi.create(pointAnnotationOptions)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    )
}

private const val MAPBOX_ICON_URL = "https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png"
