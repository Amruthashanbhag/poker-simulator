package com.icm

class Poker {

  def execute(input: List[String]): Integer = {
    val hand1 = createHand(input.slice(0, 5))
    val hand2 = createHand(input.slice(5, 10))
    if(hand1.isEmpty || hand2.isEmpty)
    {
      -1
    }
    else {
      play(hand1.get, hand2.get)
    }
  }

  def play(hand1: Hand, hand2: Hand): Integer = {
    val result1 = hand1.score().get
    val result2 = hand2.score().get
    val rank1 = result1.rank
    val rank2 = result2.rank
    if (rank1 > rank2) {
      1
    } else if (rank1 < rank2) {
      2
    } else {
      val highCards1 = result1.highest
      val highCards2 = result2.highest
      val highPairs = highCards1.zip(highCards2)
      val diffPair = highPairs.find(pair => pair._1 != pair._2)
      val res = diffPair.map(p => if (p._1 > p._2) 1 else 2).getOrElse(-1)
      res
    }
  }

  def createCard(input: String): Option[Card] = {
    val suit = input.charAt(1) match {
      case 'D' => Some(D)
      case 'H' => Some(H)
      case 'S' => Some(S)
      case 'C' => Some(C)
      case _ => None
    }
    val symbol = input.charAt(0) match {
      case '1' => Some(1)
      case '2' => Some(2)
      case '3' => Some(3)
      case '4' => Some(4)
      case '5' => Some(5)
      case '6' => Some(6)
      case '7' => Some(7)
      case '8' => Some(8)
      case '9' => Some(9)
      case 'T' => Some(10)
      case 'J' => Some(11)
      case 'Q' => Some(12)
      case 'K' => Some(13)
      case 'A' => Some(14)
      case _ => None
    }
    if(suit.isEmpty || symbol.isEmpty) {
      None
    }
    else {
      Some(Card(suit.get, symbol.get))
    }
  }

  def createHand(input: List[String]): Option[Hand] = {
    var cards = input flatMap (i => createCard(i))
    if (cards.size == 5) Some(new Hand(cards)) else None
  }
}