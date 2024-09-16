package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.PerpetualCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.auto.BulkCacheCommand;
import org.firstinspires.ftc.teamcode.commands.liftcommands.ManualLiftCommand;
import org.firstinspires.ftc.teamcode.commands.liftcommands.ManualLiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.presets.MoveToScoringCommand;
import org.firstinspires.ftc.teamcode.commands.presets.RetractOuttakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivebase;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Mechanisms;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.Slides;
import org.firstinspires.ftc.teamcode.subsystems.V4B;

@TeleOp
public class MainTeleOp extends CommandOpMode {

    private Slides lift;
    private V4B v4b;
    private Outtake outtake;
    public Drivebase drivebase;
    public Intake intake;

    private ManualLiftCommand manualLiftCommand;
    private ManualLiftResetCommand manualLiftResetCommand;
    public static final double DEBOUNCE_THRESHOLD = 0.05;

    private boolean doClimbing = false;

    @Override
    public void initialize() {
        schedule(new BulkCacheCommand(hardwareMap));
        GamepadEx driver = new GamepadEx(gamepad1);
        GamepadEx manipulator = new GamepadEx(gamepad2);

        lift = new Slides(hardwareMap);
        v4b = new V4B(hardwareMap);
        outtake = new Outtake(hardwareMap);
        drivebase = new Drivebase(hardwareMap);
        intake = new Intake(hardwareMap);

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

//        manipulator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
//                .whenHeld(manualLiftResetCommand);

        telemetry.addLine("Initialization Done");
        telemetry.update();
    }

    @Override
    public void run(){
        super.run();

        drivebase.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        if (gamepad1.square) {
            drivebase.reset();
            gamepad1.rumble(250); // Angle recalibrated
        }

        if (debounce(gamepad1.left_trigger) || debounce(gamepad1.right_trigger)) {
            double powerRight = gamepad1.left_trigger;
            double powerLeft = gamepad1.right_trigger;
//            if (intakeCommand.isScheduled()) {
//                telemetry.addLine("ICSX2");
//                intakeCommand.cancel();
//            }
            if (powerRight > powerLeft) {
                intake.setPower(1);
            } else if (powerLeft > powerRight) {
                intake.setPower(-1);
            }
//        } else if (intakeCommand.isScheduled()){
//            telemetry.addLine("Intake Command Scheduled");
        } else {
            intake.setPower(0);
        }

//        if (gamepad1.left_trigger > 0.05) {
//            intake.outtake();
//        } else if (gamepad1.right_trigger > 0.05) {
//            intake.intake();
//        } else {
//            intake.forceStop();
//        }

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


//        if (gamepad2.left_bumper) {
//            impasta.resetSlide();
//        }
//
//        if (!pidActive) {
//            impasta.runManual(gamepad2.left_stick_y, doClimbing);
//            telemetry.addLine("Lift Position: " + -rightSlide.getCurrentPosition());
//        }

//        if (gamepad1.triangle) {
//            mechanisms.launcher.reset();
//        } else if (gamepad1.square) {
//            mechanisms.launcher.launch();
//        }

//        if (gamepad2.right_bumper) {
//            doClimbing = !doClimbing;
//            if (doClimbing) {
//                leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            } else {
//                leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//            }
//        }

        telemetry.addLine("isClimbing State: " + doClimbing);
        telemetry.addData("imuHeading", drivebase.getCorrectedYaw());
        telemetry.addData("imuNONCO", drivebase.imu.getYaw());



//        if (gamepad2.square) {
//            mechanisms.drivebase.frontLeft.setPower(1);
//        } else if (gamepad2.triangle) {
//            mechanisms.drivebase.frontRight.setPower(1);
//        } else if (gamepad2.cross) {
//            mechanisms.drivebase.backLeft.setPower(1);
//        } else if (gamepad2.circle) {
//            mechanisms.drivebase.backRight.setPower(1);
//        }

//
//        mechanisms.outtake.outtakes();

        telemetry.update();
    }


    public static boolean debounce(double input) {
        return Math.abs(input) > DEBOUNCE_THRESHOLD;
    }
}
