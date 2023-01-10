package eniso.gte2.tp4iheb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.parseAsHtml

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import eniso.gte2.tp4iheb.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val tunis = LatLng(36.8, 10.17)
        mMap.addMarker(MarkerOptions().position(tunis).title("Marker in Tunis"))
        val sousse = LatLng(35.82, 10.64)
        mMap.addMarker(MarkerOptions().position(sousse).title("Marker in Sousse"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sousse,7.0f))
        mMap.mapType= GoogleMap.MAP_TYPE_NORMAL
        //ligne droite reliant tunis et sousse
        val options = PolylineOptions()
        options.add(sousse,tunis)
        //options.add(LatLng(36.8, 10.17),LatLng(35.82, 10.64))
        mMap.addPolyline(options)
        mMap.setOnMapClickListener {
            Toast.makeText(this,"latuitude=${it.latitude}\nlongitude=${it.longitude}",Toast.LENGTH_LONG).show()
        }
        mMap.setOnMarkerClickListener {
            val uri= Uri.parse("https://fr.wikipedia.org/wiki/$it")
            val i =Intent(Intent.ACTION_VIEW,uri)
            startActivity(i)
        true

        }
    }
}