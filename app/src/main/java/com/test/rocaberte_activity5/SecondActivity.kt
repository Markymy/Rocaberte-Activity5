package com.test.rocaberte_activity5

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 123
        private val PERMISSIONS_REQUIRED = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val buttonTakePhoto = findViewById<Button>(R.id.button_take_photo)
        buttonTakePhoto.setOnClickListener {
            requestPermission(Manifest.permission.CAMERA, "Camera")
        }

        val buttonGetLocation = findViewById<Button>(R.id.button_get_location)
        buttonGetLocation.setOnClickListener {
            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, "Location")
        }

        val buttonAccessStorage = findViewById<Button>(R.id.button_access_storage)
        buttonAccessStorage.setOnClickListener {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Storage")
        }
    }

    private fun requestPermission(permission: String, name: String) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSIONS_REQUEST_CODE)
        } else {
            Toast.makeText(this, "$name permission already granted", Toast.LENGTH_SHORT).show()
            // TODO: Implement the feature that requires this permission
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            grantResults.forEachIndexed { index, result ->
                val permission = permissions[index]
                val permissionName = when (permission) {
                    Manifest.permission.CAMERA -> "Camera"
                    Manifest.permission.ACCESS_FINE_LOCATION -> "Location"
                    Manifest.permission.WRITE_EXTERNAL_STORAGE -> "Storage"
                    else -> "Unknown"
                }
                if (result == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "$permissionName permission granted", Toast.LENGTH_SHORT).show()
                    // TODO: Implement the feature that requires this permission
                } else {
                    Toast.makeText(this, "$permissionName permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}