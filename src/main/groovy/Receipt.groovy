/**
 * Created by justi on 11.11.2015.
 */
class Receipt {
    def drugs

    @Override
    String toString() {
        "$drugs.size $drugs"
    }
}
