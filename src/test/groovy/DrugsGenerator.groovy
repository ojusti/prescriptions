/**
 * Created by justi on 12.11.2015.
 */
class DrugsGenerator {

    def char drugName =(char)'a' - 1

    def drug(boxes) {
        drugName ++
        new Drug(name: drugName, boxes: boxes)
    }

}
