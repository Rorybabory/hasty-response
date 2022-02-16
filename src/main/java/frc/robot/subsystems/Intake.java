package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private MotorController mc_roller;
    private DoubleSolenoid ds_extender;
    public Intake(){
        mc_roller = new CANSparkMax(Constants.Intake.INTAKE_SPARK_CAN, MotorType.kBrushless);
        ds_extender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Intake.INTAKE_SOLENOID_PCM, Constants.Intake.INTAKE_SOLENOID_PCM_2);
    }
    public void extendIntake()
    {
        ds_extender.set(Value.kForward);
    }
    public void retractIntake()
    {
        ds_extender.set(Value.kReverse);
    }
    public void enableMotor(){
        mc_roller.set(Constants.Intake.INTAKE_SPEED);
        System.out.println("running mc roller");
    }
    public void disableMotor(){
        mc_roller.disable();
    }
    public void disablePneumatics(){
        ds_extender.set(Value.kOff);
    }
}
