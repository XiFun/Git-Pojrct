// 球门类
class Goal {
    int x; // 球门x坐标
    int y; // 球门y坐标
    int width; // 球门宽度
    int height; // 球门高度

    public Goal(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}

// 球类
class Ball {
    int x;     // 球x坐标
    int y;     // 球y坐标
    int speedX; // x方向速度
    int speedY; // y方向速度

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Ball(int x, int y, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }
}

// 机器人类
class Robot {
    int x;       // 机器人x坐标
    int y;       // 机器人y坐标
    Goal goal;   // 球门

    public Robot(int x, int y, Goal goal) {
        this.x = x;
        this.y = y;
        this.goal = goal;
    }

    // 计算机器人与球的距离
    double distanceToBall(Ball ball) {
        return Math.sqrt(Math.pow(ball.x - x, 2) + Math.pow(ball.y - y, 2));
    }

    // 马里奥转向机器人
    void turnToBall(Ball ball) {
        // 计算机器人与球的角度
        double angle = Math.atan2(ball.y - y, ball.x - x);

        // 转向球的方向
        x += (int)(Math.cos(angle) * 5);
        y += (int)(Math.sin(angle) * 5);
    }

    // 机器人射门
    void kickToGoal(Ball ball) {
        // 预测球运动轨迹,计算射门角度
        double vy = ball.speedY;
        double g = 9.8;
        double t = (goal.y - ball.y) / vy;
        double x = ball.x + ball.speedX * t;
        double y = ball.y + vy*t - 0.5*g*t*t;
        double angle = Math.atan2(goal.y + goal.height/2 - y,
                goal.x + goal.width/2 - x);

        // 转向射门角度并射门
        turnToBall(new Ball(goal.x + goal.width/2, goal.y + goal.height/2));
        shoot();
    }

    // 射门方法
    void shoot() {
        System.out.println("射门!");
    }
}

public class PenaltyShootout {
    public static void main(String[] args) {
        Goal goal = new Goal(200, 150, 50, 30);   // 球门
        Ball ball = new Ball(50, 50, 10, 15);     // 球
        Robot robot = new Robot(0, 0, goal);      // 机器人

        // 机器人转向球
        robot.turnToBall(ball);

        // 预测运动轨迹射门
        robot.kickToGoal(ball);
    }
}