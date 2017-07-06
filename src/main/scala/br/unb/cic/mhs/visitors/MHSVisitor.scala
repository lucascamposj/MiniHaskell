package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast._

/**
 * Define a hierarquia de classes visitors. 
 * Precisa ter um metodo aceitar para cada 
 * classe concreta. 
 */
trait MHSVisitor[+T] {
  def visitar(e : ValorInteiro)     : T
  def visitar(e : ValorBooleano)    : T
  def visitar(e : DecLambda)  : T
  def visitar(e : ExpressaoBinaria) : T
  def visitar(e : ExpressaoSoma)    : T
  def visitar(e : ExpressaoITE)     : T
  def visitar(e : Aplicacao)        : T
  def visitar(e : AplicacaoLambda)  : T
  def visitar(e : ExpressaoLet)     : T
  def visitar(e : Referencia)       : T
  def visitar(e : ReferenciaLambda) : T
  def visitar(e : ExpressaoFatorial): T
  def visitar(e : ExpressaoNegativa): T
  def visitar(e : ExpressaoDiferenca): T
  def visitar(e : ExpressaoIgualdade) : T
  def visitar(e : ExpressaoMaiorQue) : T
  def visitar(e : ExpressaoMenorQue): T
}