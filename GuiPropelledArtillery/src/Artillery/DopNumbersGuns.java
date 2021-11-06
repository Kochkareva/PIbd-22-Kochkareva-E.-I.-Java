package Artillery;

public enum DopNumbersGuns {
    one,
    two,
    three;

    public static DopNumbersGuns SetNumberGuns(int newNumber) {
        return switch (newNumber) {
            case 1 -> one;
            case 2 -> two;
            case 3 -> three;
            default -> one;
        };
    }
}
