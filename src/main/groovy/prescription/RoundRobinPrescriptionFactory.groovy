package prescription

import prescription.spec.EmptyPrescriptionException

import static java.lang.Math.ceil

class RoundRobinPrescriptionFactory {
    def specification

    def makePrescription(drugList) {
        if(drugList == null || drugList.isEmpty()) {
            throw new EmptyPrescriptionException()
        }

        separateListsOfDrugsForEachReceipt(drugList).inject(new Prescription()) { prescription, listOfDrugsForReceipt -> prescription.addReceiptFor(listOfDrugsForReceipt) }
    }

    private def separateListsOfDrugsForEachReceipt(drugList) {
        def receipts = makeEmptyReceipts(computeReceiptCount(drugList))
        def sequenceOfOneBoxDrugs = drugList.collect { separateBoxes(it) }.flatten()

        sequenceOfOneBoxDrugs.inject(0) { index, drugBox ->  receipts[index] << drugBox; (index + 1) % receipts.size() }
        return receipts.values()
    }

    private def makeEmptyReceipts(def count) {
        (0..count-1).collectEntries {[(it): []]}
    }

    private def separateBoxes(def drug) {
        (1..drug.boxCount).collect { new Drug(name: drug.name, boxCount: 1) }
    }

    private def computeReceiptCount(drugList) {
        def receiptSpec = specification.receiptSpec
        def drugSpec = receiptSpec.drugSpec
        def minNumberOfReceipts = ceil(ceil(drugList.boxCount.sum() / drugSpec.maxBoxCount) / receiptSpec.maxSize)
        def minNumberOfReceiptsForOneDrug = ceil(drugList.boxCount.max() / drugSpec.maxBoxCount)
        [minNumberOfReceiptsForOneDrug, minNumberOfReceipts].max()
    }

}
