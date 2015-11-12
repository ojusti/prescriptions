/**
 * Created by justi on 11.11.2015.
 */
class Receipt {
    def drugList = []

    @Override
    String toString() {
        "$drugList.size drugs: $drugList"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        drugList == o.drugList
    }

    int hashCode() {
        return drugList.hashCode()
    }
}
