
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.motorcontrol.Talon;


public class Shooter extends SubsystemBase {
  private MotorController flyWheel;
  private MotorController flyWheel2;

  private Servo servo;
  
  public Shooter() {
    servo = new Servo(Constants.Shooter.SERVO_PWM);
    flyWheel = new Talon(Constants.Shooter.SHOOTER_PWM_0);
    flyWheel2 = new Talon(Constants.Shooter.SHOOTER_PWM_1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setServoPosition(double value) { //value is 0 to 1
    servo.set(value);
  }
  public void shoot(double speed){
      flyWheel.set(speed);
      flyWheel2.set(-speed);
      System.out.println("shooting");
  }

  public void stopShoot(){
      flyWheel.set(0);
      flyWheel2.set(0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}