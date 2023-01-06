package org.firstinspires.ftc.teamcode.Betas; /*The first line declares that this code belongs to the package
"org.firstinspires.ftc.teamcode.Betas".
This is a package name convention commonly used in FIRST
Tech Challenge (FTC) robotics competitions.

 The next few lines import various classes that are needed for the code to run.
    These include classes for running an autonomous op mode
    (a type of program that runs on the robot without requiring user input),
    classes for interacting with hardware components of the robot (such as motors and sensors),
    and a class for representing an inertial measurement unit (IMU).

     */
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.units.qual.A;

//@Autonomous //The code then specifies that this is an autonomous op mode by using the "@Autonomous" annotation.
public class auto_3 extends LinearOpMode {
/* The code defines a class called "tortise_testing"
that extends the "LinearOpMode" class and overrides its "runOpMode()"
method. The "runOpMode()" method is the main method that runs when the op mode is started.

 */

    /* The code then declares several variables at the class level:
    "front_left", "front_right", "back_left", "back_right", "imu", "a", and "b".
    These variables will be used to store references to various hardware components of the robot.

     */

    DcMotorEx front_left;
    DcMotorEx front_right;
    DcMotorEx back_left;
    DcMotorEx back_right;
    DcMotorEx lif;
    IMU imu;
    DistanceSensor a;
    DistanceSensor b;

    Servo s;
    Servo f;

    public boolean define (DcMotorEx front_l, DcMotorEx front_r, DcMotorEx back_l, DcMotorEx back_r, DcMotorEx li, IMU im, DistanceSensor c, DistanceSensor d, Servo y, Servo p) {
        front_left = front_l;
        front_right = front_r;
        back_left = back_l;
        back_right = back_r;
        lif = li;
        imu = im;
        a = c;
        b = d;
        s = y;
        f = p;


        return true;
    }

    public boolean go() {
        Robot robot = new Robot();
        Auto_home home = new Auto_home();
        To_stack stack = new To_stack();
        Lift lift = new Lift();
        Claw claw = new Claw();
        //s.setPosition(0);
        //f.setPosition(1);
        claw.define(s,f);
        claw.close();

        home.define(front_left, front_right, back_left, back_right, a, b);
        lift.define(lif);



        /*The code then calls several methods on the "robot" and "home" objects to perform various actions
        , such as driving forward, turning, strafing, and auto homing onto poles.

         */

        sleep(200);
        robot.define(front_left, front_right, back_left, back_right, imu);
        lift.ground();
        robot.set_speed(0.2);
        robot.drive_forward(7);
        robot.turn_left(90);
        robot.set_speed(0.3);
        robot.drive_backward(28);
        robot.turn_left(90);
        lift.medium();

        robot.set_speed(0.3);
        robot.strafe_right(35);
        robot.drive_backward(3);
        lift.medium();
        home.home(); // auto homes onto the poles
        claw.open();
        robot.drive_backward(7);
        robot.turn_left(90);
        robot.strafe_left(14);
        robot.drive_forward(1);
        return true;
    }


    @Override
    public void runOpMode() throws InterruptedException {

        /*In the "runOpMode()" method, the code creates instances of the "Robot" and "Auto_home"
        classes and passes references to the hardware components as arguments.
         */
        Robot robot = new Robot();
        Auto_home home = new Auto_home();
        To_stack stack = new To_stack();
        Lift lift = new Lift();
        Claw claw = new Claw();
        imu = hardwareMap.get(IMU.class, "imu");
        front_left = hardwareMap.get(DcMotorEx.class, "0");
        front_right = hardwareMap.get(DcMotorEx.class, "1");
        back_left = hardwareMap.get(DcMotorEx.class, "2");
        back_right = hardwareMap.get(DcMotorEx.class, "3");
        lif = hardwareMap.get(DcMotorEx.class, "e0");
        a = hardwareMap.get(DistanceSensor.class,"i2c0");
        b = hardwareMap.get(DistanceSensor.class,"i2c1");
        s = hardwareMap.get(Servo.class, "s0");
        f = hardwareMap.get(Servo.class, "s1");




        home.define(front_left, front_right, back_left, back_right, a, b);
        lift.define(lif);
        claw.define(s,f);


        /*The code then calls several methods on the "robot" and "home" objects to perform various actions
        , such as driving forward, turning, strafing, and auto homing onto poles.

         */
        claw.close();
        sleep(200);
        robot.define(front_left, front_right, back_left, back_right, imu);
        lift.ground();
        robot.set_speed(0.2);
        robot.drive_forward(7);
        robot.turn_left(90);
        robot.set_speed(0.3);
        robot.drive_backward(28);
        robot.turn_left(90);
        lift.medium();

        robot.set_speed(0.3);
        robot.strafe_right(40);
        robot.drive_backward(3);
        lift.medium();
        home.home(); // auto homes onto the poles
        claw.open();
        robot.drive_backward(7);
        robot.turn_left(90);
        robot.strafe_left(16);
        robot.drive_forward(25);
/*
        //drive from medium to stack
        robot.drive_backward(5);
        lift.ground();
        robot.strafe_right(12);
        robot.drive_forward(30);
        stack.stack();  //uses color sensors to allign with the stack using tape on the ground
        */







    }
}











