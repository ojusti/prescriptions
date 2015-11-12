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
        def sequenceofOneBoxDrugs = drugList.collect { separateBoxes(it) }.flatten()
        new RoundRobinDistribution(computeReceiptCount(drugList)).putAll(sequenceofOneBoxDrugs).getBuckets()
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


    private static class RoundRobinDistribution {
        private def buckets
        private def index;
        RoundRobinDistribution(bucketCount) {
            buckets = (0..bucketCount-1).collectEntries {[(it): []]}
            index = 0;
        }
        def putAll(sequence) {
            sequence.each { put(it) }
            return this
        }

        def put(item) {
            buckets[index] << item
            index = nextIndex()
            return this
        }

        private nextIndex() {
            (index + 1) % buckets.size()
        }

        def getBuckets() {
            buckets.values()
        }
    }


}
