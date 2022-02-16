package frc.robot.commands;


import frc.robot.subsystems.Shooter;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Shoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter s;
  private double speed;

 
  public Shoot(Shooter subsystem, double sp) {
    s = subsystem;
    speed = sp;
    addRequirements(subsystem);
  }


  @Override
  public void initialize() {

  }


  @Override
  public void execute() {
    System.out.println("shooting");
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

