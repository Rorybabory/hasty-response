package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI;


public class DriveTrain extends SubsystemBase
{
    //initialize speed controllers and their groups.
    private MotorController sp_left1;
    private MotorController sp_left2;
    private MotorController sp_right1;
    private MotorController sp_right2;
    private MotorControllerGroup spg_left;
    private MotorControllerGroup spg_right;
    private DifferentialDrive dd_drive;
    public static final AHRS NAVX = new AHRS(SPI.Port.kMXP);

    public DriveTrain(){
        sp_left1 = new Spark(Constants.DriveTrain.DRIVE_PWM_LEFT1);
        sp_left2 = new Spark(Constants.DriveTrain.DRIVE_PWM_LEFT2);
        sp_right1 = new Spark(Constants.DriveTrain.DRIVE_PWM_RIGHT1);
        sp_right2 = new Spark(Constants.DriveTrain.DRIVE_PWM_RIGHT2);
        spg_left = new MotorControllerGroup(sp_left1, sp_left2);
        spg_right = new MotorControllerGroup(sp_right1, sp_right2);
        dd_drive = new DifferentialDrive(spg_left, spg_right);

        NAVX.reset();
    }    
    public void arcadeDrive(double x, double y, double z){
       dd_drive.arcadeDrive(-x, (y+(z*.5))); 
    }
    public void fieldOrientedDrive(double joystickAngle, double x, double y) {
        double strength = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        if (strength > 0.1) {
            if (joystickAngle + 4 > NAVX.getAngle()) {
                dd_drive.arcadeDrive(0, -0.5); 
            }else if (joystickAngle - 4 < NAVX.getAngle()) {
                dd_drive.arcadeDrive(0, 0.5); 
            }else {
                dd_drive.arcadeDrive(0.75, 0);
            }
        }
    }
    
}
