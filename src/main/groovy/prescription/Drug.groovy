package prescription

class Drug {
    def name
    def boxCount

    @Override
    String toString() {
        "$name($boxCount boxes)"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        return boxCount == o.boxCount && name == o.name
    }

    int hashCode() {
        int result
        result = name.hashCode()
        result = 31 * result + boxCount.hashCode()
        return result
    }
}
