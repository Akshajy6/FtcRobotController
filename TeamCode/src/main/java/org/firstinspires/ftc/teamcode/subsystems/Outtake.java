package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Outtake extends SubsystemBase {

    public enum outtakePos {
        OPEN(0.961),
        CLOSED(0.86),
        TRANSPORT(0.83);

        public double position;

        outtakePos(double position) {
            this.position = position;
        }
    }

    ServoImplEx leftOuttake, rightOuttake;
    DistanceSensor leftSensor, rightSensor;
    int distance = 6;

    public Outtake(HardwareMap hardwareMap) {
        leftOuttake = hardwareMap.get(ServoImplEx.class, "leftOut");
        rightOuttake = hardwareMap.get(ServoImplEx.class, "rightOut");
        leftSensor = hardwareMap.get(DistanceSensor.class, "leftDistance");
        rightSensor = hardwareMap.get(DistanceSensor.class, "rightDistance");

        //TODO: Test the following to see if it fixes DC issues of power module
        leftOuttake.setPwmRange(new PwmControl.PwmRange(500, 2500));
        rightOuttake.setPwmRange(new PwmControl.PwmRange(500, 2500));
    }

    public double returnDistanceLeft() {
        return leftSensor.getDistance(DistanceUnit.CM);
    }
    
    public double returnDistanceRight() {
        return rightSensor.getDistance(DistanceUnit.CM);
    }

    public void detectDistance() {
        if (leftSensor.getDistance(DistanceUnit.CM) < distance) {
            leftOuttake.setPosition(outtakePos.CLOSED.position);
        } else {
            leftOuttake.setPosition(outtakePos.OPEN.position);
        }

        if (rightSensor.getDistance(DistanceUnit.CM) < distance) {
            rightOuttake.setPosition(outtakePos.CLOSED.position);
        } else {
            rightOuttake.setPosition(outtakePos.OPEN.position);
        }
    }

    void left_open () {
        leftOuttake.setPosition(outtakePos.OPEN.position);
    }

    void right_open () {
        rightOuttake.setPosition(outtakePos.OPEN.position);
    }

    void left_close () {
        leftOuttake.setPosition(outtakePos.CLOSED.position);
    }

    void right_close () {
        rightOuttake.setPosition(outtakePos.CLOSED.position);
    }

    void left_transport () {
        leftOuttake.setPosition(outtakePos.TRANSPORT.position);
    }

    void right_transport () {
        rightOuttake.setPosition(outtakePos.TRANSPORT.position);
    }

    public void close () {
        left_close();
        right_close();
    }

    public void open () {
        right_open();
        left_open();
    }

    public void transport () {
        right_transport();
        left_transport();
    }

    public void setPosition (double position) {
        leftOuttake.setPosition(position);
        rightOuttake.setPosition(position);
    }

    public double getPosition() {
        return leftOuttake.getPosition();
    }
}
