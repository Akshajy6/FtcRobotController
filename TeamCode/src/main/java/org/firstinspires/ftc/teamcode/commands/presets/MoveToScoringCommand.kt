package org.firstinspires.ftc.teamcode.commands.presets

import android.transition.Slide
import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.commands.liftcommands.ProfiledLiftCommand
import org.firstinspires.ftc.teamcode.subsystems.Outtake
import org.firstinspires.ftc.teamcode.subsystems.Slides
import org.firstinspires.ftc.teamcode.subsystems.Slides.SlidePositions
import org.firstinspires.ftc.teamcode.subsystems.V4B

class MoveToScoringCommand(lift: Slides, v4b: V4B, outtake: Outtake, preset: Presets) : ParallelCommandGroup() {

    enum class Presets {
        SHORT,
        MID,
        HIGH
    }

    init {
        addCommands(
            ParallelCommandGroup(
                SequentialCommandGroup(
                    // Change this ms to change when the arm comes up
                    WaitCommand(800),
                    InstantCommand({
                        v4b.extend()
                    }),
                ),
                InstantCommand({
                    outtake.transport()
                }),
                when (preset) {
                    Presets.SHORT ->
                        ProfiledLiftCommand(lift, SlidePositions.SHORT.position, true)
                    Presets.MID ->
                        ProfiledLiftCommand(lift, SlidePositions.MID.position, true)
                    Presets.HIGH ->
                        ProfiledLiftCommand(lift, SlidePositions.HIGH.position, true)

                }
            )
        )
        addRequirements(lift)
    }
}