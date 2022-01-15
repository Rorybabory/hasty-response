package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    boolean startingDrive = false; //used in Field Oriented Drive
    public boolean driveDir = true; // false = backwards true = forwards
    public DriveTrain(){
        sp_left1 = new Spark(Constants.DriveTrain.DRIVE_PWM_LEFT1);
        sp_left2 = new Spark(Constants.DriveTrain.DRIVE_PWM_LEFT2);
        sp_right1 = new Spark(Constants.DriveTrain.DRIVE_PWM_RIGHT1);
        sp_right2 = new Spark(Constants.DriveTrain.DRIVE_PWM_RIGHT2);
        spg_left = new MotorControllerGroup(sp_left1, sp_left2);
        spg_right = new MotorControllerGroup(sp_right1, sp_right2);
        dd_drive = new DifferentialDrive(spg_left, spg_right);

        NAVX.zeroYaw();
    }    
    public void arcadeDrive(double x, double y, double z){
       dd_drive.arcadeDrive(-x, (y+(z*.5))); 
    }
    public void fieldOrientedDrive(double joystickAngle, double x, double y) {
        double turn_speed = 0.7;
        double strength = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        double turn_val = 0.0;
        double drive_val = 0.5;
        System.out.println("NAVX: " + NAVX.getAngle() + " Joystick: " + joystickAngle);

        if (strength > 0.1) {
            if (joystickAngle > NAVX.getAngle() + 4 && startingDrive == false) {
                turn_val = turn_speed;
            }else if (joystickAngle < NAVX.getAngle() - 4 && startingDrive == false) {
                turn_val = -turn_speed;
            }else {
                startingDrive = true;
            }
            if (!driveDir) {
                strength = -strength;
            }
            dd_drive.arcadeDrive(turn_val, strength);
        }else {
            startingDrive = false;
            dd_drive.arcadeDrive(0, 0);
        }
        
    }
    
}
