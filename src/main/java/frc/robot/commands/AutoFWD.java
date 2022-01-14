package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

public class AutoFWD extends SequentialCommandGroup{
    public AutoFWD (DriveTrain drt){
        addCommands(new AutonomousThingy(drt));
    }    
}
