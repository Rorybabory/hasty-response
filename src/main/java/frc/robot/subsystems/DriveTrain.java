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
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;

import java.sql.Driver;
import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
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
    private NetworkTable nt_sptable;
    private NetworkTableEntry[] nte_sparkErrors;
    private NetworkTableEntry[] nte_sparkFaults;
    
    // private Encoder enc_Left, enc_Right;
    public static final AHRS NAVX = new AHRS(SPI.Port.kMXP);
    private DifferentialDriveOdometry o_odometry = new DifferentialDriveOdometry(new Rotation2d(0));
    boolean isSpark = false;
    
    //an array of speed controller pointers for spark max specific code
    public ArrayList<CANSparkMax> sparkMotors; //NOTE: ONLY ACCESS IF isSpark IS TRUE

    public DriveTrain(boolean isCAN){
        this.isSpark = isCAN;
        if (isCAN) {
            sparkMotors.add(new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_LEFT1, MotorType.kBrushless));
            sparkMotors.add(new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_LEFT1, MotorType.kBrushless));
            sparkMotors.add(new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_LEFT1, MotorType.kBrushless));
            sparkMotors.add(new CANSparkMax(Constants.DriveTrain.DRIVE_CAN_LEFT1, MotorType.kBrushless));
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
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        nt_sptable = inst.getTable("SparkMAX Debugging");
        nte_sparkErrors = new NetworkTableEntry[4];
        nte_sparkFaults = new NetworkTableEntry[4];
        for(int i = 0; i<4; i++){
            nte_sparkFaults[i] = nt_sptable.getEntry("Fault " + i);
            nte_sparkErrors[i] = nt_sptable.getEntry("Error " + i);
        }
       // nt_sptable = new NetworkTable(null, null)
        

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
    }
    public String getStartingAlliance(){
        return DriverStation.getAlliance().toString();
    }
    public int getStartingPosition(){
        return DriverStation.getLocation();
    }
    public void setOdometryPosition(){
        int index = getStartingPosition() - 1;
        if(getStartingAlliance().equals("Red")){
            index *=2;
        }
        o_odometry.resetPosition(Constants.DriveTrain.DRIVE_STARTING_LOCATIONS[index], new Rotation2d()); 
    }

    public double getEncoderLeft(){
        if (isSpark) {
            return sparkMotors.get(0).getEncoder().getPosition();
        }else {
            System.out.println("CAUTION: ATTEMPTING TO ACCESS ENCODER ON TALON");
            return -1.;
        }
        
    }
    public double getEncoderRight(){
        if (isSpark) {
            return sparkMotors.get(2).getEncoder().getPosition();
        }else {
            System.out.println("CAUTION: ATTEMPTING TO ACCESS ENCODER ON TALON");
            return -1.;
        }
    }
    public void updateOdometry(){
        o_odometry.update(NAVX.getRotation2d(), getEncoderLeft(), getEncoderRight());
    }
    public void outputErrors(){
        if(isSpark){
            for(int i = 0; i < 4; i++){
                short error = sparkMotors.get(i).getFaults();
                SmartDashboard.putString("SparkMAX "+ i + "error", sparkMotors.get(i).getLastError().toString());
                nte_sparkErrors[i].setString(sparkMotors.get(i).getLastError().toString());
                for(int x = 0; x < 16; x++){
                    short bitmask = (short) Math.pow(2, x);
                    short result = (short) (error & bitmask);
                    if(result >0){
                        SmartDashboard.putString("SparkMAX "+ i + " fault", Constants.DriveTrain.DRIVE_SPARK_ERRORS[x]);
                        nte_sparkFaults[x].setString(Constants.DriveTrain.DRIVE_SPARK_ERRORS[x]);
                    }
                }
            
            }
        }
    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      SmartDashboard.putNumber("Encoder Left", getEncoderLeft());
      SmartDashboard.putNumber("Encoder Right", getEncoderRight());
      System.out.println("enc left: " + getEncoderLeft() + " enc right: " + getEncoderRight());
      updateOdometry();
      SmartDashboard.putData("Field", f_field);
      f_field.setRobotPose(o_odometry.getPoseMeters());
      outputErrors();
      
    }
    
}
