package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
public class Home_on_stack extends LinearOpMode {

    DcMotorEx front_left;
    DcMotorEx front_right;
    DcMotorEx back_left;
    DcMotorEx back_right;
    IMU imu;
    RevColorSensorV3 front;
    RevColorSensorV3 back;
    Rev2mDistanceSensor a;

    @Override
    public void runOpMode() throws InterruptedException {
        To_stack stack = new To_stack();
        imu = hardwareMap.get(IMU.class, "imu");
        front_left = hardwareMap.get(DcMotorEx.class, "0");
        front_right = hardwareMap.get(DcMotorEx.class, "1");
        back_left = hardwareMap.get(DcMotorEx.class, "2");
        back_right = hardwareMap.get(DcMotorEx.class, "3");

        front = hardwareMap.get(RevColorSensorV3.class, "i2c2");
        back= hardwareMap.get(RevColorSensorV3.class, "i2c3");

        stack.define(front_left, front_right, back_left, back_right, imu, front, back, a);

        stack.stack();
    }
}
