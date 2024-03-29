package org.usfirst.frc.team3196.robot.commands;

import org.usfirst.frc.team3196.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithEncoder extends Command {

	long encoderDist = 0;
	
    public DriveWithEncoder(long dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ssDrive);
    	requires(Robot.ssSensors);
    	
    	encoderDist = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDrive.resetEncoders();
    	Robot.ssDrive.setSetpoint(encoderDist);
    	Robot.ssDrive.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(Robot.ssDrive.getPIDController().getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.ssDrive.getEncoderRight() >= (encoderDist - 50) && Robot.ssDrive.getEncoderRight() >= (encoderDist + 50));
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.print("Finished forward at ");
    	System.out.println(Robot.ssDrive.getEncoderRight());
    	
    	Robot.ssDrive.stopMotors();
    	Robot.ssDrive.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
