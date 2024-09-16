package org.firstinspires.ftc.teamcode.teleop;

import android.annotation.SuppressLint;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.PerpetualCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.auto.BulkCacheCommand;
import org.firstinspires.ftc.teamcode.commands.liftcommands.ManualLiftCommand;
import org.firstinspires.ftc.teamcode.commands.liftcommands.ManualLiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.presets.MoveToScoringCommand;
import org.firstinspires.ftc.teamcode.commands.presets.RetractOuttakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.Slides;
import org.firstinspires.ftc.teamcode.subsystems.V4B;

@TeleOp (name="Lift Tester")
public class LiftTester extends CommandOpMode {
    //    private Drivebase drivebase;
    private Slides lift;
    private V4B v4b;
    private Outtake outtake;
    private ManualLiftCommand manualLiftCommand;
    private ManualLiftResetCommand manualLiftResetCommand;

    @Override
    public void initialize(){
        // Use a bulk cache to loop faster using old values instead of blocking a thread kinda
        schedule(new BulkCacheCommand(hardwareMap));
        GamepadEx driver = new GamepadEx(gamepad1);
        GamepadEx manipulator = new GamepadEx(gamepad2);

        lift = new Slides(hardwareMap);
        v4b = new V4B(hardwareMap);
        outtake = new Outtake(hardwareMap);

        outtake.transport();
        v4b.retract();

        manualLiftCommand = new ManualLiftCommand(lift, manipulator);
        manualLiftResetCommand = new ManualLiftResetCommand(lift, manipulator);

        lift.setDefaultCommand(new PerpetualCommand(manualLiftCommand));

        new Trigger(() -> manipulator.getLeftY() > 0.4)
                .whenActive(new MoveToScoringCommand(lift, v4b, outtake, MoveToScoringCommand.Presets.HIGH)
                        .withTimeout(1900)
                        .interruptOn(() -> manualLiftCommand.isManualActive()));

        new Trigger(() -> manipulator.getLeftY() < -0.6)
                .whenActive(new RetractOuttakeCommand(lift, v4b, outtake)
                        .withTimeout(1900)
                        .interruptOn(() -> manualLiftCommand.isManualActive()));

        //Mid preset
        new Trigger(() -> manipulator.getRightY() > -0.4)
                .whenActive(new MoveToScoringCommand(lift, v4b, outtake, MoveToScoringCommand.Presets.MID)
                        .withTimeout(1900)
                        .interruptOn(() -> manualLiftCommand.isManualActive()));

        //Short preset
        new Trigger(() -> manipulator.getRightY() < 0.4)
                .whenActive(new MoveToScoringCommand(lift, v4b, outtake, MoveToScoringCommand.Presets.SHORT)
                        .withTimeout(1900)
                        .interruptOn(() -> manualLiftCommand.isManualActive()));
    }


    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        super.run();

        telemetry.addData("position", lift.getLiftPosition());

        if (gamepad1.left_bumper){
            v4b.setPosition(V4B.V4BState.RETRACT);
        } else if(gamepad1.right_bumper){
            v4b.setPosition(V4B.V4BState.EXTEND);
        }

        if (gamepad2.right_bumper) {
            outtake.transport();
        } else if (gamepad2.left_bumper) {
            outtake.open();
        }

        telemetry.update();
    }
}