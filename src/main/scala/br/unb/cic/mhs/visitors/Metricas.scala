package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.Aplicacao
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.Referencia

class Metricas extends MHSVisitor[Int] {
  def visitar(e : ValorInteiro)  = 1
  def visitar(e : ValorBooleano) = 1
  def visitar(e : ExpressaoSoma) = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoITE)  = 1 + e.condicao.aceitar(this) + e.clausulaThen.aceitar(this)  + e.clausulaElse.aceitar(this)
  def visitar(e : Aplicacao)     = 1 + e.args.map(e => e.aceitar(this)).sum
  def visitar(e : ExpressaoLet)  = 1 + e.expNomeada.aceitar(this) + e.corpo.aceitar(this)
  def visitar(e : Referencia)    = 1 
}