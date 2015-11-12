package prescription

class Receipt {
    def drugList = []

    @Override
    String toString() {
        "$drugList.size drugs: $drugList"
    }

    def size() {
        drugList.size()
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
