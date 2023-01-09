package org.firstinspires.ftc.teamcode.Betas;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
public class new_Auto extends LinearOpMode {
    DcMotorEx front_left;
    DcMotorEx front_right;
    DcMotorEx back_left;
    DcMotorEx back_right;
    DcMotorEx lif;
    IMU imu;
    DistanceSensor a;
    DistanceSensor b;
    RevColorSensorV3 front;
    RevColorSensorV3 back;

    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot();
        Auto_home home = new Auto_home();
        Lift lift = new Lift();
        To_stack stak = new To_stack();
        imu = hardwareMap.get(IMU.class, "imu");
        front_left = hardwareMap.get(DcMotorEx.class, "0");
        front_right = hardwareMap.get(DcMotorEx.class, "1");
        back_left = hardwareMap.get(DcMotorEx.class, "2");
        back_right = hardwareMap.get(DcMotorEx.class, "3");
        lif = hardwareMap.get(DcMotorEx.class, "e0");
        a = hardwareMap.get(DistanceSensor.class,"i2c0");
        a = hardwareMap.get(DistanceSensor.class,"i2c0");
        b = hardwareMap.get(DistanceSensor.class,"i2c1");
        front = hardwareMap.get(RevColorSensorV3.class, "i2c2");
        back= hardwareMap.get(RevColorSensorV3.class, "i2c3");
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("x", 3.7);
        packet.put("status", "alive");
        FtcDashboard dashboard = FtcDashboard.getInstance();
        dashboard.sendTelemetryPacket(packet);



        robot.define(front_left, front_right, back_left, back_right, imu);
        home.define(front_left, front_right, back_left, back_right, a, b);
        stak.define(front_left, front_right, back_left, back_right, imu, front, back);
        lift.define(lif);




        telemetry.addData("> ", "Press start");


        waitForStart();

        robot.set_speed(0.3);

        robot.drive_forward(5);
        lift.low();
        robot.turn_left(0);
        robot.strafe_right(18);
        home.home();
        robot.drive_backward(5);
        robot.strafe_left(15);
        lift.ground();
        robot.drive_forward(5);
        robot.turn_left(90);
        robot.drive_backward(3);
        //robot.drive_forward(10);

        robot.strafe_right(50);
        robot.turn_left(90);
        robot.drive_forward(20);
        robot.turn_right(50);
        stak.stack();

        //insert auto home here




    }
}
