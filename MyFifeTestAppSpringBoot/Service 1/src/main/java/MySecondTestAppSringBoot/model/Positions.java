package MySecondTestAppSringBoot.model;

public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    PO(1.0, true),
    TPM(1.0, true),
    CTO(1.0, true);

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }

    public double getPositionCoefficient() {
        return positionCoefficient;
    }

    public boolean isManager() {
        return isManager;
    }
}
