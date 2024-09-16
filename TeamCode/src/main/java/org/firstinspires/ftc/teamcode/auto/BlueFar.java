//package org.firstinspires.ftc.teamcode.auto;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
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
//import org.firstinspires.ftc.teamcode.subsystems.Slides;
//import org.firstinspires.ftc.teamcode.subsystems.V4B;
//import org.firstinspires.ftc.teamcode.vision.trajectorysequence.TrajectorySequence;
//
//import java.util.concurrent.TimeUnit;
//
//@Autonomous(name="Blue Far",group="Far")
//public class BlueFar extends CommandOpMode {
//    //Add Motors and servos not for drivebase here
//    SampleMecanumDrive drive;
//    Slides s;
//    Outtake o;
//    V4B v4b;
//    int backBoard = 12;
//    Gamepad gamepad;
//    private final int READ_PERIOD = 1;
//    private HuskyLens huskyLens;
//
//    TrajectorySequence prop;
//    TrajectorySequence score;
//    TrajectorySequence park;
//
//    public static Pose2d startPoseFarBlue = new Pose2d(-35,62, Math.toRadians(-90));
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
//        drive.setPoseEstimate(startPoseFarBlue);
//
//
//        TrajectorySequence propFarMidBlue = drive.trajectorySequenceBuilder(startPoseFarBlue)
//                .lineToLinearHeading(new Pose2d(-35.6, 32, Math.toRadians(-90)))
//                .back(6)
//                .strafeRight(15)
//                .lineToLinearHeading(new Pose2d(-50.6, 14, Math.toRadians(0)))
//                .build();
//
//        TrajectorySequence propFarRightBlue =  drive.trajectorySequenceBuilder(startPoseFarBlue)
//                .forward(25)
//                .lineToLinearHeading(new Pose2d(-36.6, 30.5, Math.toRadians(180)))
//                .build();
//
//        TrajectorySequence propFarLeftBlue = drive.trajectorySequenceBuilder(startPoseFarBlue)
//                .forward(25)
//                .lineToLinearHeading(new Pose2d(-32.6, 30.5, Math.toRadians(0)))
//                .back(5)
//                .lineToLinearHeading(new Pose2d(-32.6, 30.5, Math.toRadians(0)))
//                .build();
//
//
//        TrajectorySequence scoreFarLeftBlue = drive.trajectorySequenceBuilder(propFarMidBlue.end())
//                .back(3)
//                .lineToLinearHeading(new Pose2d(-34, 14, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(46, 14, Math.toRadians(0)))
//                .strafeLeft(25)
//               .build();
//        TrajectorySequence scoreFarMidBlue = drive.trajectorySequenceBuilder(propFarMidBlue.end())
//                .lineToLinearHeading(new Pose2d(46, 14, Math.toRadians(0)))
//                .strafeLeft(20)
//                .build();
//         TrajectorySequence scoreFarRightBlue = drive.trajectorySequenceBuilder(propFarRightBlue.end())
//                 .back(3)
//                 .lineToLinearHeading(new Pose2d(-34, 14, Math.toRadians(0)))
//                 .lineToLinearHeading(new Pose2d(46, 14, Math.toRadians(0)))
//                 .strafeLeft(14)
//                .build();
//
//        TrajectorySequence parkFarLeftBlue = drive.trajectorySequenceBuilder(scoreFarLeftBlue.end())
//                .strafeRight(25)
//                .forward(14)
//                .build();
//
//        TrajectorySequence parkFarMidBlue = drive.trajectorySequenceBuilder(scoreFarMidBlue.end())
//                .strafeRight(20)
//                .forward(14)
//                .build();
//        TrajectorySequence parkFarRightBlue = drive.trajectorySequenceBuilder(scoreFarMidBlue.end())
//                .strafeRight(14)
//                .forward(14)
//                .build();
//
////        Splines
//
//        TrajectorySequence leftSplines = drive.trajectorySequenceBuilder(startPoseFarBlue)
////                Prop
//                .lineToLinearHeading(new Pose2d(-37, 30, Math.toRadians(180)))
////                Score
//                .lineToLinearHeading(new Pose2d(-34, 14, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(35, 14, Math.toRadians(0)))
//                .splineToConstantHeading(new Vector2d(46, 28), Math.toRadians(0))
////                Park
//                .strafeRight(5)
//                .splineToConstantHeading(new Vector2d(60, 11), Math.toRadians(0))
//                .build();
//
//        TrajectorySequence rightSplines = drive.trajectorySequenceBuilder(startPoseFarBlue)
////                Prop
//                .splineToLinearHeading(new Pose2d(-33, -30, Math.toRadians(0)), 0)
////                Score
//                .back(5)
//                .lineToLinearHeading(new Pose2d(-34, -14, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(35, -14, Math.toRadians(0)))
//                .splineToConstantHeading(new Vector2d(46, -39), Math.toRadians(0))
////                Park
//                .strafeLeft(10)
//                .splineToConstantHeading(new Vector2d(60, -11), Math.toRadians(0))
//                .build();
//
//        TrajectorySequence midSplines = drive.trajectorySequenceBuilder(startPoseFarBlue)
////                Prop
//                .splineToLinearHeading(new Pose2d(-52, 48, Math.toRadians(-90)), Math.toRadians(-90))
//                .splineToLinearHeading(new Pose2d(-39, 22, Math.toRadians(0)), 0)
////                Score
//                .back(5)
//                .splineToLinearHeading(new Pose2d(-39, 14, Math.toRadians(0)), 0)
//                .lineToLinearHeading(new Pose2d(35, 14, Math.toRadians(0)))
//                .splineToConstantHeading(new Vector2d(46, 34), Math.toRadians(0))
////                Park
//                .strafeRight(10)
//                .splineToConstantHeading(new Vector2d(60, 11), Math.toRadians(0))
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
//                    if (blocks[i].id == 1) { //If blue, then detect
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
//                prop = propFarLeftBlue;
//                score = scoreFarLeftBlue;
//                park = parkFarLeftBlue;
//                break;
//            case "MIDDLE":
//                prop = propFarMidBlue;
//                score = scoreFarMidBlue;
//                park = parkFarMidBlue;
//                break;
//            default:
//                prop = propFarRightBlue;
//                score = scoreFarRightBlue;
//                park = parkFarRightBlue;
//                break;
//        }
//
////        schedule(new SequentialCommandGroup ( //Makes the following code run one after another, like norma
////            new ParallelCommandGroup(
////                new SequentialCommandGroup(
////                    new TrajectorySequenceCommand(drive, propFarMidBlue),
////                    new TrajectorySequenceCommand(drive, scoreFarMidBlue),
////                    new TrajectorySequenceCommand(drive, parkFarMidBlue)
////                ),
////                new InstantCommand(() -> {
////                    new WaitCommand(4000);
////                    s.goToPosition(Slides.SlidePos.LOW);
//////                    new WaitCommand(2000);
//////                    v4b.togglePower();
//////                    new WaitCommand(2000);
//////                    o.open();
//////                    new WaitCommand(2000);
//////                    o.Close();
//////                    v4b.togglePower();
//////                    new WaitCommand(1000);
//////                    s.goToPosition(Slides.SlidePos.DOWN);
////                })
////            )
////        ));
//
//        schedule(new SequentialCommandGroup(
//              new TrajectorySequenceCommand(drive, prop),
//              new TrajectorySequenceCommand(drive, score),
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
//               new TrajectorySequenceCommand(drive, park)
//        ));
//    }
//}
