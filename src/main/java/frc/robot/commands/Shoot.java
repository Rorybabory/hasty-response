package frc.robot.commands;


import frc.robot.Constants;
import frc.robot.subsystems.BTS;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Shoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  private final Intake m_intake;
  private final BTS m_bts;
  private double speed;
  private Timer timer;
 
  public Shoot(Shooter shooter, Intake intake, BTS bts, double sp) {
    m_shooter = shooter;
    m_intake = intake;
    m_bts = bts;
    speed = sp;
    timer = new Timer();
  }


  @Override
  public void initialize() {
    timer.start();
  }


  @Override
  public void execute() {
    if (timer.hasElapsed(1.25)) {
      m_intake.enableDoghouse();
      m_bts.setRoller(Constants.BTS.BTS_SPEED);
    }
    System.out.println("time running command is: " + timer.get());
    System.out.println("shooting");
    
    double distance = m_shooter.getDistance(); //inches
    if (distance < 105) {
      speed = 0.66;
      m_shooter.setServoPosition(0.82);
    }else {
      speed = 1.0;
      m_shooter.setServoPosition(0.17);
    }

    m_shooter.shoot(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_shooter.stopShoot();
      m_intake.disableDoghouse();
      m_bts.setRoller(0.0);
      timer.stop();
      timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

