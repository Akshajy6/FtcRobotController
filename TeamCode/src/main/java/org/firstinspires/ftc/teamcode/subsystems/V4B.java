package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class V4B extends SubsystemBase {
    // Enum states
    public enum V4BState {
        EXTEND(0.45),
        RETRACT(0.01);

        public double position;

        V4BState(double position) {
            this.position = position;
        }
    }

    // Motion profiling
    private ElapsedTime timer = new ElapsedTime();
    private double prevPositionTarget = V4BState.RETRACT.position; // retracted is the position initially
    private TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(2, 1);
    private TrapezoidProfile motionProfile = new TrapezoidProfile(
            constraints,
            new TrapezoidProfile.State(V4BState.RETRACT.position, 0),
            new TrapezoidProfile.State(V4BState.RETRACT.position, 0)
    );


    // Initial initialization
    Servo leftV4B, rightV4B;
    final double deadband = 0.05;
    public V4B(HardwareMap hardwareMap) {
        leftV4B = hardwareMap.get(ServoImplEx.class, "leftV4B"); // Left Side
        rightV4B = hardwareMap.get(ServoImplEx.class, "rightV4B"); // Right Side
    }

    // Overriden functions
    @Override
    public void periodic() {
        if(!motionProfile.isFinished(timer.seconds())) {
            // Read the current target for the profile
            double leftPosition = motionProfile.calculate(timer.seconds()).position;
            double rightPosition = motionProfile.calculate(timer.seconds()).position;

            // Set servo positions according to the profile
            leftV4B.setPosition(leftPosition);
//            rightV4B.setPosition(rightPosition);
        }
    }

    // Normal functions
    public void setMotionPosition(double target) {
        if(prevPositionTarget != target){
            motionProfile = new TrapezoidProfile(
                    constraints,
                    new TrapezoidProfile.State(target, 0),
                    new TrapezoidProfile.State(this.getPosition(), 0)
            );

            //Reset the timer
            timer.reset();
        }
        prevPositionTarget = target;
    }

    public void setPosition(V4BState target) {
        setPosition(target.position);
    }

    public void setPosition (double forced) {
        leftV4B.setPosition(forced);
        rightV4B.setPosition(forced);
    }

    public void extend() {
//        setMotionPosition(V4BState.EXTEND.position);
        setPosition(V4BState.EXTEND.position);
    }

    public void retract() {
//        setMotionPosition(V4BState.RETRACT.position);
        setPosition(V4BState.RETRACT.position);
    }

    public double getPosition() {
        return leftV4B.getPosition();
    }

    public void togglePower() {
        // If the arm is in the retract position
        if (Math.abs(getPosition() - V4BState.RETRACT.position) < deadband) {
            extend();
        } else {
            retract();
        }
    }
}
