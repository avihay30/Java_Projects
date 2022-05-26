package mines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Class that represents and manages a mine matrix with Slots
public class Mines {

    private final Slot[][] mineMatrix;
    // if true all prints of slots in mineMatrix are faked that they are opened
    private boolean showAll;

    /**
     * Constructor that makes mineMatrix in size height X width
     * and generating numMines mines in random places in mineMatrix
     */
    public Mines(int height, int width, int numMines) {
        mineMatrix = new Slot[height][width];
        initMatrix();

        // Generating numMines mines in random places in mineMatrix
        Random rand = new Random();
        int randRow, randCol;
        for (int i = 0; i < numMines; i++) {
            do { // continue generating randoms in matrix borders if slot is already mined
                randRow = rand.nextInt(height);
                randCol = rand.nextInt(width);
            } while (!addMine(randRow, randCol)); // continue while can't set the mine
        }
    }

    /**
     * Adds a mine in the specified i,j slot
     *
     * @return true if new mine has been set,
     * false if there was already a mine in specified slot
     */
    public boolean addMine(int i, int j) {
        if (mineMatrix[i][j].setMine()) {
            // setMine already does incSurroundingMineCount if new mine
            // iterating over all Neighbors
            for (Slot slot : getAllNeighbors(i, j)) {
                slot.incSurroundingMineCount();
            }
            return true; // if new mine has been set
        }
        return false;
    }

    /**
     * Recursive method that open all neighbors slots
     * of the specified i,j slot if specified slot has no surrounding mines.
     *
     * @return false if the specified slot is mined, true otherwise.
     */
    public boolean open(int i, int j) {
        Slot slot = mineMatrix[i][j];
        // if tries to open a mine return false
        if (slot.isMined()) return false;
        slot.setSlotStatus(SlotStatus.OPENED);

        // if there are no surrounding mines
        if (slot.getSurroundingMinesAmount() == 0) {
            for (Slot neighborSlot : getAllNeighbors(i, j)) {
                // if neighbor isn't open already
                if (!neighborSlot.getSlotStatus().equals(SlotStatus.OPENED))
                    open(neighborSlot.getX(), neighborSlot.getY());
            }
        }
        return true; // returns true if it's not a mine
    }

    /**
     * Set a flag in the specified x,y slot if non exist yet,
     * else removes the flag
     */
    public void toggleFlag(int x, int y) {
        Slot slot = mineMatrix[x][y];

        if (slot.getSlotStatus().equals(SlotStatus.FLAGGED))
            slot.setSlotStatus(SlotStatus.CLOSED);
        else if (slot.getSlotStatus().equals(SlotStatus.CLOSED))
            slot.setSlotStatus(SlotStatus.FLAGGED);
    }

    /**
     * @return true if all closed slots are mines.
     * in other words, return false if there is a closed slot without a mine
     */
    public boolean isDone() {
        for (Slot[] rowSlots : mineMatrix) {
            for (Slot slot : rowSlots) {
                // if slot is not a mine, and it's closed -> not done
                if (!slot.isMined() && slot.getSlotStatus().equals(SlotStatus.CLOSED))
                    return false;
            }
        }
        return true;
    }

    /**
     * @return String representation of the specified i,j slot
     */
    public String get(int i, int j) {
        Slot slot = mineMatrix[i][j];
        SlotStatus prevStatus = slot.getSlotStatus(); // save the last status of the slot

        // if showAll flag is true changing status to OPENED
        if (showAll) slot.setSlotStatus(SlotStatus.OPENED);
        String slotStr = slot.toString();
        slot.setSlotStatus(prevStatus); // back to original status
        return slotStr;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    /**
     * @return String representation of the mineMatrix
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mineMatrix.length; i++) {
            for (int j = 0; j < mineMatrix[i].length; j++) {
                sb.append(get(i, j));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void initMatrix() {
        for (int i = 0; i < mineMatrix.length; i++)
            for (int j = 0; j < mineMatrix[i].length; j++)
                mineMatrix[i][j] = new Slot(i, j);
    }

    /**
     * @return list of all valid neighbors in
     * mineMatrix boundaries (without (x,y) slot)
     */
    private List<Slot> getAllNeighbors(int x, int y) {
        List<Slot> slots = new ArrayList<>();
        // only valid neighbors from [x-1 to x+1, y-1 to y+1]
        for (int i = Math.max(0, x - 1);
             i <= Math.min(mineMatrix.length - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1);
                 j <= Math.min(mineMatrix[i].length - 1, y + 1); j++) {
                // add only neighbors (without myself)
                if (i != x || j != y) slots.add(mineMatrix[i][j]);
            }
        }
        return slots;
    }
}
