package hw1;

public class Wall implements Obstacles {
    private int wallHeight;

    public int getWallHeight() {
        return wallHeight;
    }

    public void setWallHeight(int wallHeight) {
        this.wallHeight = wallHeight;
    }

    public Wall(int wallHeight) {
        this.wallHeight = wallHeight;
    }
}

