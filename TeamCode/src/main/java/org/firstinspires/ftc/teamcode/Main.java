package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.*;


@TeleOp

public class Main extends LinearOpMode {

    // todo: write your code here
    

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor tLeftDrive = null;
    private DcMotor tRightDrive = null;
    private DcMotor bLeftDrive = null;
    private DcMotor bRightDrive = null;
    private Servo grabber = null;
    private Servo cubeGrabber = null;
    private DcMotor arm = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        tLeftDrive  = hardwareMap.get(DcMotor.class, "tLeftDrive");
        tRightDrive = hardwareMap.get(DcMotor.class, "tRightDrive");
        bLeftDrive = hardwareMap.get(DcMotor.class, "bLeftDrive");
        bRightDrive = hardwareMap.get(DcMotor.class, "bRightDrive");
        grabber = hardwareMap.get(Servo.class, "grabber");
        cubeGrabber = hardwareMap.get(Servo.class, "cubeGrabber");
        arm = hardwareMap.get(DcMotor.class, "arm");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        tLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        tRightDrive.setDirection(DcMotor.Direction.REVERSE);
        bLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        bRightDrive.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        
        
        
        
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        
        double tgtPower = 0; // servo stuff
        int servoPower = 0; // servo stuff pt 2
        
        
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
           
            
            // Setup a variable for each drive wheel to save power level for telemetry
            double tLeftPower;
            double tRightPower;
            double bLeftPower;
            double bRightPower;
            double arm;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double side  =  gamepad1.right_stick_x;
            boolean turnRight  =  gamepad1.dpad_right;
            boolean turnLeft  =  gamepad1.dpad_left;
            boolean stopRight  =  !gamepad1.dpad_right;
            double armPower = gamepad1.right_trigger;
            
                
             

        
            
            tLeftPower    = Range.clip(drive + side, -1.0, 1.0) ;
            tRightPower   = Range.clip(drive - side, -1.0, 1.0) ;
            bLeftPower    = Range.clip(drive + side, -1.0, 1.0) ;
            bRightPower   = Range.clip(drive - side, -1.0, 1.0) ;
            arm           = Range.clip(armPower, -1.0, 1.0);
            
        
            while (turnLeft){
            tLeftDrive.setPower(1.0);
            tRightDrive.setPower(-1.0);
            bLeftDrive.setPower(1.0);
            bRightDrive.setPower(1.0);
                if(gamepad1.dpad_up){
            tLeftDrive.setPower(0);
            tRightDrive.setPower(0);
            bLeftDrive.setPower(0);
            bRightDrive.setPower(0);
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

            

// Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "topLeft (%.2f), topRight (%.2f), bottomLeft (%.2f), bottomRight (%.2f)", tLeftPower, tRightPower, bLeftPower, bRightPower);
            telemetry.addData("Servo Position", grabber.getPosition());
            telemetry.addData("Cube Grabber Position", cubeGrabber.getPosition());
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Status", "Running");
            telemetry.update();
    
}

}
}
