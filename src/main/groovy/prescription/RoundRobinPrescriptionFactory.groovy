package prescription

import prescription.spec.EmptyPrescriptionException

class RoundRobinPrescriptionFactory {
    def drugList = []

    def makePrescription() {
        if(drugList.isEmpty()) {
            throw new EmptyPrescriptionException()
        }


        def prescription = new Prescription()
        drugList.each {
            drug -> (1..drug.boxCount).each {
                prescription.addReceiptFor([ new Drug(name: drug.name, boxCount: 1) ])
            }
        }
        return prescription
    }
}
