package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends SubsystemBase {

    private DcMotorEx intakeMotor;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake_leftEncoder");
    }

    public void intake() {
        intakeMotor.setPower(1);
    }

    public void setPower(double power) {
        intakeMotor.setPower(power);
    }

    public void outtake() {
        intakeMotor.setPower(-1);
    }

    public void forceStop() {
        intakeMotor.setPower(0);
    }
}
