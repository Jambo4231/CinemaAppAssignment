package com.example.cinemaapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Add a marker in a default location and move the camera with a zoom level
        val cinemaLocation = LatLng(56.819817, -5.105218)
        googleMap.addMarker(MarkerOptions().position(cinemaLocation).title("Cinema Location"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cinemaLocation, 15f))
    }
}
