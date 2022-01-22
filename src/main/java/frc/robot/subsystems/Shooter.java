
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
 private MotorController flyWheel;
 private MotorController flyWheel2;
  public Shooter() {
      flyWheel = new CANSparkMax(Constants.Shooter.SHOOTER_CAN,MotorType.kBrushless);
      flyWheel2 = new CANSparkMax(Constants.Shooter.SHOOTER_2_CAN,MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot(double speed){
      flyWheel.set(speed);
      flyWheel2.set(-speed);
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