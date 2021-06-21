public class TrafficLight {
    //sec
    private int redTime;
    private int greenTime;
    private int rightGreenTime;
    private int yellowTime;
    private boolean turnOn;
    private boolean redLight;
    private boolean greenLight;
    private boolean rightGreenLight;
    private boolean yellowLight;

    public static void main(String[] args) {
        TrafficLight a = new TrafficLight();
        a.runTrafficLight();
    }

    public TrafficLight() {
        redTime = 60;
        greenTime = 60;
        rightGreenTime = 30;
        yellowTime = 10;
        redLight = false;
        greenLight = false;
        rightGreenLight = false;
        yellowLight = false;
    }

    public void runTrafficLight() {
        turnOn = true;
        while(turnOn) {
            exeGreen();
            exeYellow();
            exeRightGreen();
            exeRightYellow();
            exeRed();
        }
    }

    public void exeRed() {
        turnOnRedLight();
        System.out.println("turnOn Red Light");
        try {
            Thread.sleep(redTime * 1000);
        } catch (Exception e) {
            System.err.println("error in exeRed wait time" + e);
        }
        turnOffRedLight();
    }

    public void exeGreen() {
        turnOnGreenLight();
        System.out.println("turnOn Green Light");
        try {
            Thread.sleep(greenTime * 1000);
        } catch (Exception e) {
            System.err.println("error in exeGreen wait time" + e);
        }
        turnOffGreenLight();
    }

    public void exeRightGreen() {
        turnOnRedLight();
        turnOnGreenLight();
        System.out.println("turnOn RightGreen Light");
        try {
            Thread.sleep(rightGreenTime * 1000);
        } catch (Exception e) {
            System.err.println("error in exeRightGreen wait time" + e);
        }
        turnOffRedLight();
        turnOffGreenLight();
    }

    public void exeYellow() {
        turnOnYellowLight();
        System.out.println("turnOn Yellow Light");
        try {
            Thread.sleep(yellowTime * 1000);
        } catch (Exception e) {
            System.err.println("error in exeYellow wait time" + e);
        }
        turnOffYellowLight();
    }

    public void exeRightYellow() {
        turnOnRedLight();
        turnOnYellowLight();
        System.out.println("turnOn Yellow Light");
        try {
            Thread.sleep(yellowTime * 1000);
        } catch (Exception e) {
            System.err.println("error in exeYellow wait time" + e);
        }
        turnOffRedLight();
        turnOffYellowLight();
    }

    public void turnOnAllLight() {
        redLight = true;
        greenLight = true;
        rightGreenLight = true;
        yellowLight = true;
    }

    public void turnOffAllLight() {
        redLight = false;
        greenLight = false;
        rightGreenLight = false;
        yellowLight = false;
    }

    public void setRedTime(int time) {
        redTime = time;
    }

    public void setGreenTime(int time) {
        greenTime = time;
    }

    public void setRightGreenTime(int time) {
        rightGreenTime = time;
    }

    public void setYellowTime(int time) {
        yellowTime = time;
    }

    public void turnOnRedLight() {
        redLight = true;
    }

    public void turnOffRedLight() {
        redLight = false;
    }

    public void turnOnGreenLight() {
        greenLight = true;
    }

    public void turnOffGreenLight() {
        greenLight = false;
    }

    public void turnOnRightGreenLight() {
        rightGreenLight = true;
    }

    public void turnOffRightGreenLight() {
        rightGreenLight = false;
    }

    public void turnOnYellowLight() {
        yellowLight = true;
    }

    public void turnOffYellowLight() {
        yellowLight = false;
    }
}
