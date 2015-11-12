import spock.lang.Specification

/**
 * Created by justi on 11.11.2015.
 */
class RoundRobinTest extends Specification {

    def generator
    def setup() {
        generator = new DrugsGenerator()
    }
    void "should distribute 0 drug to 0 receipts"() {
        given:
        def distributor = new RoundRobin()
        expect:
        [] == distributor.distribute()
    }

    void "should distribute 1 box of 1 drug to 1 receipts"() {
        given:
        def drugList = [ drug(1) ]
        def distributor = new RoundRobin(drugList: drugList)
        expect:
        [new Receipt(drugList: drugList)] == distributor.distribute()
    }

    def drug(boxesCount) {
        generator.drug(boxesCount)
    }

}
