package com.pramonow.androidadvanced

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.pramonow.androidadvanced.R


class ShakeActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var accSensor: Sensor
    lateinit var magnetSensor: Sensor

    /** Minimum movement force to consider.  */
    private val MIN_FORCE = 45

    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f

    private lateinit var countShake:TextView
    private lateinit var powerMeter:TextView

    private var topPower = 0
    private var count = 0

    private var lastUpdate = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shake)

        sensorManager =  getSystemService(SENSOR_SERVICE) as SensorManager
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magnetSensor, SensorManager.SENSOR_DELAY_NORMAL)

        countShake = findViewById(R.id.amount_shake)
        powerMeter = findViewById(R.id.power)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(se: SensorEvent) {

        val curTime = System.currentTimeMillis()

        if ((curTime - lastUpdate) > 100) {
            val diffTime = curTime - lastUpdate
            lastUpdate = curTime

            var gravity = FloatArray(3)
            var linear_acceleration = FloatArray(3)
            val alpha = 0.8f

            gravity[0] = alpha * gravity[0] + (1 - alpha) * se.values[0]
            gravity[1] = alpha * gravity[1] + (1 - alpha) * se.values[1]
            gravity[2] = alpha * gravity[2] + (1 - alpha) * se.values[2]

            linear_acceleration[0] = se.values[0] - gravity[0]
            linear_acceleration[1] = se.values[1] - gravity[1]
            linear_acceleration[2] = se.values[2] - gravity[2]

            val speed = Math.abs(linear_acceleration[0] + linear_acceleration[1] + linear_acceleration[2] - lastX - lastY - lastZ)

            if (speed > MIN_FORCE) {

                count++;
                countShake.setText(count.toString())

                if (topPower < speed) {
                    topPower = speed.toInt()
                    powerMeter.setText(topPower.toString())
                }

                // store last sensor data
                lastX = linear_acceleration[0]
                lastY = linear_acceleration[1]
                lastZ = linear_acceleration[2]
            }
        }
    }
}