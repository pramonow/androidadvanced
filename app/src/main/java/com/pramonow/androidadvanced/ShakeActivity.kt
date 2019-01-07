package com.pramonow.androidadvanced

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorListener
import android.support.v7.app.AppCompatActivity
import android.R.attr.y
import android.R.attr.x
import android.widget.Toast
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log


class ShakeActivity : AppCompatActivity(),SensorEventListener{

    lateinit var sensorManager: SensorManager
    lateinit var accSensor:Sensor
    lateinit var magnetSensor:Sensor

    /** Minimum movement force to consider.  */
    private val MIN_FORCE = 40

    /**
     * Minimum times in a shake gesture that the direction of movement needs to
     * change.
     */
    private val MIN_DIRECTION_CHANGE = 20

    /** Maximum pause between movements.  */
    private val MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE = 1000

    /** Minimum allowed time for shake gesture.  */
    private val MIN_TOTAL_DURATION_OF_SHAKE = 1.5 // 8 seconds

    /** Time when the gesture started.  */
    private var mFirstDirectionChangeTime: Long = 0

    /** Time when the last movement started.  */
    private var mLastDirectionChangeTime: Long = 0

    /** How many movements are considered so far.  */
    private var mDirectionChangeCount = 0

    /** The last x position.  */
    private var lastX = 0f

    /** The last y position.  */
    private var lastY = 0f

    /** The last z position.  */
    private var lastZ = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager =  getSystemService(SENSOR_SERVICE) as SensorManager
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magnetSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(se: SensorEvent) {
        // get sensor data
        val x = se.values[SensorManager.DATA_X]
        val y = se.values[SensorManager.DATA_Y]
        val z = se.values[SensorManager.DATA_Z]

        // calculate movement
        val totalMovement = Math.abs(x + y + z - lastX - lastY - lastZ)

        //Log.d("baniman","movement $totalMovement")

        if (totalMovement > MIN_FORCE) {

            // get time
            val now = System.currentTimeMillis()

            // store first movement time
            if (mFirstDirectionChangeTime == 0L) {
                mFirstDirectionChangeTime = now
                mLastDirectionChangeTime = now
            }

            // check if the last movement was not long ago
            val lastChangeWasAgo = now - mLastDirectionChangeTime
            if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {

                // store movement data
                mLastDirectionChangeTime = now
                mDirectionChangeCount++

                // store last sensor data
                lastX = x
                lastY = y
                lastZ = z

                // check how many movements are so far
                if (mDirectionChangeCount >= MIN_DIRECTION_CHANGE) {

                    // check total duration
                    val totalDuration = now - mFirstDirectionChangeTime
                    if (totalDuration >= MIN_TOTAL_DURATION_OF_SHAKE) {
                        Log.d("baniman","shaking")
                        resetShakeParameters()
                    }
                }

            } else {
                resetShakeParameters()
            }
        }
    }

    /**
     * Resets the shake parameters to their default values.
     */
    private fun resetShakeParameters() {
        mFirstDirectionChangeTime = 0
        mDirectionChangeCount = 0
        mLastDirectionChangeTime = 0
        lastX = 0f
        lastY = 0f
        lastZ = 0f
    }

}