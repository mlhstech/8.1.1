package org.firstinspires.ftc.teamcode.Betas;

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







    @Override
    public void runOpMode() throws InterruptedException {

        front = hardwareMap.get(RevColorSensorV3.class, "i2c2");




        while (!isStopRequested()) {


            telemetry.addData(">", front.getLightDetected());
            telemetry.addData(">", front.getRawLightDetected());
            telemetry.update();
        }


    }
}
