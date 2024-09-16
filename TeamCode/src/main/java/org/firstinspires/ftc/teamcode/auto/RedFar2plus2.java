//package org.firstinspires.ftc.teamcode.auto;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.arcrobotics.ftclib.command.CommandOpMode;
//import com.arcrobotics.ftclib.command.InstantCommand;
//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//import com.arcrobotics.ftclib.command.WaitCommand;
//import com.qualcomm.hardware.dfrobot.HuskyLens;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.Gamepad;
//
//import org.firstinspires.ftc.robotcore.internal.system.Deadline;
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.subsystems.Intake;
//import org.firstinspires.ftc.teamcode.subsystems.Outtake;
//import org.firstinspires.ftc.teamcode.subsystems.Slides;
//import org.firstinspires.ftc.teamcode.subsystems.V4B;
//import org.firstinspires.ftc.teamcode.vision.trajectorysequence.TrajectorySequence;
//
//import java.util.concurrent.TimeUnit;
//
//
//@Autonomous(name="Red Far 2plus2", group="Far")
//public class RedFar2plus2 extends CommandOpMode {
//    //Add Motors and servos not for drivebase here
//    SampleMecanumDrive drive;
//    Slides s;
//    Outtake o;
//    V4B v4b;
//    Intake i;
//    Gamepad gamepad;
//    private final int READ_PERIOD = 1;
//    private HuskyLens huskyLens;
//    public static Pose2d startPoseFarRed = new Pose2d(-35,-62, Math.toRadians(90));
//
//    TrajectorySequence prop;
//    TrajectorySequence score;
//    TrajectorySequence parkAfter2;
//    TrajectorySequence scorePlus;
//    TrajectorySequence intakeIt;
//    TrajectorySequence park;
//
//    public static Pose2d startPoseCloseRed = new Pose2d(12,62, Math.toRadians(-90));
//    String section;
//
//    @Override
//    public void initialize() {
//        drive = new SampleMecanumDrive(hardwareMap);
//        s = new Slides(hardwareMap);
//        o = new Outtake(gamepad, hardwareMap);
//        v4b = new V4B(hardwareMap);
//        huskyLens = hardwareMap.get(HuskyLens.class, "huskylens");
//
//        Deadline rateLimit = new Deadline(READ_PERIOD, TimeUnit.SECONDS);
//        rateLimit.expire();
//
//        if (!huskyLens.knock()) {
//            telemetry.addData(">>", "Problem communicating with " + huskyLens.getDeviceName());
//            telemetry.update();
//        }
//        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
//
//        drive.setPoseEstimate(startPoseFarRed);
//
//        TrajectorySequence propFarMidRed = drive.trajectorySequenceBuilder(startPoseFarRed)
//
//                .lineToLinearHeading(new Pose2d(-35.6, -32, Math.toRadians(90)))
//                .back(6)
//                .strafeLeft(15)
//                .lineToLinearHeading(new Pose2d(-51.6, -7.4, Math.toRadians(0)))
//                .build();
//
//        TrajectorySequence propFarLeftRed =  drive.trajectorySequenceBuilder(startPoseFarRed)
//                .forward(25)
//                .lineToLinearHeading(new Pose2d(-36.6, -30.5, Math.toRadians(180)))
//                .build();
//
//        TrajectorySequence propFarRightRed = drive.trajectorySequenceBuilder(startPoseFarRed)
//                .forward(25)
//                .lineToLinearHeading(new Pose2d(-32.6, -30.5, Math.toRadians(0)))
//                .build();
//
//
//        TrajectorySequence scoreFarLeftRed = drive.trajectorySequenceBuilder(propFarMidRed.end())
//                .lineToLinearHeading(new Pose2d(47.4, -7.4, Math.toRadians(0)))
//                .strafeRight(25-3)
//                .build();
//        TrajectorySequence scoreFarMidRed = drive.trajectorySequenceBuilder(propFarMidRed.end())
//                .lineToLinearHeading(new Pose2d(47.4, -7.4, Math.toRadians(0)))
//                .strafeRight(25)
//                .build();
//        TrajectorySequence scoreFarRightRed = drive.trajectorySequenceBuilder(propFarRightRed.end())
//                .lineToLinearHeading(new Pose2d(47.4, -7.4, Math.toRadians(0)))
//                .strafeRight(25+3)
//                .build();
//
////        TrajectorySequence parkFarLeftRed = drive.trajectorySequenceBuilder(scoreFarLeftRed.end())
////                .back(4)
////                .lineToLinearHeading(new Pose2d(46.0, -11.6, Math.toRadians(0)))
////                .forward(10)
////                .build();
////
////        TrajectorySequence parkFarMidRed = drive.trajectorySequenceBuilder(scoreFarMidRed.end())
////                .back(4)
////                .lineToLinearHeading(new Pose2d(46.0, -11.6, Math.toRadians(0)))
////                .forward(10)
////                .build();
////
////        TrajectorySequence parkFarRightRed = drive.trajectorySequenceBuilder(scoreFarMidRed.end())
////                .back(4)
////                .lineToLinearHeading(new Pose2d(46.0, -11.6, Math.toRadians(0)))
////                .forward(10)
////                .build();
//        TrajectorySequence getPixel = drive.trajectorySequenceBuilder(scoreFarMidRed.end())
//                .lineToLinearHeading(new Pose2d(46.5, -10.9, Math.toRadians(180)))
//                .lineToLinearHeading(new Pose2d(-60, -10.9, Math.toRadians(180)))
//                .build();
//        //intake here(code this late)
//
//        TrajectorySequence andScore = drive.trajectorySequenceBuilder(getPixel.end())
//                .lineToLinearHeading(new Pose2d(46.5, -10.9, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(47.2, -29.3, Math.toRadians(0)))
//                .build();
//        TrajectorySequence parkFromScore = drive.trajectorySequenceBuilder(andScore.end())
//                .lineToLinearHeading(new Pose2d(46.5, -10.9, 0))
//                .forward(10)
//                .build();
//
//
//        while (opModeInInit()) {
//            if (!rateLimit.hasExpired()) {
//                continue;
//            }
//            rateLimit.reset();
//
//            HuskyLens.Block[] blocks = huskyLens.blocks();
//
//            if (blocks.length > 0) {
//                telemetry.addData("Objects Detected: ", blocks.length);
//                for (int i = 0; i < blocks.length; i++) {
//                    if (blocks[i].id == 2) { //If red, then detect
//                        telemetry.addData("Object " + (i + 1) + ": ", blocks[i].toString());
//
//                        // Determine which section the object is in based on its X-coordinate
//                        int xCoordinate = blocks[i].x;
//                        int yCoordinate = blocks[i].y;
//
//                        // Calculate section boundaries
//                        int sectionWidth = 320 / 3; // Because horizontal screen resolution is 320
//                        int cropHeight = 50;
//
//                        // Determine the section
//                        if (xCoordinate < sectionWidth && yCoordinate > cropHeight) {
//                            section = "LEFT";
//                        } else if (xCoordinate < 2 * sectionWidth && yCoordinate > cropHeight) {
//                            section = "MIDDLE";
//                        } else {
//                            section = "RIGHT";
//                        }
//                        telemetry.addData("Object Section: ", section);
//                    } else { //No objects detected or only red objects detected
//                        section = "RIGHT";
//                        telemetry.addLine("Default - RIGHT");
//                    }
//                }
//            }
//            telemetry.update();
//        }
//
//        telemetry.addLine("START - Section is " + section);
//        telemetry.update();
//
//        switch(section) {
//            case "LEFT":
//                prop = propFarLeftRed;
//                score = scoreFarLeftRed;
////                park = parkFarLeftRed;
//                intakeIt = getPixel;
//                scorePlus = andScore;
//                parkAfter2 = parkFromScore;
//                break;
//            case "MIDDLE":
//                prop = propFarMidRed;
//                score = scoreFarMidRed;
////                park = parkFarMidRed;
//                intakeIt = getPixel;
//                scorePlus = andScore;
//                parkAfter2 = parkFromScore;
//
//                break;
//            default:
//                prop = propFarRightRed;
//                score = scoreFarRightRed;
////                park = parkFarRightRed;
//                intakeIt = getPixel;
//                scorePlus = andScore;
//                parkAfter2 = parkFromScore;
//                break;
//        }
//
////        schedule(new SequentialCommandGroup ( //Makes the following code run one after another, like norma
////            new ParallelCommandGroup(
////                new SequentialCommandGroup(
////                    new TrajectorySequenceCommand(drive, propFarMidRed),
////                    new TrajectorySequenceCommand(drive, scoreFarMidRed),
////                    new TrajectorySequenceCommand(drive, parkFarMidRed)
////                ),
////                new InstantCommand(() -> {
////                    new WaitCommand(4000);
//// /                   s.goToPosition(Slides.SlidePos.LOW);
//////                    new WaitCommand(2000);
//////                    v4b.togglePower();
//////                    new WaitCommand(2000);
//////                    o.open();
//////                    new WaitCommand(2000);
//////                    o.close();
//////                    v4b.togglePower();
//////                    new WaitCommand(1000);
//////                    s.goToPosition(Slides.SlidePos.DOWN);
////                })
////            )
////        ));
//
//        schedule(new SequentialCommandGroup(
//                new TrajectorySequenceCommand(drive, prop),
////                new InstantCommand(() -> {
////                    s.runToPos(Slides.SlidePos.LOW.position);
////                })
//                new TrajectorySequenceCommand(drive, score),
//                new InstantCommand(() -> {
//                    new WaitCommand(20);
//                    s.goToPosition(70);
//                    new WaitCommand(200);
//                    v4b.togglePower();
//                    new WaitCommand(200);
//                    o.open();
//                    new WaitCommand(400);
//                    o.close();
//                    v4b.togglePower();
//                    new WaitCommand(200);
//                    s.goToPosition(50);
//                }),
//                new TrajectorySequenceCommand(drive, park),
//                new TrajectorySequenceCommand(drive, intakeIt),
//                new InstantCommand(() -> {
//                    i.autoIntake(30);//get better number
//                    new WaitCommand(3000);
//                    i.forceStop();
//                    i.autoIntake(-30);
//                    new WaitCommand(1000);
//                    i.forceStop();
//
//                }),
//                new TrajectorySequenceCommand(drive, andScore),
//                new InstantCommand(() -> {
//                    new WaitCommand(20);
//                    s.goToPosition(70);
//                    new WaitCommand(200);
//                    v4b.togglePower();
//                    new WaitCommand(200);
//                    o.open();
//                    new WaitCommand(400);
//                    o.close();
//                    v4b.togglePower();
//                    new WaitCommand(200);
//                    s.goToPosition(50);
//                }),
//                new TrajectorySequenceCommand(drive, parkAfter2)
//
//
//        ));
//    }
//}
