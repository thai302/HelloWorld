package src;

public enum Color {
    RED(1, "haha"),
    GREEN(2, "hehe");

    private int value;
    private String x;

    Color(int value, String x) {
        this.value = value;
        this.x = x;
    }

    public static Color getByValue(int value){
        for (Color d : Color.values()) {
            if (d.value == value) {
                return d;
            }
        }
        return null;
    }
}