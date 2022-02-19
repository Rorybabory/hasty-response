// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveTrain {
        public static final int DRIVE_PWM_LEFT1 = 0;
        public static final int DRIVE_PWM_LEFT2 = 1;
        public static final int DRIVE_PWM_RIGHT1 = 2;
        public static final int DRIVE_PWM_RIGHT2 = 3;

        public static final int DRIVE_CAN_LEFT1 = 2;
        public static final int DRIVE_CAN_LEFT2 = 3;
        public static final int DRIVE_CAN_RIGHT1 = 4;
        public static final int DRIVE_CAN_RIGHT2 = 5; 

        public static final int DRIVE_DIO_ENC_LEFT1 = 7;
        public static final int DRIVE_DIO_ENC_LEFT2 = 6;
        public static final int DRIVE_DIO_ENC_RIGHT1 =  9;
        public static final int DRIVE_DIO_ENC_RIGHT2 =  8;
        public static final double DRIVE_DISTANCE_PER_PULSE_LEFT = 0.0012101341281669;
        public static final double DRIVE_DISTANCE_PER_PULSE_RIGHT = 0.0003752310536044362;
    }
    public static final class Controls {
        public static final int JOYSTICK_USB = 0;
        public static final int BUTTON_SHOOT_FLYWHEEL = 1;
        public static final int BUTTON_DOOR = 4;
        public static final int BUTTON_HANGER_UP = 12;
        public static final int BUTTON_HANGER_DOWN = 11;
        public static final int BUTTON_INTAKE_EXTEND = 4;
        public static final int BUTTON_INTAKE_RETRACT = 3;
        public static final int BUTTON_INTAKE_ROLLER = 2;
        public static final int BUTTON_SHOOT_FLYWHEEL_2 = 6;
        public static final int BUTTON_RESET_NAVX = 3;
        public static final int BUTTON_HANGER_OPEN = 10;
        public static final int BUTTON_HANGER_CLOSED = 11;
        public static final int BUTTON_BTS_ROLLER = 9;
    }
    public static final class Pneumatics{
        public static final int SOLENOID_PCM_1 = 0; //Out
        public static final int SOLENOID_PCM_2 = 1; //In
    }
    public static final class Hanger {
        public static final int HANGER_CAN = 9;
        public static final int HANGER_CAN_2 = 8;
        public static final int HANGER_SERVO_PWM = 3;
        public static final double HANGER_SERVO_POS_OPEN = 0;
        public static final double HANGER_SERVO_POS_CLOSED = 180;
    }
    public static final class Shooter {
        public static final double SHOOTER_SPEED = 0.43;
        public static final int SHOOTER_PWM_0 = 0;
        public static final int SHOOTER_PWM_1 = 1;
        public static final int SERVO_PWM = 2;
    }
    public static final class Intake {
        public static final int INTAKE_SPARK_CAN = 7;
        public static final int INTAKE_SOLENOID_PCM = 0;
        public static final int INTAKE_SOLENOID_PCM_2 = 1;
        public static final double INTAKE_SPEED = .3;
    }
    public static final class BTS {
        public static final int BTS_ROLLER_PWM = 4;
        public static final double BTS_SPEED = 0.5;
    }
}
