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
  boolean setServoPos = false;
  public Shoot(Shooter shooter, Intake intake, BTS bts) {
    m_shooter = shooter;
    m_intake = intake;
    m_bts = bts;
    timer = new Timer();
  }


  @Override
  public void initialize() {
    timer.start();
    setServoPos = false;
  }
  public void runShoot() { //seperated to reduce reduncency with auto.
    if (timer.hasElapsed(2.0)) {
      m_intake.enableDoghouse();
      m_bts.setRoller(Constants.BTS.BTS_SPEED);
    }
    System.out.println("time running command is: " + timer.get());
    System.out.println("shooting");
    
    double distance = m_shooter.getDistance(); //inches
    if (!m_shooter.isOverridden && setServoPos == false) {
      if (distance < 105) {
        m_shooter.setServoPosition(0.8);
        speed = 0.66;
        System.out.println("under 105");
        //setServoPos = true;
      }else if (distance > 300) {
        m_shooter.setServoPosition(0.2);
        speed = 1.0;
        System.out.println("over 300");
        //setServoPos = true;
      }else {
        System.out.println("neither");
        double percent = (distance-100.0)/200.0;
        speed = (percent*(1.0/3.0)) + (2.0/3.0)+0.05;
        m_shooter.setServoPosition(-(percent*.6)+.8);
      }

    }

    m_shooter.shoot(speed);

  }

  @Override
  public void execute() {
    runShoot();
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

