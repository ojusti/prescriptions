/**
 * Created by justi on 11.11.2015.
 */
class RoundRobinPrescriptionFactory {
    def drugList = []

    def makePrescription() {
        if(drugList.isEmpty()) {
            throw new EmptyPrescriptionException()
        }


        def prescription = new Prescription()
        drugList.each { drug -> (1..drug.boxes).each { prescription.addReceiptFor([ new Drug(name: drug.name, boxes: 1) ]) } }
        return prescription
    }
}
