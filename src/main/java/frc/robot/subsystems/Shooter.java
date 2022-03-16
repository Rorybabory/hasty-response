
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Shooter extends SubsystemBase {
  private PWMTalonFX mc_flyWheel;
  private PWMTalonFX mc_flyWheel2;
  private Servo sv_servo_l;
  private Servo sv_servo_r;
  private NetworkTable nt_table;

  public Shooter() {
    sv_servo_l = new Servo(Constants.Shooter.SERVO_PWM);
    sv_servo_r = new Servo(Constants.Shooter.SERVO_PWM_2);

    mc_flyWheel = new PWMTalonFX(Constants.Shooter.SHOOTER_PWM_0);
    mc_flyWheel2 = new PWMTalonFX(Constants.Shooter.SHOOTER_PWM_1);
    nt_table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Target Distance", getDistance());
    // This method will be called once per scheduler run
  }
  public void setServoPosition(double value) { //value is 0 to 1
    sv_servo_l.set(value);
    sv_servo_r.set(value);

  }
  public void shoot(double speed){
    mc_flyWheel.set(speed);
    mc_flyWheel2.set(-speed);
    System.out.println("shooting");
  }

  public void stopShoot(){
    mc_flyWheel.set(0);
    mc_flyWheel2.set(0);
  }
  public void getEncoderDifference(){
    
  }
  public double getDistance() {
    NetworkTableEntry ty = nt_table.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);

    double angleToGoalDegrees = Constants.Vision.VISION_CAMERA_ANGLE + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (Math.PI/180.0);

    double distanceFromLimelightToGoalInches = (Constants.Vision.VISION_TARGET_HEIGHT-Constants.Vision.VISION_CAMERA_HEIGHT)/Math.tan(angleToGoalRadians);

    return distanceFromLimelightToGoalInches;
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
