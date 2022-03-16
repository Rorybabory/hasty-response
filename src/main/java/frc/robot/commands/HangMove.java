package frc.robot.commands;


// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hanger;

/** An example command that uses an example subsystem. */
public class HangMove extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Hanger s;
  private double speed;
  boolean stop; //artificially stops once it reaches top.
 
  public HangMove(Hanger subsystem, double sp) {
    s = subsystem;
    speed = sp;
    stop = false;
    addRequirements(subsystem);
  }


  @Override
  public void initialize() {

  }


  @Override
  public void execute() {
    s.move(speed);
    // if (speed > 0) { //if going up
    //   if (s.getEncoder() < Constants.Hanger.HANGER_TOP) {
    //     stop = true;
    //     System.out.println("hanger stop");
    //     s.stopMove();
    //   }
    // }else if (speed < 0) { // if going down
    //   if (s.getEncoder() > Constants.Hanger.HANGER_BOTTOM) {
    //     stop = true;
    //     System.out.println("hanger stop");
    //     s.stopMove();
    //   }
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      s.stopMove();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}

