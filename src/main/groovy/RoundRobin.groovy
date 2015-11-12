/**
 * Created by justi on 11.11.2015.
 */
class RoundRobin {
    def drugList = []

    def distribute() {
        drugList.isEmpty() ? [] : [ new Receipt(drugList: drugList) ]
    }
}
