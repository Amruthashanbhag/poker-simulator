package com.icm



sealed trait Result
case object Won extends Result
case object Lost extends Result
case object Tie extends Result
case object Invalid extends Result