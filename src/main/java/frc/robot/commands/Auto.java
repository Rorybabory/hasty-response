package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BTS;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class Auto extends SequentialCommandGroup {
    public Auto(DriveTrain drive, Shooter shooter, Intake intake, BTS bts) {
        addCommands(new AutoIntakeOpen(intake),
                    new AutoForwardIntake(drive, intake, 3.0/*value must change later*/),
                    new AutoMove(drive, 0.5, 1.0),
                    new AutoTurnUntilTarget(drive),
                    new PivotToTarget(shooter, drive)
    }
}
