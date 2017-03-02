package com.icm



class Hand(cards: List[Card]) {

  val sortedCards = cards.sorted

  def score(): Option[HandResult] = {
    royalFlush()
      .orElse(straightFlush())
      .orElse(fourOfAKind())
      .orElse(fullHouse())
      .orElse(flush())
      .orElse(straight())
      .orElse(threeOfAKind())
      .orElse(twoPairs())
      .orElse(pair())
      .orElse(highCard())
  }

  // Same rank, same symbols. This tie can be broken only by suit rank
  private def royalFlush(): Option[HandResult] = {
    val res = sortedCards(0).symbol == 10 &&
      sortedCards(1).symbol == 11 &&
      sortedCards(2).symbol == 12 &&
      sortedCards(3).symbol == 13 &&
      sortedCards(4).symbol == 14
    if (res) {
      flush() map (r => HandResult(10, r.highest))
    }
    else None
  }

  // Same rank, same symbols. This tie can be broken only by suit rank
  private def straightFlush(): Option[HandResult] = {
    val res = straight().flatMap(x => flush())
    res map (r => HandResult(9, r.highest))
  }

  private def fourOfAKind(): Option[HandResult] = {
    val symbolMap = sortedCards.groupBy(c => c.symbol)
    val res = symbolMap.values.exists(_.size == 4)
    if (res) {
      val otherCard = symbolMap.values.find(_.size == 1).get
      val rankCards = sortedCards.diff(otherCard)
      val highest = rankCards.map(_.symbol) ::: otherCard.map(_.symbol)
      Some(HandResult(8, highest))
    }
    else None
  }
  //symbolMap.keys.toList.reverse
  private def fullHouse(): Option[HandResult] = {
    val symbolMap = sortedCards.groupBy(c => c.symbol)
    if (symbolMap.size != 2) {
      None
    } else {
      val symbolSizes = symbolMap.values.map(_.size).toList
      val res = symbolSizes.size == 2 && symbolSizes.contains(3) && symbolSizes.contains(2)
      if (res) {
        val rankCards1 = symbolMap.values.find(_.size == 3).get
        val rankCards2 = symbolMap.values.find(_.size == 2).get
        val highest = rankCards1.map(_.symbol) ::: rankCards2.map(_.symbol)
        Some(HandResult(7, highest))
      }
      else None
    }
  }

  // Same rank, same symbols. This tie can be broken only by suit rank
  private def flush(): Option[HandResult] = {
    val res = sortedCards.groupBy(c => c.suit).size == 1
    if (res) Some(HandResult(6, sortedCards.map(_.symbol).sortBy(-1 * _))) else None
  }

  // Same rank, same symbols. This tie can be broken only by suit rank
  private def straight(): Option[HandResult] = {
    def isStraight(list: List[Card]): Boolean = list match {
      case Nil => true
      case x :: Nil => true
      case x :: rest => (rest.head.symbol - x.symbol) == 1 && isStraight(rest)
    }
    val res = isStraight(sortedCards)
    if (res) Some(HandResult(5, sortedCards.map(_.symbol).sortBy(-1 * _))) else None
  }

  private def threeOfAKind() = {
    val symbolMap = sortedCards.groupBy(c => c.symbol)
    val res = symbolMap.values.exists(_.size == 3)
    if (res) {
      val rankCards = symbolMap.values.find(_.size == 3).get
      val otherCards = sortedCards.diff(rankCards)
      val highest = rankCards.map(_.symbol) ::: otherCards.map(_.symbol).sortBy(-1 * _)
      Some(HandResult(4, highest))
    }
    else None
  }

  // Same rank, same symbols. This tie can be broken only by suit rank
  private def twoPairs() = {
    val symbolMap = sortedCards.groupBy(c => c.symbol)
    val res = symbolMap.values.count(_.size == 2) == 2
    if (res) {
      val otherCard = symbolMap.values.find(_.size == 1).get
      val rankCards = sortedCards.diff(otherCard)
      val highest = rankCards.map(_.symbol).sortBy(-1 * _) ::: otherCard.map(_.symbol)
      Some(HandResult(3, highest))
    }
    else None
  }

  // Same rank, same symbols. This tie can be broken only by suit rank
  private def pair() = {
    val symbolMap = sortedCards.groupBy(c => c.symbol)
    val res = symbolMap.values.count(_.size == 2) == 1
    if (res) {
      val rankCards = symbolMap.values.find(_.size == 2).get
      val otherCards = sortedCards.diff(rankCards)
      val highest = rankCards.map(_.symbol) ::: otherCards.map(_.symbol).sortBy(-1 * _)
      Some(HandResult(2, highest))
    }
    else None
  }

  // Same rank, same symbols. This tie can be broken only by suit rank
  private def highCard() = {
    Some(HandResult(1, sortedCards.map(_.symbol).sortBy(-1 * _)))
  }
}