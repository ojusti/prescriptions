/**
 * Created by justi on 12.11.2015.
 */
class Prescription {
    private def receiptList = []

    def addReceiptFor(drugList) {
        receiptList << new Receipt(drugList: drugList)
        return this
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        return receiptList == o.receiptList
    }

    int hashCode() {
        receiptList.hashCode()
    }

    @Override
    String toString() {
        "$receiptList.size receipts: $receiptList"
    }
}
