package prescription.spec
/**
 * Created by justi on 12.11.2015.
 */
class ReceiptSpecification {
    def drugSpec
    def maxSize
    def test(receipt) {
        receipt.size() <= maxSize && receipt.drugList.every { drugSpec.test(it) }
    }
}

