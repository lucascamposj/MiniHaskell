package br.unb.cic.mhs.memoria

import scala.collection.mutable.HashMap
import br.unb.cic.mhs.ast.{Expressao, DecFuncao}
import scala.collection.mutable.HashSet

/**
 * Um ambiente para associar identificadores a expressoes. 
 * Essencial na avaliacao de expressoes como Let e avaliacao de 
 * funcoes (ainda nao implementadas). 
 */
class Ambiente[T] {
  private val contexto : HashMap[String, T] = new HashMap()
  
  /**
   * Associa um id a uma expressao
   */
  def associar(id : String, exp : T) {
    contexto += id -> exp
  }
  
  /**
   * Retorna a expressao associada a um id 
   */
  def pesquisar(id : String) : T = contexto(id)

}

object AmbienteExpressao extends Ambiente[Expressao] 

object AmbienteDecFuncao extends Ambiente[DecFuncao]