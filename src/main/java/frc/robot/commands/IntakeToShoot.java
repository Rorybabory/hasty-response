package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BTS;
import frc.robot.subsystems.Intake;

public class IntakeToShoot extends CommandBase {
  private final Intake m_intake;
  private final BTS m_bts;
 
  public IntakeToShoot(Intake intake, BTS bts) {
    m_intake = intake;
    m_bts = bts;
    // addRequirements(shooter);
  }


  @Override
  public void initialize() {

  }


  @Override
  public void execute() {
    System.out.println("IntakeToShoot");
    m_bts.setRoller(Constants.BTS.BTS_SPEED);
    m_intake.enableMotor();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_intake.disableMotor();
      m_bts.setRoller(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
