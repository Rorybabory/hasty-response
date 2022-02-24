
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hanger extends SubsystemBase {
 private MotorController mc_motor1;
 private MotorController mc_motor2;
 //private Servo hookServo;
  public Hanger() {
      mc_motor1 = new CANSparkMax(Constants.Hanger.HANGER_CAN,MotorType.kBrushless);
      mc_motor2 = new CANSparkMax(Constants.Hanger.HANGER_CAN_2,MotorType.kBrushless);
      //hookServo = new Servo(Constants.Hanger.HANGER_SERVO_PWM);
      
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void moveServo(double pos){
    //hookServo.setPosition(pos);
  }
  public void disableServo(){
    //hookServo.setDisabled();
  }
  public void shoot(double speed){
    mc_motor1.set(speed);
    mc_motor2.set(-speed);
  }

  public void stopShoot(){
    mc_motor1.set(0);
    mc_motor2.set(0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}