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
}
