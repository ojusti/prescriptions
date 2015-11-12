/**
 * Created by justi on 11.11.2015.
 */
class Drug {
    def name
    def boxes

    @Override
    String toString() {
        "$name($boxes boxes)"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        return boxes == o.boxes && name == o.name
    }

    int hashCode() {
        int result
        result = name.hashCode()
        result = 31 * result + boxes.hashCode()
        return result
    }
}
