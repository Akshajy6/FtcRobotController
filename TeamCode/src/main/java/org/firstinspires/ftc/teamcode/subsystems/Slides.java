package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public class Slides extends SubsystemBase {
    public enum SlidePositions {
        DOWN(0),
        SHORT(1560),
        MID(1900),
        HIGH(2300);

        public int position;

        SlidePositions(int position){
            this.position = position;
        }

        public int getPosition() {
            return this.position;
        }
    }

    final double winchServoInit = 0.5, winchServoEngage = 0.66;

    DcMotorEx leftMotor;
    DcMotorEx rightMotor;

    private VoltageSensor voltageSensor;
    private double voltageComp;
    private double VOLTAGE_WHEN_LIFT_TUNED = 13.0;

    public Slides(HardwareMap hardwareMap){

        leftMotor = hardwareMap.get(DcMotorEx.class, "leftSlide_frontEncoder");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightSlide");

        // Zero both the motors
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // We don't need to use builtin PID
        // THIS DOES NOT DISABLE THE ENCODER.
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Make it so the when positive power applied, lift moves up.
//        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Negate the gravity when stopped
        //TODO gravity PID coefficients?
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); // change to brake if bad
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        voltageComp = VOLTAGE_WHEN_LIFT_TUNED / voltageSensor.getVoltage();
    }

    @Override
    public void periodic(){
        // happens every loop
    }

    public void setLiftPower(double power){
        //TODO this could be the PID demon
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public void brake_power(){
        setLiftPower(0);
    }

    public double getLiftPosition(){
        return rightMotor.getCurrentPosition();
    }

    public double getLiftVelocity(){
        return leftMotor.getVelocity();
    }

    public boolean atUpperLimit(){
        return getLiftPosition() > 2350;
    }

    public boolean atLowerLimit(){
        return getLiftPosition() < 5;
    }

    public void setLeftPower (double power) {
        leftMotor.setPower(power);
    }

    public void setRightMotor (double power) {
        rightMotor.setPower(power);
    }

    public void resetLiftPosition(){
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public double getVoltageComp(){
        return voltageComp;
    }
}
