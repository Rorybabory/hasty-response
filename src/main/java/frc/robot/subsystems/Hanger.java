
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hanger extends SubsystemBase {
  private DoubleSolenoid ds_solenoid_1;
  private DoubleSolenoid ds_solenoid_2;
  //private Servo hookServo;
  public Hanger() {
      ds_solenoid_1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Hanger.SOLINOID_1_1, Constants.Hanger.SOLINOID_1_2);
      ds_solenoid_2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Hanger.SOLINOID_2_1, Constants.Hanger.SOLINOID_2_2);
      //mc_motor1.getEncoder().setPosition(0.0);
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
  public void moveUp() {
    ds_solenoid_1.set(Value.kForward);
    ds_solenoid_2.set(Value.kForward);
  }
  public void moveDown() {
    ds_solenoid_1.set(Value.kReverse);
    ds_solenoid_2.set(Value.kReverse);
  }
  public void stop() {
    ds_solenoid_1.set(Value.kOff);
    ds_solenoid_2.set(Value.kOff);
  }


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}