package com.example.splashscreen

import android.content.pm.PackageManager
import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import android.text.Layout.Alignment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.reflect.Modifier

fun RIR(
    modifier: Modifier = Modifier,
    imageResId: Int,
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .scale(1.2f)
            contentScale = ContentScale.Crop
        )Text(text = text)
        RadioButton(
            selected = ischchecked,
            onClick = {onCheckedChange(!isChecked)},
            colors = RadioButtonDefaults(MaterialTheme.colorScheme.primary)
        )
    }
}
class MapsFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the map asynchronously
        getMapAsync(this)

        // Initialize the fused location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Get current location on button click
        val btn = view.findViewById<Button>(R.id.currentLoc)

        // Adding a null check for the button
        btn?.setOnClickListener {
            // Adding a null check for the GoogleMap
            googleMap?.let { map ->
                LocationUtils.getLastLocation(requireContext(), fusedLocationClient) { location ->
                    // Handle the obtained location (LatLng)
                    // For example, you can update the map marker or perform any other actions
                    map.clear()
                    map.addMarker(MarkerOptions().position(location).title("Current Location"))
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16F))
                }
            }
        }
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        googleMap.setOnMyLocationButtonClickListener {
            // Handle My Location button click
            true
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}
