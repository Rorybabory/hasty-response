// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoFWD;
import frc.robot.commands.Drive;
import frc.robot.commands.HangMove;
import frc.robot.commands.HangerHook;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.IntakeClose;
import frc.robot.commands.IntakeOpen;
import frc.robot.commands.SetShooterServo;
import frc.robot.commands.Shoot;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Joystick j_joystick = new Joystick(Constants.Controls.JOYSTICK_USB);
  private final DriveTrain m_driveTrain = new DriveTrain(true);
  private final Shooter m_shooter = new Shooter();
  private final AutoFWD a_auto_forward = new AutoFWD(m_driveTrain);
  private final Intake m_intake = new Intake();
  private final JoystickButton b_intakeExtend;
  private final JoystickButton b_intakeRetract;
  private final JoystickButton b_intakeSpin;
  private final JoystickButton b_hanger_up;
  private final JoystickButton b_hanger_down;
  private final JoystickButton b_hanger_open;
  private final JoystickButton b_hanger_closed;
  private final Hanger flyWheel = new Hanger();
  private final JoystickButton b_runShooter;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    b_hanger_up = new JoystickButton(j_joystick, Constants.Controls.BUTTON_HANGER_UP);
    b_hanger_down = new JoystickButton(j_joystick, Constants.Controls.BUTTON_HANGER_DOWN);
    b_runShooter = new JoystickButton(j_joystick, Constants.Controls.BUTTON_SHOOT_FLYWHEEL);
    b_intakeExtend = new JoystickButton(j_joystick, Constants.Controls.BUTTON_INTAKE_EXTEND);
    b_intakeRetract = new JoystickButton(j_joystick, Constants.Controls.BUTTON_INTAKE_RETRACT);
    b_intakeSpin = new JoystickButton(j_joystick, Constants.Controls.BUTTON_INTAKE_ROLLER);
    b_hanger_open = new JoystickButton(j_joystick, Constants.Controls.BUTTON_HANGER_OPEN);
    b_hanger_closed = new JoystickButton(j_joystick, Constants.Controls.BUTTON_HANGER_CLOSED);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_driveTrain.setDefaultCommand(new Drive(m_driveTrain, j_joystick));
    m_shooter.setDefaultCommand(new SetShooterServo(m_shooter, j_joystick));
    b_hanger_up.whileHeld(new HangMove(flyWheel, 0.35));
    b_hanger_down.whileHeld(new HangMove(flyWheel, -0.8));
    b_runShooter.whileHeld(new Shoot(m_shooter, Constants.Shooter.SHOOTER_SPEED));
    b_intakeExtend.whileHeld(new IntakeOpen(m_intake));
    b_intakeRetract.whileHeld(new IntakeClose(m_intake));
    b_intakeSpin.whileHeld(new IntakeBall(m_intake));
    b_hanger_open.whenPressed(new HangerHook(flyWheel, Constants.Hanger.HANGER_SERVO_POS_OPEN));
    b_hanger_closed.whenPressed(new HangerHook(flyWheel, Constants.Hanger.HANGER_SERVO_POS_CLOSED));


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An AutonomousThingy will run in autonomous
    return a_auto_forward;
  }
}
