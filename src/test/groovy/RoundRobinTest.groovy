import spock.lang.IgnoreRest
import spock.lang.Specification

/**
 * Created by justi on 11.11.2015.
 */
class RoundRobinTest extends Specification {

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
        def drugList = [ drug(1) ]
        def distributor = new RoundRobinPrescriptionFactory(drugList: drugList)
        expect:
        new Prescription().addReceiptFor(drugList) == distributor.makePrescription()
    }

    void "should distribute 2 boxes of 1 drug to 2 receipts"() {
        given:
        def distributor = new RoundRobinPrescriptionFactory(drugList: [drug('a', 2) ])
        expect:
        new Prescription().addReceiptFor([drug('a', 1)]).addReceiptFor([drug('a', 1)]) == distributor.makePrescription()
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
