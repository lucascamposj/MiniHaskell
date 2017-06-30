package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.Aplicacao
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.Referencia

/**
 * Define a hierarquia de classes visitors. 
 * Precisa ter um metodo aceitar para cada 
 * classe concreta. 
 */
trait MHSVisitor[+T] {
  def visitar(e : ValorInteiro)  : T
  def visitar(e : ValorBooleano) : T
  def visitar(e : ExpressaoSoma) : T
  def visitar(e : ExpressaoITE)  : T
  def visitar(e : Aplicacao)     : T
  def visitar(e : ExpressaoLet)  : T
  def visitar(e : Referencia)    : T
}