package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoMove  extends CommandBase{
    private DriveTrain drive;
    private double speed;
    private double time;
    private Timer timer;
    public AutoMove(DriveTrain dt, double speed, double time){
        addRequirements(dt);
        drive = dt;
        this.speed = speed;
        this.time = time;
        timer = new Timer();
    }
    @Override
    public void initialize(){
        System.out.println("initializing autonomous");
        timer.reset();
        timer.start();
    }
    public void execute (){
        
        drive.arcadeDrive(0, speed, .0);
    }
    @Override
    public void end(boolean interrupted) {
      drive.arcadeDrive(0,0,0);
    }
    @Override
  public boolean isFinished() {
    return (timer.get() > time);
  }
} 

