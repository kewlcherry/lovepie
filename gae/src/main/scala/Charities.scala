package lovep.ie


sealed class Charity(id: Int)

trait CharityRepositoryComponent {

  val charityRepository: CharityRepository

  trait CharityRepository {
    def create(c: Charity)

    def delete(c: Charity)

    def get()
  }

}
