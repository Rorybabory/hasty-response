
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hanger extends SubsystemBase {
 private MotorController motor1;
 private MotorController motor2;
  public Hanger() {
      motor1 = new CANSparkMax(Constants.Hanger.HANGER_CAN,MotorType.kBrushless);
      motor2 = new CANSparkMax(Constants.Hanger.HANGER_2_CAN,MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot(double speed){
      motor1.set(speed);
      motor2.set(-speed);
  }

  public void stopShoot(){
      motor1.set(0);
      motor2.set(0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}