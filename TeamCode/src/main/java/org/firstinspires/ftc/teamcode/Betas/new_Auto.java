package org.firstinspires.ftc.teamcode.Betas;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

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
    Servo l;
    Servo r;

    @Override
    public void runOpMode() throws InterruptedException {

        Robot_beta robot = new Robot_beta();
        Auto_home home = new Auto_home();
        Lift lift = new Lift();
        To_stack stak = new To_stack();
        Claw claw = new Claw();
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
        l = hardwareMap.get(Servo.class,"s0");
        r = hardwareMap.get(Servo.class,"s1");
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("x", 3.7);
        packet.put("status", "alive");
        FtcDashboard dashboard = FtcDashboard.getInstance();
        dashboard.sendTelemetryPacket(packet);



        robot.define(front_left, front_right, back_left, back_right, imu);
        home.define(front_left, front_right, back_left, back_right, a, b);
        stak.define(front_left, front_right, back_left, back_right, imu, front, back);
        lift.define(lif);
        claw.define(l, r);




        telemetry.addData("> ", "Press start");


        waitForStart();

        claw.close();
        sleep(300);

        robot.set_speed(0.7);

        robot.drive_forward(5);
        lift.low();
        robot.turn_left(0);
        robot.strafe_right(18);
        home.home();
        claw.open();
        sleep(100);
        robot.drive_backward(5);
        claw.close();
        robot.strafe_left(15);
        lift.ground();
        robot.drive_forward(5);
        robot.turn_left(85);
        //robot.drive_backward(0);
        //robot.drive_forward(10);
        //757

        robot.strafe_right(53);
        sleep(500);
        robot.turn_left(90);
        robot.drive_forward(19);
        robot.set_speed(0.3);
        robot.strafe_right(5);
        stak.stack();
        lift.medium();
        claw.open();
        robot.drive_forward(5);
        claw.close();
        sleep(300);
        lift.low();
        sleep(200);
        robot.drive_backward(26);
        robot.strafe_left(14);
       // robot.turn_left(180);
        home.home();
        claw.open();
        sleep(300);
        robot.drive_backward(5);


        //insert auto home here




    }
}
