package com.example.lab05

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*

val CAMERA_REQUEST_CODE= 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCamera = findViewById<Button>(R.id.btCamera)
        btCamera.setOnClickListener {
            checkCameraPermision()
        }
    }
    private fun setupViews()
    {

    }

    private fun checkCameraPermision() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            requestCameraPermission()
        }
        else{
            Toast.makeText(this,"Ya se tiene el permiso de la camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
      if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.CAMERA)){
          Toast.makeText(this,"Rechazó el permiso antes. Habilitelo!!", Toast.LENGTH_LONG).show()
      }
        else{
            Toast.makeText(this,"Debe aceptar el permiso", Toast.LENGTH_LONG).show()
          ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE ->  {
            if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Se acepto el permiso de la Camara", Toast.LENGTH_LONG).show()
            }
                else{
                Toast.makeText(this,"Permiso negado", Toast.LENGTH_LONG).show()

            }
                return
          }
        }
    }
}