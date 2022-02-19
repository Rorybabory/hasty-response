package frc.robot.commands;


// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hanger;

/** An example command that uses an example subsystem. */
public class HangMove extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Hanger s;
  private double speed;

 
  public HangMove(Hanger subsystem, double sp) {
    s = subsystem;
    speed = sp;
    addRequirements(subsystem);
  }


  @Override
  public void initialize() {

  }


  @Override
  public void execute() {
    System.out.println("hanger move");
    s.shoot(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      s.stopShoot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

