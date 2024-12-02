package org.firstinspires.ftc.teamcode.parts;

import org.firstinspires.ftc.teamcode.PartsBackUp;

public class GearClaw implements Claw {
    public void grabs(boolean move) {
        if (move) {
            PartsBackUp.claw.setPosition(1.00);
        }
    }

    public void drops(boolean move) {
        if (move) {
            PartsBackUp.claw.setPosition(0.77);
        }
    }
}
