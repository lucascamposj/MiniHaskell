package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor

class Tipo 

case object TInteiro extends Tipo 
case object TBooleano extends Tipo 
case object TErro extends Tipo

trait Expressao {
  def avaliar() : Valor
  def verificarTipo() : Tipo
  def aceitar[T](visitor : MHSVisitor[T]) : T
}