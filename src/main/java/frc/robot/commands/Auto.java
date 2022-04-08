package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BTS;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class Auto extends SequentialCommandGroup {
    public Auto(DriveTrain drive, Shooter shooter, Intake intake, BTS bts) {
        Auto2(drive, shooter, intake, bts);
    }
    private void Auto1(DriveTrain drive, Shooter shooter, Intake intake, BTS bts) { //Uses limelight to lock onto target: shoots two balls
        addCommands(new AutoIntakeOpen(intake),
            new AutoForwardIntake(drive, intake, 0.5/*value must change later*/),
            new AutoMove(drive, -0.4, 0.5),
            new AutoTurnUntilTarget(drive, shooter),
            new PivotToTarget(shooter, drive),
            new ShootConstant(shooter, intake, bts));
    }
    private void Auto2(DriveTrain drive, Shooter shooter, Intake intake, BTS bts) { //Turns 180 degrees with the navx to look at the target: shoots two balls
        addCommands(new AutoIntakeOpen(intake),
            new AutoForwardIntake(drive, intake, 0.5/*value must change later*/),
            new AutoMove(drive, -0.4, 0.5),
            new Turn180(drive),
            new ShootConstant(shooter, intake, bts));
    }
    private void Auto3(DriveTrain drive, Shooter shooter, Intake intake, BTS bts) { //just moves back a bit and shoots, only use as backup if other two fail: shoots one ball
        addCommands(
            new AutoMove(drive, -0.4, 0.5),
            new ShootConstant(shooter, intake, bts));
    }

}
