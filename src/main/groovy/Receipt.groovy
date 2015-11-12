/**
 * Created by justi on 11.11.2015.
 */
class Receipt {
    def drugList = []

    @Override
    String toString() {
        "$drugList.size $drugList"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Receipt receipt = (Receipt) o

        if (drugList != receipt.drugList) return false

        return true
    }

    int hashCode() {
        return (drugList != null ? drugList.hashCode() : 0)
    }
}
