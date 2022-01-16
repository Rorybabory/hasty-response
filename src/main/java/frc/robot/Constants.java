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
        public static final int DRIVE_DIO_ENC_LEFT1 = 6;
        public static final int DRIVE_DIO_ENC_LEFT2 = 7;
        public static final int DRIVE_DIO_ENC_RIGHT1 =  8;
        public static final int DRIVE_DIO_ENC_RIGHT2 =  9;
        public static final double DRIVE_DISTANCE_PER_PULSE = 0.004378531;
    }
    public static final class Controls {
        public static final int JOYSTICK_USB = 0;
        public static final int BUTTON_SHOOT_FLYWHEEL = 1;
        public static final int BUTTON_RESET_NAVX = 3;
        public static final int BUTTON_SWAP_DRIVE_DIR = 4;
    }
    public static final class Pneumatics{
        public static final int SOLENOID_DIO_1 = 0;
        public static final int SOLENOID_DIO_2 = 1;
    }
    public static final class Shooter {
        public static final int SHOOTER_CAN = 5;
    }
}
