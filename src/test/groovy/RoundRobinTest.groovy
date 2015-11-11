import spock.lang.Specification

/**
 * Created by justi on 11.11.2015.
 */
class RoundRobinTest extends Specification {
    def setup() {
        resetDrugName()
    }
    void "should distribute 0 drug to 0 receipts"() {
        given:
        def distributor = new RoundRobin()
        expect:
        [] == distributor.distribute()
    }

    void "should distribute 1 box of 1 drug to 1 receipts"() {
        given:
        def distributor = new RoundRobin(drugs: drug(1))
        resetDrugName()
        expect:
        [receiptFor(drug(1))] == distributor.distribute()
    }

    def drugName
    def resetDrugName() {
        drugName = 0
    }
    def drug(boxes) {
        drugName ++
        new Drug(name: drugName, boxes: boxes)
    }

    def receiptFor(Drug drug) {
        new Receipt(drugs: [drug])
    }
}
