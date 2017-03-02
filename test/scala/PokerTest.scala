import org.scalatest._
import Matchers._
import com.icm._
import org.junit.Test


class PokerTest {

  val poker = new Poker()
  
  @Test
  def higherRankWins() {
    val cards1 = List(Card(D, 2), Card(D, 3), Card(D, 4), Card(D, 5), Card(D, 6))
    val hand1 = new Hand(cards1)
    val cards2 = List(Card(D, 6), Card(D, 3), Card(D, 6), Card(D, 6), Card(D, 6))
    val hand2 = new Hand(cards2)

    val result = poker.play(hand1, hand2) 
    result should be (1)
  }
  
  @Test
  def lowerRankLost() {
    val cards2 = List(Card(D, 2), Card(D, 3), Card(D, 4), Card(D, 5), Card(D, 6))
    val hand2 = new Hand(cards2)
    val cards1 = List(Card(D, 6), Card(D, 3), Card(D, 6), Card(D, 6), Card(D, 6))
    val hand1 = new Hand(cards1)

    val result = poker.play(hand1, hand2) 
    result should be (2)
  }
  
}