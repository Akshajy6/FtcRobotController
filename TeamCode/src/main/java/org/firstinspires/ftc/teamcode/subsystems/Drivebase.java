package org.firstinspires.ftc.teamcode.subsystems;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drivebase {
    public AHRS imu;

    private double imuPrevPositionRad = 0.0;

    public DcMotorEx frontLeft, frontRight, backLeft, backRight;


    public Drivebase (HardwareMap hardwareMap) {
        this.frontLeft = hardwareMap.get(DcMotorEx.class,"rightFront"); // Drivebase
        this.frontRight = hardwareMap.get(DcMotorEx.class,"leftFront"); // Drivebase
        this.backLeft = hardwareMap.get(DcMotorEx.class,"rightRear"); // Drivebase
        this.backRight = hardwareMap.get(DcMotorEx.class,"leftRear"); // Drivebase
        this.imu = AHRS.getInstance(hardwareMap.get(NavxMicroNavigationSensor.class, "navx"), AHRS.DeviceDataType.kProcessedData);

        commonMotorSetup();
    }

    private void commonMotorSetup() {
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

//        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

//    public void reset() {
//        imu.zeroYaw();
//    }

    public void drive(double y, double x, double rx) {
        // rx = right stick x
        // x = left stick x
        // y = left stick y (reversed in hardware)
        y = -y; // y && strafe forward back
        x = x * 1.1; // x && strafe right and left
        rx = rx; // rx && turn left right angular

        // Calculate the robot's heading from the IMU
        double botHeading = getCorrectedYaw();

        // botHeading is in Radians
        Vector2d botVector = new Vector2d(x, y).rotated(botHeading);

//        // Apply the calculated heading to the input vector for field centric
        x = botVector.getX(); // strafe r/l transform values
        y = botVector.getY(); // strafe f/b transform values
        // note rx is not here since rotation is always field centric!

        // Calculate the motor powers
        double frontLeftPower = y + x + rx;
        double frontRightPower = y - x - rx;

        double backLeftPower = y - x + rx;
        double backRightPower = y + x - rx;

        // Find the max power and make sure it ain't greater than 1
        double denominator = max(
                max(abs(frontLeftPower), abs(backLeftPower)),
                max(abs(frontRightPower), abs(backRightPower))
        );

        if (denominator > 1.0) {
            frontLeftPower /= denominator;
            backLeftPower /= denominator;
            frontRightPower /= denominator;
            backRightPower /= denominator;
        }

        // Old method below, to ensure the CONTROLLERS no more than 1
        // double denominator = max(abs(y) + abs(x) + abs(rx), 1);

        // Set the motor powers
        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

//        double botHeading = Math.toRadians(imu.getYaw());
//
//        // reverse the controller left stick because it's reversed in hardware.
//        y = -y;
//
//        // Rotate the movement direction counter to the bot's rotation
//        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
//
//        rotX = rotX * 1.1;  // Counteract imperfect strafing
//
//        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
//
//        double flpwr = (rotY + rotX + rx) / denominator;
//        double blpwr = (rotY - rotX + rx) / denominator;
//        double frpwr = (rotY - rotX - rx) / denominator;
//        double brpwr = (rotY + rotX - rx) / denominator;
//
//        frontLeft.setPower(flpwr);
//        backLeft.setPower(blpwr);
//        frontRight.setPower(frpwr);
//        backRight.setPower(brpwr);
    }

    public double getCorrectedYaw () {
        double imuDeg = imu.getYaw();
        double imuRad = AngleUnit.RADIANS.fromDegrees(imuDeg);
//        double imuRad = imuDeg;
        double correctedRadReset = imuRad-imuPrevPositionRad;
        return (1.0) * correctedRadReset; // * (14.0/180.0);
    }

    public void reset() {
        imuPrevPositionRad = AngleUnit.RADIANS.fromDegrees(imu.getYaw());
    }
}
