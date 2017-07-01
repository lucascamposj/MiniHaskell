package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.Expressao
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.Aplicacao
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.Referencia

class TransformaITE extends TransformacaoG {

  override def visitar(e : ExpressaoITE)  = {
    val condicao = e.condicao.aceitar(this)
    val tupla = (e.clausulaThen, e.clausulaElse)
    tupla match {
      case (ValorBooleano(true), ValorBooleano(false)) => condicao
      case _ => new ExpressaoITE(condicao, e.clausulaThen.aceitar(this), e.clausulaElse.aceitar(this))
    }
  }

}