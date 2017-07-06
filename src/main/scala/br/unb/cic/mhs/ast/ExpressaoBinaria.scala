package br.unb.cic.mhs.ast
import br.unb.cic.mhs.visitors.MHSVisitor

abstract class ExpressaoBinaria(val lhs : Expressao, val rhs : Expressao) extends Expressao{}