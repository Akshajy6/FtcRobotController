package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Launcher extends SubsystemBase {

    private Servo launchServo;

    public enum PlaneState {
        LAUNCH(1),
        RESET(0);

        public double position;

        PlaneState(double position) {
            this.position = position;
        }
    }

    public Launcher(HardwareMap hardwareMap) {
        launchServo = hardwareMap.get(ServoImplEx.class, "launcher");
    }

    public void launch() {
        launchServo.setPosition(PlaneState.LAUNCH.position);
    }

    public void reset() {
        launchServo.setPosition(PlaneState.RESET.position);
    }
}
