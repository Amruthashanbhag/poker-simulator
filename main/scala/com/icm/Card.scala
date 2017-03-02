package com.icm



case class Card(suit: Suit, symbol: Int) extends Ordered[Card] {
  
  def compare(that: Card): Int = {
    symbol.compareTo(that.symbol)
  }
  
}