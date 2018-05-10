package com.example.leh2.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvTemp: TextView
    private lateinit var tvFahren: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var mySM: SensorManager
    private lateinit var myTemp: Sensor
    private lateinit var myHumidity: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTemp = findViewById(R.id.tvTemp)
        tvFahren = findViewById(R.id.tvFahren)
        tvHumidity = findViewById(R.id.tvHumidity)
        mySM = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        myTemp = mySM.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        myHumidity = mySM.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        mySM.registerListener(this, myTemp , SensorManager.SENSOR_DELAY_NORMAL)
        mySM.registerListener(this, myHumidity , SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(sensorEvent: SensorEvent){
        val sensor = sensorEvent.sensor
        if (sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            var fahrenheit = sensorEvent.values[0]*9/5+32
            tvTemp.text = ""+sensorEvent.values[0]+" degrees Celsius"
            tvFahren.text = ""+(sensorEvent.values[0]*9/5+32)+" degrees Fahrenheit"
            if (72 > fahrenheit && fahrenheit > 68){
                Toast.makeText(this, "Ideal temperature", Toast.LENGTH_SHORT).show()
            } else if (74 > fahrenheit && fahrenheit > 66) {
                Toast.makeText(this, "Acceptable temperature", Toast.LENGTH_SHORT).show()
            }
        }
        if (sensor.type == Sensor.TYPE_RELATIVE_HUMIDITY) {
            tvHumidity.text = sensorEvent.values[0].toString() + "%"
            if (49 > sensorEvent.values[0] && sensorEvent.values[0] > 41){
                Toast.makeText(this, "Ideal humidity", Toast.LENGTH_SHORT).show()
            } else if (53 > sensorEvent.values[0] && sensorEvent.values[0] > 37) {
                Toast.makeText(this, "Acceptable humidity", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        mySM.registerListener (this, myTemp, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        mySM.unregisterListener(this)
    }
}
