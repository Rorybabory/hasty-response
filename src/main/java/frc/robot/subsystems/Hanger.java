
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hanger extends SubsystemBase {
 private CANSparkMax mc_motor1;
 private CANSparkMax mc_motor2;
 //private Servo hookServo;
  public Hanger() {
      //mc_motor1.getEncoder().setPosition(0.0);
      mc_motor1 = new CANSparkMax(Constants.Hanger.HANGER_CAN,MotorType.kBrushless);
      mc_motor2 = new CANSparkMax(Constants.Hanger.HANGER_CAN_2,MotorType.kBrushless);
      //hookServo = new Servo(Constants.Hanger.HANGER_SERVO_PWM);
      
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("hanger motor 1 encoder value", getEncoder());
  }

  public double getEncoder() {
    return mc_motor1.getEncoder().getPosition();
  }
  public void moveServo(double pos){
    //hookServo.setPosition(pos);
  }
  public void disableServo(){
    //hookServo.setDisabled();
  }
  public void move(double speed){
    mc_motor1.set(speed);
    mc_motor2.set(-speed);
  }

  public void stopMove(){
    mc_motor1.set(0);
    mc_motor2.set(0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}