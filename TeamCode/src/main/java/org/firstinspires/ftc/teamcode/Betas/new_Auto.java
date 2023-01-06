package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;

import org.checkerframework.checker.units.qual.A;

@Autonomous
public class new_Auto extends LinearOpMode {
    DcMotorEx front_left;
    DcMotorEx front_right;
    DcMotorEx back_left;
    DcMotorEx back_right;
    IMU imu;
    DistanceSensor a;
    DistanceSensor b;

    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot();
        Auto_home home = new Auto_home();
        imu = hardwareMap.get(IMU.class, "imu");
        front_left = hardwareMap.get(DcMotorEx.class, "0");
        front_right = hardwareMap.get(DcMotorEx.class, "1");
        back_left = hardwareMap.get(DcMotorEx.class, "2");
        back_right = hardwareMap.get(DcMotorEx.class, "3");
        a = hardwareMap.get(DistanceSensor.class,"i2c0");
        b = hardwareMap.get(DistanceSensor.class,"i2c1");



        robot.define(front_left, front_right, back_left, back_right, imu);
        home.define(front_left, front_right, back_left, back_right, a, b);
        robot.set_speed(0.3);
        robot.drive_forward(5);
        robot.turn_left(0);
        robot.strafe_right(18);
        home.home();
        robot.drive_backward(5);
        robot.strafe_left(15);
        robot.drive_forward(10);
        robot.turn_left(90);
        robot.strafe_right(45);
        robot.turn_left(90);
        robot.drive_forward(12);
        //insert auto home here




    }
}
