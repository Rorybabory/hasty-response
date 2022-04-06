package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoTurnUntilTarget extends CommandBase {
    private DriveTrain drive;
    NetworkTable nt;
    NetworkTableEntry nte_tv;
    public AutoTurnUntilTarget(DriveTrain dt){
        addRequirements(dt);
        drive = dt;
        nt = NetworkTableInstance.getDefault().getTable("limelight");
        nte_tv = nt.getEntry("tv");
    }
    @Override
    public void initialize(){
        System.out.println("initializing autonomous");
    }
    public void execute (){
        drive.arcadeDrive(0.2, 0., 0.);
    }
    @Override
    public void end(boolean interrupted) {
      drive.arcadeDrive(0,0,0);
    }
    @Override
    public boolean isFinished() {
        return nte_tv.getBoolean(false);
    }
}
