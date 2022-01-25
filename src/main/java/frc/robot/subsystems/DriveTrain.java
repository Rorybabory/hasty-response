package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Encoder;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;


public class DriveTrain extends SubsystemBase
{
    //initialize speed controllers and their groups.
    private Field2d f_field;
    private MotorController sp_left1;
    private MotorController sp_left2;
    private MotorController sp_right1;
    private MotorController sp_right2;
    private MotorControllerGroup spg_left;
    private MotorControllerGroup spg_right;
    private DifferentialDrive dd_drive;
    private Encoder enc_Left, enc_Right;
    private Rotation2d r_rotation;
    public static final AHRS NAVX = new AHRS(SPI.Port.kMXP);
    boolean startingDrive = false; //used in Field Oriented Drive
    public boolean driveDir = true; // false = backwards true = forwards
    private DifferentialDriveOdometry o_odometry = new DifferentialDriveOdometry(new Rotation2d(0));
    public DriveTrain(boolean isCAN){
        if (isCAN) {
            sp_left1 = new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_LEFT1, MotorType.kBrushless);
            sp_left2 = new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_LEFT2, MotorType.kBrushless);
            sp_right1 = new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_RIGHT1, MotorType.kBrushless);
            sp_right2 = new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_RIGHT2, MotorType.kBrushless);
    
        }else {
            sp_left1 = new Talon(Constants.DriveTrain.DRIVE_PWM_LEFT1);
            sp_left2 = new Talon(Constants.DriveTrain.DRIVE_PWM_LEFT2);
            sp_right1 = new Talon(Constants.DriveTrain.DRIVE_PWM_RIGHT1);
            sp_right2 = new Talon(Constants.DriveTrain.DRIVE_PWM_RIGHT2);
        }
        f_field = new Field2d();
        spg_left = new MotorControllerGroup(sp_left1, sp_left2);
        spg_right = new MotorControllerGroup(sp_right1, sp_right2);
        dd_drive = new DifferentialDrive(spg_left, spg_right);
        enc_Left = new Encoder(Constants.DriveTrain.DRIVE_DIO_ENC_LEFT1, Constants.DriveTrain.DRIVE_DIO_ENC_LEFT2, false);
        enc_Right = new Encoder(Constants.DriveTrain.DRIVE_DIO_ENC_RIGHT1, Constants.DriveTrain.DRIVE_DIO_ENC_RIGHT2, false);
        enc_Left.setDistancePerPulse(Constants.DriveTrain.DRIVE_DISTANCE_PER_PULSE);
        enc_Right.setDistancePerPulse(Constants.DriveTrain.DRIVE_DISTANCE_PER_PULSE);
        enc_Left.reset();
        enc_Right.reset();
        NAVX.zeroYaw();
        r_rotation = NAVX.getRotation2d();

         //I think this has to be initiated after resetting encoders.
    }    
    public void arcadeDrive(double x, double y, double z){
       dd_drive.arcadeDrive(-x, (y+(z*.5))); 
       
    }
    public double getEncoderLeft(){
        return enc_Left.getDistance();
    }
    public double getEncoderRight(){
        return enc_Right.getDistance();
    }
    public void updateOdometry(){
        o_odometry.update(NAVX.getRotation2d(), getEncoderLeft(), getEncoderRight());
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
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      SmartDashboard.putNumber("Encoder Left", getEncoderLeft());
      SmartDashboard.putNumber("Encoder Right", getEncoderRight());
      System.out.println("enc left: " + getEncoderLeft() + " enc right: " + getEncoderRight());
      o_odometry.update(NAVX.getRotation2d(), getEncoderLeft(), getEncoderRight());
      SmartDashboard.putData("Field", f_field);
      f_field.setRobotPose(o_odometry.getPoseMeters());
      
    }
    
}
