/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.henrywisewood.robotics;

import edu.wpi.first.wpilibj.*;
/**
 *
 * @author Work
 */
public class Transmission extends RobotDrive {
    
    private final boolean isFourWheelDrive;
    
    public Transmission(int leftMotorChannel, int rightMotorChannel) {
        this(new Victor(leftMotorChannel), new Victor(rightMotorChannel));
    }
    public Transmission(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor) {
        this(new Victor(frontLeftMotor), new Victor(rearLeftMotor), new Victor(frontRightMotor), new Victor(rearRightMotor));
    }
    public Transmission(SpeedController leftMotorChannel, SpeedController rightMotorChannel) {
        super(leftMotorChannel, rightMotorChannel);
        isFourWheelDrive = false;
    }
    public Transmission(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        isFourWheelDrive = true;
    }
    
    public void joystickDrive(GenericHID joystick) {
        joystickDrive(joystick.getY(), joystick.getX());
    }
    public void joystickDrive(double throttle, double rotate) {
        double vector1 = (1 - Math.abs(rotate)) * throttle + throttle;
        double vector2 = (1 - Math.abs(throttle)) * -rotate - rotate;
        double rightMotorValue = (vector1+vector2) / 2;
        double leftMotorValue = (vector1-vector2) / 2;           
        tankDrive(leftMotorValue, rightMotorValue);
    }
    
    public boolean getDriving() {
        if(isFourWheelDrive) {
            if(m_frontLeftMotor.get() != 0) {
                return true;
            }
            if(m_frontRightMotor.get() != 0) {
                return true;
            }
        }
        if(m_rearLeftMotor.get() != 0) {
            return true;
        }
        if(m_rearRightMotor.get() != 0) {
            return true;
        }
        return false;
    }

}
