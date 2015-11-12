package prescription.spec
/**
 * Created by justi on 12.11.2015.
 */
class PrescriptionSpecification {
    def receiptSpec
    def test(prescription) {
        prescription.receiptList.every{ receiptSpec.test(it) }
    }
}
