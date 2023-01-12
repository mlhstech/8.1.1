package org.firstinspires.ftc.teamcode.Betas;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Auto_home extends LinearOpMode {

    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    DistanceSensor x;
    DistanceSensor y;
    int count = 0;
    double speed = 0.2;

    public void define(DcMotorEx front_left, DcMotorEx front_right, DcMotorEx back_left, DcMotorEx back_right, DistanceSensor a, DistanceSensor b) {

        fl = front_left;
        fr = front_right;
        bl = back_left;
        br = back_right;
        x = a;
        y = b;






    }

    public boolean home() {
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Auto_home homes = new Auto_home();
        homes.define(fl, fr, bl, br, x, y);
        count = 0;
        TelemetryPacket packet = new TelemetryPacket();

        FtcDashboard dashboard = FtcDashboard.getInstance();


        while (x.getDistance(DistanceUnit.MM) >= 205 && count <= 500) {

            fl.setPower(speed-0.1);
            fr.setPower(speed-0.1);
            bl.setPower(speed-0.1);
            br.setPower(speed-0.1);
            count++;
            packet.put("front_right_sensor", x.getDistance(DistanceUnit.MM));
            dashboard.sendTelemetryPacket(packet);

        }

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        homes.homeb();




        return true;
    }

    public boolean homeb() {

        TelemetryPacket packet = new TelemetryPacket();

        FtcDashboard dashboard = FtcDashboard.getInstance();

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        count = 0;
        if (x.getDistance(DistanceUnit.MM) < 100) {
            while (y.getDistance(DistanceUnit.MM) >= 190 && count <= 500) {
                packet.put("back_sensor", y.getDistance(DistanceUnit.MM));
                dashboard.sendTelemetryPacket(packet);
                fl.setPower(speed);
                fr.setPower(-speed);
                bl.setPower(-speed);
                br.setPower(speed);

                count++;
            }

            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        } else if (x.getDistance(DistanceUnit.MM) > 110) {
            while (y.getDistance(DistanceUnit.MM) >= 190) {
                packet.put("back_sensor", y.getDistance(DistanceUnit.MM));
                dashboard.sendTelemetryPacket(packet);
                fl.setPower(-speed);
                fr.setPower(speed);
                bl.setPower(speed);
                br.setPower(-speed);

            }

            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }





        return true;
    }

    public void set_speed(double a) {speed = a;}

    @Override
    public void runOpMode() throws InterruptedException {

    }
}

