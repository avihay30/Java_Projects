package mines;

// Class that represents a Slot in Mine matrix
public class Slot {
    // represents if this slot is mined
    private boolean isMined;
    private SlotStatus slotStatus = SlotStatus.CLOSED;
    // represents the number of mines in this slot surroundings (including me)
    private int surroundingMinesAmount;
    private final int x, y;

    public Slot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isMined() {
        return isMined;
    }

    /**
     * @return true if new mine has been set,
     * false if there was already a mine in this slot
     */
    public boolean setMine() {
        if (isMined) return false;
        isMined = true;
        incSurroundingMineCount();
        return true;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public int getSurroundingMinesAmount() {
        return surroundingMinesAmount;
    }

    public void incSurroundingMineCount() {
        this.surroundingMinesAmount++;
    }

    /**
     * @return String representation of this slot
     */
    @Override
    public String toString() {
        switch (slotStatus) {
            case FLAGGED:
                return "F";
            case CLOSED:
                return ".";
            case OPENED:
                if (isMined) return "X";
                else {
                    if (surroundingMinesAmount == 0) return " ";
                    return "" + surroundingMinesAmount;
                }
            default: // not reachable
                return "";
        }
    }
}
