package prescription

import prescription.spec.EmptyPrescriptionException

class RoundRobinPrescriptionFactory {
    def drugList = []

    def makePrescription() {
        if(drugList.isEmpty()) {
            throw new EmptyPrescriptionException()
        }

        def prescription = new Prescription()
        separateListsOfDrugsForReceipts().each { prescription.addReceiptFor(it) }
        return prescription
    }

    private def separateListsOfDrugsForReceipts() {
        def receipts = makeEmptyReceipts(computeReceiptCount())
        def sequenceOfOneBoxDrug = drugList.collect { separateBoxes(it) }.flatten()

        for (int receiptIndex = 0; !sequenceOfOneBoxDrug.isEmpty(); receiptIndex = (receiptIndex + 1) % receipts.size()) {

            receipts[receiptIndex] << sequenceOfOneBoxDrug.remove(0)
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
