//package org.firstinspires.ftc.teamcode.teleop;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//import org.firstinspires.ftc.teamcode.subsystems.Impasta;
//import org.firstinspires.ftc.teamcode.subsystems.PID;
//
//@TeleOp
//public class SlidePIDTest extends LinearOpMode {
//
//    DcMotor leftSlide;
//    DcMotor rightSlide;
//
//    Impasta impasta;
//    PID pid = new PID(0.5, 0, 0);
//
//    @Override
//    public void runOpMode() {
//
//        leftSlide = hardwareMap.dcMotor.get("frontEncoder");
//        rightSlide = hardwareMap.dcMotor.get("Right Slide");
//
//        impasta = new Impasta(leftSlide, rightSlide);
//
//        impasta.resetSlide();
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            double pos = leftSlide.getCurrentPosition();
//            telemetry.addData("Pos: ", pos);
//            telemetry.update();
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
//        }
//    }
//}
