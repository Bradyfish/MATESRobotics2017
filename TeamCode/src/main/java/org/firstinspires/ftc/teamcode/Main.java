/*
Copyright 2017 FIRST Tech Challenge Team 12365

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp

public class Main extends LinearOpMode {
    private DcMotor tLeftDrive;
    private DcMotor tRightDrive;
    private DcMotor bLeftDrive;
    private DcMotor bRightDrive;
    private DcMotor arm;
    private Servo cubeGrabber;
    private Servo grabber;


    @Override
    public void runOpMode() {
        tLeftDrive = hardwareMap.get(DcMotor.class, "tLeftDrive");
        tRightDrive = hardwareMap.get(DcMotor.class, "tRightDrive");
        bLeftDrive = hardwareMap.get(DcMotor.class, "bLeftDrive");
        bRightDrive = hardwareMap.get(DcMotor.class, "bRightDrive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        cubeGrabber = hardwareMap.get(Servo.class, "cubeGrabber");
        grabber = hardwareMap.get(Servo.class, "grabber");
        
        

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            
            // Setup a variable for each drive wheel to save power level for telemetry
            int position = arm.getCurrentPosition();
            double tLeftPower;
            double tRightPower;
            double bLeftPower;
            double bRightPower;
           // double arm;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double side  =  gamepad1.right_stick_x;
            boolean turnRight  =  gamepad1.dpad_right;
            boolean turnLeft  =  gamepad1.dpad_left;
            boolean stopRight  =  !gamepad1.dpad_right;
           // double armPower = gamepad1.right_trigger;
            
                
             

        
            
            tLeftPower    = Range.clip(drive + side, -1.0, 1.0) ;
            tRightPower   = Range.clip(drive - side, -1.0, 1.0) ;
            bLeftPower    = Range.clip(drive + side, -1.0, 1.0) ;
            bRightPower   = Range.clip(drive - side, -1.0, 1.0) ;
          //  arm           = Range.clip(armPower, -1.0, 1.0);
            
        
            while (turnLeft){
            tLeftDrive.setPower(1.0);
            tRightDrive.setPower(-1.0);
            bLeftDrive.setPower(1.0);
            bRightDrive.setPower(-1.0);
                if(gamepad1.dpad_up){
        
                    turnLeft = false;
                }
            
                
                }
            
         while (turnRight){
            tLeftDrive.setPower(-1.0);
            tRightDrive.setPower(1.0);
            bLeftDrive.setPower(-1.0);
            bRightDrive.setPower(1.0);
            
            if(gamepad1.dpad_up){
                turnRight = false;
            }
            }
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // tLeftPower  = -gamepad1.left_stick_y ;
            // tRightPower = -gamepad1.right_stick_y ;
            // bLeftPower = -gamepad1.left_stick_y ;
            // bRightPower = -gamepad1.left_stick_y ;

            // Send calculated power to wheels
            
            
            tLeftDrive.setPower(-tLeftPower);
            tRightDrive.setPower(-tRightPower);
            bLeftDrive.setPower(bLeftPower);
            bRightDrive.setPower(bRightPower);
            

            
            double grabberPosition = gamepad1.left_trigger;
            cubeGrabber.setPosition(grabberPosition);
            
            if(gamepad1.y){
                grabber.setPosition(0);
                 
                
            }
            else if (gamepad1.x){
                grabber.setPosition(1);
            }
        
            
         if(gamepad1.a){
           
            arm.setPower(1.0);
            arm.setTargetPosition(400);
            
            gamepad1.a = false;
            
        }
        else if (gamepad1.b){
            
            arm.setPower(1.0);
            arm.setTargetPosition(10);
            
            gamepad1.b = false;
            
        }
            

// Show the elapsed game time and wheel power.
        //    telemetry.addData("Status", "Run Time: " + runtime.toString());
            // telemetry.addData("Motors", "tLeft (%.2f), topRight (%.2f), bottomLeft (%.2f), bottomRight (%.2f)", tLeftPower, tRightPower, bLeftPower, bRightPower);
            telemetry.addData("Servo Position", grabber.getPosition());
            telemetry.addData("Encoder Position", position);
            telemetry.addData("Cube Grabber Position", cubeGrabber.getPosition());
          //  telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Status", "Running");
            telemetry.addData("Back Left Wheel:", bLeftDrive);
            telemetry.update();
    
}

}

        }
