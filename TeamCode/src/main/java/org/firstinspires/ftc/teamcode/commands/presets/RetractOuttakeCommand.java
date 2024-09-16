package org.firstinspires.ftc.teamcode.commands.presets;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.liftcommands.LiftPositionCommand;
import org.firstinspires.ftc.teamcode.commands.liftcommands.ProfiledLiftCommand;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.Slides;
import org.firstinspires.ftc.teamcode.subsystems.V4B;

//Brings everything to rest position
public class RetractOuttakeCommand extends ParallelCommandGroup {

    public RetractOuttakeCommand(Slides lift, V4B v4b, Outtake outtake){
        addCommands(
            new InstantCommand(outtake::transport),
            new InstantCommand(v4b::retract),
            new SequentialCommandGroup(
                    new WaitCommand(800),
                    new LiftPositionCommand(lift, Slides.SlidePositions.DOWN.position, false)
            )
//            new InstantCommand(outtake::open)
        );

        addRequirements(lift);
    }

}