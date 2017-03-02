import com.icm._
import org.scalatest._
import org.junit.Test
import Matchers._


class HandTest {
  
  @Test
  def royalSuite {
    val cards = List(Card(D, 10), Card(D, 11), Card(D, 12), Card(D, 13), Card(D, 14))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (10)
  }
  
  @Test
  def straightFlush {
    val cards = List(Card(D, 2), Card(D, 3), Card(D, 4), Card(D, 5), Card(D, 6))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (9)
  }
  
  @Test
  def fourOfAKind {
    val cards = List(Card(D, 6), Card(D, 3), Card(D, 6), Card(D, 6), Card(D, 6))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (8)
  }
  
  @Test
  def fullHouse {
    val cards = List(Card(D, 6), Card(D, 3), Card(D, 6), Card(D, 3), Card(D, 3))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (7)
  }
  
  @Test
  def flush {
    val cards = List(Card(D, 6), Card(D, 3), Card(D, 2), Card(D, 10), Card(D, 9))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (6)
  }

  @Test
  def straight {
    val cards = List(Card(D, 3), Card(D, 4), Card(C, 5), Card(S, 7), Card(H, 6))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (5)
  }
  
  @Test
  def threeOfAKind {
    val cards = List(Card(D, 3), Card(D, 4), Card(C, 3), Card(S, 7), Card(H, 3))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (4)
  }

  @Test
  def twoPairs {
    val cards = List(Card(D, 3), Card(D, 4), Card(C, 3), Card(S, 7), Card(H, 4))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (3)
  }

  @Test
  def pair {
    val cards = List(Card(D, 2), Card(D, 4), Card(C, 3), Card(S, 7), Card(H, 4))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (2)
  }
  
  @Test
  def highCard {
    val cards = List(Card(D, 2), Card(D, 4), Card(C, 9), Card(S, 7), Card(H, 13))
    val hand = new Hand(cards)
    val rank = hand.score().get.rank
    rank should be (1)
  }

}