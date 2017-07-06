package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast._

class Metricas extends MHSVisitor[Int] {
  def visitar(e : ValorInteiro)       = 1
  def visitar(e : ValorBooleano)      = 1
  def visitar(e : ExpressaoSoma)      = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoBinaria)   = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoITE)       = 1 + e.condicao.aceitar(this) + e.clausulaThen.aceitar(this)  + e.clausulaElse.aceitar(this)
  def visitar(e : Aplicacao)          = 1 + e.args.map(e => e.aceitar(this)).sum
  def visitar(e : ExpressaoLet)       = 1 + e.expNomeada.aceitar(this) + e.corpo.aceitar(this)
  def visitar(e : Referencia)         = 1
  def visitar(e : ReferenciaLambda)   = 1
  def visitar(e : DecLambda)          = 1
  def visitar(e : AplicacaoLambda)    = 1 + e.expLambda.aceitar(this)
  def visitar(e : ExpressaoFatorial)  = 1 + e.v.aceitar(this)
  def visitar(e : ExpressaoNegativa)  = 1 + e.v.aceitar(this)
  def visitar(e : ExpressaoDiferenca) = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoIgualdade) = 1 + e.rhs.aceitar(this) + e.lhs.aceitar(this)
  def visitar(e : ExpressaoMenorQue)  = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoMaiorQue)  = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
}