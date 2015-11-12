package prescription.spec
/**
 * Created by justi on 12.11.2015.
 */
class DrugSpecification {
    def maxBoxCount
    def test(drug) {
        drug.boxCount <= maxBoxCount
    }
}
