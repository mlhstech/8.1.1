package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
public class tortise_testing extends LinearOpMode {
    DcMotorEx front_left;
    DcMotorEx front_right;
    DcMotorEx back_left;
    DcMotorEx back_right;
    IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot();
        imu = hardwareMap.get(IMU.class, "imu");
        front_left = hardwareMap.get(DcMotorEx.class, "0");
        front_right = hardwareMap.get(DcMotorEx.class, "1");
        back_left = hardwareMap.get(DcMotorEx.class, "2");
        back_right = hardwareMap.get(DcMotorEx.class, "3");



        robot.define(front_left, front_right, back_left, back_right, imu);

        robot.drive_forward(7);
        robot.turn_left(90);
        robot.drive_backward(28);
        robot.turn_right(4);
        robot.set_speed(0.3);
        robot.strafe_right(40);

        //insert auto home here




    }
}
