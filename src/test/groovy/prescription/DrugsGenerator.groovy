package prescription
/**
 * Created by justi on 12.11.2015.
 */
class DrugsGenerator {

    def char drugName =(char)'a' - 1

    def drug(boxes) {
        drugName ++
        drug(drugName, boxes)
    }

    static def drug(name, boxes) {
        new Drug(name: name, boxCount: boxes)
    }

}
