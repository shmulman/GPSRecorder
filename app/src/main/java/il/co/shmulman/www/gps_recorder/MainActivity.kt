package il.co.shmulman.www.gps_recorder

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import java.io.File
import java.io.FileWriter
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    // Define constants
    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    // Define data list for output
    var mutableStringDataList: MutableList<String> = mutableListOf<String>()

    // Define location manager
    //var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener {
            statusUpdate.text = "Start button pressed"

            var date = Date();
            val formatter  = SimpleDateFormat("d/M/y  hh:mm:ss")
            val dataString: String = formatter.format(date) + "\n"

            //outputText.append(getLocation().toString())

            mutableStringDataList.add(dataString)
            outputText.append(dataString)

        }

        clearButton.setOnClickListener {
            statusUpdate.text = "Clear button pressed"
            mutableStringDataList.clear()
            outputText.text = "GPS Data:\n"
        }

        saveButton.setOnClickListener {
            statusUpdate.text = "Save button pressed"

            try{
                val filename = "myfile"
                val fileContents = "Hello world! - Shimon's app"
                applicationContext.openFileOutput(filename, Context.MODE_WORLD_READABLE).use {
                    it.write(fileContents.toByteArray())
                }
            } catch (ex:Exception){
                statusUpdate.text = ex.message
            }
        }

        markButton.setOnClickListener {
            statusUpdate.text = "Mark button pressed"

        }

        fileName.setOnClickListener {
            statusUpdate.text = "File name: ${fileName.text}"

        }
    }

//    private fun getLocation():Location {
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
//        }
//        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//        //locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
//            when (grantResults[0]) {
//                PackageManager.PERMISSION_GRANTED -> getLocation()
//                PackageManager.PERMISSION_DENIED -> statusUpdate.text = "ERROR: Can not work without location permissions."
//            }
//        }
//    }

}
