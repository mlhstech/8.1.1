package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class Robot_beta extends LinearOpMode{


    //circumference = 11.87374
    //516 ticks per rev
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    IMU imu;
    double speed = 0.5;
    double turn_speed = 0.2;


    public void define(DcMotorEx front_left, DcMotorEx front_right, DcMotorEx back_left, DcMotorEx back_right, IMU ime) {

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
        imu.resetYaw();
    }




    public boolean drive_forward(int distance) {


        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        int tpi = 43;
        int total = tpi * distance;
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (a < total && b < total && c < total && d < total) {
            a = fl.getCurrentPosition();
            b = fr.getCurrentPosition();
            c = bl.getCurrentPosition();
            d = br.getCurrentPosition();
            fl.setTargetPosition(total);
            fr.setTargetPosition(total);
            bl.setTargetPosition(total);
            br.setTargetPosition(total);

            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fl.setPower(speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(speed);
        }

        return true;
    }

    public boolean drive_backward(int distance) {


        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        int tpi = 43;
        int total = -(tpi * distance);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (a > total && b > total && c > total && d > total) {
            a = fl.getCurrentPosition();
            b = fr.getCurrentPosition();
            c = bl.getCurrentPosition();
            d = br.getCurrentPosition();
            fl.setTargetPosition(total);
            fr.setTargetPosition(total);
            bl.setTargetPosition(total);
            br.setTargetPosition(total);

            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fl.setPower(speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(speed);
        }

        return true;


    }

    public boolean strafe_left(int distance) {



        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        int tpi = 49;
        int total = tpi * distance;
        int opposite = -(tpi * distance);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (a < total && b < total && c < total && d < total) {
            a = fl.getCurrentPosition();
            b = fr.getCurrentPosition();
            c = bl.getCurrentPosition();
            d = br.getCurrentPosition();
            fl.setTargetPosition(opposite);
            fr.setTargetPosition(total);
            bl.setTargetPosition(total);
            br.setTargetPosition(opposite);

            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fl.setPower(speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(speed);
        }
        return true;

    }

    public boolean strafe_right(int distance) {

        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        int tpi = 49;
        int total = tpi * distance;
        int opposite = -(tpi * distance);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (a < total && b < total && c < total && d < total) {
            a = fl.getCurrentPosition();
            b = fr.getCurrentPosition();
            c = bl.getCurrentPosition();
            d = br.getCurrentPosition();
            fl.setTargetPosition(total);
            fr.setTargetPosition(opposite);
            bl.setTargetPosition(opposite);
            br.setTargetPosition(total);

            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fl.setPower(speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(speed);
        }

        return true;

    }

    public boolean turn_left(int distance){

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Now initialize the IMU with this mounting orientation
        // Note: if you choose two conflicting directions, this initialization will cause a code exception.
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //imu.resetYaw();

        sleep(100);

        while (orientation.getYaw(AngleUnit.DEGREES) <= distance) {

            double powerl = 0.5;
            double powerr = 0.5;

            orientation = imu.getRobotYawPitchRollAngles();




            if (orientation.getYaw(AngleUnit.DEGREES) >= distance - 1 && orientation.getYaw(AngleUnit.DEGREES) <= distance + 1) {
                fl.setPower(0);
                bl.setPower(0);
                fr.setPower(0);
                br.setPower(0);
            } else if (orientation.getYaw(AngleUnit.DEGREES) <= distance) {
                fl.setPower(-0.4);
                bl.setPower(-0.4);
                fr.setPower(0.4);
                br.setPower(0.4);
            } else if (orientation.getYaw(AngleUnit.DEGREES) >= distance) {
                fl.setPower(0.4);
                bl.setPower(0.4);
                fr.setPower(-0.4);
                br.setPower(-0.4);
            }



        }



        return true;

    }

    public boolean turn_right(int distance) {

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Now initialize the IMU with this mounting orientation
        // Note: if you choose two conflicting directions, this initialization will cause a code exception.
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //imu.resetYaw();

        sleep(100);

        while (orientation.getYaw(AngleUnit.DEGREES) >= distance) {

            double powerl = 0.5;
            double powerr = 0.5;

            orientation = imu.getRobotYawPitchRollAngles();




            if (orientation.getYaw(AngleUnit.DEGREES) >= distance - 1 && orientation.getYaw(AngleUnit.DEGREES) <= distance + 1) {
                fl.setPower(0);
                bl.setPower(0);
                fr.setPower(0);
                br.setPower(0);
            } else if (orientation.getYaw(AngleUnit.DEGREES) <= distance) {
                fl.setPower(-turn_speed);
                bl.setPower(-turn_speed);
                fr.setPower(turn_speed);
                br.setPower(turn_speed);
            } else if (orientation.getYaw(AngleUnit.DEGREES) >= distance) {
                fl.setPower(turn_speed);
                bl.setPower(turn_speed);
                fr.setPower(-turn_speed);
                br.setPower(-turn_speed);
            }



        }



        return true;


    }





    public void set_speed(double spee) {
        speed = spee;
    }

    public void set_turn_speed (double sp) {
        turn_speed = sp;
    }


    @Override
    public void runOpMode() throws InterruptedException {

    }
}

