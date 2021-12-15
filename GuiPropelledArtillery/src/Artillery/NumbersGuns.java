package Artillery;

public enum NumbersGuns {
    one,
    two,
    three;

    public static NumbersGuns SetNumberGuns(int newNumber) {
        return switch (newNumber) {
            case 1 -> one;
            case 2 -> two;
            case 3 -> three;
            default -> one;
        };
    }
}
