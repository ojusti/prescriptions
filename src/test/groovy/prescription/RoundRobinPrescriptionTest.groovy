package prescription

import prescription.spec.DrugSpecification
import prescription.spec.EmptyPrescriptionException
import prescription.spec.PrescriptionSpecification
import prescription.spec.ReceiptSpecification
import spock.lang.Specification

/**
 * Created by justi on 11.11.2015.
 */
class RoundRobinPrescriptionTest extends Specification {

    def generator

    def setup() {
        generator = new DrugsGenerator()
    }

    void "should distribute 0 drugs to 0 receipts"() {
        given:
        def distributor = new RoundRobinPrescriptionFactory()
        when:
        distributor.makePrescription()
        then:
        thrown(EmptyPrescriptionException)
    }

    void "should distribute 1 box of 1 drug to 1 receipt"() {
        given:
        def drugList = [drug(1)]
        def factory = new RoundRobinPrescriptionFactory(drugList: drugList)
        expect:
        new Prescription().addReceiptFor(drugList) == factory.makePrescription()
    }

    void "should distribute 2 boxes of 1 drug to 2 receipts"() {
        given:
        def factory = new RoundRobinPrescriptionFactory(drugList: [drug('a', 2)])
        expect:
        new Prescription().addReceiptFor([drug('a', 1)])
                .addReceiptFor([drug('a', 1)]) == factory.makePrescription()
    }

    void "should distribute 2 boxes of 2 drugs to 2 receipts"() {
        given:
        def factory = new RoundRobinPrescriptionFactory(drugList: [drug('a', 2), drug('b', 2)])
        expect:
        new Prescription().addReceiptFor([drug('a', 1), drug('b', 1)])
                .addReceiptFor([drug('a', 1), drug('b', 1)]) == factory.makePrescription()
    }

    void "should distribute 10 boxes of 10 drugs and 2 boxes of another to 11 receipts"() {
        given:
        def longDrugList = [drug('a', 10), drug('b', 10), drug('c', 10), drug('d', 10), drug('e', 10), drug('f', 10), drug('g', 10), drug('h', 10), drug('i', 10), drug('j', 10), drug('k', 2)]
        def factory = new RoundRobinPrescriptionFactory(drugList: longDrugList)
        when:
        def spec = new PrescriptionSpecification(receiptSpec: new ReceiptSpecification(maxSize: 10, drugSpec: new DrugSpecification(maxBoxCount: 1)))
        then:
        spec.test(factory.makePrescription())
    }

    def drug(boxesCount) {
        generator.drug(boxesCount)
    }

    def drug(drugName, boxesCount) {
        generator.drug(drugName, boxesCount)
    }

    def receiptFor(drugList) {
        new Receipt(drugList: drugList)
    }

}
