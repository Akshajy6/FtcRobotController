//package org.firstinspires.ftc.teamcode.subsystems;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//public class SlidePID extends CommandBase {
//    private Impasta impasta;
//    private double target;
//
//    double tolerance = 25;
//
//    public SlidePID(Impasta slides, double target) {
//        this.impasta = slides;
//        this.target = target;
//    }
//
//    @Override
//    public void initialize() {}
//
//    @Override
//    public void execute(){
//        impasta.runPID(target);
//    }
//
//    @Override
//    public boolean isFinished() {
//        return Math.abs(impasta.getSlidesPos() - target) < tolerance;
//    }
//
//    @Override
//    public void end (boolean interrupted) {
//        impasta.runManual(0, false);
//    }
//}
