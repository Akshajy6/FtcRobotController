package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.drive.Drive;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Mechanisms extends SubsystemBase {
    private Gamepad gamepad1, gamepad2;
    private ServoImplEx out1, out2, launchPlane;
    public V4B v4b;
    public Launcher launcher;
    public Outtake outtake;
    public Intake intake;
    public Drivebase drivebase;

    public Mechanisms(HardwareMap hardwareMap) {
        v4b = new V4B(hardwareMap);
        launcher = new Launcher(hardwareMap);
        outtake = new Outtake(hardwareMap);
        intake = new Intake(hardwareMap);
        drivebase = new Drivebase(hardwareMap);
    }
}
