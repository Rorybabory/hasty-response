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
  private double speed1;
  private double oldSpeed;
  protected Timer time;
  boolean setServoPos = false;
  public Shoot(Shooter shooter, Intake intake, BTS bts) {
    m_shooter = shooter;
    m_intake = intake;
    m_bts = bts;
    time = new Timer();
  }

  @Override
  public void initialize() {
    time.start();
    setServoPos = false;
  }
  
  public void runShoot() { //seperated to reduce reduncency with auto.
    if (time.hasElapsed(1.0)) {
      m_intake.enableDoghouse();
      m_bts.setRoller(Constants.BTS.BTS_SPEED);
    }
    System.out.println("time running command is: " + time.get());
    System.out.println("shooting");
    
    double distance = m_shooter.getDistance(); //inches
    // if (!m_shooter.isOverridden && setServoPos == false) {
      // if (distance < 105) {
      //   m_shooter.setServoPosition(0.8);
      //   speed = 0.7;
      //   System.out.println("under 105");
      //   //setServoPos = true;
      // }else if (distance > 300) {
      //   m_shooter.setServoPosition(0.2);
      //   speed = 1.0;
      //   System.out.println("over 300");
      //   //setServoPos = true;
      // }else {
       // System.out.println("neither");
        double percent = (distance-100.0)/200.0;
        oldSpeed = (percent*(1.0/3.0)) + (2.0/3.0)+.03;
        double s = (((Math.sqrt(19.6*(distance*0.0254)))/34.22)*2);
        speed1 = s;
        double a = Math.atan(6/(distance*0.0254));
        
        
        double j = 8.75*Math.sin(a);
        double k = 8.75*Math.cos(a);
        double f = (10-k);
        double o = Math.sqrt(Math.pow(f,2)+Math.pow(j,2));
        double e = (25.4*(o-9)*0.0164); 
        //e =  Double.parseDouble(String.format("%.2d", e));
       
       
       
       
       
       
       
        if(e>.17 && e<.82) {
          m_shooter.setServoPosition(e);
          System.out.println("good");
          //System.out.println("a= " + a + "\n" + "j= " + j + "\n" + "l=  " + l + "\n" + "o= " + o + "\n" + "e= " + e + "\n");
        }
        else if(e<.17)
        {
          m_shooter.setServoPosition(.17);
          System.out.println("Out Of Range");
          speed1=oldSpeed;

        }
        else if(e>.82){
          m_shooter.setServoPosition(.82);
          System.out.println("TOO CLOSE, use your eyes");
          speed1=oldSpeed;
        }
        else {
          System.out.println("Something impossible happened. Go buy a lottery ticket!");
        }
      

    

    
   // System.out.println("speed: " + speed1);
// change parameter to speed1
    System.out.println(speed1);

    // change parameter to speed1
    m_shooter.shoot(speed1);
    }
  
  public void stopAll() {
    m_shooter.stopShoot();
    m_intake.disableDoghouse();
    m_bts.setRoller(0.0);
    time.stop();
    time.reset();

  }
  @Override
  public void execute() {
    runShoot();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stopAll();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

