package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Door extends SubsystemBase{
    //private DoubleSolenoid solenoid;


    public Door(){
        //solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Pneumatics.SOLENOID_DIO_1, Constants.Pneumatics.SOLENOID_DIO_2);

    }
    public void moveDoor(){
        //solenoid.toggle();
    }
}
