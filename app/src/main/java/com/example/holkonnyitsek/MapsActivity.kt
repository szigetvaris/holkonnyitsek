package com.example.holkonnyitsek

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.holkonnyitsek.data.DataManagerInterface
import com.example.holkonnyitsek.data.WCObject
import com.example.holkonnyitsek.data.WCRating
import com.example.holkonnyitsek.databinding.ActivityMapsBinding
import com.example.holkonnyitsek.fragments.AddWCFragment
import com.example.holkonnyitsek.fragments.EditWCFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Thread.sleep

var DMI = DataManagerInterface()

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,GoogleMap.OnMapLongClickListener, AddWCFragment.AddWCDialogListener {

    private lateinit var mMap: GoogleMap

    private lateinit var binding: ActivityMapsBinding

    private var longitude: Float = 0.0f
    private var latitude: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FONTOS !!!
        DMI.init()
        // add markers from server

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // TESZTELESHEZ ROGTON A WCINFOT inditom el
        // Kommenteld ki hogy elinditsd a WC infot!
        // val changeToInfoActivity = Intent(this, WcInfoActivity::class.java)
        // startActivity(changeToInfoActivity)
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
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
        // Add a marker in Sydney and move the camera
        val bmeI = LatLng(47.4726408, 19.0583993)
        mMap.addMarker(MarkerOptions().position(bmeI).title("Marker in BME I building"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bmeI,17f))

//        DMI.addWC(WCObject(
//            19.058658921028538f,
//            47.4783681678217f,
//            "Budapest, Egry József u. 1, 1111",
//            "07:00-20:00",
//            mutableListOf<WCRating>(WCRating("Bela", "2022.08.33",5, "Szuper jo volt!!" ),
//                WCRating("Juci", "2022.08.33", 3, "Voltam mar jobban is.."),
//                WCRating("Juci", "2022.08.34", 5, "Masodjara sokkal jobban tetszett :)"),
//                WCRating("Bela", "2022.08.35",5, "Mindig szeretek ide szarni!" ),
//                WCRating("Bandi", "2022.08.33", 2, "Lorem ipsum subi dubi legyen ez a komment tuti$$ szerintem lehetne jobb is rosszabb is most 2 csillagot adtam ra es csak jartatom a szam")),
//            true,
//
//            "ingyenes","id"
//        ))
        updateMarkers()

    }
    /** Called when the user clicks a marker.  */
    override fun onMarkerClick(marker: Marker): Boolean {
        var wc = findWCbyMarker(marker)!!
        if (wc != null){
            DMI.SelectedWC = wc
        }
        else println("Marker not found")
        val changeToInfoActivity = Intent(this, WcInfoActivity::class.java)
        startActivity(changeToInfoActivity)

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return true
    }
    override fun  onMapLongClick(latLng : LatLng) {
            // popup majd ide jon
            longitude = latLng.longitude.toFloat()
            latitude = latLng.latitude.toFloat()
            AddWCFragment().show(
                supportFragmentManager,
                AddWCFragment.TAG
            )
            println("valami nagyon jól működik")
        return
    }

    override fun onWCCreated(newWC: WCObject) {
        println("letrejott a klotyo")
        newWC.longitude = longitude
        newWC.latitude = latitude
        DMI.addWC(newWC)
        updateMarkers()
    }

    fun addMarker(wc: WCObject){
        val wcPos = LatLng(wc.latitude.toDouble(), wc.longitude.toDouble())
        println("addMarker: " + wcPos)
        mMap.addMarker(MarkerOptions().position(wcPos).title(wc.name))
    }
    fun updateMarkers() {
        if (::mMap.isInitialized) { //prevent crashing if the map doesn't exist yet (eg. on starting activity)
            mMap.clear()
            // add markers from database to the map
            println("onResume???")
        }
        for (wc in DMI.WCList){
            addMarker(wc)
        }
    }
    override fun onResume() {
        super.onResume()
        if (::mMap.isInitialized) { //prevent crashing if the map doesn't exist yet (eg. on starting activity)
            mMap.clear()
            // add markers from database to the map
            updateMarkers()
            println("onResume???")
        }
    }
    fun findWCbyMarker(marker: Marker): WCObject? {
        for (wc in DMI.WCList){
            println("positions: " + wc.latitude + ", " + marker.position.latitude.toFloat() + "; " + wc.longitude + ", " + marker.position.longitude.toFloat())
            if(wc.latitude == marker.position.latitude.toFloat() && wc.longitude == marker.position.longitude.toFloat() ){
                return wc
            }
        }
        return null
        //return WCObject(0f,0f, "", "", mutableListOf<WCRating>(WCRating("", "",5, "" ),), true, "","")
    }



}