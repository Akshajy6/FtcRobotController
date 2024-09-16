//package org.firstinspires.ftc.teamcode.teleop;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.kauailabs.navx.ftc.AHRS;
//import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
//
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.teamcode.subsystems.Drivebase;
//import org.firstinspires.ftc.teamcode.subsystems.Impasta;
//import org.firstinspires.ftc.teamcode.subsystems.Mechanisms;
//
//@Disabled
//@TeleOp
//public class ImpastaTeleOp extends LinearOpMode {
//    // Declaring hardware variables
//    private DcMotor fl, fr, bl, br, leftSlide, rightSlide, Intake;
//    private Servo out1, out2, launchPlane, DRV4BL, DRV4BR;
//    private DistanceSensor leftSensor;
//    private DistanceSensor rightSensor;
//    private AHRS imu;
//    private double up, down, current, distance;
//    private boolean doDetectDistance;
//    Impasta impasta;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        // Initializing motors, servos, and sensors
//        Mechanisms mechanisms = new Mechanisms()
//
////        leftSensor = hardwareMap.get(DistanceSensor.class, "leftSensor");
////        rightSensor = hardwareMap.get(DistanceSensor.class, "rightSensor");
//
//        distance = 1;
//        doDetectDistance = false;
//
//        imu = AHRS.getInstance(hardwareMap.get(NavxMicroNavigationSensor.class, "navx"), AHRS.DeviceDataType.kProcessedData);
//
//        // Creating an instance of the Impasta class
//
//        boolean reset = true;
//
//        boolean DRV4BReset = false;
//
//        telemetry.addLine("Initialization Done, pos reset: " + reset + " DRV4B reset: " + DRV4BReset);
//        telemetry.update();
//
//        // Waiting for the start button to be pressed
//        waitForStart();
//
//        if (isStopRequested()) return;
//        // Main TeleOp loop
//        while (opModeIsActive()) {
//            /** gamepad1                                                                                */
//            // Driving the robot based on gamepad input
//            impasta.driveBaseField(-gamepad1.left_stick_y, gamepad1.left_stick_x * 1.1, -gamepad1.right_stick_x);
//
//            // Controlling intake based on trigger input
//            impasta.intake(gamepad1.left_trigger - gamepad1.right_trigger);
//
//            // Resetting IMU yaw angle if left bumper is pressed
//            if (gamepad1.left_bumper || gamepad1.right_bumper) {
//                impasta.reset();
//                gamepad1.rumble(2000);
//            }
//
//            if (gamepad2.left_bumper || gamepad1.right_bumper) {
//                impasta.resetSlide();
//            }
//
//            if (gamepad2.dpad_down && !impasta.atLower()) {
//                impasta.runPID(0);
//            } else if (gamepad2.dpad_left) {
//                impasta.runPID(150);
//            } else if (gamepad2.dpad_right) {
//                impasta.runPID(300);
//            } else if (gamepad2.dpad_up && !impasta.atUpper()) {
//                impasta.runPID(450);
//            } else {
//                impasta.setSlidesPower(gamepad2.left_stick_y);
//            }
//
//            telemetry.addLine("Left Slide Pos: " + leftSlide.getCurrentPosition());
//
//            //TODO Test Airplane Aim
//            if (gamepad2.triangle) {
//                launchPlane.setPosition(0);
//            } else if (gamepad2.square) {
//                launchPlane.setPosition(1);
//            }
//
//            if (gamepad2.circle) {
//                DRV4BL.setPosition(0.65);
//                DRV4BR.setPosition(0.65);
//            } else if (gamepad2.cross) {
//                DRV4BL.setPosition(0);
//                DRV4BR.setPosition(0);
//            }
//
//            if (gamepad1.dpad_left) {
//                doDetectDistance = !doDetectDistance;
//            }
//
//            // Outtake switches between scoring and rest position based on button press //Swap later
//            if (gamepad2.left_trigger > 0.3 /*|| DetectDistance() <= distance*/) {
//                gamepad1.rumble(1000);
//                out1.setPosition(0.75); // left //lower
//            } else {
//                out1.setPosition(0.55); // left //raise
//            }
//
//            if (gamepad2.right_trigger > 0.3 /*|| DetectDistance() <= distance */) {
//                gamepad1.rumble(1000);
//                out2.setPosition(0.5); // right //lower
//            } else {
//                out2.setPosition(0.6); // right //raise
//            }
//
//            telemetry.update();
//        }
//    }
//
////    public double DetectDistance() {
////        double whatToDo;
////        if (doDetectDistance) {
////            whatToDo = leftSensor.getDistance(DistanceUnit.INCH);
////        } else {whatToDo = 10;}
////        return whatToDo;
////    }
//}
