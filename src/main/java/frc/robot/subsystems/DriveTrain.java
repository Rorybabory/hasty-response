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

import java.util.ArrayList;

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
    // private Encoder enc_Left, enc_Right;
    public static final AHRS NAVX = new AHRS(SPI.Port.kMXP);
    private DifferentialDriveOdometry o_odometry = new DifferentialDriveOdometry(new Rotation2d(0));
    boolean isSpark = false;
    
    //an array of speed controller pointers for spark max specific code
    //public ArrayList<CANSparkMax> sparkMotors; //NOTE: ONLY ACCESS IF isSpark IS TRUE
    
    public DriveTrain(boolean isCAN){
        this.isSpark = isCAN;
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
        // enc_Left = new Encoder(Constants.DriveTrain.DRIVE_DIO_ENC_LEFT1, Constants.DriveTrain.DRIVE_DIO_ENC_LEFT2, false);
        // enc_Right = new Encoder(Constants.DriveTrain.DRIVE_DIO_ENC_RIGHT1, Constants.DriveTrain.DRIVE_DIO_ENC_RIGHT2, false);
        // enc_Left.setDistancePerPulse(Constants.DriveTrain.DRIVE_DISTANCE_PER_PULSE_LEFT);
        // enc_Right.setDistancePerPulse(Constants.DriveTrain.DRIVE_DISTANCE_PER_PULSE_RIGHT);
        // enc_Left.reset();
        // enc_Right.reset();
        NAVX.zeroYaw();
    }    
    public void arcadeDrive(double x, double y, double z){
      dd_drive.arcadeDrive(-x, (y+(z*.5))); 
      System.out.println("running arcade drive");
    }
    // public double getEncoderLeft(){
    //     if (isSpark) {
    //         return sparkMotors.get(0).getEncoder().getPosition();
    //     }else {
    //         System.out.println("CAUTION: ATTEMPTING TO ACCESS ENCODER ON TALON");
    //         return -1.;
    //     }
        
    // }
    // public double getEncoderRight(){
    //     if (isSpark) {
    //         return sparkMotors.get(2).getEncoder().getPosition();
    //     }else {
    //         System.out.println("CAUTION: ATTEMPTING TO ACCESS ENCODER ON TALON");
    //         return -1.;
    //     }
    // }
    // public void updateOdometry(){
    //     o_odometry.update(NAVX.getRotation2d(), getEncoderLeft(), getEncoderRight());
    // }
        
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    //   SmartDashboard.putNumber("Encoder Left", getEncoderLeft());
    //   SmartDashboard.putNumber("Encoder Right", getEncoderRight());
    //   System.out.println("enc left: " + getEncoderLeft() + " enc right: " + getEncoderRight());

    //   updateOdometry();
      SmartDashboard.putData("Field", f_field);
      f_field.setRobotPose(o_odometry.getPoseMeters());
    }
}
