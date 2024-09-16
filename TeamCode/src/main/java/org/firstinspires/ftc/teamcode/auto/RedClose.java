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
//import org.firstinspires.ftc.teamcode.subsystems.Outtake;
//import org.firstinspires.ftc.teamcode.subsystems.V4B;
//import org.firstinspires.ftc.teamcode.vision.trajectorysequence.TrajectorySequence;
//import org.firstinspires.ftc.teamcode.subsystems.Slides;
//
//import java.util.concurrent.TimeUnit;
//
//
//@Autonomous(name="Red Close", group="Close")
//public class RedClose extends CommandOpMode {
//    //Add Motors and servos not for drivebase here
//    SampleMecanumDrive drive;
//    Slides s;
//    Outtake o;
//    V4B v4b;
//    Gamepad gamepad;
//    private final int READ_PERIOD = 1;
//    private HuskyLens huskyLens;
//
//    TrajectorySequence prop;
//    TrajectorySequence score;
//    TrajectorySequence park;
//
//    public static Pose2d startPoseCloseRed = new Pose2d(12,-62, Math.toRadians(90));
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
//        drive.setPoseEstimate(startPoseCloseRed);
//
//        TrajectorySequence propCloseLeftRed = drive.trajectorySequenceBuilder(startPoseCloseRed)
//                .forward(25)
//                .turn(Math.toRadians(90))
//                .forward(3)
//                .back(3)
//                .lineToLinearHeading(new Pose2d(12, -62, 0))
//                .build();
//        TrajectorySequence scoreCloseLeftRed = drive.trajectorySequenceBuilder(propCloseLeftRed.end())
//                .forward(34)
//                .strafeLeft(30)
//                .build();
//
//        TrajectorySequence parkCloseLeftRed = drive.trajectorySequenceBuilder(scoreCloseLeftRed.end())
//                .strafeRight(30)
//                .forward(12)
//                .build();;
//
//        TrajectorySequence propCloseMidRed = drive.trajectorySequenceBuilder(startPoseCloseRed)
//                .forward(28)
//                .lineToLinearHeading(new Pose2d(12, -62, 0))
//                .build();
//
//        TrajectorySequence scoreCloseMidRed = drive.trajectorySequenceBuilder(propCloseMidRed.end())
//                .forward(34)
//                .strafeLeft(25)
//                .build();
//
//        TrajectorySequence parkCloseMidRed = drive.trajectorySequenceBuilder(scoreCloseMidRed.end())
//                .strafeRight(25)
//                .forward(12)
//                .build();
//
//        TrajectorySequence propCloseRightRed = drive.trajectorySequenceBuilder(startPoseCloseRed)
//                .forward(25)
//                .turn(Math.toRadians(-90))
//                .forward(3)
//                .back(3)
//                .strafeRight(25)
//                .build();
//
//        TrajectorySequence scoreCloseRightRed = drive.trajectorySequenceBuilder(propCloseRightRed.end())
//                .forward(34)
//                .strafeLeft(20)
//                .build();
//
//        TrajectorySequence parkCloseRightRed = drive.trajectorySequenceBuilder(scoreCloseRightRed.end())
//                .strafeRight(20)
//                .forward(12)
//                .build();
//
//        // Spline trajectories
//
//        TrajectorySequence LeftSplines = drive.trajectorySequenceBuilder(startPoseCloseRed)
////                Prop
//                .splineToLinearHeading(new Pose2d(9, -30, Math.toRadians(180)), Math.toRadians(180))
////                Score
//                .back(5)
//                .splineToSplineHeading(new Pose2d(46, -32, Math.toRadians(0)), 0)
////                Park
//                .strafeRight(20)
//                .splineToLinearHeading(new Pose2d(60, -62, 0), 0)
//                .build();
//
//        TrajectorySequence RightSplines = drive.trajectorySequenceBuilder(startPoseCloseRed)
////                Prop
//                .lineToLinearHeading(new Pose2d(13, -37, Math.toRadians(0)))
////                Score
//                .back(3)
//                .strafeRight(10)
//                .splineToSplineHeading(new Pose2d(46, -42, Math.toRadians(0)), 0)
////                Park
//                .strafeRight(10)
//                .splineToLinearHeading(new Pose2d(60, -62, 0), 0)
//                .build();
//
//        TrajectorySequence MidSplines = drive.trajectorySequenceBuilder(startPoseCloseRed)
////                Prop
//                .forward(28)
////                Score
//                .back(3)
//                .splineToSplineHeading(new Pose2d(46, -37, Math.toRadians(0)), 0)
////                Park
//                .strafeRight(15)
//                .splineToLinearHeading(new Pose2d(60, -62, 0), 0)
//                .build();
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
//                prop = propCloseLeftRed;
//                score = scoreCloseLeftRed;
//                park = parkCloseLeftRed;
//                break;
//            case "MIDDLE":
//                prop = propCloseMidRed;
//                score = scoreCloseMidRed;
//                park = parkCloseMidRed;
//                break;
//            default:
//                prop = propCloseRightRed;
//                score = scoreCloseRightRed;
//                park = parkCloseRightRed;
//                break;
//        }
//
////        schedule(new SequentialCommandGroup ( //Makes the following code run one after another, like norma
////            new ParallelCommandGroup(
////                new SequentialCommandGroup(
////                    new TrajectorySequenceCommand(drive, propCloseMidRed),
////                    new TrajectorySequenceCommand(drive, scoreCloseMidRed),
////                    new TrajectorySequenceCommand(drive, parkCloseMidRed)
////                ),
////                new InstantCommand(() -> {
////                    new WaitCommand(4000);
////                    s.goToPosition(Slides.SlidePos.LOW);
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
//                new TrajectorySequenceCommand(drive, park)
//        ));
//    }
//}
