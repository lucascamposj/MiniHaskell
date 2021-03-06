package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast._

/**
 * Classe abstrata com a implementacao de um MHSVisitor 
 * para transformacoes que preservam a AST. Classes concretas 
 * desta classe abstrata prcisam redefinir apenas os metodos 
 * de interesse. 
 */
trait TransformacaoG extends MHSVisitor[Expressao] {
  override def visitar(vb : ValorBooleano)     : Expressao =  vb
  override def visitar(vi : ValorInteiro)      : Expressao =  vi
  override def visitar(e  : DecLambda)   : Expressao =  e
  override def visitar(e  : ExpressaoSoma)     : Expressao = new ExpressaoSoma(e.lhs.aceitar(this),e.rhs.aceitar(this))
  override def visitar(e  : ExpressaoITE)      : Expressao = new ExpressaoITE(e.condicao.aceitar(this), e.clausulaThen.aceitar(this), e.clausulaElse.aceitar(this))
  override def visitar(e  : Aplicacao)         : Expressao = new Aplicacao(e.nome, e.args.map(exp => exp.aceitar(this)) : _*)
  override def visitar(e  : ExpressaoLet)      : Expressao = new ExpressaoLet(e.id, e.expNomeada.aceitar(this), e.corpo.aceitar(this))
  override def visitar(e  : Referencia)        : Expressao = e
  override def visitar(e  : AplicacaoLambda)   : Expressao = new AplicacaoLambda(e.expLambda.aceitar(this), e.valor.aceitar(this))
  override def visitar(e  : ExpressaoBinaria)  : Expressao = e
  override def visitar(e  : ReferenciaLambda)  : Expressao = e
  override def visitar(e  : ExpressaoFatorial) : Expressao = e
  override def visitar(e  : ExpressaoNegativa) : Expressao = new ExpressaoNegativa(e.v.aceitar(this))

  override def visitar(e: ExpressaoMaiorQue): Expressao = new ExpressaoMaiorQue(e.lhs.aceitar(this), e.rhs.aceitar(this))

  override def visitar(e: ExpressaoDiferenca): Expressao = new ExpressaoDiferenca(e.lhs.aceitar(this), e.rhs.aceitar(this))

  override def visitar(e: ExpressaoIgualdade): Expressao = new ExpressaoIgualdade(e.lhs.aceitar(this), e.rhs.aceitar(this))

  override def visitar(e: ExpressaoMenorQue): Expressao = new ExpressaoMenorQue(e.lhs.aceitar(this), e.rhs.aceitar(this))

}