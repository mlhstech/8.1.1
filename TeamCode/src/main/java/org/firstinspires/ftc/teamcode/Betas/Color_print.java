package org.firstinspires.ftc.teamcode.Betas;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
public class Color_print extends LinearOpMode {

    RevColorSensorV3 front;
    RevColorSensorV3 back;








    @Override
    public void runOpMode() throws InterruptedException {

        front = hardwareMap.get(RevColorSensorV3.class, "i2c2");
        back = hardwareMap.get(RevColorSensorV3.class, "i2c3");

        TelemetryPacket packet = new TelemetryPacket();

        FtcDashboard dashboard = FtcDashboard.getInstance();





        while (!isStopRequested()) {


            telemetry.addData(">", front.getLightDetected());
            telemetry.addData(">", front.getRawLightDetected());
            telemetry.update();
            packet.put("front_Raw", front.getRawLightDetected());
            packet.put("front", front.getLightDetected());
            packet.put("back_Raw", back.getRawLightDetected());
            packet.put("back", back.getLightDetected());
            packet.put("status", "alive");
            //FtcDashboard dashboard = FtcDashboard.getInstance();
            dashboard.sendTelemetryPacket(packet);
        }


    }
}
