package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Door extends SubsystemBase{
   //  private DoubleSolenoid solenoid;


    public Door(){
   //   solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Pneumatics.SOLENOID_PCM_1, Constants.Pneumatics.SOLENOID_PCM_2);

    }
    public void openDoor() {
      //  solenoid.set(Value.kForward);
    }
    public void closeDoor() {
      //  solenoid.set(Value.kReverse);
    }
    public void disableDoor() {
      //  solenoid.set(Value.kOff);
    }
    
    public void moveDoor(){
      //  solenoid.toggle();
    }
}
