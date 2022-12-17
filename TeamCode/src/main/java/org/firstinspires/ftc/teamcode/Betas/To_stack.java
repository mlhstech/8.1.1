package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

public class To_stack extends LinearOpMode {

    RevColorSensorV3 front;
    RevColorSensorV3 back;
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    IMU imu;

    public boolean define(DcMotorEx front_left, DcMotorEx front_right, DcMotorEx back_left, DcMotorEx back_right, IMU ime, RevColorSensorV3 a, RevColorSensorV3 b) {

        front = a;
        back = b;
        fl = front_left;
        fr = front_right;
        bl = back_left;
        br = back_right;
        imu = ime;



        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        return true;
    }

    public boolean stack() {

        Robot robot = new Robot();
        robot.define(fl, fr, bl, br, imu);

        while (front.getRawLightDetected()<200) {
            fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            fl.setPower(-0.3);
            bl.setPower(-0.3);
            fr.setPower(0.3);
            br.setPower(0.3);

                        }
        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);

        robot.drive_forward(8);




        while (back.getRawLightDetected()<500) {

            fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            fl.setPower(0.3);
            bl.setPower(0.3);
            fr.setPower(-0.3);
            br.setPower(-0.3);


        }


        return true;
    }


    @Override
    public void runOpMode() throws InterruptedException {


    }
}
