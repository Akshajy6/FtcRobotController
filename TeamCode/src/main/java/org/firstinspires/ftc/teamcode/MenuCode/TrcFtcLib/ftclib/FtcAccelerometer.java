/*
 * Copyright (c) 2015 Titan Robotics Club (http://www.titanrobotics.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.MenuCode.TrcFtcLib.ftclib;

import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;

import org.firstinspires.ftc.teamcode.MenuCode.TrcCommonLib.trclib.TrcAccelerometer;
import org.firstinspires.ftc.teamcode.MenuCode.TrcCommonLib.trclib.TrcFilter;
import org.firstinspires.ftc.teamcode.MenuCode.TrcCommonLib.trclib.TrcTimer;

/**
 * This class implements the platform dependent accelerometer extending TrcAccelerometer. It provides implementation
 * of the abstract methods in TrcAccelerometer. It supports 3 axes: x, y and z. It provides acceleration data for all
 * 3 axes. However, it doesn't provide any velocity or distance data.
 */
public class FtcAccelerometer extends TrcAccelerometer
{
    private final AccelerationSensor accel;

    /**
     * Constructor: Creates an instance of the object.
     *
     * @param hardwareMap specifies the global hardware map.
     * @param instanceName specifies the instance name.
     * @param filters specifies an array of filters to use for filtering sensor noise, one for each axis. Since we
     *                have 3 axes, the array should have 3 elements. If no filters are used, it can be set to null.
     */
    public FtcAccelerometer(HardwareMap hardwareMap, String instanceName, TrcFilter[] filters)
    {
        super(instanceName, 3,
              ACCEL_HAS_X_AXIS | ACCEL_HAS_Y_AXIS | ACCEL_HAS_Z_AXIS | ACCEL_INTEGRATE | ACCEL_DOUBLE_INTEGRATE,
              filters);
        accel = hardwareMap.get(AccelerationSensor.class, instanceName);
    }   //FtcAccelerometer

    /**
     * Constructor: Creates an instance of the object.
     *
     * @param instanceName specifies the instance name.
     * @param filters specifies an array of filters to use for filtering sensor noise, one for each axis. Since we
     *                have 3 axes, the array should have 3 elements. If no filters are used, it can be set to null.
     */
    public FtcAccelerometer(String instanceName, TrcFilter[] filters)
    {
        this(FtcOpMode.getInstance().hardwareMap, instanceName, filters);
    }   //FtcAccelerometer

    /**
     * Constructor: Creates an instance of the object.
     *
     * @param instanceName specifies the instance name.
     */
    public FtcAccelerometer(String instanceName)
    {
        this(instanceName, null);
    }   //FtcAccelerometer

    /**
     * This method calibrates the sensor.
     */
    public void calibrate()
    {
        calibrate(DataType.ACCELERATION);
    }   //calibrate

    //
    // Implements TrcAccelerometer abstract methods.
    //

    /**
     * This method returns the raw data of the specified type for the x-axis.
     *
     * @param dataType specifies the data type.
     * @return raw data of the specified type for the x-axis.
     */
    @Override
    public synchronized SensorData<Double> getRawXData(DataType dataType)
    {
        SensorData<Double> data;

        if (dataType == DataType.ACCELERATION)
        {
            Acceleration accelData = accel.getAcceleration();
            data = new SensorData<>(TrcTimer.getCurrentTime(), accelData.xAccel);
        }
        else
        {
            throw new UnsupportedOperationException("Accelerometer sensor does not provide velocity or distance data.");
        }

        return data;
    }   //getRawXData

    /**
     * This method returns the raw data of the specified type for the y-axis.
     *
     * @param dataType specifies the data type.
     * @return raw data of the specified type for the y-axis.
     */
    @Override
    public synchronized SensorData<Double> getRawYData(DataType dataType)
    {
        SensorData<Double> data;

        if (dataType == DataType.ACCELERATION)
        {
            Acceleration accelData = accel.getAcceleration();
            data = new SensorData<>(TrcTimer.getCurrentTime(), accelData.yAccel);
        }
        else
        {
            throw new UnsupportedOperationException("Accelerometer sensor does not provide velocity or distance data.");
        }

        return data;
    }   //getRawYData

    /**
     * This method returns the raw data of the specified type for the z-axis.
     *
     * @param dataType specifies the data type.
     * @return raw data of the specified type for the z-axis.
     */
    @Override
    public synchronized SensorData<Double> getRawZData(DataType dataType)
    {
        SensorData<Double> data;

        if (dataType == DataType.ACCELERATION)
        {
            Acceleration accelData = accel.getAcceleration();
            data = new SensorData<>(TrcTimer.getCurrentTime(), accelData.zAccel);
        }
        else
        {
            throw new UnsupportedOperationException("Accelerometer sensor does not provide velocity or distance data.");
        }

        return data;
    }   //getRawZData

}   //class FtcAccelerometer
