package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BTS extends SubsystemBase { //Ball Transfer System (BTS)
    MotorController mc_elevator;
    public BTS() {
        mc_elevator = new VictorSP(Constants.BTS.BTS_ROLLER_PWM);
    }
    public void setRoller(double speed) {
        mc_elevator.set(speed);
    }
    @Override
    public void periodic() {

    }
}
