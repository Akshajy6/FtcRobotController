package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class V4B extends SubsystemBase {

    public enum V4BState {
        RETRACT(0.01),
        EXTEND(0.45);

        public double position;

        V4BState(double position) {
            this.position = position;
        }
    }

    private Servo servo1, servo2;

    public V4B(HardwareMap hardwareMap) {
        servo1 = hardwareMap.get(Servo.class, "leftV4B"); // Left Side
        servo2 = hardwareMap.get(Servo.class, "rightV4B"); // Right Side

    }

    @Override
    public void periodic() {}

    public void setPosition(V4BState state) {
        servo1.setPosition(state.position);
        servo2.setPosition(state.position);
    }

    public void extend() {
        servo1.setPosition(V4BState.EXTEND.position);
        servo2.setPosition(V4BState.EXTEND.position);
    }

    public void retract() {
        servo1.setPosition(V4BState.RETRACT.position);
        servo2.setPosition(V4BState.RETRACT.position);
    }

    public double getPosition() {
        return servo1.getPosition();
    }

    public void togglePower() {
        if (getPosition() == V4BState.RETRACT.position) {
            setPosition(V4BState.EXTEND);
        } else {
            setPosition(V4BState.RETRACT);
        }
    }
}
