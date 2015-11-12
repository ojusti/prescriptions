package prescription

import prescription.spec.EmptyPrescriptionException

class RoundRobinPrescriptionFactory {
    def drugList = []

    def makePrescription() {
        if(drugList.isEmpty()) {
            throw new EmptyPrescriptionException()
        }

        def receipts = makeListOfDrugsForReceipts()

        def prescription = new Prescription()
        receipts.each { prescription.addReceiptFor(it) }
        return prescription
    }

    private def makeListOfDrugsForReceipts() {
        def receipts = makeEmptyReceipts(computeReceiptCount())
        def oneBoxDrugList = drugList.collect { separateBoxes(it) }.flatten()

        for (int receiptIndex = 0; !oneBoxDrugList.isEmpty(); receiptIndex = (receiptIndex + 1) % receipts.size()) {

            receipts[receiptIndex] << oneBoxDrugList.remove(0)
        }
        return receipts.values()
    }

    private def makeEmptyReceipts(def count) {
        Map receipts = [:]
        (0..count-1).each { receipts.put(it, []) }
        return receipts
    }

    private def separateBoxes(def drug) {
        def boxes = []
        (1..drug.boxCount).each { boxes << new Drug(name: drug.name, boxCount: 1) }
        return boxes
    }

    private def computeReceiptCount() {
        (int) [drugList.boxCount.max(), Math.ceil(drugList.boxCount.sum() / 10)].max()
    }

}
