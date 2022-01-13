package frc.robot.commands;

import edu.wpi.first.wpilibj.TimesliceRobot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutonomousThingy  extends CommandBase{
    private DriveTrain drive;
    public AutonomousThingy(DriveTrain dt){
        addRequirements(dt);
        drive = dt;
    }
    @Override
    public void initialize(){
        System.out.println("Doing a thingy");
    }
    public void execute (){
        drive.arcadeDrive(0, .1, .0);
    }
}

