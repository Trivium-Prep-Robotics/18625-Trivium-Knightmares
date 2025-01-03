package org.firstinspires.ftc.teamcode.parts;

import org.firstinspires.ftc.teamcode.Parts;

public class GearClaw implements Claw {
    public void grabs(boolean move) {
        if (move) {
            Parts.claw.setPosition(0.7);
        }
    }

    public void drops(boolean move) {
        if (move) {
            Parts.claw.setPosition(0.4);
        }
    }
}
